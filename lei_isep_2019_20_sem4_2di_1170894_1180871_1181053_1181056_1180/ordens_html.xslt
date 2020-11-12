<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
<xsl:template match="/">
	<html>
 		<body>
 			<h2>Ordens de Producao</h2>
 			<table border="1">
 				<tr bgcolor="#9acd32">
					 <th>Codigo de Ordem</th>
                     <th>Data Emissao</th>
                     <th>Data Prevista</th>
                     <th>Codigo de Fabrico de Produto</th>
                     <th>Quantidade</th>
                     <th>Unidade</th>
                     <th>Encomendas</th>
                     <th>Estado</th>
				 </tr>
				<!-- para cada ordem -->
				<xsl:apply-templates select="ChaoDeFabrica" />
			 </table>
 		</body>
 	</html>

</xsl:template>
	<xsl:template match="Ordem">
	<tr>
		<td><xsl:value-of select="./@CodigoOrdem"/></td>
		<td><xsl:value-of select="./DataEmissao"/></td>
		<td><xsl:value-of select="./DataPrevista"/></td>
		<td><xsl:value-of select="./CodigoFabricoProduto"/></td>
		<td><xsl:value-of select="./Quantidade"/></td>
		<td><xsl:value-of select="./Unidade"/></td>
		<td><xsl:value-of select="./Encomendas"/></td>
		<td><xsl:value-of select="./Estado"/></td>
	</tr>
	</xsl:template>
</xsl:stylesheet>


