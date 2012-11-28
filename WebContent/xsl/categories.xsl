<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<p>
					Category1:
					<xsl:value-of select="//category/@name" />
					<xsl:value-of select="count(//category/subcategory/product)" />
				</p>
				<p>
					Sub:
					<xsl:value-of select="//subcategory/@name"></xsl:value-of>
				</p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>