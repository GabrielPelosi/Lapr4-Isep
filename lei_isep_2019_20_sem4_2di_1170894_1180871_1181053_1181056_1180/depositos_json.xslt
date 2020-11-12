<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="utf-8"/>

    <xsl:template match="/">
        <xsl:text>{</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica)"/><xsl:text>" : {</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica/Deposito)"/><xsl:text>" : [</xsl:text>
        <xsl:apply-templates select="ChaoDeFabrica" />
        <xsl:text>]</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>}</xsl:text>
        <xsl:text>}</xsl:text>
    </xsl:template>

    <xsl:template match="Deposito">
        <xsl:text>{</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(@CodigoDeposito)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="@CodigoDeposito" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(DescricaoDeposito)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="DescricaoDeposito" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(MateriasQuantidades)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="MateriasQuantidades" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ProdutosQuantidades)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="ProdutosQuantidades" /> <xsl:text>"</xsl:text>
        <xsl:text>}</xsl:text>
        <xsl:if test="following-sibling::*">
            <xsl:text> , </xsl:text>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>