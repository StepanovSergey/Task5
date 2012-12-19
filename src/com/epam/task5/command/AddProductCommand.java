package com.epam.task5.command;

import static com.epam.task5.resource.Constants.ADD_PRODUCT_XSLT;
import static com.epam.task5.resource.Constants.COLOR_TAG;
import static com.epam.task5.resource.Constants.CURRENT_CATEGORY_PARAMETER;
import static com.epam.task5.resource.Constants.CURRENT_SUBCATEGORY_PARAMETER;
import static com.epam.task5.resource.Constants.DATE_OF_ISSUE_TAG;
import static com.epam.task5.resource.Constants.DEFAULT_PRICE;
import static com.epam.task5.resource.Constants.MODEL_TAG;
import static com.epam.task5.resource.Constants.NOT_IN_STOCK_TAG;
import static com.epam.task5.resource.Constants.PRICE_TAG;
import static com.epam.task5.resource.Constants.PRODUCER_TAG;
import static com.epam.task5.resource.Constants.PRODUCT_PARAMETER;
import static com.epam.task5.resource.Constants.VALIDATOR_PARAMETER;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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

import com.epam.task5.model.Product;
import com.epam.task5.transform.XsltTransformerFactory;
import com.epam.task5.validation.ProductValidator;

/**
 * Save product page
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class AddProductCommand implements ICommand {
    private static final Logger logger = Logger
	    .getLogger(AddProductCommand.class);
    private static final String ENCODING = "UTF-8";
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

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
	    ProductValidator validator = new ProductValidator();
	    transformer.setParameter(VALIDATOR_PARAMETER, validator);
	    Product product = setProductParameters(request);
	    transformer.setParameter(PRODUCT_PARAMETER, product);
	    Writer result = new StringWriter();
	    try {
		readLock.lock();
		transformer.transform(
			new StreamSource(CommandFactory.getXmlFile()),
			new StreamResult(result));

	    } catch (TransformerException e) {
		if (logger.isEnabledFor(Level.ERROR)) {
		    logger.error(e.getMessage(), e);
		}
	    } finally {
		readLock.unlock();
	    }

	    Writer writer = response.getWriter();

	    if (validator.isProductValid(product)) {
		write(CommandFactory.getXmlFile(), result);
		String link = "Controller?command=show_products&current_category="
			+ currentCategory
			+ "&current_subcategory="
			+ currentSubcategory;
		response.sendRedirect(link);
	    } else {
		writer.write(result.toString());
	    }
	} catch (IOException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
    }

    private Product setProductParameters(HttpServletRequest request) {
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
	return product;
    }

    private void write(File xmlFile, Writer writer) {
	try {
	    Writer fileWriter = new PrintWriter(xmlFile, ENCODING);
	    writeLock.lock();
	    fileWriter.write(writer.toString());
	    fileWriter.flush();
	} catch (IOException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	} finally {
	    writeLock.unlock();
	}
    }
}
