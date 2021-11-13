/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tbl_Shoes.Tbl_ShoesDAO;
import tbl_Shoes.Tbl_ShoesDTO;

/**
 *
 * @author Admin
 */
public class UserSearchController extends HttpServlet {

    private final String SUCCESS = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            // Get parameter from home.jsp
            String current_Search = request.getParameter("userSearch");
            // Validation input search
            boolean checkAllCorrect = true;
            String checkCurrent_Search = current_Search.trim();
            if (checkCurrent_Search.isEmpty() || checkCurrent_Search.equals(" ")) {
                request.setAttribute("INPUT_SEARCH_ERROR", "No blankspace!");
                url = SUCCESS;
                checkAllCorrect = false;
            }
            if (checkAllCorrect == true) {
                //Call shoesDAO function search id or name
                Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
                shoesDAO.searchIdAndName(current_Search);
                // Get id or name out
                ArrayList<Tbl_ShoesDTO> searchList = shoesDAO.getshoesList();
                ArrayList<Tbl_ShoesDTO> listSale = new ArrayList<>();
                //Show list that notSale = false
                for (Tbl_ShoesDTO shoes : searchList) {
                    if (shoes.isNotSale() == false) {
                        String shoesId = shoes.getShoesId();
                        String shoesName = shoes.getShoesName();
                        float price = shoes.getPrice();
                        String description = shoes.getDescription();
                        int quantity = shoes.getQuantity();
                        String image = shoes.getImage();
                        Tbl_ShoesDTO shoesSale = new Tbl_ShoesDTO(shoesId, shoesName, price, description, quantity, image, false);
                        listSale.add(shoesSale);
                    }
                }
                // Create session 
                if (session != null) {
                    request.setAttribute("CURRENT_USER_SEARCH", current_Search);
                    session.setAttribute("USER_SEARCH_LIST", listSale);
                }
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("loi tai UserSearchController" + e.toString());
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
