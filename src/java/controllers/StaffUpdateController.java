/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tbl_Shoes.Tbl_ShoesDAO;
import tbl_Shoes.Tbl_ShoesDTO;

/**
 *
 * @author Admin
 */
public class StaffUpdateController extends HttpServlet {

    private final String SUCESS = "staffPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCESS;
        try {
            // Get parameter from staffpage.jsp
            String shoesId = request.getParameter("shoesId");
            float price = Float.parseFloat(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            boolean notSale = request.getParameter("notSale")!= null;
            String current_Search = request.getParameter("current_Search");
            //store parameter to shoes
            Tbl_ShoesDTO shoes = new Tbl_ShoesDTO(shoesId, null, price, null, quantity, null, notSale);
            // Call DAO for update
            Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
            shoesDAO.update(shoes);
            request.setAttribute("STATEMENT_UPDATE", "Update success");
            // Send to STAFF_SEARCH_CONTROLLER current search value 
            url = "MainController"
                    + "?btAction=Search"
                    + "&searchValue=" + URLEncoder.encode(current_Search, "UTF-8");
        } catch (Exception e) {
            log("loi tai staff update controller" + e.toString());
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
