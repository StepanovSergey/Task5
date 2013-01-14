package com.epam.task5.validation;

import static com.epam.task5.resource.Constants.COLOR_PATTERN;
import static com.epam.task5.resource.Constants.DATE_PATTERN;
import static com.epam.task5.resource.Constants.MODEL_PATTERN;
import static com.epam.task5.resource.Constants.PRICE_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.epam.task5.model.Product;

/**
 * This class provides product validator. It validates fields on add product
 * page
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class ProductValidator {
    /**
     * Pattern for model data tag
     */
    private static final Pattern modelPattern = Pattern
	    .compile("^(([A-Za-zА-Яа-яЁё]){2}([0-9]){3})$");
    /**
     * Pattern for date data tag
     */
    private static final Pattern datePattern = Pattern
	    .compile("^((0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(19[7-9][0-9]|2[0-2][0-9][0-9]))$");
    /**
     * Pattern for price data tag
     */
    private static final Pattern pricePattern = Pattern
	    .compile("^([1-9])(\\d*)|(\\d*\\.\\d{1,2})$");
    /**
     * Pattern for color data tag
     */
    private static final Pattern colorPattern = Pattern
	    .compile("^[A-Za-zА-Яа-яЁё]+$");

    private boolean isProductValid = true;
    private boolean isProducerInvalid;
    private boolean isModelInvalid;
    private boolean isDateInvalid;
    private boolean isColorInvalid;
    private boolean isPriceInvalid;

    /**
     * Default constructor
     */
    public ProductValidator() {
    }

    /**
     * Tests product for validation
     * 
     * @param product
     *            product to test
     * @return is valid product
     */
    public void isProductValid(Product product) {
	if (product == null) {
	    isProductValid = false;
	}
	if (isProducerInvalid(product.getProducer())) {
	    isProducerInvalid = true;
	    isProductValid = false;
	}
	if (isModelInvalid(product.getModel())) {
	    isModelInvalid = true;
	    isProductValid = false;
	}
	if (isColorInvalid(product.getColor())) {
	    isColorInvalid = true;
	    isProductValid = false;
	}
	String date = product.getDateOfIssue();
	if (isDateInvalid(date)) {
	    isDateInvalid = true;
	    isProductValid = false;
	}
	if (!product.isNotInStock()) {
	    if (isPriceInvalid(product.getPrice())) {
		isPriceInvalid = true;
		isProductValid = false;
	    }
	}
    }

    /**
     * Test producer for validation
     * 
     * @param producer
     *            producer to test
     * @return is producer valid
     */
    public boolean isProducerInvalid(String producer) {
	return isNull(producer);
    }

    /**
     * Test model for validation
     * 
     * @param model
     *            model to test
     * @return is model valid
     */
    public boolean isModelInvalid(String model) {
	return isDataInvalid(model, MODEL_PATTERN);
    }

    /**
     * Test color for validation
     * 
     * @param color
     *            color to test
     * @return is color valid
     */
    public boolean isColorInvalid(String color) {
	return isDataInvalid(color, COLOR_PATTERN);
    }

    /**
     * Test date for validation
     * 
     * @param date
     *            date to test
     * @return is date valid
     */
    public boolean isDateInvalid(String date) {
	return isDataInvalid(date, DATE_PATTERN);
    }

    /**
     * Test price for validation
     * 
     * @param price
     *            price to test
     * @return is price valid
     */
    public boolean isPriceInvalid(String price) {
	return isDataInvalid(price, PRICE_PATTERN);
    }

    private boolean isNull(String data) {
	if (data.isEmpty() || data == null) {
	    return true;
	} else {
	    return false;
	}
    }

    private boolean isDataInvalid(String data, String pattern) {
	boolean isDataInvalid = true;
	try {
	    if (isNull(data)) {
		return true;
	    } else {
		Matcher matcher = null;
		if (pattern.equals(MODEL_PATTERN)) {
		    matcher = modelPattern.matcher(data);
		} else {
		    if (pattern.equals(DATE_PATTERN)) {
			matcher = datePattern.matcher(data);
		    } else {
			if (pattern.equals(PRICE_PATTERN)) {
			    matcher = pricePattern.matcher(data);
			} else {
			    if (pattern.equals(COLOR_PATTERN)) {
				matcher = colorPattern.matcher(data);
			    } else {
				throw new PatternSyntaxException(
					"Error in pattern!", pattern, -1);
			    }
			}
		    }
		}
		isDataInvalid = !(matcher.matches());
		return isDataInvalid;
	    }
	} catch (PatternSyntaxException e) {
	    throw e;
	}
    }

    /**
     * @return the isProductValid
     */
    public boolean isProductValid() {
	return isProductValid;
    }

    /**
     * @param isProductValid
     *            the isProductValid to set
     */
    public void setProductValid(boolean isProductValid) {
	this.isProductValid = isProductValid;
    }

    /**
     * @return the isProducerInvalid
     */
    public boolean isProducerInvalid() {
	return isProducerInvalid;
    }

    /**
     * @param isProducerInvalid
     *            the isProducerInvalid to set
     */
    public void setProducerInvalid(boolean isProducerInvalid) {
	this.isProducerInvalid = isProducerInvalid;
    }

    /**
     * @return the isModelInvalid
     */
    public boolean isModelInvalid() {
	return isModelInvalid;
    }

    /**
     * @param isModelInvalid
     *            the isModelInvalid to set
     */
    public void setModelInvalid(boolean isModelInvalid) {
	this.isModelInvalid = isModelInvalid;
    }

    /**
     * @return the isDateInvalid
     */
    public boolean isDateInvalid() {
	return isDateInvalid;
    }

    /**
     * @param isDateInvalid
     *            the isDateInvalid to set
     */
    public void setDateInvalid(boolean isDateInvalid) {
	this.isDateInvalid = isDateInvalid;
    }

    /**
     * @return the isColorInvalid
     */
    public boolean isColorInvalid() {
	return isColorInvalid;
    }

    /**
     * @param isColorInvalid
     *            the isColorInvalid to set
     */
    public void setColorInvalid(boolean isColorInvalid) {
	this.isColorInvalid = isColorInvalid;
    }

    /**
     * @return the isPriceInvalid
     */
    public boolean isPriceInvalid() {
	return isPriceInvalid;
    }

    /**
     * @param isPriceInvalid
     *            the isPriceInvalid to set
     */
    public void setPriceInvalid(boolean isPriceInvalid) {
	this.isPriceInvalid = isPriceInvalid;
    }
}