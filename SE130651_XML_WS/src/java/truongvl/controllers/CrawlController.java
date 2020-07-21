/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongvl.analyzer.Analyzer;
import truongvl.clients.ColorClient;
import truongvl.clients.ColorOfProductClient;
import truongvl.clients.HouseClient;
import truongvl.clients.MovieCharacterClient;
import truongvl.clients.ProductClient;
import truongvl.constants.Constants;
import truongvl.crawler.ColorCrawler;
import truongvl.crawler.HarryPotterPlatform934Crawler;
import truongvl.crawler.HarryPotterShopCrawler;
import truongvl.dtos.Color;
import truongvl.dtos.ColorOfProduct;
import truongvl.dtos.Colors;
import truongvl.dtos.House;
import truongvl.dtos.Houses;
import truongvl.dtos.MovieCharacter;
import truongvl.dtos.MovieCharacters;
import truongvl.dtos.Product;
import truongvl.dtos.Products;
import truongvl.staxreader.ColorReader;
import truongvl.staxreader.HouseReader;
import truongvl.staxreader.MovieCharacterReader;
import truongvl.staxreader.ProductReader;
import truongvl.utils.XMLUtils;
import truongvl.xslapplier.XSLApplier;

/**
 *
 * @author Nero
 */
public class CrawlController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "home";
        try {
            // XSL path declaration
            String hpsXslPath = getServletContext().getRealPath(Constants.HARRY_POTTER_SHOP_XSL_PATH);
            String hppXslPath = getServletContext().getRealPath(Constants.HARRY_POTTER_PLATFORM_XSL_PATH);
            String characterXslPath = getServletContext().getRealPath(Constants.CHARACTER_XSL_PATH);
            String houseXslPath = getServletContext().getRealPath(Constants.HOUSE_XSL_PATH);
            String colorXslPath = getServletContext().getRealPath(Constants.COLOR_XSL_PATH);
            
            // XSD path declaration
            String hpsXsdPath = getServletContext().getRealPath(Constants.HARRY_POTTER_SHOP_XSD_PATH);
            String hppXsdPath = getServletContext().getRealPath(Constants.HARRY_POTTER_PLATFORM_XSD_PATH);
            String houseXsdPath = getServletContext().getRealPath(Constants.HOUSE_XSD_PATH);
            String characterXsdPath = getServletContext().getRealPath(Constants.CHARACTER_XSD_PATH);
            String colorXsdPath = getServletContext().getRealPath(Constants.COLOR_XSD_PATH);
            
            // DTD path declaration
            String hpsDtdPath = getServletContext().getRealPath(Constants.HARRY_POTTER_SHOP_DTD_PATH);
            
            // CRAWL html
            HarryPotterShopCrawler hpsCrawler = new HarryPotterShopCrawler();
            HarryPotterPlatform934Crawler hppCrawler = new HarryPotterPlatform934Crawler();
            ColorCrawler colorCrawler = new ColorCrawler();
            String hpsXmlString = hpsCrawler.crawl();
            String hpfXmlString = hppCrawler.crawl();
            String colorString = colorCrawler.crawl();
            String characterAndHouseString = hpsCrawler.crawlCharacterAndHouse();
            
            // APPLY XSL
            XSLApplier applier = new XSLApplier();
            ByteArrayInputStream hpsProductStreamWithDtd = new ByteArrayInputStream(applier.applyStylesheetWithDtd(hpsDtdPath, hpsXslPath, hpsXmlString).toByteArray());
            ByteArrayInputStream colorStreamWithXsd = new ByteArrayInputStream(applier.applyStylesheet(colorXslPath, colorString).toByteArray());
            ByteArrayInputStream characterStreamWithXsd = new ByteArrayInputStream(applier.applyStylesheet(characterXslPath, characterAndHouseString).toByteArray());
            ByteArrayInputStream houseStreamWithXsd = new ByteArrayInputStream(applier.applyStylesheet(houseXslPath, characterAndHouseString).toByteArray());
            ByteArrayInputStream hpfProductStreamWithXsd = new ByteArrayInputStream(applier.applyStylesheet(hppXslPath, hpfXmlString).toByteArray());

//            // VALIDATE
            boolean isProductStreamValidated = XMLUtils.validateXMLUsingDtd(hpsProductStreamWithDtd);
            boolean isColorStreamValidated = XMLUtils.validateXMLUsingXsd(colorXsdPath, colorStreamWithXsd);
            boolean isCharacterStreamValidated = XMLUtils.validateXMLUsingXsd(characterXsdPath, characterStreamWithXsd);
            boolean isHouseStreamValidated = XMLUtils.validateXMLUsingXsd(houseXsdPath, houseStreamWithXsd);
            boolean isHpfProductStreamValidated = XMLUtils.validateXMLUsingXsd(hppXsdPath, hpfProductStreamWithXsd);

            
            // CREATE client instances
            ColorClient colorClient = new ColorClient();
            ProductClient productClient = new ProductClient();
            ColorOfProductClient colorOfProductClient = new ColorOfProductClient();
            HouseClient houseClient = new HouseClient();
            MovieCharacterClient movieCharacterClient = new MovieCharacterClient();
            
            // INSERT INTO DB
            // TABLE Color
            if (isColorStreamValidated) {
                // must create another stream because the created stream was handled
                ByteArrayInputStream colorStream = new ByteArrayInputStream(applier.applyStylesheet(colorXslPath, colorString).toByteArray());
                ColorReader colorReader = new ColorReader();
                List<Color> list = colorReader.getColors(colorStream, colorXsdPath);
                list.forEach((t) -> {
                    t.setId(0);
                    colorClient.create_XML(t);
                });
            }
            
            // TABLE Character
            if (isCharacterStreamValidated) {
                // must create another stream because the created stream was handled
                ByteArrayInputStream characterStream = new ByteArrayInputStream(applier.applyStylesheet(characterXslPath, characterAndHouseString).toByteArray());
                MovieCharacterReader movieCharacterReader = new MovieCharacterReader();
                List<MovieCharacter> list = movieCharacterReader.getCharacters(characterStream, characterXsdPath);
                list.forEach((t) -> {
                    t.setId(0);
                    movieCharacterClient.create_XML(t);
                });
            }
            
            // TABLE House
            if (isHouseStreamValidated) {
                // must create another stream because the created stream was handled
                ByteArrayInputStream houseStream = new ByteArrayInputStream(applier.applyStylesheet(houseXslPath, characterAndHouseString).toByteArray());
                HouseReader houseReader = new HouseReader();
                List<House> list = houseReader.getHouses(houseStream, houseXsdPath);
                list.forEach((t) -> {
                    t.setId(0);
                    houseClient.create_XML(t);
                });
            }

            // TABLE Product
            Analyzer analyzer = new Analyzer();
            MovieCharacters movieCharacters = (MovieCharacters) XMLUtils.unmarshal(new ByteArrayInputStream(movieCharacterClient.findAll_XML(String.class).getBytes()), MovieCharacters.class);
            Houses houses = (Houses) XMLUtils.unmarshal(new ByteArrayInputStream(houseClient.findAll_XML(String.class).getBytes()), Houses.class);
            if (isProductStreamValidated) {
                // must create another stream because the created stream was handled
                ByteArrayInputStream hpsProductStream = new ByteArrayInputStream(applier.applyStylesheet(hpsXslPath, hpsXmlString).toByteArray());
                ProductReader productReader = new ProductReader();
                List<Product> list = productReader.getProducts(hpsProductStream, hpsXsdPath);
                list.forEach((t) -> {
                    t.setId(0);
                    t.setPoint(0);
                    t.setCharacterId(analyzer.getCharacterOfProduct(t, movieCharacters));
                    t.setHouseId(analyzer.getHouseOfProduct(t, houses));
                    productClient.create_XML(t);
                });
            }

            if (isHpfProductStreamValidated) {
                ByteArrayInputStream hpfProductStream = new ByteArrayInputStream(applier.applyStylesheet(hppXslPath, hpfXmlString).toByteArray());
                ProductReader productReader = new ProductReader();
                List<Product> list = productReader.getProducts(hpfProductStream, hppXsdPath);
                list.forEach((t) -> {
                    t.setId(0);
                    t.setPoint(0);
                    t.setCharacterId(analyzer.getCharacterOfProduct(t, movieCharacters));
                    t.setHouseId(analyzer.getHouseOfProduct(t, houses));
                    productClient.create_XML(t);
                });
            }

            // TABLE ColorOfProduct
            Colors colors = (Colors) XMLUtils.unmarshal(new ByteArrayInputStream(colorClient.findAll_XML(String.class).getBytes()), Colors.class);
            Products products = (Products) XMLUtils.unmarshal(new ByteArrayInputStream(productClient.findAll_XML(String.class).getBytes()), Products.class);
            colors.getColors().forEach((t) -> {
                List<Product> productList = analyzer.getColorOfProduct(products, t);
                productList.forEach((product) -> {
                    ColorOfProduct colorOfProduct = new ColorOfProduct();
                    colorOfProduct.setId(0);
                    colorOfProduct.setProductId(product);
                    colorOfProduct.setColorId(t);
                    colorOfProductClient.create_XML(colorOfProduct);
                });
            });
        } catch (Exception e) {
            log("ERROR at CrawlController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
