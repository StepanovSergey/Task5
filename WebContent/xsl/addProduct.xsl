<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:product="xalan://com.epam.task5.model.Product"
	xmlns:validator="xalan://com.epam.task5.validation.ProductValidator">
	<!-- Variables -->
	<xsl:param name="product" />
	<xsl:param name="current_category" />
	<xsl:param name="current_subcategory" />
	<xsl:param name="validator" />

	<xsl:template match="/">
		<!-- Validating product -->
		<xsl:value-of select="validator:isProductValid($validator, $product)"></xsl:value-of>
		<xsl:choose>
			<xsl:when test="validator:isProductValid($validator)">
			<!-- If product valid -> Save product to file -->
				<xsl:apply-templates />
			</xsl:when>
			<xsl:otherwise>
			<!-- If product invalid -> Display form with error messages -->
				<html>
					<head>
						<link rel="stylesheet" type="text/css" href="css/style.css"></link>
						<title>Add product</title>
					</head>
					<body>
						<p>Add product</p>
						<xsl:call-template name="addProduct" />
					</body>
				</html>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

<!-- Add product form template -->
	<xsl:template name="addProduct">
		<table>
			<form action="Controller" metod="POST">
				<tr>
					<th>Producer</th>
					<td>
						<input type="text" name="producer" value="{product:getProducer($product)}" />
					</td>
					<td>
						<xsl:if test="validator:isProducerInvalid($validator)">
							<p class="error">Producer must be specified</p>
						</xsl:if>
					</td>
				</tr>
				<tr>
					<th>Model</th>
					<td>
						<input type="text" name="model" value="{product:getModel($product)}" />
					</td>
					<td>
						<xsl:if test="validator:isModelInvalid($validator)">
							<p class="error">Model must contain 2 letters and 3 digits</p>
						</xsl:if>
					</td>
				</tr>
				<tr>
					<th>Date Of Issue</th>
					<td>
						<input type="text" name="date_of_issue" value="{product:getDateOfIssue($product)}" />
					</td>
					<td>
						<xsl:if test="validator:isDateInvalid($validator)">
							<p class="error">Date must be dd-MM-yyyy</p>
						</xsl:if>
					</td>
				</tr>
				<tr>
					<th>Color</th>
					<td>
						<input type="text" name="color" value="{product:getColor($product)}" />
					</td>
					<td>
						<xsl:if test="validator:isColorInvalid($validator)">
							<p class="error">Color must be specified</p>
						</xsl:if>
					</td>
				</tr>
				<tr>
					<th>Price</th>
					<td>
						<input type="text" name="price" value="{product:getPrice($product)}" />
					</td>
					<td>
						<xsl:if test="validator:isPriceInvalid($validator)">
							<p class="error">Price must be e.g 14, 10.0 or 17.12</p>
						</xsl:if>
					</td>
				</tr>
				<tr>
					<th>Not in stock</th>
					<td>
						<xsl:variable name="notInStock" />
						<xsl:choose>
							<xsl:when test="product:isNotInStock($product)">
								<input type="checkbox" name="not_in_stock" checked="true" />
							</xsl:when>
							<xsl:otherwise>
								<input type="checkbox" name="not_in_stock" />
							</xsl:otherwise>
						</xsl:choose>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<input type="hidden" name="command" value="add_product" />
						<input type="hidden" name="current_category" value="{$current_category}" />
						<input type="hidden" name="current_subcategory" value="{$current_subcategory}" />
						<input type="submit" value="Save" />
						<input type="button" value="Back"
							onclick="window.location='Controller?command=show_products&amp;current_category={$current_category}&amp;current_subcategory={$current_subcategory}'" />
					</td>
				</tr>
			</form>
		</table>
	</xsl:template>

	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

	<xsl:template
		match="products/category[@name=$current_category]/subcategory[@name=$current_subcategory]">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
			<xsl:element name="product">
				<xsl:element name="producer">
					<xsl:value-of select="product:getProducer($product)" />
				</xsl:element>
				<xsl:element name="model">
					<xsl:value-of select="product:getModel($product)" />
				</xsl:element>
				<xsl:element name="date_of_issue">
					<xsl:value-of select="product:getDateOfIssue($product)" />
				</xsl:element>
				<xsl:element name="color">
					<xsl:value-of select="product:getColor($product)" />
				</xsl:element>
				<xsl:choose>
					<xsl:when test="product:getPrice($product) != 0">
						<xsl:element name="price">
							<xsl:value-of select="product:getPrice($product)" />
						</xsl:element>
					</xsl:when>
					<xsl:otherwise>
						<xsl:element name="not_in_stock" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:element>
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>