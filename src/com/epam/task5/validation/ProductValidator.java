package com.epam.task5.validation;

import static com.epam.task5.resource.Constants.DATE_PATTERN;
import static com.epam.task5.resource.Constants.MODEL_PATTERN;
import static com.epam.task5.resource.Constants.PRICE_PATTERN;
import static com.epam.task5.resource.Constants.WRONG_COLOR;
import static com.epam.task5.resource.Constants.WRONG_DATE;
import static com.epam.task5.resource.Constants.WRONG_MODEL;
import static com.epam.task5.resource.Constants.WRONG_PRICE;
import static com.epam.task5.resource.Constants.WRONG_PRODUCER;

import java.util.ArrayList;
import java.util.List;
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
    private static List<String> errorList = new ArrayList<>();
    public static final Pattern modelPattern = Pattern
	    .compile("^(([A-Za-zР-пр-џ]){2}([0-9]){3})$");
    public static final Pattern datePattern = Pattern
	    .compile("^((0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(19[7-9][0-9]|2[0-2][0-9][0-9]))$");
    public static final Pattern pricePattern = Pattern
	    .compile("^(\\d+)|(\\d+\\.\\d{1,2})$");

    public ProductValidator() {
    }

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
	if (product.isNotInStock()) {
	    if (isPriceInvalid(String.valueOf(product.getPrice()))) {
		return false;
	    }
	}
	return true;
    }

    public boolean isProducerInvalid(String producer) {
	return isNull(producer, WRONG_PRODUCER);
    }

    public boolean isModelInvalid(String model) {
	return isDataInvalid(model, MODEL_PATTERN, WRONG_MODEL);
    }

    public boolean isColorInvalid(String color) {
	return isNull(color, WRONG_COLOR);
    }

    public boolean isDateInvalid(String date) {
	return isDataInvalid(date, DATE_PATTERN, WRONG_DATE);
    }

    public boolean isPriceInvalid(String price) {
	return isDataInvalid(price, PRICE_PATTERN, WRONG_PRICE);
    }

    private boolean isNull(String data, String errorMessage) {
	if (data.isEmpty() || data == null) {
	    errorList.add(errorMessage);
	    return true;
	} else {
	    return false;
	}
    }

    private boolean isDataInvalid(String data, String pattern,
	    String errorMessage) {
	boolean isDataInvalid = true;
	try {
	    if (isNull(data, errorMessage)) {
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
			    throw new PatternSyntaxException(
				    "Error in pattern!", pattern, -1);
			}
		    }
		}
		isDataInvalid = !(matcher.matches());
		if (isDataInvalid) {
		    errorList.add(errorMessage);
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

    /**
     * @return the errorList
     */
    public static List<String> getErrorList() {
	return errorList;
    }

}
