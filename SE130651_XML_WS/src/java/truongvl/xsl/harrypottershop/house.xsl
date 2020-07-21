<?xml version="1.0" encoding="UTF-8"?>
<!--
    Document   : product.xsl
    Created on : June 28, 2020, 2:53 PM
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
    <xsl:strip-space elements="*"/>
    <xsl:template match="/">
        <xsl:element name="houses">
            <xsl:for-each select=".//*[@class='secondary-navigation-slideout-container slideout--hogwarts-houses']/div/div/a/span">
                <xsl:element name="house">
                    <xsl:element name="name">
                        <xsl:value-of select="text()"/>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
