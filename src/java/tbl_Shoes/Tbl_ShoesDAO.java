/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_Shoes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBOHelper;

/**
 *
 * @author Admin
 */
public class Tbl_ShoesDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    private ArrayList<Tbl_ShoesDTO> shoesList;

    public ArrayList<Tbl_ShoesDTO> getshoesList() {
        return shoesList;
    }

    public void searchIdAndName(String searchValue)
            throws ClassNotFoundException, SQLException {
        String query = " SELECT SHOESID, SHOESNAME, PRICE, DESCRIPTION, QUANTITY, IMAGE, NOTSALE "
                + " FROM TBL_SHOES "
                + " WHERE SHOESID LIKE ? OR SHOESNAME LIKE ? ";
        try {
            // Open Connect
            // Transfer query to database
            // take result
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");
                rs = stm.executeQuery();
                if (shoesList == null) {
                    shoesList = new ArrayList<>();
                }
                while (rs.next()) {
                    String shoesId = rs.getString(1);
                    String shoesName = rs.getString(2);
                    float price = rs.getFloat(3);
                    String description = rs.getString(4);
                    int quantity = rs.getInt(5);
                    String image = rs.getString(6);
                    boolean notSale = rs.getBoolean(7);
                    Tbl_ShoesDTO shoes = new Tbl_ShoesDTO(shoesId, shoesName, price, description, quantity, image, notSale);
                    shoesList.add(shoes);
                }
            }
        } finally {
            closeConnection();
        }
    }

    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String query = " DELETE FROM TBL_SHOES "
                + " WHERE SHOESID=? ";
        // Open Connect
        // Transfer query to database 
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, id);
                stm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean update(Tbl_ShoesDTO shoes) throws ClassNotFoundException, SQLException {
        String query = "UPDATE TBL_SHOES "
                + "SET PRICE=?, QUANTITY=?, NOTSALE=? "
                + "WHERE SHOESID=? ";
        // Open Connect
        // Transfer query to database
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setFloat(1, shoes.getPrice());
                stm.setInt(2, shoes.getQuantity());
                stm.setBoolean(3, shoes.isNotSale());
                stm.setString(4, shoes.getShoesId());
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean insertNewShoes(Tbl_ShoesDTO shoes) throws ClassNotFoundException, SQLException {
        String query = " INSERT INTO TBL_SHOES (SHOESID, SHOESNAME, PRICE, DESCRIPTION, QUANTITY, IMAGE, NOTSALE) VALUES (?,?,?,?,?,?,?) ";
        // Open Connect
        // Transfer query to database
        // take result
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, shoes.getShoesId());
                stm.setString(2, shoes.getShoesName());
                stm.setFloat(3, shoes.getPrice());
                stm.setString(4, shoes.getDescription());
                stm.setInt(5, shoes.getQuantity());
                stm.setString(6, shoes.getImage());
                stm.setBoolean(7, shoes.isNotSale());
                stm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

//    public void searchRange(float inRange) throws ClassNotFoundException, SQLException {
//        String query = "SELECT shoesID, DESCRIPTION, PRICE, shoesNAME, YEAROFPRODUCTION"
//                + ", QUANTITY, NOTSALE "
//                + "FROM TBL_shoes "
//                + "WHERE PRICE=? ";
//        // Open Connect
//        // Transfer query to database
//        // take result
//        try {
//            con = DBOHelper.getConnection();
//            if (con != null) {
//                stm = con.prepareStatement(query);
//                stm.setFloat(1, inRange);
//                rs = stm.executeQuery();
//                if (shoesList == null) {
//                    shoesList = new ArrayList<>();
//                }
//                while (rs.next()) {
//                    String shoesId = rs.getString(1);
//                    String description = rs.getString(2);
//                    float price = rs.getFloat(3);
//                    String shoesName = rs.getString(4);
//                    int yearOfProduction = rs.getInt(5);
//                    int quantity = rs.getInt(6);
//                    boolean notSale = rs.getBoolean(7);
//                    Tbl_shoesDTO shoes = new Tbl_shoesDTO(shoesId, description, price, shoesName, yearOfProduction, quantity, notSale);
//                    shoesList.add(shoes);
//                }
//            }
//        } finally {
//            closeConnection();
//        }
//    }
//
//    public boolean checkIdExist(String shoesId) throws SQLException, ClassNotFoundException {
//        boolean check = false;
//        String query = "SELECT shoesID, DESCRIPTION, PRICE, shoesNAME, YEAROFPRODUCTION"
//                + ", QUANTITY, NOTSALE "
//                + "FROM TBL_shoes "
//                + "WHERE shoesID=? ";
//        // Open Connect
//        // Transfer query to database
//        // take result
//        try {
//            con = DBOHelper.getConnection();
//            if (con != null) {
//                stm = con.prepareCall(query);
//                stm.setString(1, shoesId);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    check = true;
//                }
//            }
//        } finally {
//            closeConnection();
//        }
//        return check;
//    }
//
    public Tbl_ShoesDTO getshoesById(String id) throws ClassNotFoundException, SQLException {
        String query = " SELECT SHOESID, SHOESNAME, PRICE, DESCRIPTION, QUANTITY, IMAGE, NOTSALE "
                + " FROM TBL_SHOES "
                + " WHERE SHOESID=? ";
        // Open Connect
        // Transfer query to database
        // take result
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, id);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String shoesId = rs.getString(1);
                    String shoesName = rs.getString(2);
                    float price = rs.getFloat(3);
                    String description = rs.getString(4);
                    int quantity = rs.getInt(5);
                    String image = rs.getString(6);
                    boolean notSale = rs.getBoolean(7);
                    Tbl_ShoesDTO shoes = new Tbl_ShoesDTO(shoesId, shoesName, price, description, quantity, image, notSale);
                    return shoes;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public void getAllShoes() throws SQLException, ClassNotFoundException {
        String query = "SELECT SHOESID, SHOESNAME, PRICE, DESCRIPTION, QUANTITY, IMAGE, NOTSALE "
                + "FROM TBL_SHOES ";
        try {
            con = DBOHelper.getConnection();
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            if (shoesList == null) {
                shoesList = new ArrayList<>();
            }
            while (rs.next()) {
                String shoesId = rs.getString(1);
                String shoesName = rs.getString(2);
                float price = rs.getFloat(3);
                String description = rs.getString(4);
                int quantity = rs.getInt(5);
                String image = rs.getString(6);
                boolean notSale = rs.getBoolean(7);
                Tbl_ShoesDTO shoes = new Tbl_ShoesDTO(shoesId, shoesName, price, description, quantity, image, notSale);
                shoesList.add(shoes);
            }
        } finally {
            closeConnection();
        }
    }

    // update quantity when check outy
    public boolean updateCheckOut(Tbl_ShoesDTO shoes) throws ClassNotFoundException, SQLException {
        String query = "UPDATE TBL_SHOES "
                + "SET QUANTITY=? "
                + "WHERE SHOESID=? ";
        // Open Connect
        // Transfer query to database
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setFloat(1, shoes.getQuantity());
                stm.setString(2, shoes.getShoesId());
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Tbl_ShoesDAO dao = new Tbl_ShoesDAO();
//        dao.getAllShoes();
//        ArrayList<Tbl_ShoesDTO> list = dao.getshoesList();
//        for (Tbl_ShoesDTO tbl_ShoesDTO : list) {
//            System.out.println(tbl_ShoesDTO.toString());
//        }

        //     dao.searchIdAndName("NIKE");
        //     ArrayList<Tbl_ShoesDTO> list = dao.getshoesList();
        //        for (Tbl_ShoesDTO tbl_ShoesDTO : list) {
        //            System.out.println(tbl_ShoesDTO.getShoesName());
        //        }
        //        dao.deleteById("N001");
//        
//        Tbl_ShoesDTO shoesTest2;
//        float test2 = 13;
//        shoesTest2 = new Tbl_ShoesDTO("23", "23", test2, "23", 23, "23", true);
//        dao.insertNewShoes(shoesTest2);
//        System.out.println();
        Tbl_ShoesDTO check = dao.getshoesById("A001");
        System.out.println(check);
    }
}
