package com.epam.task5.model;

/**
 * This class provides shop model (bean)
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class Product {
    private String producer;
    private String model;
    private String dateOfIssue;
    private String color;
    private boolean notInStock;
    private String price;

    /**
     * @return the producer
     */
    public String getProducer() {
	return producer;
    }

    /**
     * @param producer
     *            the producer to set
     */
    public void setProducer(String producer) {
	this.producer = producer;
    }

    /**
     * @return the model
     */
    public String getModel() {
	return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
	this.model = model;
    }

    /**
     * @return the color
     */
    public String getColor() {
	return color;
    }

    /**
     * @param color
     *            the color to set
     */
    public void setColor(String color) {
	this.color = color;
    }

    /**
     * @return the notInStock
     */
    public boolean isNotInStock() {
	return notInStock;
    }

    /**
     * @param notInStock
     *            the notInStock to set
     */
    public void setNotInStock(boolean notInStock) {
	this.notInStock = notInStock;
    }

    /**
     * @return the dateOfIssue
     */
    public String getDateOfIssue() {
	return dateOfIssue;
    }

    /**
     * @param dateOfIssue
     *            the dateOfIssue to set
     */
    public void setDateOfIssue(String dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

    /**
     * @return the price
     */
    public String getPrice() {
	return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(String price) {
	this.price = price;
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
	result = prime * result + ((color == null) ? 0 : color.hashCode());
	result = prime * result
		+ ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
	result = prime * result + ((model == null) ? 0 : model.hashCode());
	result = prime * result + (notInStock ? 1231 : 1237);
	result = prime * result + ((price == null) ? 0 : price.hashCode());
	result = prime * result
		+ ((producer == null) ? 0 : producer.hashCode());
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
	Product other = (Product) obj;
	if (color == null) {
	    if (other.color != null)
		return false;
	} else if (!color.equals(other.color))
	    return false;
	if (dateOfIssue == null) {
	    if (other.dateOfIssue != null)
		return false;
	} else if (!dateOfIssue.equals(other.dateOfIssue))
	    return false;
	if (model == null) {
	    if (other.model != null)
		return false;
	} else if (!model.equals(other.model))
	    return false;
	if (notInStock != other.notInStock)
	    return false;
	if (price == null) {
	    if (other.price != null)
		return false;
	} else if (!price.equals(other.price))
	    return false;
	if (producer == null) {
	    if (other.producer != null)
		return false;
	} else if (!producer.equals(other.producer))
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
	builder.append("Product [producer=");
	builder.append(producer);
	builder.append(", model=");
	builder.append(model);
	builder.append(", dateOfIssue=");
	builder.append(dateOfIssue);
	builder.append(", color=");
	builder.append(color);
	builder.append(", notInStock=");
	builder.append(notInStock);
	builder.append(", price=");
	builder.append(price);
	builder.append("]");
	return builder.toString();
    }

}
