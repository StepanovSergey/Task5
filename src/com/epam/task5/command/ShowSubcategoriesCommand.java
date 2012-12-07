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
public class ShowSubcategoriesCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(ShowSubcategoriesCommand.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();


    /* (non-Javadoc)
     * @see com.epam.task5.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
	/*String xmlFilePath = request.getSession().getServletContext().getRealPath("");
	File xmlFile = new File(xmlFilePath + "/xml/products.xml");
	File xsltFile = new File(xmlFilePath + "/xsl/subcategories.xsl");
	try {
	Source xmlSource = new StreamSource(xmlFile);
	Source xsltSource = new StreamSource(xsltFile);
	Result result = new StreamResult(response.getWriter());
	
	// create an instance of TransformerFactory
	TransformerFactory transFact = javax.xml.transform.TransformerFactory
		.newInstance();
	Transformer transformer;
	
	    transformer = transFact.newTransformer(xsltSource);
	    transformer.transform(xmlSource, result);
	} catch (TransformerException | IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}*/
	readLock.lock();
	try {
	    Transformer transformer = XsltTransformerFactory
		    .getTransformer(Constants.SUBCATEGORIES_XSLT);
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
