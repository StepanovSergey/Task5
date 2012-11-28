<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:for-each select="//category">
			<p>
				<a href="#">
					<xsl:value-of select="@name" />
					(
					<xsl:value-of select="count(subcategory/product)" />
					items)
				</a>
			</p>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>