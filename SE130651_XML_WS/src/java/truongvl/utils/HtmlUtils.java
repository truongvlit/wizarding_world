/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import truongvl.xmlchecker.XMLSyntaxChecker;

/**
 *
 * @author Nero
 */
public class HtmlUtils {
    
    public static String refineHtml(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);
        XMLSyntaxChecker checker = new XMLSyntaxChecker();
        src = checker.check(src);
        src = getBody(src);
        return src;
    }
    
    public static String getMainContent(String src) {
        String expression = "<main.*?</main>";
        Matcher matcher = Pattern.compile(expression).matcher(src);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return src;
    }
    
    private static String getBody(String src) {
        //remove head fragment before getting body fragment
        Matcher matcher = Pattern.compile("<head.*?</head>").matcher(src);
        String result = src, tmp = "";
        if (matcher.find()) {
            tmp = matcher.group(0);
            result = result.replace(tmp, "");
        }
        // end remove head
        String expression = "<body.*?</body>";
        matcher = Pattern.compile(expression).matcher(result);
        if (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }
    
    public static String removeMiscellaneousTags(String src) {
        String result = src;
        // remove all script tags
        String expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");
        // remove all comments
        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");
        // remove all whitespace
        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");
        return result;
    }
    
    public static String getHtmlFromUrl(String aUrl) throws Exception {
        String content = "";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                @Override
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Access an https URL without having the certificate in the truststore
        URL url = new URL(aUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            content += inputLine;
        }
        br.close();
        return content.trim().replace("	", "");
    }
}
