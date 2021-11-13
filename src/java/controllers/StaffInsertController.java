/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import tbl_Shoes.Tbl_ShoesDAO;
import tbl_Shoes.Tbl_ShoesDTO;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class StaffInsertController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "staffInsert.jsp";
        try {
            // Get parameter from staffInsert.jsp
            String shoesId = request.getParameter("shoesId").trim();
            String shoesName = request.getParameter("shoesName").trim();
            String price = request.getParameter("price").trim();
            String description = request.getParameter("description").trim();
            String quantity = request.getParameter("quantity").trim();
            String stringNotSale = request.getParameter("notSale").trim();
            String current_Search = "%";
            boolean checkAllCorrect = true;
            Tbl_ShoesDAO shoesDAO = new Tbl_ShoesDAO();
            shoesDAO.getAllShoes();
            ArrayList<Tbl_ShoesDTO> listShoes = shoesDAO.getshoesList();
            request.setAttribute("CURRENT_SHOESID", shoesId);
            request.setAttribute("CURRENT_SHOESNAME", shoesName);
            request.setAttribute("CURRENT_PRICE", price);
            request.setAttribute("CURRENT_DESCRIPTION", description);
            request.setAttribute("CURRENT_QUANTITY", quantity);
            request.setAttribute("CURRENT_STRINGNOTSALE", stringNotSale);
            boolean notSale = false;
            for (Tbl_ShoesDTO listShoe : listShoes) {
                if (listShoe.getShoesId().equals(shoesId)) {
                    request.setAttribute("SHOES_ID_ERROR", "ID exist ");
                    request.removeAttribute("CURRENT_SHOESID");
                    checkAllCorrect = false;;
                    break;
                }
            }
            if (!shoesId.matches("^SP([0-9]){1,10000}")) {
                if (shoesId.equals(" ") || shoesId.isEmpty()) {
                    request.setAttribute("SHOES_ID_ERROR", "No blankspace");
                } else {
                    request.setAttribute("SHOES_ID_ERROR", "Start with SP then go with number");
                }
                request.removeAttribute("CURRENT_SHOESID");
                checkAllCorrect = false;
            }
            if (shoesName.equals(" ") || shoesName.isEmpty()) {
                request.setAttribute("SHOES_NAME_ERROR", "No blankspace");
                checkAllCorrect = false;
            }
            if (description.equals(" ") || description.isEmpty()) {
                request.setAttribute("SHOES_DESCRIPTION_ERROR", "No blankspace");
                checkAllCorrect = false;
            }
            // Input false or true will go with that boolean 
            if (stringNotSale.equalsIgnoreCase("true")) {
                notSale = true;
            } else if (stringNotSale.equalsIgnoreCase("false")) {
                notSale = false;
            } else {
                if (stringNotSale.equals(" ") || stringNotSale.isEmpty()) {
                    request.setAttribute("NOT_SALE_ERROR", "No blankspace");
                } else {
                    request.setAttribute("NOT_SALE_ERROR", "Input TRUE/FALSE");
                }
                request.removeAttribute("CURRENT_STRINGNOTSALE");
                checkAllCorrect = false;
            }
            // Call to method uploadFile
            String filename = uploadFile(request);
            if (filename.equals("")) {
                checkAllCorrect = false;
            }
            // CAll shoesDAO to insert to DB
            if (checkAllCorrect == true) {
                Tbl_ShoesDTO shoes = new Tbl_ShoesDTO(shoesId, shoesName, Float.parseFloat(price), description, Integer.parseInt(quantity), filename, notSale);
                boolean check = shoesDAO.insertNewShoes(shoes);
                // success add to DB will go to staffPage.jsp
                if (check == true) {

                    url = "MainController"
                            + "?btAction=Search"
                            + "&searchValue=" + URLEncoder.encode(current_Search, "UTF-8");
                }
            }
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffInsertController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StaffInsertController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part part = request.getPart("photo");
            String realPath = request.getServletContext().getRealPath("/images");
            fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(realPath + "/" + fileName);
            System.out.println(fileName);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        return fileName;
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
        System.out.println("get");
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
        System.out.println("post");
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
