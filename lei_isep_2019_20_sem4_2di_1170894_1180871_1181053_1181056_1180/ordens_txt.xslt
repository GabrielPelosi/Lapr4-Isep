<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes" indent="yes"/>
	<xsl:template match="/">
		<xsl:text>Codigo Ordem;Data Emissao;Data Prevista;Produto;Quantidade;Unidade;Encomendas;Estado</xsl:text>
		<xsl:apply-templates select="ChaoDeFabrica" />
	</xsl:template>

	<xsl:template match="Ordem">
		<xsl:value-of select="concat(./@CodigoOrdem,';',./DataEmissao,';',./DataPrevista,';',./CodigoFabricoProduto,';',./Quantidade,';',./Unidade,';',./Encomendas,';',./Estado)"/>
	</xsl:template>
</xsl:stylesheet>


