package com.epam.task5.transform;

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
    private static Map<String, Templates> xsltTemplates = new HashMap<>();

    private XsltTransformerFactory() {
    }

    /**
     * Gets transformer from factory
     * 
     * @param xsltFilePath
     *            path to xsl file
     * @return cached transformer
     */
    public static Transformer getTransformer(String xsltFilePath) {
	Transformer transformer = null;
	try {
	    Templates template = xsltTemplates.get(xsltFilePath);
	    if (template == null) {
		String realPath = CommandFactory.getRealPath();
		TransformerFactory factory = TransformerFactory.newInstance();
		template = factory.newTemplates(new StreamSource(new File(
			realPath + xsltFilePath)));
		xsltTemplates.put(xsltFilePath, template);
	    }
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
