package com.epam.task5.command;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.task5.resource.Constants;
import com.epam.task5.transform.XsltTransformerFactory;

/**
 * @author Siarhei_Stsiapanau
 * 
 */
public class ShowCategoriesCommand implements ICommand {
    private static final Logger logger = Logger
	    .getLogger(ShowCategoriesCommand.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epam.task5.command.ICommand#execute(javax.servlet.http.HttpServletRequest
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
	readLock.lock();
	try {
	    Transformer transformer = XsltTransformerFactory
		    .getTransformer(Constants.CATEGORIES_XSLT);
	    transformer.transform(
		    new StreamSource(CommandFactory.getXmlFile()),
		    new StreamResult(response.getWriter()));
	} catch (TransformerException | IOException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	} finally {
	    readLock.unlock();
	}
    }

}
