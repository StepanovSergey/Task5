package com.epam.task5.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides category entity
 * @author Siarhei_Stsiapanau
 * 
 */
public class Category {
    private String name;
    private List<Subcategory> subcategoryList = new ArrayList<Subcategory>();

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
     * @return the subcategoryList
     */
    public List<Subcategory> getSubcategoryList() {
	return subcategoryList;
    }

    /**
     * @param subcategoryList
     *            the subcategoryList to set
     */
    public void setSubcategoryList(List<Subcategory> subcategoryList) {
	this.subcategoryList = subcategoryList;
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
		+ ((subcategoryList == null) ? 0 : subcategoryList.hashCode());
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
	Category other = (Category) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (subcategoryList == null) {
	    if (other.subcategoryList != null)
		return false;
	} else if (!subcategoryList.equals(other.subcategoryList))
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
	builder.append("Category [name=");
	builder.append(name);
	builder.append(", subcategoryList=");
	builder.append(subcategoryList);
	builder.append("]");
	return builder.toString();
    }

}
