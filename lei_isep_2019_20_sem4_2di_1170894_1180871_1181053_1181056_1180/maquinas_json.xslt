<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="utf-8"/>

    <xsl:template match="/">
        <xsl:text>{</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica)"/><xsl:text>" : {</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(ChaoDeFabrica/Maquina)"/><xsl:text>" : [</xsl:text>
        <xsl:apply-templates select="ChaoDeFabrica" />
        <xsl:text>]</xsl:text>
        <xsl:text>&#10;</xsl:text>
        <xsl:text>}</xsl:text>
        <xsl:text>}</xsl:text>
    </xsl:template>

    <xsl:template match="Maquina">
        <xsl:text>{</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(@CodigoInternoMaquina)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="@CodigoInternoMaquina" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(NumeroSerie)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="NumeroSerie" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(DescricaoMaquina)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="DescricaoMaquina" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(Marca)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="Marca" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(Modelo)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="Modelo" /> <xsl:text>"</xsl:text>
        <xsl:text> , </xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="name(IdProtocolo)" /><xsl:text>" : </xsl:text><xsl:text>"</xsl:text><xsl:value-of select="IdProtocolo" /> <xsl:text>"</xsl:text>

        <xsl:text>}</xsl:text>
        <xsl:if test="following-sibling::*">
            <xsl:text> , </xsl:text>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>