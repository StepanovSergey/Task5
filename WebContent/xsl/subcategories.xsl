<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="current_category" />
	<xsl:template match="/">
		<html>
			<head>
				<title>Subcategories</title>
			</head>
			<body>
				<p>
					Choose subcategory:
				</p>
				<xsl:for-each select="//category[@name=$current_category]/subcategory">
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
		<xsl:param name="subcategory_name" select="@name" />
		<p>
			<a
				href="Controller?command=show_products&amp;current_category={$current_category}&amp;current_subcategory={$subcategory_name}">
				<xsl:value-of select="$subcategory_name" />
				(
				<xsl:value-of select="count(product)" />
				items)
			</a>
		</p>
	</xsl:template>
</xsl:stylesheet>