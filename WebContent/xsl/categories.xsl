<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>Categories</title>
			</head>
			<body>
				<p>Choose category:</p>
				<xsl:for-each select="//category">
					<xsl:call-template name="categories" />
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>


	<xsl:template name="categories">
		<xsl:param name="category_name" select="@name" />
		<p>
			<a href="Controller?command=show_subcategories&amp;current_category={$category_name}">
				<xsl:value-of select="$category_name" />
				(
				<xsl:value-of select="count(subcategory/product)" />
				items)
			</a>
		</p>
	</xsl:template>
</xsl:stylesheet>