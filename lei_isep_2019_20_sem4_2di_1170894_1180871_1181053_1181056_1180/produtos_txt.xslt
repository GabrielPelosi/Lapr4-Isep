<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" omit-xml-declaration="yes" indent="yes"/>
	<xsl:template match="/">
		<xsl:text>Codigo Fabrico;Codigo Comercial;Descricao Breve;Descricao Completa;Unidade;Categoria</xsl:text>
		<xsl:apply-templates select="ChaoDeFabrica" />
	</xsl:template>
	<xsl:template match="Produto">
		<xsl:value-of select="concat(./@CodigoFabrico,';',./CodigoComercial,';',./DescricaoBreve,';',./DescricaoCompleta,';',./Quantidade,';',./Unidade,';',./Categoria)"/>
	</xsl:template>

</xsl:stylesheet>
