<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>Products</title>
			</head>
			<body>
				<xsl:for-each select="//category[@name=$categoryName]/subcategory">
					<xsl:call-template name="products" />
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template name="products">
			<p>
				<a href="#">
					<xsl:value-of select="@name" />
					(
					<xsl:value-of select="count(product)" />
					items)
				</a>
			</p>
	</xsl:template>
</xsl:stylesheet>