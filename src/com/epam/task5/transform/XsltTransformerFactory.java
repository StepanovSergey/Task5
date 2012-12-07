package com.epam.task5.transform;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.task5.command.CommandFactory;
import com.epam.task5.resource.Constants;

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
    private static Map<String, File> xsltFiles;

    private XsltTransformerFactory() {
	String realPath = CommandFactory.getRealPath();
	xsltFiles = new HashMap<>();
	xsltFiles.put(Constants.CATEGORIES_XSLT, new File(realPath
		+ Constants.CATEGORIES_XSLT_PATH));
	xsltFiles.put(Constants.SUBCATEGORIES_XSLT, new File(realPath
		+ Constants.SUBCATEGORIES_XSLT_PATH));
	xsltFiles.put(Constants.PRODUCTS_XSLT, new File(realPath
		+ Constants.PRODUCTS_XSLT_PATH));
    }

    public static Transformer getTransformer(String xsltFilePath) {
	TransformerFactory transFact = TransformerFactory.newInstance();
	Transformer transformer = null;
	try {
	    File file = xsltFiles.get(xsltFilePath);
	    Source source = new StreamSource(file);
	    transformer = transFact.newTransformer(source);
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
