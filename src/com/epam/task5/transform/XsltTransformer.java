package com.epam.task5.transform;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Siarhei_Stsiapanau
 * 
 */
public final class XsltTransformer {
    private static final Logger logger = Logger
	    .getLogger(XsltTransformer.class);
    private static Writer writer;

    public void transform(Source xmlSource, Source xsltSource,
	    HttpServletResponse response) {
	try {
	    if (writer == null) {
		writer = response.getWriter();
	    }
	    
	} catch (IOException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
    }
}
