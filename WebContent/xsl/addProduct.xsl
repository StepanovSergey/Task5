<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:product="xalan://com.epam.task5.model.Product"
	xmlns:validator="xalan://com.epam.task5.validation.ProductValidator">
	<xsl:param name="product" />
	<xsl:param name="current_category" />
	<xsl:param name="current_subcategory" />
	<xsl:param name="validator" />
	<xsl:template match="/">
		<html>
			<head>
				<title>Add product</title>
			</head>
			<body>
				<p>Add product</p>
				<xsl:value-of select="product:toString($product)"></xsl:value-of>
				<table>
					<xsl:call-template name="addProduct" />
					<tr>
						<td></td>
						<td>
							<form action="Controller">
								<input type="hidden" name="command" value="show_products" />
								<input type="hidden" name="current_category" value="{$current_category}" />
								<input type="hidden" name="current_subcategory" value="{$current_subcategory}" />
								<input type="submit" value="Back" />
							</form>
						</td>
					</tr>
				</table>
			</body>
		</html>
	</xsl:template>


	<xsl:template name="addProduct">
		<form action="Controller">
			<input type="hidden" name="command" value="add_product" />
			<input type="hidden" name="current_category" value="{$current_category}" />
			<input type="hidden" name="current_subcategory" value="{$current_subcategory}" />
			<tr>
				<th>Producer</th>
				<td>
					<input type="text" name="producer" value="{product:getProducer($product)}" />
				</td>
				<td>
					<xsl:if
						test="validator:isProducerInvalid(product:getProducer($product))">
						Invalid!
					</xsl:if>
				</td>
			</tr>
			<tr>
				<th>Model</th>
				<td>
					<input type="text" name="model" value="{product:getModel($product)}" />
				</td>
				<td>
					<xsl:if test="validator:isModelInvalid(product:getModel($product))">
						Invalid!
					</xsl:if>
				</td>
			</tr>
			<tr>
				<th>Date Of Issue</th>
				<td>
					<input type="text" name="date_of_issue" value="{product:getDateOfIssue($product)}" />
				</td>
				<td>
					<xsl:if test="validator:isDateInvalid(product:getDateOfIssue($product))">
						Invalid!
					</xsl:if>
				</td>
			</tr>
			<tr>
				<th>Color</th>
				<td>
					<input type="text" name="color" value="{product:getColor($product)}" />
				</td>
				<td>
					<xsl:if test="validator:isColorInvalid(product:getColor($product))">
						Invalid!
					</xsl:if>
				</td>
			</tr>
			<tr>
				<th>Price</th>
				<td>
					<input type="text" name="price" value="{product:getPrice($product)}" />
				</td>
				<td>
					<xsl:if test="validator:isPriceInvalid(product:getPrice($product))">
						Invalid!
					</xsl:if>
				</td>
			</tr>
			<tr>
				<th>Not in stock</th>
				<td>
				<xsl:variable name="notInStock" />
					<xsl:choose>
						<xsl:when test="product:isNotInStock($product)=true">
							<xsl:variable name="notInStock" select="1" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:variable name="notInStock" select="0" />
						</xsl:otherwise>
					</xsl:choose>
					<input type="checkbox" name="not_in_stock" value="{$notInStock}" />
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<input type="submit" value="Save" />
				</td>
			</tr>
		</form>
	</xsl:template>
</xsl:stylesheet>