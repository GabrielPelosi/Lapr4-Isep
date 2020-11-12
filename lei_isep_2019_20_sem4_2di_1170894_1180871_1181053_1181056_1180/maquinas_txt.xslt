<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
	<xsl:template match="/">
		<xsl:text>Codigo Interno Maquina;Descricao de Categoria;Numero Serie;Descricao Maquina;Marca;Modelo;IdProtocolo</xsl:text>
		<xsl:apply-templates select="ChaoDeFabrica" />
	</xsl:template>

	<xsl:template match="Maquina">
		<xsl:value-of select="concat(@CodigoInternoMaquina,';',NumeroSerie,';',DescricaoMaquina,';',DataInstalacao,';',Marca,';',Modelo,';',IdProtocolo,';')"/>
	</xsl:template>

</xsl:stylesheet>