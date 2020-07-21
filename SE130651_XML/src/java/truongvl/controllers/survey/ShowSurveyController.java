/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.controllers.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truongvl.clients.AnswerClient;
import truongvl.clients.QuestionClient;
import truongvl.dtos.Answer;
import truongvl.dtos.Answers;
import truongvl.dtos.Question;
import truongvl.dtos.Questions;

/**
 *
 * @author Nero
 */
public class ShowSurveyController extends HttpServlet {

    private static final String SUCCESS = "survey.jsp";
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
            HashMap<Question, ArrayList<Answer>> map = new HashMap<>();
            QuestionClient questionClient = new QuestionClient();
            AnswerClient answerClient = new AnswerClient();
            Questions questions = questionClient.findAll_XML(Questions.class);
            Answers answers = answerClient.findAll_XML(Answers.class);
            questions.getQuestion().forEach((question) -> {
                map.put(question, new ArrayList<>());
                answers.getAnswer().forEach((answer) -> {
                    if (answer.getQuestionId().getId().intValue() == question.getId().intValue()) {
                        ArrayList<Answer> tmp = map.get(question);
                        tmp.add(answer);
                        map.put(question, tmp);
                    }
                });
            });
            request.setAttribute("INFO", map);
            url = SUCCESS;
        } catch (Exception e) {
            log("ERROR at ShowSurveyController: " + e.getMessage());
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
