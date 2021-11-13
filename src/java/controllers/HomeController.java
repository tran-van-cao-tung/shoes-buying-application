/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Call shoesDAO to get all list products 
            Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
            shoesDAO.searchIdAndName("%");
            ArrayList<Tbl_ShoesDTO> listShoes = shoesDAO.getshoesList();
            ArrayList<Tbl_ShoesDTO> listSale = new ArrayList<>();
            //Show list that notSale = false
            for (Tbl_ShoesDTO shoes : listShoes) {
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
            HttpSession session = request.getSession();
            session.setAttribute("USER_SEARCH_LIST", listSale);
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
