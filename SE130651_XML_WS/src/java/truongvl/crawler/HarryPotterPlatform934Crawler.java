/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.crawler;

import java.io.StringReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import truongvl.constants.Constants;
import truongvl.utils.HtmlUtils;
import truongvl.utils.XMLUtils;

/**
 *
 * @author Nero
 */
public class HarryPotterPlatform934Crawler {
    public String crawl() throws Exception {
        String homePage = HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(Constants.HARRY_POTTER_PLATFORM_934_URL));
        XPath xPath = XMLUtils.createXPath();
        String categoryLinksExp = ".//*[@class='navigation__dropdown']/div/div/ul/li/a[1]";
        Node node = (Node) xPath.evaluate(categoryLinksExp, new InputSource(new StringReader(homePage)), XPathConstants.NODE);
        String link = node.getAttributes().getNamedItem("href").getNodeValue();
        String categoryLink = Constants.HARRY_POTTER_PLATFORM_934_URL + link;
        String categoryPage = HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(categoryLink));
        String productLinksExp = "(.//*[@class='collections__product']/div/div/a[1])[2]";
        Node productNode = (Node) xPath.evaluate(productLinksExp, new InputSource(new StringReader(categoryPage)), XPathConstants.NODE);
        String productLink = Constants.HARRY_POTTER_PLATFORM_934_URL + productNode.getAttributes().getNamedItem("href").getNodeValue();
        String productPage = HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(productLink));
        System.out.println(productPage);
        return productPage;
    }
}
