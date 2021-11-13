/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tbl_OrderDetails.Tbl_OrderDetailsDAO;
import tbl_Shoes.Tbl_ShoesDAO;
import tbl_Shoes.Tbl_ShoesDTO;

/**
 *
 * @author Admin
 */
public class StaffDeleteController extends HttpServlet {

    private final String SUCCESS = "staffPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            // Get parameter from staffPage.jsp
            String shoesId = request.getParameter("shoesId");
            String current_Search = request.getParameter("current_Search");
            // Call dao to function delete by id
            Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
            Tbl_OrderDetailsDAO orderDetailsDAO = new Tbl_OrderDetailsDAO();
            // Check in orderDetails if shoesId exist
            boolean checkExsitOrder = orderDetailsDAO.checkOrderDetailsByShoesId(shoesId);
            if (checkExsitOrder == true) {
                request.setAttribute("STATEMENT_DELETE", "Cannot Delete because order still have");
            } else {
                boolean check = shoesDAO.deleteById(shoesId);
                request.setAttribute("STATEMENT_DELETE", "Delete success");
            }
            // Send to STAFF_SEARCH_CONTROLLER current search value 
            url = "MainController"
                    + "?btAction=Search"
                    + "&searchValue=" + URLEncoder.encode(current_Search, "UTF-8");
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
