<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Depositos</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>CodigoFichaProducao</th>
                        <th>Descricao do Deposito</th>
                        <th>Materias e quantidades</th>
                        <th>Produtos e Quantidades</th>
                    </tr>
                    <!-- para cada ordem -->
                    <xsl:apply-templates select="ChaoDeFabrica" />
                </table>
            </body>
        </html>

    </xsl:template>
    <xsl:template match="Deposito">
        <tr>
            <td><xsl:value-of select="./@CodigoDeposito"/></td>
            <td><xsl:value-of select="./DescricaoDeposito"/></td>
            <td><xsl:value-of select="./MateriasQuantidades"/></td>
            <td><xsl:value-of select="./ProdutosQuantidades"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>