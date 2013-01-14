package com.epam.task5.transform;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import com.epam.task5.command.CommandFactory;

/**
 * This class provides XSLT transformer factory
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class XsltTransformerFactory {
    private static final XsltTransformerFactory instance = new XsltTransformerFactory();
    private static Map<String, Templates> xsltTemplates = new HashMap<>();
    private static Lock lock = new ReentrantLock();

    private XsltTransformerFactory() {
    }

    /**
     * Gets transformer from factory
     * 
     * @param xsltFilePath
     *            path to xsl file
     * @return cached transformer
     * @throws TransformerConfigurationException
     *             if there are problems to create new template or transformer
     */
    public static Transformer getTransformer(String xsltFilePath)
	    throws TransformerConfigurationException {
	Transformer transformer = null;
	String realPath = CommandFactory.getRealPath();

	Templates template = xsltTemplates.get(xsltFilePath);
	if (template == null) {
	    lock.lock();
	    try {
		template = xsltTemplates.get(xsltFilePath);
		if (template == null) {
		    TransformerFactory factory = TransformerFactory
			    .newInstance();
		    File file = new File(realPath + xsltFilePath);
		    Source source = new StreamSource(file);
		    template = factory.newTemplates(source);
		    xsltTemplates.put(xsltFilePath, template);
		}
	    } catch (TransformerConfigurationException e) {
		throw e;
	    } finally {
		lock.unlock();
	    }
	}
	transformer = template.newTransformer();
	return transformer;
    }

    /**
     * @return the instance
     */
    public static XsltTransformerFactory getInstance() {
	return instance;
    }
}
