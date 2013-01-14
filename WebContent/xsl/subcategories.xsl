<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="current_category" />
	<xsl:template match="/">
		<html>
			<head>
				<title>Subcategories</title>
				<link rel="stylesheet" type="text/css" href="css/style.css" />
			</head>
			<body>
				<p>
					Category:
					<xsl:value-of select="$current_category" />
				</p>
				<xsl:choose>
					<xsl:when
						test="count(//category[@name=$current_category]/subcategory) = 0">
						<p class="error">No subcategories in current category</p>
					</xsl:when>
					<xsl:otherwise>
						<p>Choose subcategory:</p>
						<xsl:for-each select="//category[@name=$current_category]/subcategory">
							<xsl:call-template name="subcategories" />
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
				<input type="button" value="Back"
					onclick="window.location = 'Controller?command=show_categories'" />
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