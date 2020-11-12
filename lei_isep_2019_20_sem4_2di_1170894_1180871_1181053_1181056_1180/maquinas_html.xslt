<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
 		<body>
 			<h2>Maquinas</h2>
 			<table border="1">
 				<tr bgcolor="#9acd32">
					 <th>Codigo Interno Maquina</th>
                     <th>Descricao de Categoria</th>
                     <th>Numero Serie</th>
                     <th>Descricao Maquina</th>
                     <th>Marca</th>
                     <th>Modelo</th>
                     <th>IdProtocolo</th>
				 </tr>
				<xsl:apply-templates select="ChaoDeFabrica">
				</xsl:apply-templates>
			 </table>
 		</body>
 	</html>
</xsl:template>

	<xsl:template match="Maquina">
	<tr>
		<td><xsl:value-of select="@CodigoInternoMaquina"/></td>
		<td><xsl:value-of select="NumeroSerie"/></td>
		<td><xsl:value-of select="DescricaoMaquina"/></td>
		<td><xsl:value-of select="DataInstalacao"/></td>
		<td><xsl:value-of select="Marca"/></td>
		<td><xsl:value-of select="Modelo"/></td>
		<td><xsl:value-of select="IdProtocolo"/></td>
	</tr>
	</xsl:template>

</xsl:stylesheet>