<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/">
	<xsl:text>Codigo Interno Materia;Descricao da Materia;Categoria;Ficha Tecnica</xsl:text>
	<xsl:apply-templates select="ChaoDeFabrica" />
</xsl:template>
	<xsl:template match="MateriaPrima">
	<xsl:value-of select="concat(./@CodigoInternoMateria,';',./DescricaoMateria,';',./Categoria,';',./FichaTecnica)"/>
	</xsl:template>
</xsl:stylesheet>