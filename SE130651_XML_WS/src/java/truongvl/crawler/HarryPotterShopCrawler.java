/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.crawler;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import truongvl.constants.Constants;
import truongvl.utils.HtmlUtils;
import truongvl.utils.XMLUtils;

/**
 *
 * @author Nero
 */
public class HarryPotterShopCrawler {
    public String crawl() throws Exception {
        String htmlPage = "<root>";
        String homePage = HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(Constants.HARRY_POTTER_SHOP_URL));
        // create an xPath instance
        XPath xPath = XMLUtils.createXPath();
        // get all category links
        List<String> categoryLinks = new ArrayList<>();
        String categoryLinksExp = ".//*[contains(@class, 'menu-navigation-section') and not(contains(@class,'level'))]/div/ul/li/a";
        NodeList nodeList = (NodeList) xPath.evaluate(categoryLinksExp, new InputSource(new StringReader(homePage)), XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            categoryLinks.add(node.getAttributes().getNamedItem("href").getNodeValue());
        }
        // for loop access to every link to get products
        for (String link : categoryLinks) {
            String categoryLink = Constants.HARRY_POTTER_SHOP_URL + link;
            String categoryPage = HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(categoryLink));
            // get max page
            String maxPageExp = ".//li[@class='pagination__text' and ancestor::*[@class='top-pagination']][3]/a";
            Node maxPageNode = (Node) xPath.evaluate(maxPageExp, new InputSource(new StringReader(categoryPage)), XPathConstants.NODE);
            int maxPage = maxPageNode == null ? 1 : Integer.parseInt(maxPageNode.getTextContent());
            // traverse each page to get product link
            int currentPage = 1;
            String productLinksExp = ".//*[@class='grid__item grid__item--collection-template small--one-half medium-up--one-third']/div/a[1]";
            String categoryWithPagingLink, categoryWithPagingPage, productLink, productPage;
            do { // do while loop to get product link
                categoryWithPagingLink = categoryLink + "?page=" + currentPage;
                categoryWithPagingPage = HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(categoryWithPagingLink));
                nodeList = (NodeList) xPath.evaluate(productLinksExp, new InputSource(new StringReader(categoryWithPagingPage)), XPathConstants.NODESET);
                for (int i = 0; i < nodeList.getLength(); i++) { // for loop to get product information
                    try { // try catch block to avoid null exceptions
                        Node node = nodeList.item(i);
                        productLink = Constants.HARRY_POTTER_SHOP_URL + node.getAttributes().getNamedItem("href").getNodeValue();
                        productPage = HtmlUtils.getMainContent(HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(productLink)));
                        htmlPage += productPage;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } // end try catch block
                } // end get product information for loop
            } while (++currentPage <= maxPage); // end do while loop
        } // end for loop
        htmlPage += "</root>";
        return htmlPage;
    }
    
    public String crawlCharacterAndHouse() throws Exception {
        return HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(Constants.HARRY_POTTER_SHOP_URL));
    }
}
