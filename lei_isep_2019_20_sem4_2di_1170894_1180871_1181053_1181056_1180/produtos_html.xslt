<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
 		<body>
 			<h2>Produtos</h2>
 			<table border="1">
 				<tr bgcolor="#9acd32">
					 <th>Codigo Fabrico</th>
                     <th>Codigo Comercial</th>
                     <th>Descricao Breve</th>
                     <th>Descricao Completa</th>
                     <th>Unidade</th>
                     <th>Categoria</th>
				 </tr>
				<xsl:apply-templates select="ChaoDeFabrica" />
			 </table>
 		</body>
     </html>
</xsl:template>
	<xsl:template match="Produto">
		<tr>
			<td><xsl:value-of select="./@CodigoFabrico"/></td>
			<td><xsl:value-of select="./CodigoComercial"/></td>
			<td><xsl:value-of select="./DescricaoBreve"/></td>
			<td><xsl:value-of select="./DescricaoCompleta"/></td>
			<td><xsl:value-of select="./Unidade"/></td>
			<td><xsl:value-of select="./Categoria"/></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
