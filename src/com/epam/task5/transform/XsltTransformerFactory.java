package com.epam.task5.transform;

import static com.epam.task5.resource.Constants.ADD_PRODUCT_XSLT;
import static com.epam.task5.resource.Constants.CATEGORIES_XSLT;
import static com.epam.task5.resource.Constants.PRODUCTS_XSLT;
import static com.epam.task5.resource.Constants.SUBCATEGORIES_XSLT;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.task5.command.CommandFactory;

/**
 * This class provides XSLT transformer factory
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class XsltTransformerFactory {
    private static final Logger logger = Logger
	    .getLogger(XsltTransformerFactory.class);
    private static final XsltTransformerFactory instance = new XsltTransformerFactory();
    private static Map<String, Templates> xsltTemplates;

    private XsltTransformerFactory() {
	String realPath = CommandFactory.getRealPath();
	TransformerFactory factory = TransformerFactory.newInstance();
	xsltTemplates = new HashMap<>();
	try {
	    xsltTemplates.put(
		    CATEGORIES_XSLT,
		    factory.newTemplates(new StreamSource(new File(realPath
			    + CATEGORIES_XSLT))));
	    xsltTemplates.put(
		    SUBCATEGORIES_XSLT,
		    factory.newTemplates(new StreamSource(new File(realPath
			    + SUBCATEGORIES_XSLT))));
	    xsltTemplates.put(
		    PRODUCTS_XSLT,
		    factory.newTemplates(new StreamSource(new File(realPath
			    + PRODUCTS_XSLT))));
	    xsltTemplates.put(
		    ADD_PRODUCT_XSLT,
		    factory.newTemplates(new StreamSource(new File(realPath
			    + ADD_PRODUCT_XSLT))));
	} catch (TransformerConfigurationException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
    }

    public static Transformer getTransformer(String xsltFilePath) {
	Transformer transformer = null;
	try {
	    Templates template = xsltTemplates.get(xsltFilePath);
	    transformer = template.newTransformer();
	} catch (TransformerConfigurationException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
	return transformer;
    }

    /**
     * @return the instance
     */
    public static XsltTransformerFactory getInstance() {
	return instance;
    }
}
