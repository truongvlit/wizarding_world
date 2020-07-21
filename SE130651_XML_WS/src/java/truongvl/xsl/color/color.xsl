<?xml version="1.0" encoding="UTF-8"?>
<!--
    Document   : color.xsl
    Created on : July 2, 2020, 10:51 PM
    Author     : Nero
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="xml" 
                omit-xml-declaration="yes" 
                indent="yes"
    />
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:strip-space elements="*"/>
    <xsl:template match="/">
        <xsl:element name="colors">
            <xsl:for-each select=".//table[@class='wikitable sortable']/tbody/tr/td/a">
                <xsl:element name="color">
                    <xsl:element name="name">
                        <xsl:value-of select="text()"/>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
