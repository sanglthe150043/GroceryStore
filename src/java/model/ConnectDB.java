/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ConnectDB {
    public Connection conn=null;
    public ConnectDB(String URL, String userName, String password){
//                    URL: connection string: address, port. database of server
        try {
//                      call drivers
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
//            connections
            conn=DriverManager.getConnection(URL, userName, password);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public ConnectDB(){
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1615","sa","123456");
    }
    
    public ResultSet getData(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs=state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    
    public static void main(String[] args) {
        new ConnectDB();
    }
}
