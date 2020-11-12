<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <xsl:text>Codigo Interno Materia;Descricao da Materia;Materias e quantidades;Produtos e Quantidades</xsl:text>
        <xsl:apply-templates select="ChaoDeFabrica" />
    </xsl:template>
    <xsl:template match="FichaProducao">
        <xsl:value-of select="concat(./@CodigoDeposito,'|',./DescricaoDeposito,'|',./MateriasQuantidades,'|',./ProdutosQuantidades)"/>
    </xsl:template>
</xsl:stylesheet>