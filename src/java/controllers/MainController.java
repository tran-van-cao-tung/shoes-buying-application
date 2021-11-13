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

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String HOME_PAGE = "home.jsp";
    private final String STAFF_PAGE = "staffPage.jsp";
    private final String LOGIN_CONTROLLER = "LoginController";
    private final String STAFF_SEARCH_CONTROLLER = "StaffSearchController";
    private final String STAFF_DELETE_CONTROLLER = "StaffDeleteController";
    private final String STAFF_UPDATE_CONTROLLER = "StaffUpdateController";
    private final String STAFF_INSERT_CONTROLLER = "StaffInsertController";
    private final String HOME_CONTROLLER = "HomeController";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String STAFF_INSERT_PAGE = "staffInsert.jsp";
    private final String CREATE_USERS_CONTROLLER = "userCreateController";
    private final String USER_SEARCH_CONTROLLER = "UserSearchController";
    private final String USER_REMOVE_CONTROLLER = "UserRemoveController";
    private final String USER_UPDATE_CONTROLLER = "UserUpdateController";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    private final String BUY_CONTROLLER = "BuyController";
    private final String CHECK_ORDER_CONTROLLER = "CheckOrderController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("btAction");
        String url = HOME_CONTROLLER;
        try {
            if (action == null) {
                url = HOME_CONTROLLER;
            } else if (action.equals("Sign in")) {
                url = LOGIN_PAGE;
            } else if (action.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (action.equals("Search")) {
                url = STAFF_SEARCH_CONTROLLER;
            } else if (action.equals("Delete")) {
                url = STAFF_DELETE_CONTROLLER;
            } else if (action.equals("Update")) {
                url = STAFF_UPDATE_CONTROLLER;
            } else if (action.equals("Insert")) {
                url = STAFF_INSERT_PAGE;
            } else if (action.equals("Back to Page")) {
                url = STAFF_PAGE;
            } else if (action.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals("Create")) {
                url = CREATE_USERS_CONTROLLER;
            } else if (action.equals("Search products")) {
                url = USER_SEARCH_CONTROLLER;
            } else if (action.equals("AddToCart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (action.equals("Remove")) {
                url = USER_REMOVE_CONTROLLER;
            } else if (action.equals("Update cart")) {
                url = USER_UPDATE_CONTROLLER;
            } else if (action.equals("Buy")) {
                url = BUY_CONTROLLER;
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
