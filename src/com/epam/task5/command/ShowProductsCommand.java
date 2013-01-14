package com.epam.task5.command;

import static com.epam.task5.resource.Constants.CURRENT_CATEGORY_PARAMETER;
import static com.epam.task5.resource.Constants.CURRENT_SUBCATEGORY_PARAMETER;
import static com.epam.task5.resource.Constants.PRODUCTS_XSLT;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.task5.transform.XsltTransformerFactory;

/**
 * This command shows products list
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class ShowProductsCommand implements ICommand {
    private final Lock readLock = CommandFactory.getLock().readLock();

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epam.task5.command.ICommand#execute(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	Transformer transformer = XsltTransformerFactory
		.getTransformer(PRODUCTS_XSLT);
	String currentCategory = request
		.getParameter(CURRENT_CATEGORY_PARAMETER);
	transformer.setParameter(CURRENT_CATEGORY_PARAMETER, currentCategory);
	String currentSubcategory = request
		.getParameter(CURRENT_SUBCATEGORY_PARAMETER);
	transformer.setParameter(CURRENT_SUBCATEGORY_PARAMETER,
		currentSubcategory);
	try {
	    readLock.lock();
	    transformer.transform(
		    new StreamSource(CommandFactory.getXmlFile()),
		    new StreamResult(response.getWriter()));
	} catch (TransformerException | IOException e) {
	    throw e;
	} finally {
	    readLock.unlock();
	}

    }

}
