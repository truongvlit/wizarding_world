/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.crawler;

import truongvl.constants.Constants;
import truongvl.utils.HtmlUtils;

/**
 *
 * @author Nero
 */
public class ColorCrawler {
    public String crawl() throws Exception {
        return HtmlUtils.refineHtml(HtmlUtils.getHtmlFromUrl(Constants.COLOR_PAGE_URL));
    }
}
