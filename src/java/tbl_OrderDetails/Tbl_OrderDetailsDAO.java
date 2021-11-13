/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_OrderDetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import tbl_Shoes.Tbl_ShoesDTO;
import utils.DBOHelper;

/**
 *
 * @author Admin
 */
public class Tbl_OrderDetailsDAO implements Serializable {

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

    public boolean insertOrderDetails(Tbl_OrderDetailsDTO orderDetails, Timestamp paidDay, float total) throws ClassNotFoundException, SQLException {
        String query = " INSERT INTO TBL_DETAIL (USERID, PAID_DAY, SHOESID, SHOESNAME, QUANTITY, PRICE, TOTAL) "
                + " VALUES (?,?,?,?,?,?,?) ";
        // Open Connect
        // Transfer query to database
        // take result
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, orderDetails.getOrderId());
                stm.setTimestamp(2, paidDay);
                stm.setString(3, orderDetails.getShoesId());
                stm.setString(4, orderDetails.getShoesName());
                stm.setInt(5, orderDetails.getOrderQuantity());
                stm.setFloat(6, orderDetails.getPrice());
                stm.setFloat(7, total);
                stm.executeUpdate();
                stm.toString();
                return true;

            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean checkOrderDetailsByShoesId(String id) throws ClassNotFoundException, SQLException {
        String query = " SELECT  USERID, PAID_DAY, SHOESID, SHOESNAME, QUANTITY, PRICE, TOTAL "
                + "FROM TBL_DETAIL "
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
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Tbl_OrderDetailsDAO dao = new Tbl_OrderDetailsDAO();
//        Tbl_OrderDetailsDTO orderDetails = new Tbl_OrderDetailsDTO("1", "1", "1", 1, 1, "1");
//        boolean check = dao.insertOrderDetails(orderDetails);
//        System.out.println(check);
//
        Calendar cal = Calendar.getInstance();
        Timestamp paidDay = new Timestamp(cal.getTimeInMillis());
        Timestamp paidDay2 = new Timestamp(1, 1, 1, 1, 1, 1, 1);
        System.out.println(paidDay2);
        String st = paidDay.toString();
        System.out.println(st);
        System.out.println(paidDay);
        System.out.println(cal);
////        stm.setTimestamp(2, paidDay, cal);

    }

}
