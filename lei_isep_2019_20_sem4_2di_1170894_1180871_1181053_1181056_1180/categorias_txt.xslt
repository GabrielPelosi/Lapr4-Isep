<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes" indent="yes"/>
	<xsl:template match="/">
		<xsl:text>Codigo de Categoria;Descricao da Categoria</xsl:text>
		<xsl:apply-templates select="ChaoDeFabrica" />
	</xsl:template>

	<xsl:template match="Categoria">
		<xsl:value-of select="concat(./@CodigoCategoria,';',./DescricaoCategoria)" />
	</xsl:template>
</xsl:stylesheet>


