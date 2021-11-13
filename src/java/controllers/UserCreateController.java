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
public class UserCreateController extends HttpServlet {

    private final String SUCCESS = "login.jsp";
    private final String FAIL = "userCreate.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            // Get parameter from userCreate.jsp
            String userId = request.getParameter("userId").trim();
            String password = request.getParameter("password").trim();
            String fullName = request.getParameter("fullName").trim();
            String phone = request.getParameter("phone").trim();
            String address = request.getParameter("address").trim();
            int role = 1;
            // Call DAO check exist id
            Tbl_UsersDAO userDAO = new Tbl_UsersDAO();
            boolean checkExist = userDAO.getUserId(userId);
            boolean checkAllCorrect = true;
            boolean check = false;
            HttpSession session = request.getSession();
            // Save current input 
            request.setAttribute("CURRENT_ID", userId);
            request.setAttribute("CURRENT_PASSWORD", password);
            request.setAttribute("CURRENT_FULLNAME", fullName);
            request.setAttribute("CURRENT_PHONE", phone);
            request.setAttribute("CURRENT_ADDRESS", address);
            // Validation user input
            if (userId.isEmpty() || userId.equals(" ")) {
                request.setAttribute("ID_USER_ERROR", "No blankspace");
                checkAllCorrect = false;
            }
            if (fullName.isEmpty() || fullName.equals(" ")) {
                request.setAttribute("FULLNAME_USER_ERROR", "No blankspace");
                checkAllCorrect = false;
            }
            if (phone.isEmpty() || phone.equals(" ")) {
                request.setAttribute("PHONE_USER_ERROR", "No blankspace");
                checkAllCorrect = false;
            }
            if (address.isEmpty() || address.equals(" ")) {
                request.setAttribute("ADDRESS_USER_ERROR", "No blankspace");
                checkAllCorrect = false;
            }
            if (password.isEmpty() || password.equals(" ")) {
                request.setAttribute("PASSWORD_USER_ERROR", "No blankspace");
                checkAllCorrect = false;
            }
            if (checkExist == true) {
                request.setAttribute("ID_USER_ERROR", "user id exist");
                request.removeAttribute("CURRENT_ID");
                checkAllCorrect = false;
            }
            if (password.length() > 10) {
                request.setAttribute("PASSWORD_USER_ERROR", "password length <= 10");
                request.removeAttribute("CURRENT_PASSWORD");
                checkAllCorrect = false;
            }
            if (phone.length() != 10){
                request.setAttribute("PHONE_USER_ERROR", "phone number length = 10");
                request.removeAttribute("CURRENT_PHONE");
                checkAllCorrect = false;
            }
            // Save to DB
            if (checkAllCorrect == true) {
                Tbl_UsersDTO user = new Tbl_UsersDTO(userId, password, fullName, Integer.parseInt(phone), address, role);
                check = userDAO.createUser(user);
                url = SUCCESS;
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
