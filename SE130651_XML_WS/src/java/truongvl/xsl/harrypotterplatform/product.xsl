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
        <xsl:element name="products">
            <xsl:element name="product">
                <xsl:element name="category">
                    <xsl:value-of select=".//div[@class='product__wrapper group']/div/div/div/nav/ul/li[2]/a[1]/text()"/>
                </xsl:element>
                <xsl:element name="name">
                    <xsl:value-of select=".//*[@class='product__panel-top']/h1/text()"/>
                </xsl:element>
                <xsl:element name="price">
                    <xsl:variable name="priceString" select="substring-after(.//div[@class='product__price']/div/p/span/text(), '£')"/>
                    <xsl:choose>
                        <xsl:when test="not(string($priceString))">
                            <xsl:value-of select="0.0"/>
                        </xsl:when>
                        <xsl:when test="contains($priceString, 'USD')">
                            <xsl:value-of select="substring-before($priceString, 'USD')"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="$priceString"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:element>
                <xsl:element name="note">
                    <xsl:for-each select=".//div[@class='information__description']/p/em/text()">
                        <xsl:value-of select="."/>
                        <xsl:text>&#xa;</xsl:text>
                    </xsl:for-each>
                </xsl:element>
                <xsl:element name="description">
                    <xsl:for-each select=".//div[@class='information__description']/p/text()">
                        <xsl:value-of select="."/>
                        <xsl:text>&#xa;</xsl:text>
                    </xsl:for-each>
                </xsl:element>
                <xsl:element name="img">
                    <xsl:variable name="imgLink" select=".//div[@class='information__image']/img/@src"/>
                    <xsl:value-of select="$imgLink"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>