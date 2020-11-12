<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="utf-8"/>

    <xsl:template match="/">
        <xsl:text>{</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica)"/><xsl:text>" : {</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica/Ordem)"/><xsl:text>" : [</xsl:text>
        <xsl:apply-templates select="ChaoDeFabrica" />
        <xsl:text>]</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>}</xsl:text>
        <xsl:text>}</xsl:text>
    </xsl:template>

    <xsl:template match="Ordem">
        <xsl:text>{</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(@CodigoOrdem)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="@CodigoOrdem" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(DataEmissao)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="DataEmissao" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(DataPrevista)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="DataPrevista" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(CodigoFabricoProduto)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="CodigoFabricoProduto" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(Quantidade)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="Quantidade" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(Unidade)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="Unidade" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(Encomendas)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="Encomendas" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(Estado)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="Estado" /> <xsl:text>"</xsl:text>

        <xsl:text>}</xsl:text>
        <xsl:if test="following-sibling::*">
            <xsl:text> , </xsl:text>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>