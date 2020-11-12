<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="utf-8"/>

    <xsl:template match="/">
        <xsl:text>{</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica)"/><xsl:text>" : {</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica/Categoria)"/><xsl:text>" : [</xsl:text>
        <xsl:apply-templates select="ChaoDeFabrica" />
        <xsl:text>]</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>}</xsl:text>
        <xsl:text>}</xsl:text>
    </xsl:template>

    <xsl:template match="Categoria">
        <xsl:text>{</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(@CodigoCategoria)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="@CodigoCategoria" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(DescricaoCategoria)" /><xsl:text>" : </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="DescricaoCategoria" /> <xsl:text>"</xsl:text>
        <xsl:text>}</xsl:text>
        <xsl:if test="following-sibling::*">
            <xsl:text> , </xsl:text>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>