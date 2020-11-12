<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
    <xsl:template match="/">
        <xsl:text>Id Linha Producao;Codigos Internos Maquinas;Estado Processamento;Data Ultimo Processamento</xsl:text>
        <xsl:apply-templates select="ChaoDeFabrica" />
    </xsl:template>
    <xsl:template match="LinhaProducao">
        <xsl:value-of select="concat(./@IdLinhaProducao,';',./CodigoInternoMaquina,';',./EstadoProcessamento,';',./DataUltimoProcessamento)"/>
    </xsl:template>
</xsl:stylesheet>
