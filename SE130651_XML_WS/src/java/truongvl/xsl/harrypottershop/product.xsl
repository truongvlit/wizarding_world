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
            <xsl:for-each select=".//main">
                <xsl:element name="product">
                    <xsl:element name="category">
                        <xsl:value-of select=".//nav[@class='breadcrumb']/a[2]/text()"/>
                    </xsl:element>
                    <xsl:element name="name">
                        <xsl:value-of select=".//*[@class='product-single__title']/text()"/>
                    </xsl:element>
                    <xsl:element name="price">
                        <xsl:variable name="priceString" select="substring-after(.//*[@id='ProductPrice-product-template']/text(), '$')"/>
                        <xsl:choose>
                            <xsl:when test="not(string($priceString))">
                                <xsl:value-of select="0.0"/>
                            </xsl:when>
                            <xsl:when test="contains($priceString, '-')">
                                <xsl:value-of select="substring-before($priceString, '-')"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="$priceString"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:element>
                    <xsl:element name="note">
                        <xsl:for-each select=".//div[@class='meta-text']/p">
                            <xsl:value-of select="text()"/>
                            <xsl:text>&#xa;</xsl:text>
                        </xsl:for-each>
                    </xsl:element>
                    <xsl:element name="description">
                        <xsl:for-each select=".//div[@id='product-accordion']/div[1]/text()">
                            <xsl:value-of select="."/>
                            <xsl:text>&#xa;</xsl:text>
                        </xsl:for-each>
                    </xsl:element>
                    <xsl:element name="img">
                        <xsl:variable name="imgLink" select=".//*[@class='grid__item product-single__photos medium-up--one-half']/div/div/img/@src"/>
                        <xsl:value-of select="substring-before($imgLink, '_300x300')"/>
                        <xsl:value-of select="substring-after($imgLink, '_300x300')"/>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
