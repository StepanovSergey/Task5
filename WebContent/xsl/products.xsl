<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="current_category" />
	<xsl:param name="current_subcategory" />
	<xsl:template match="/">
		<html>
			<head>
				<title>Products</title>
			</head>
			<body>
				<p>Products</p>
				<table border="1">
					<tr>
						<th>Producer</th>
						<th>Model</th>
						<th>Color</th>
						<th>Date of issue</th>
						<th>Price</th>
					</tr>
					<xsl:for-each
						select="//category[@name=$current_category]/subcategory[@name=$current_subcategory]/product">
						<xsl:call-template name="products" />
					</xsl:for-each>
				</table>
				<form action="Controller">
					<input type="hidden" name="command" value="add_product" />
					<input type="hidden" name="current_category" value="{$current_category}" />
					<input type="hidden" name="current_subcategory" value="{$current_subcategory}" />
					<input type="submit" value="Add Product" />
				</form>
				<form action="Controller">
					<input type="hidden" name="command" value="show_subcategories" />
					<input type="hidden" name="current_category" value="{$current_category}" />
					<input type="hidden" name="current_subcategory" value="{$current_subcategory}" />
					<input type="submit" value="Back" />
				</form>
			</body>
		</html>
	</xsl:template>

	<xsl:template name="products">
		<tr>
			<td>
				<xsl:value-of select="producer" />
			</td>
			<td>
				<xsl:value-of select="model" />
			</td>
			<td>
				<xsl:value-of select="color" />
			</td>
			<td>
				<xsl:value-of select="date_of_issue" />
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="price&gt;0">
						<xsl:value-of select="price" />
					</xsl:when>
					<xsl:otherwise>
						Not in stock
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>