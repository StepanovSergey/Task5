package com.epam.task5.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides sub category entity.
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class Subcategory {
    private String name;
    private List<Product> productList = new ArrayList<Product>();

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the productList
     */
    public List<Product> getProductList() {
	return productList;
    }

    /**
     * @param productList
     *            the productList to set
     */
    public void setProductList(List<Product> productList) {
	this.productList = productList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((productList == null) ? 0 : productList.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Subcategory other = (Subcategory) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (productList == null) {
	    if (other.productList != null)
		return false;
	} else if (!productList.equals(other.productList))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Subcategory [name=");
	builder.append(name);
	builder.append(", productList=");
	builder.append(productList);
	builder.append("]");
	return builder.toString();
    }

}
