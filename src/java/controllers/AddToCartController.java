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
import tbl_OrderDetails.Tbl_OrderDetailsDTO;
import tbl_Shoes.Tbl_ShoesDAO;
import tbl_Shoes.Tbl_ShoesDTO;
import tbl_Users.Tbl_UsersDTO;

/**
 *
 * @author Admin
 */
public class AddToCartController extends HttpServlet {

    private final String SUCCESS = "home.jsp";
    public ArrayList<Tbl_OrderDetailsDTO> listOrderDetails;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String buySuccess = request.getParameter("buySuccess");
        if (buySuccess == null) {
            if (listOrderDetails == null) {
                listOrderDetails = new ArrayList<>();
            }
        } else if (buySuccess.equals("true")) {
            listOrderDetails = new ArrayList<>();
            request.setAttribute("STATEMENT_BUY", "Buy Success");
        }

        String url = SUCCESS;
        try {
            // Get parameter from home.jsp
            String shoesId = request.getParameter("shoesId");
            // Get shoes with the same id
            Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
            Tbl_ShoesDTO chooseShoes = shoesDAO.getshoesById(shoesId);
            // Get all shoes list          
            HttpSession session = request.getSession();
            session.setAttribute("STORE_QUANTITY", chooseShoes.getQuantity());
            Tbl_UsersDTO current_user = (Tbl_UsersDTO) session.getAttribute("CURRENT_USER");

            if (chooseShoes.getShoesId().equals(shoesId)) {
                String orderId = current_user.getUserId();
                String orderShoesId = chooseShoes.getShoesId();
                String orderShoesName = chooseShoes.getShoesName();
                int orderQuantity = 1;
                float orderPrice = chooseShoes.getPrice();
                String orderImages = chooseShoes.getImage();
                boolean check = false;
                for (Tbl_OrderDetailsDTO cartList : listOrderDetails) {
                    // User click add to cart
                    // If shoesId exist in list then remove the old quantity and update new quantity
                    if (cartList.getShoesId().equals(shoesId)) {
                        check = true;
                        String newOrderId = cartList.getOrderId();
                        String newOrderShoesId = cartList.getShoesId();
                        String newOrderShoesName = cartList.getShoesName();
                        int newQuantity = cartList.getOrderQuantity() + 1;
                        float newPrice = cartList.getPrice() * newQuantity;
                        String newOrderImages = cartList.getImages();
                        listOrderDetails.remove(cartList);
                        Tbl_OrderDetailsDTO orderDetails = new Tbl_OrderDetailsDTO(newOrderId, newOrderShoesId, newOrderShoesName, newQuantity, newPrice, newOrderImages);
                        listOrderDetails.add(orderDetails);
                        break;
                    }
                }
                // If not exist shoesId in cart then add to cart with new shoesId
                if (check == false) {
                    Tbl_OrderDetailsDTO orderDetails = new Tbl_OrderDetailsDTO(orderId, orderShoesId, orderShoesName, orderQuantity, orderPrice, orderImages);
                    listOrderDetails.add(orderDetails);
                }
            }
            session.setAttribute("ORDER_DETAIL_LIST", listOrderDetails);
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
