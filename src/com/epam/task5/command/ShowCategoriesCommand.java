/**
 * 
 */
package com.epam.task5.command;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Siarhei_Stsiapanau
 * 
 */
public class ShowCategoriesCommand implements ICommand {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epam.task5.command.ICommand#execute(javax.servlet.http.HttpServletRequest
     * 
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

	readLock.lock();
	readLock.unlock();
	String xmlFilePath = request.getSession().getServletContext()
		.getRealPath("");
	File xmlFile = new File(xmlFilePath + "/xml/products.xml");
	File xsltFile = new File(xmlFilePath + "/xsl/categories.xsl");
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
	}
    }

}
