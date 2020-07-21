/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.controllers.collection;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongvl.clients.AccountClient;
import truongvl.clients.ChoiceOfUserClient;
import truongvl.clients.ProductClient;
import truongvl.dtos.Account;
import truongvl.dtos.ChoiceOfUser;
import truongvl.dtos.Product;

/**
 *
 * @author Nero
 */
public class AddToCollectionController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "result.jsp";
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
        String url = ERROR;
        try {
            String productId = request.getParameter("id");
            String userId = (String) request.getSession().getAttribute("USER");
            ChoiceOfUserClient choiceOfUserClient = new ChoiceOfUserClient();
            ProductClient productClient = new ProductClient();
            AccountClient accountClient = new AccountClient();
            Product product = productClient.find_XML(Product.class, productId);
            Account account = accountClient.find_XML(Account.class, userId);
            
            product.setPoint(product.getPoint() + 1);
            ChoiceOfUser choiceOfUser = new ChoiceOfUser(0, account, product);
            choiceOfUserClient.create_XML(choiceOfUser);
            productClient.edit_XML(product, product.getId().toString());
            System.out.println("54");
            url = SUCCESS;
        } catch (Exception e) {
            log("ERROR at AddToCollectionController: " + e.getMessage());
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
