<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="current_category" />
	<xsl:template match="/">
		<xsl:param name="subcategory" select="@name" />
		<html>
			<head>
				<title>Subcategories</title>
			</head>
			<body>
				<p>Choose subcategory:</p>
				<p>
					Cat =
					<xsl:value-of select="$current_category" />
				</p>
				<xsl:for-each select="//category[@name='electronic']/subcategory">
					<xsl:call-template name="subcategories" />
				</xsl:for-each>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="show_categories" />
					<input type="submit" value="Back" />
				</form>
			</body>
		</html>
	</xsl:template>


	<xsl:template name="subcategories">

		<p>
			<a href="#">
				123
				<!-- <xsl:value-of select="$subcategoryName" /> ( <xsl:value-of select="count(product)" 
					/> items) -->
			</a>
		</p>
	</xsl:template>
</xsl:stylesheet>