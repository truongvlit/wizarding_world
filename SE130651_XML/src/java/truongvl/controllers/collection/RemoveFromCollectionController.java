/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.controllers.collection;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongvl.clients.ChoiceOfUserClient;

/**
 *
 * @author Nero
 */
public class RemoveFromCollectionController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "my-collection";
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
        String url  = ERROR;
        try {
            String userId = (String) request.getSession().getAttribute("USER");
            String productId = request.getParameter("id");
            ChoiceOfUserClient client = new ChoiceOfUserClient();
            String id = client.findByUserIdAndProductId(userId, productId);
            if (id == null || id.isEmpty()) {
                // do nothing
            } else {
                Integer result = client.delete(Integer.class, id);
                if (result == 1) {
                    request.setAttribute("RESULT", "The product is already removed from your collection");
                } else if (result == 0) {
                    request.setAttribute("RESULT", "This product was deleted before");
                }
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("ERROR at RemoveFromCollectionController: " + e.getMessage());
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
