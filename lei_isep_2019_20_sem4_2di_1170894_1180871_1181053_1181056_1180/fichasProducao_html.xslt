<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Fichas de Producao</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>CodigoFichaProducao</th>
                        <th>FichaMateria</th>
                        <th>FichaQuantidade</th>
                        <th>FichaUnidade</th>
                    </tr>
                    <!-- para cada ordem -->
                    <xsl:apply-templates select="ChaoDeFabrica" />
                </table>
            </body>
        </html>

    </xsl:template>
    <xsl:template match="FichaProducao">
        <tr>
            <td><xsl:value-of select="./@CodigoFichaProducao"/></td>
            <td><xsl:value-of select="./FichaMateria"/></td>
            <td><xsl:value-of select="./FichaQuantidade"/></td>
            <td><xsl:value-of select="./FichaUnidade"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>