/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tbl_Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.DBOHelper;

/**
 *
 * @author Admin
 */
public class Tbl_OrderDAO {

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

    public boolean insertOrder(Tbl_OrderDTO order, Timestamp paidDay) throws ClassNotFoundException, SQLException {
        String query = " INSERT INTO TBL_ORDER ( USERID, PAID_DAY, TOTAL )"
                + " VALUES ( ?,?,? ) ";
        // Open Connect
        // Transfer query to database
        // take result
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, order.getUserId());
                stm.setTimestamp(2, paidDay);
                stm.setFloat(3, order.getTotal());
                stm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
