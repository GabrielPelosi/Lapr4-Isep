<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
 		<body>
 			<h2>MateriasPrimas</h2>
 			<table border="1">
 				<tr bgcolor="#9acd32">
					 <th>Codigo Interno Materia</th>
                     <th>Descricao da Materia</th>
                     <th>Categoria</th>
                     <th>Ficha Tecnica</th>
				 </tr>
				<xsl:apply-templates select="ChaoDeFabrica">
				</xsl:apply-templates>
			 </table>
 		</body>
 	</html>
</xsl:template>

	<xsl:template match="MateriaPrima">
		<tr>
			<td><xsl:value-of select="@CodigoInternoMateria"/></td>
			<td><xsl:value-of select="DescricaoMateria"/></td>
			<td><xsl:value-of select="Categoria"/></td>
			<td><xsl:value-of select="FichaTecnica"/></td>
		</tr>
	</xsl:template>

</xsl:stylesheet>