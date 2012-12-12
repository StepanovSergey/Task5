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

import static com.epam.task5.resource.Constants.*;

import com.epam.task5.model.Product;
import com.epam.task5.transform.XsltTransformerFactory;
import com.epam.task5.validation.ProductValidator;

/**
 * Go to "Add product" page
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class AddProductCommand implements ICommand {
    private static final Logger logger = Logger
	    .getLogger(AddProductCommand.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epam.task5.command.ICommand#execute(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
	try {
	    Product product = new Product();
	    String producer = request.getParameter(PRODUCER_TAG);
	    if (producer == null) {
		product.setProducer("");
	    } else {
		product.setProducer(producer);
	    }
	    String model = request.getParameter(MODEL_TAG);
	    if (model == null) {
		product.setModel("");
	    } else {
		product.setModel(model);
	    }
	    String color = request.getParameter(COLOR_TAG);
	    if (color == null) {
		product.setColor("");
	    } else {
		product.setColor(color);
	    }
	    String dateOfIssue = request.getParameter(DATE_OF_ISSUE_TAG);
	    if (dateOfIssue == null) {
		product.setDateOfIssue("");
	    } else {
		product.setDateOfIssue(dateOfIssue);
	    }
	    String notInStock = request.getParameter(NOT_IN_STOCK_TAG);
	    if (notInStock != null) {
		product.setNotInStock(true);
		product.setPrice(DEFAULT_PRICE);
	    } else {
		String price = request.getParameter(PRICE_TAG);
		if (price != null && price != "") {
		    product.setPrice(price);
		} else {
		    product.setPrice(DEFAULT_PRICE);
		}
	    }
	    Transformer transformer = XsltTransformerFactory
		    .getTransformer(ADD_PRODUCT_XSLT);
	    String currentCategory = request
		    .getParameter(CURRENT_CATEGORY_PARAMETER);
	    transformer.setParameter(CURRENT_CATEGORY_PARAMETER,
		    currentCategory);
	    String currentSubcategory = request
		    .getParameter(CURRENT_SUBCATEGORY_PARAMETER);
	    transformer.setParameter(CURRENT_SUBCATEGORY_PARAMETER,
		    currentSubcategory);
	    transformer.setParameter(VALIDATOR_PARAMETER,
		    new ProductValidator());
	    transformer.setParameter(PRODUCT_PARAMETER, product);
	    readLock.lock();
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
