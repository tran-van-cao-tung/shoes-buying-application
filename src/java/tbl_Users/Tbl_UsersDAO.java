/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_Users;

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
public class Tbl_UsersDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public Tbl_UsersDAO() {
    }

    public Tbl_UsersDTO user;
    public ArrayList<Tbl_UsersDTO> listUser;

    public ArrayList<Tbl_UsersDTO> getListUser() {
        return listUser;
    }

    public Tbl_UsersDTO getUser() {
        return user;
    }

    public boolean checkLogin(String userId, String pwd)
            throws SQLException, ClassNotFoundException {
        try {
            //1. Open connection
            con = DBOHelper.getConnection();
            //2. Create query string
            String query = "SELECT USERID, PASSWORD, FULLNAME,PHONE, ADDRESS, ROLE "
                    + "FROM TBL_USERS "
                    + "WHERE USERID=? AND PASSWORD=?";
            //3. Prepare statement
            stm = con.prepareStatement(query);
            stm.setString(1, userId);
            stm.setString(2, pwd);
            //4. Execute
            rs = stm.executeQuery();
            //5. Process result
            if (rs.next()) {
                String fullname = rs.getString("fullname");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");
                int role = rs.getInt("role");
                user = new Tbl_UsersDTO(userId, pwd, fullname, phone, address, role);
                return true;
            }
        } finally {
            //6. Close objects
            closeConnection();
        }
        return false;
    }

    public boolean createUser(Tbl_UsersDTO user) throws ClassNotFoundException, SQLException {
        String query = " INSERT INTO TBL_USERS (USERID, PASSWORD, FULLNAME, PHONE, ADDRESS, ROLE) "
                + " VALUES (?,?,?,?,?,?) ";
        // Open Connect
        // Transfer query to database
        // take result
        try {
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, user.getUserId());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getFullName());
                stm.setInt(4, user.getPhone());
                stm.setString(5, user.getAddress());
                stm.setInt(6, user.getRole());
                stm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

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

    public void getAllUsers() throws SQLException, ClassNotFoundException {
        String query = " SELECT USERID, PASSWORD, FULLNAME, PHONE, ADDRESS, ROLE "
                + " FROM TBL_USERS ";
        try {
            con = DBOHelper.getConnection();
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            if (listUser == null) {
                listUser = new ArrayList<>();
            }
            while (rs.next()) {
                String userId = rs.getString(1);
                String password = rs.getString(2);
                String fullName = rs.getString(3);
                int phone = rs.getInt(4);
                String address = rs.getString(5);
                int role = rs.getInt(6);
                Tbl_UsersDTO user = new Tbl_UsersDTO(userId, password, fullName, phone, address, role);
                listUser.add(user);
            }
        } finally {
            closeConnection();
        }
    }

    public boolean getUserId(String userId)
            throws ClassNotFoundException, SQLException {
        String query = " SELECT USERID, PASSWORD, FULLNAME, PHONE, ADDRESS, ROLE "
                + " FROM TBL_USERS "
                + " WHERE USERID=? ";
        try {
            // Open Connect
            // Transfer query to database
            // take result
            con = DBOHelper.getConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                stm.setString(1, userId);
                rs = stm.executeQuery();            
                //5. Process result
                if (rs.next()) {
                    String pwd = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    int phone = rs.getInt("phone");
                    String address = rs.getString("address");
                    int role = rs.getInt("role");
                    user = new Tbl_UsersDTO(userId, pwd, fullname, phone, address, role);
                    return true;
                }
            }
        }finally {
            //6. Close objects
            closeConnection();
        }
            return false;

        }

    

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Tbl_UsersDTO user = new Tbl_UsersDTO("a", "a", "a", 123, "a", 1);
        Tbl_UsersDAO userDAO = new Tbl_UsersDAO();
//        userDAO.createUser(user);
//        userDAO.getAllUsers();
//        ArrayList<Tbl_UsersDTO> list = userDAO.getListUser();
//        for (Tbl_UsersDTO tbl_UsersDTO : list) {
//            System.out.println(tbl_UsersDTO);
//        }
boolean check = userDAO.getUserId("customer3");
        System.out.println(check);
        
    }
}
