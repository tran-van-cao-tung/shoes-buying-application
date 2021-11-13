/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBOHelper implements Serializable{
    public static Connection getConnection() 
            throws ClassNotFoundException, SQLException{
        //1. Load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create url string protocal:serverDB://domain:port;databaseName=?
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_SE1506_Ass_Group10";
        //3 Get connection
        Connection con = DriverManager.getConnection(url, "sa", "1234");
        return con;
    }
}
