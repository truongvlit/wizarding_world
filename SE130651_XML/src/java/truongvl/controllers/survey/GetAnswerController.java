/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.controllers.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongvl.analyzer.Analyzer;
import truongvl.clients.ColorOfProductClient;
import truongvl.clients.ProductClient;
import truongvl.dtos.Answer;
import truongvl.dtos.ColorOfProducts;
import truongvl.dtos.Product;
import truongvl.dtos.Products;
import truongvl.dtos.Question;

/**
 *
 * @author Nero
 */
public class GetAnswerController extends HttpServlet {
    
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "result.jsp";
    private static final String INVALID = "survey";
    
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
            String[] questionIds = request.getParameterValues("questions");
            String[] targetedValues = request.getParameterValues("target");
            String[] points = request.getParameterValues("point");
            String[] answerArray;
            ArrayList<Answer> answers;
            HashMap<Question, List<Answer>> map = new HashMap<>();
            for (int i = 0; i < questionIds.length; i++) {
                Question question = new Question();
                answerArray = request.getParameterValues(questionIds[i]);
                if (answerArray == null) {
                    url = INVALID;
                    break;
                } else {
                    List<String> tmp = Arrays.asList(answerArray);
                    answers = new ArrayList<>();
                    for (String ans : tmp) {
                        Answer answer = new Answer();
                        answer.setContent(ans);
                        answer.setPoint(Integer.parseInt(points[i]));
                        answers.add(answer);
                    }
                    question.setId(Integer.parseInt(questionIds[i]));
                    question.setTargetedAttribute(Integer.parseInt(targetedValues[i]));
                    map.put(question, answers);
                }
            }
            if (!url.equals(INVALID)) {
                ProductClient productClient = new ProductClient();
                Products products = productClient.findAllOfUser(Products.class, (String) request.getSession().getAttribute("USER"));
                ColorOfProductClient colorOfProductClient = new ColorOfProductClient();
                ColorOfProducts colorOfProducts = colorOfProductClient.findAll_XML(ColorOfProducts.class);
                Analyzer analyzer = new Analyzer();
                ArrayList<Product> list = analyzer.analyzeProducts(products, colorOfProducts, map);
                request.setAttribute("INFO", list);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("ERROR at GetAnswerController: " + e.getMessage());
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
