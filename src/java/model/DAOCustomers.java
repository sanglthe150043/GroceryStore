/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customers;
import entity.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DAOCustomers  extends ConnectDB{
    
    public int addCustomers(Customers cus){
        int n =0;
        String sql2 ="insert into Customers(CustomerID,CompanyName,ContactName,ContactTitle,Address,"
                + "City,Region,PostalCode,Country,Phone,Fax) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql2);
            pre.setString(1,cus.getCustomerID());
            pre.setString(2,cus.getCompanyName());
            pre.setString(3,cus.getContactName());
            pre.setString(4,cus.getContactTitle());
            pre.setString(5,cus.getAddress());
            pre.setString(6,cus.getCity());
            pre.setString(7,cus.getRegion());
            pre.setString(8,cus.getPostalCode());
            pre.setString(9,cus.getCountry());
            pre.setString(10,cus.getPhone());
            pre.setString(11,cus.getFax());
            
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int register(Customers cus){
        int n =0;
        String sql2 ="insert into Customers(CustomerID,CompanyName,ContactName,ContactTitle,Address,"
                + "City,Region,PostalCode,Country,Phone,Fax,userName,password) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql2);
            pre.setString(1,cus.getCustomerID());
            pre.setString(2,cus.getCompanyName());
            pre.setString(3,cus.getContactName());
            pre.setString(4,cus.getContactTitle());
            pre.setString(5,cus.getAddress());
            pre.setString(6,cus.getCity());
            pre.setString(7,cus.getRegion());
            pre.setString(8,cus.getPostalCode());
            pre.setString(9,cus.getCountry());
            pre.setString(10,cus.getPhone());
            pre.setString(11,cus.getFax());
            pre.setString(12,cus.getUsername());
            pre.setString(13,cus.getPassword());
            
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateCustomers(Customers cus){
        int n=0;
        String sql ="UPDATE [Customers] SET [CompanyName] = ?,"
                + "[ContactName] = ?,[ContactTitle] = ?,[Address] = ?,[City] = ?,"
                + "[Region] = ?,[PostalCode] = ?,[Country] = ?,[Phone] = ?,[Fax] = ?" +
" WHERE  [CustomerID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCompanyName());
            pre.setString(2, cus.getContactName());
            pre.setString(3, cus.getContactTitle());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getCity());
            pre.setString(6, cus.getRegion());
            pre.setString(7, cus.getPostalCode());
            pre.setString(8, cus.getCountry());
            pre.setString(9, cus.getPhone());
            pre.setString(10, cus.getFax());
            pre.setString(11, cus.getCustomerID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Customers> listAllCus(){
        Vector<Customers> vec = new Vector<>();
        String sql="select * from Customers";
        ResultSet rs =getData(sql);
        String id, companyName,contactName,contactTitle,address,city,
                region,postalCode,country,phone,fax;
        try {
            while(rs.next()){
                id=rs.getString(1);
                companyName=rs.getString(2);
                contactName=rs.getString(3);
                contactTitle=rs.getString(4);
                address=rs.getString(5);
                city=rs.getString(6);
                region=rs.getString(7);
                postalCode=rs.getString(8);
                country=rs.getString(9);
                phone=rs.getString(10);
                fax=rs.getString(11);
                vec.add(new Customers(id, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }
    
    public int deletebyID(String id){
        int n=0;
        String sql="delete from Customers where CustomerID='"+id+"'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Customers checkUser(String user, String password){
        String sql="select * from Customers where userName='"+user+"' and password='"+password+"'";
        ResultSet rs =getData(sql);
        String id, companyName,contactName,contactTitle,address,city,
                region,postalCode,country,phone,fax,username,pass;
        try {
            while(rs.next()){
                id=rs.getString(1);
                companyName=rs.getString(2);
                contactName=rs.getString(3);
                contactTitle=rs.getString(4);
                address=rs.getString(5);
                city=rs.getString(6);
                region=rs.getString(7);
                postalCode=rs.getString(8);
                country=rs.getString(9);
                phone=rs.getString(10);
                fax=rs.getString(11);
                username=rs.getString(12);
                pass=rs.getString(13);
                return (new Customers(id, companyName, contactName, contactTitle,
                        address, city, region, postalCode, country, phone, fax,username,pass));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Vector<Customers> getCustomerByName(String parameter) {
        Vector<Customers> vec = new Vector<>();
        String sql="select * from Customers where CompanyName like'%"+parameter+"%'";
        ResultSet rs =getData(sql);
        String id, companyName,contactName,contactTitle,address,city,
                region,postalCode,country,phone,fax;
        try {
            while(rs.next()){
                id=rs.getString(1);
                companyName=rs.getString(2);
                contactName=rs.getString(3);
                contactTitle=rs.getString(4);
                address=rs.getString(5);
                city=rs.getString(6);
                region=rs.getString(7);
                postalCode=rs.getString(8);
                country=rs.getString(9);
                phone=rs.getString(10);
                fax=rs.getString(11);
                vec.add(new Customers(id, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Customers getCustomerByID(String parameter) {
        Customers customer ;
        String sql="select * from Customers where CustomerID='"+parameter+"'";
        ResultSet rs =getData(sql);
        String id, companyName,contactName,contactTitle,address,city,
                region,postalCode,country,phone,fax;
        try {
                rs.next();
                id=rs.getString(1);
                companyName=rs.getString(2);
                contactName=rs.getString(3);
                contactTitle=rs.getString(4);
                address=rs.getString(5);
                city=rs.getString(6);
                region=rs.getString(7);
                postalCode=rs.getString(8);
                country=rs.getString(9);
                phone=rs.getString(10);
                fax=rs.getString(11);
                return customer=(new Customers(id, companyName, contactName, contactTitle,
                        address, city, region, postalCode, country, phone, fax));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
//        int n = dao.addCustomers(new Customers("DEMO","DEMO","DEMO","DEMO","DEMO","DEMO",
//                "DEMO","DEMO","DEMO","DEMO","DEMO"));
//        if(n>0){
//            System.out.println("Inserted Customers");
//        }
        
//        int n = dao.updateCustomers("zaz",new Customers("xmn","DEMO3","DEMO2","DEMO2","DEMO2","DEMO2",
//                "DEMO2","DEMO2","DEMO2","DEMO2","DEMO2"));
//        if(n>0) System.out.println("Updated Customers");
        
//        Vector<Customers> vec = dao.listAllCus();
//        for (Customers customers : vec) System.out.println(customers);
    
//          int n=dao.deletebyID("c");
//          if(n>0) System.out.println("ok");

//            boolean a = dao.checkUser("user1", "12345678");
//            if(a) System.out.println("T");
//            else System.out.println("F");
    }

    
}
