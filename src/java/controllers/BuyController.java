/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Tbl_Order.Tbl_OrderDAO;
import Tbl_Order.Tbl_OrderDTO;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tbl_Shoes.Tbl_ShoesDAO;
import tbl_Shoes.Tbl_ShoesDTO;
import tbl_OrderDetails.Tbl_OrderDetailsDAO;
import tbl_OrderDetails.Tbl_OrderDetailsDTO;
import tbl_Users.Tbl_UsersDTO;

/**
 *
 * @author Admin
 */
public class BuyController extends HttpServlet {

    private final String SUCCESS = "home.jsp";
    private final String FAIL = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            boolean checkAllCorrect = true;

            String total = request.getParameter("total");
            if (total == "") {
                request.setAttribute("BUY_ERROR", "No products to buy");
                checkAllCorrect = false;
                url = FAIL;
            }
            float totalcheck = Float.parseFloat(total);
            HttpSession session = request.getSession();
            // Get list orders
            ArrayList<Tbl_OrderDetailsDTO> listOrder = (ArrayList<Tbl_OrderDetailsDTO>) session.getAttribute("ORDER_DETAIL_LIST");

            if (checkAllCorrect == true) {
                Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
                shoesDAO.getAllShoes();
                //Get list shoes in store
                ArrayList<Tbl_ShoesDTO> listShoes = shoesDAO.getshoesList();
                Tbl_OrderDetailsDAO orderDetailsDAO = new Tbl_OrderDetailsDAO();
                Date date = new Date(System.currentTimeMillis());
                Timestamp paidDay = new Timestamp(date.getTime());
                int newQuantityStore = 0;

                for (Tbl_OrderDetailsDTO orderDetails : listOrder) {
                    for (Tbl_ShoesDTO shoesRepo : listShoes) {
                        if (orderDetails.getShoesId().equals(shoesRepo.getShoesId())) {
                            // Insert into tbl order details DB
                            boolean buySuccess = orderDetailsDAO.insertOrderDetails(orderDetails, paidDay, totalcheck);
                            // Get attribute from the shoes have the same id
                            if (buySuccess == true) {
                                String newShoesId = shoesRepo.getShoesId();
                                String newShoesName = shoesRepo.getShoesName();
                                float newPrice = shoesRepo.getPrice();
                                String newDescription = shoesRepo.getDescription();
                                // Update quantity in the shoes store
                                newQuantityStore = shoesRepo.getQuantity() - orderDetails.getOrderQuantity();
                                String newImage = shoesRepo.getImage();
                                boolean newNotSale = shoesRepo.isNotSale();
                                //Update quantity of store in DB
                                Tbl_ShoesDTO newShoes = new Tbl_ShoesDTO(newShoesId, newShoesName, newPrice, newDescription, newQuantityStore, newImage, newNotSale);
                                shoesDAO.updateCheckOut(newShoes);
                            }
                        }
                    }
                }
                Tbl_UsersDTO current_user = (Tbl_UsersDTO) session.getAttribute("CURRENT_USER");
                String userId = current_user.getUserId();
                Tbl_OrderDTO orderDTO = new Tbl_OrderDTO(userId, totalcheck);
                Tbl_OrderDAO orderDAO = new Tbl_OrderDAO();
                orderDAO.insertOrder(orderDTO, paidDay);
                session.removeAttribute("ORDER_DETAIL_LIST");
                url = "MainController?btAction=AddToCart&buySuccess=true";
            }
        } catch (Exception e) {
            System.out.println(e);
            log("loi tai buycontroller" + e.toString());
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
