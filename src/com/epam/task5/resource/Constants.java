package com.epam.task5.resource;

/**
 * @author Siarhei_Stsiapanau
 * 
 */
public final class Constants {
    private Constants() {
    }

    // Common constants
    public static final String COMMAND_PARAMETER = "command";
    public static final String NO_COMMAND = "no_command";
    public static final String XML_PATH = "/xml/products.xml";
    public static final String ERROR_PAGE = "jsp/error.jsp";
    public static final String CURRENT_CATEGORY_PARAMETER = "current_category";
    public static final String CURRENT_SUBCATEGORY_PARAMETER = "current_subcategory";
    // Categories constants
    public static final String SHOW_CATEGORIES_COMMAND = "show_categories";
    public static final String CATEGORIES_XSLT = "/xsl/categories.xsl";
    // Subcategories constants
    public static final String SHOW_SUBCATEGORIES_COMMAND = "show_subcategories";
    public static final String SUBCATEGORIES_XSLT = "/xsl/subcategories.xsl";
    // Products constants
    public static final String SHOW_PRODUCTS_COMMAND = "show_products";
    public static final String PRODUCTS_XSLT = "/xsl/products.xsl";
    // Add product constants
    public static final String ADD_PRODUCT_COMMAND = "add_product";
    public static final String ADD_PRODUCT_XSLT = "/xsl/addProduct.xsl";
    public static final String PRODUCT_PARAMETER = "product";
    // XML tags
    public static final String CATEGORY_TAG = "category";
    public static final String SUBCATEGORY_TAG = "subcategory";
    public static final String PRODUCT_TAG = "product";
    public static final String PRODUCER_TAG = "producer";
    public static final String MODEL_TAG = "model";
    public static final String DATE_OF_ISSUE_TAG = "date_of_issue";
    public static final String PRICE_TAG = "price";
    public static final String COLOR_TAG = "color";
    public static final String NOT_IN_STOCK_TAG = "not_in_stock";
    public static final String NAME_ATTRIBUTE = "name";
    // Validator constants
    public static final String VALIDATOR_PARAMETER = "validator";
    public static final String MODEL_PATTERN = "model_pattern";
    public static final String PRICE_PATTERN = "price_pattern";
    public static final String DATE_PATTERN = "date_pattern";
    public static final String COLOR_PATTERN = "color_pattern";
    public static final String DEFAULT_PRICE = "0";
    
}
