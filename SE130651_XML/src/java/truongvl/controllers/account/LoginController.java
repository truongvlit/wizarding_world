/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.controllers.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import truongvl.clients.AccountClient;

/**
 *
 * @author Nero
 */
public class LoginController extends HttpServlet {

    private static final String SUCCESS = "home";
    private static final String INVALID = "login.jsp";
    private static final String ERROR = "error.jsp";
    
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
            // GET parameters
            String id = request.getParameter("username");
            String password = request.getParameter("password");
            // VALIDATE
            if (id.isEmpty() || password.isEmpty()) {
                request.setAttribute("MESSAGE", "Please fill in Username and Password");
                url = INVALID;
            } else {
                // CREATE clients instance
                AccountClient client = new  AccountClient();
                System.out.println("50");
                String result = client.checkLogin(id, password);
                System.out.println("52");
                if (result.equals("admin") || result.equals("user")) {
                    System.out.println("GOOD");
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", id);
                    session.setAttribute("ROLE", result);
                    url = SUCCESS;
                } else if (result.contains("getSingleResult() did not retrieve any entities.")) {
                    request.setAttribute("MESSAGE", "Username or Password wrong");
                    url = INVALID;
                } else {
                    request.setAttribute("MESSAGE", "Unexpected error happens. Please try again later.");
                    url = INVALID;
                }
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
