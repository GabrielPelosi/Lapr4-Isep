<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
	<xsl:template match="/">
		<html>
			<body>
				<h2>Linhas de Producao</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Id linha de producao</th>
						<th>Codigos Internos das Maquinas</th>
						<th>Estado do Processamento</th>
						<th>Data do Ultimo Processamento</th>
					</tr>
					<!-- para cada linha de producao -->
					<xsl:apply-templates select="ChaoDeFabrica" />
				</table>

			</body>
		</html>
	</xsl:template>
	<xsl:template match="LinhaProducao">
		<tr>
			<td>
				<xsl:value-of select="./@IdLinhaProducao" />
			</td>
			<td>
				<xsl:value-of select="./CodigoInternoMaquina" />
			</td>
			<td>
				<xsl:value-of select="./EstadoProcessamento" />
			</td>
			<td>
				<xsl:value-of select="./DataUltimoProcessamento" />
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>