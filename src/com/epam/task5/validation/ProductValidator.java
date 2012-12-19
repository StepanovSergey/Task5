package com.epam.task5.validation;

import static com.epam.task5.resource.Constants.DATE_PATTERN;
import static com.epam.task5.resource.Constants.MODEL_PATTERN;
import static com.epam.task5.resource.Constants.PRICE_PATTERN;
import static com.epam.task5.resource.Constants.COLOR_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.task5.model.Product;

/**
 * This class provides product validator. It validates fields on add product
 * page
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class ProductValidator {
    private static final Logger logger = Logger
	    .getLogger(ProductValidator.class);
    /**
     * Pattern for model data tag
     */
    public static final Pattern modelPattern = Pattern
	    .compile("^(([A-Za-zÀ-ßà-ÿ¨¸]){2}([0-9]){3})$");
    /**
     * Pattern for date data tag
     */
    public static final Pattern datePattern = Pattern
	    .compile("^((0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(19[7-9][0-9]|2[0-2][0-9][0-9]))$");
    /**
     * Pattern for price data tag
     */
    public static final Pattern pricePattern = Pattern
	    .compile("^([1-9])(\\d*)|(\\d*\\.\\d{1,2})$");
    /**
     * Pattern for color data tag
     */
    public static final Pattern colorPattern = Pattern
	    .compile("^[A-Za-zÀ-ßà-ÿ¨¸]+$");

    public ProductValidator() {
    }

    /**
     * Tests product for validation
     * 
     * @param product
     *            product to test
     * @return is valid product
     */
    public boolean isProductValid(Product product) {
	if (product == null) {
	    return false;
	}
	if (isProducerInvalid(product.getProducer())) {
	    return false;
	}
	if (isModelInvalid(product.getModel())) {
	    return false;
	}
	if (isColorInvalid(product.getColor())) {
	    return false;
	}
	String date = product.getDateOfIssue();
	if (isDateInvalid(date)) {
	    return false;
	}
	if (!product.isNotInStock()) {
	    if (isPriceInvalid(product.getPrice())) {
		return false;
	    }
	}
	return true;
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
		if (isDataInvalid) {
		}
		return isDataInvalid;
	    }
	} catch (PatternSyntaxException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	    return isDataInvalid;
	}
    }
}