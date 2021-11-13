/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tbl_Users.Tbl_UsersDAO;
import tbl_Users.Tbl_UsersDTO;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private final String SUCCESS = "home.jsp";
    private final String FAIL = "login.jsp";
    private final String STAFF_PAGE = "staffPage.jsp";
    private final String USER_PAGE = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = SUCCESS;
        try {
            // Get parameter from login.jsp
            String userName = request.getParameter("userId");
            String pwd = request.getParameter("pwd");

            Tbl_UsersDAO userDAO = new Tbl_UsersDAO();
            boolean userExsit = userDAO.checkLogin(userName, pwd);
            // If session not exist then create
            HttpSession session = request.getSession(true);
            // user exist then go user's position          
            if (userExsit == true) {
                // get user
                Tbl_UsersDTO user = userDAO.getUser();
                if (user.getRole() == 0) {
                    url = STAFF_PAGE;
                } else if (user.getRole() == 1) {
                    url = USER_PAGE;
                }
                session.setAttribute("CURRENT_USER", user);
                // Add to cart without login
            } else if (userName == null && pwd == null) {

                url = FAIL;
            } else {
                if (userName.trim().equals(" ") || userName.trim().isEmpty() || pwd.trim().equals(" ") || pwd.trim().isEmpty()) {
                    request.setAttribute("LOGIN_ERROR", "No blankspace");
                } else {
                    request.setAttribute("LOGIN_ERROR", "Wrong user name or password!");
                }
                url = FAIL;
            }
        } catch (Exception e) {
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
