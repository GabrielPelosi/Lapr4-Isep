<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
	<xsl:template match="/">
		<html>
			<body>
				<h2>Categorias de Materias-primas</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Codido de Categoria</th>
						<th>Descricao de Categoria</th>
					</tr>
					<!-- para cada categoria -->
					<xsl:apply-templates select="ChaoDeFabrica" />
				</table>

			</body>
		</html>
	</xsl:template>
	<xsl:template match="Categoria">
		<tr>
			<td>
				<xsl:value-of select="@CodigoCategoria" /> <!-- ir buscar o código da categoria-->
			</td>
			<td>
				<xsl:value-of select="DescricaoCategoria" /> <!-- o valor da categoria -->
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>
