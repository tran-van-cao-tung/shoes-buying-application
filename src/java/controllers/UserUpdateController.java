/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tbl_Shoes.Tbl_ShoesDAO;
import tbl_Shoes.Tbl_ShoesDTO;
import tbl_OrderDetails.Tbl_OrderDetailsDTO;

/**
 *
 * @author Admin
 */
public class UserUpdateController extends HttpServlet {

    private final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ArrayList<Tbl_OrderDetailsDTO> listOrder = (ArrayList<Tbl_OrderDetailsDTO>) session.getAttribute("ORDER_DETAIL_LIST");
        String url = SUCCESS;
        try {
            // Get parameter from viewCart.jsp
            String shoesId = request.getParameter("shoesId");
            String updateQuantity = request.getParameter("orderQuantity");

            Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
            shoesDAO.getAllShoes();
            // Get first price from all products to make total money equal quantity increase or descrease
            ArrayList<Tbl_ShoesDTO> listShoes = shoesDAO.getshoesList();
            for (Tbl_ShoesDTO shoes : listShoes) {
                if (shoes.getShoesId().equals(shoesId)) {
                    float firstPrice = shoes.getPrice();
                    float total = firstPrice * Integer.parseInt(updateQuantity);
                    for (Tbl_OrderDetailsDTO cart : listOrder) {
                        if (cart.getShoesId().equals(shoesId)) {
                            cart.setOrderQuantity(Integer.parseInt(updateQuantity));
                            cart.setPrice(total);
                            break;
                        }
                    }
                    break;
                }
            }
            request.setAttribute("STATEMENT_UPDATE", "Update success");
            session.setAttribute("ORDER_DETAIL_LIST", listOrder);
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
