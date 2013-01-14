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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.task5.model.Product;
import com.epam.task5.transform.XsltTransformerFactory;
import com.epam.task5.validation.ProductValidator;

/**
 * Save product page
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class AddProductCommand implements ICommand {
    private static final String ENCODING = "UTF-8";
    private final Lock readLock = CommandFactory.getLock().readLock();
    private final Lock writeLock = CommandFactory.getLock().writeLock();

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

	// Defining variables
	Transformer transformer = XsltTransformerFactory
		.getTransformer(ADD_PRODUCT_XSLT);
	String currentCategory = request
		.getParameter(CURRENT_CATEGORY_PARAMETER);
	String currentSubcategory = request
		.getParameter(CURRENT_SUBCATEGORY_PARAMETER);
	ProductValidator validator = new ProductValidator();
	Product product = setProductParameters(request);
	Writer result = new StringWriter();
	long lastModified = CommandFactory.getXmlFile().lastModified();

	// Setting transformer parameters
	transformer.setParameter(CURRENT_CATEGORY_PARAMETER, currentCategory);
	transformer.setParameter(CURRENT_SUBCATEGORY_PARAMETER,
		currentSubcategory);
	transformer.setParameter(VALIDATOR_PARAMETER, validator);
	transformer.setParameter(PRODUCT_PARAMETER, product);
	// Transforming file
	readLock.lock();
	try {
	    Source source = new StreamSource(CommandFactory.getXmlFile());
	    Result res = new StreamResult(result);
	    transformer.transform(source, res);
	} catch (TransformerException e) {
	    throw e;
	} finally {
	    readLock.unlock();
	}
	try {
	    Writer writer = response.getWriter();
	    if (validator.isProductValid()) {
		if (lastModified == CommandFactory.getXmlFile().lastModified()) {
		    // Write to file
		    write(CommandFactory.getXmlFile(), result);
		    String link = "Controller?command=show_products&current_category="
			    + currentCategory
			    + "&current_subcategory="
			    + currentSubcategory;
		    // Show products page
		    response.sendRedirect(link);
		} else {
		    // Retry to transform
		    Source source = new StreamSource(
			    CommandFactory.getXmlFile());
		    Result res = new StreamResult(result);
		    transformer.transform(source, res);
		}
	    } else {
		// Display add form with error messages
		writer.write(result.toString());
	    }
	} catch (IOException | TransformerException e) {
	    throw e;
	}
    }

    /**
     * Get product data from request and return product instance
     * 
     * @param request
     *            get parameters from this request
     * @return product instance
     */
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

    /**
     * Write data from writer to file
     * 
     * @param xmlFile
     *            file to write
     * @param writer
     *            data to write
     * @throws IOException
     *             if there is problem to write file
     */
    private void write(File xmlFile, Writer writer) throws IOException {
	try {
	    Writer fileWriter = new PrintWriter(xmlFile, ENCODING);
	    writeLock.lock();
	    fileWriter.write(writer.toString());
	    fileWriter.flush();
	} catch (IOException e) {
	    throw e;
	} finally {
	    writeLock.unlock();
	}
    }
}
