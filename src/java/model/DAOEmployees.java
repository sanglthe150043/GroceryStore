/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Employees;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DAOEmployees extends ConnectDB {
    public int addEmployees(Employees emp){
        int n=0;
        String sql = "insert into Employees(LastName,FirstName,Title,TitleOfCourtesy,BirthDate,HireDate," +
"Address,City,Region,PostalCode,Country,HomePhone,Extension,Notes,ReportsTo,PhotoPath)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
//            Connect DB
//            Create Statement
//            Run        
//            Statement state = conn.createStatement();
//            n=state.executeUpdate(sql);
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getNote());
            pre.setInt(15, emp.getReportsTo());
            pre.setString(16, emp.getPhotoPath());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateEmployees(Employees emp){
        int n=0;
        String sql = "update Employees set LastName=?,FirstName=?,Title=?,"
                + "TitleOfCourtesy=?,BirthDate=?,HireDate=?," +
        "Address=?,City=?,Region=?,PostalCode=?,Country=?,HomePhone=?,"
                + "Extension=?,Notes=?,ReportsTo=?,PhotoPath=?"
                + " where EmployeeID=? " ;
                
        try {
//            Connect DB
//            Create Statement
//            Run        
//            Statement state = conn.createStatement();
//            n=state.executeUpdate(sql);
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getNote());
            pre.setInt(15, emp.getReportsTo());
            pre.setString(16, emp.getPhotoPath());
            pre.setInt(17, emp.getEmployeeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Employees> listAllEmp(){
        Vector<Employees> vec = new Vector<>();
        String sql="select * from Employees";
        ResultSet rs =getData(sql);
        int id,report;
        String lname,fname,title,titleOfCourse,birthDate,hireDate,address,city,
                region,postalCode,country,homePhone,extension,photo,notes,photoPath;
        try {
            while(rs.next()){
                id=rs.getInt(1);
                lname=rs.getString(2);
                fname=rs.getString(3);
                title=rs.getString(4);
                titleOfCourse=rs.getString(5);
                birthDate=rs.getString(6);
                hireDate=rs.getString(7);
                address=rs.getString(8);
                city=rs.getString(9);
                region=rs.getString(10);
                postalCode=rs.getString(11);
                country=rs.getString(12);
                homePhone=rs.getString(13);
                extension=rs.getString(14);
                photo=rs.getString(15);
                notes=rs.getString(16);
                report=rs.getInt(17);
                photoPath=rs.getString(18);
                vec.add(new Employees(id, lname, fname, title, titleOfCourse, 
                        birthDate, hireDate, address, city, region, postalCode, 
                        country, homePhone, extension, notes, report, photoPath));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }
    
    public int deletebyID(int id){
        int n=0;
        String sql="delete from Employees where EmployeeID="+id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Employees checkUser(String user, String password) {
        String sql="select * from Employees where username='"+user+"' and password='"+password+"'";
        ResultSet rs =getData(sql);
        
        int id,report;
        String lname,fname,title,titleOfCourse,birthDate,hireDate,address,city,
                region,postalCode,country,homePhone,extension,photo,notes,photoPath;
        try {
            while(rs.next()){
                id=rs.getInt(1);
                lname=rs.getString(2);
                fname=rs.getString(3);
                title=rs.getString(4);
                titleOfCourse=rs.getString(5);
                birthDate=rs.getString(6);
                hireDate=rs.getString(7);
                address=rs.getString(8);
                city=rs.getString(9);
                region=rs.getString(10);
                postalCode=rs.getString(11);
                country=rs.getString(12);
                homePhone=rs.getString(13);
                extension=rs.getString(14);
                photo=rs.getString(15);
                notes=rs.getString(16);
                report=rs.getInt(17);
                photoPath=rs.getString(18);
                return (new Employees(id, lname, fname, title, titleOfCourse, 
                        birthDate, hireDate, address, city, region, postalCode, 
                        country, homePhone, extension, notes, report, photoPath));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
     public Employees getEmployeeByID(int employeeID) {
         String sql="select * from Employees where EmployeeID="+employeeID;
        ResultSet rs =getData(sql);
        int id,report;
        String lname,fname,title,titleOfCourse,birthDate,hireDate,address,city,
                region,postalCode,country,homePhone,extension,photo,notes,photoPath;
        try {
            while(rs.next()){
                id=rs.getInt(1);
                lname=rs.getString(2);
                fname=rs.getString(3);
                title=rs.getString(4);
                titleOfCourse=rs.getString(5);
                birthDate=rs.getString(6);
                hireDate=rs.getString(7);
                address=rs.getString(8);
                city=rs.getString(9);
                region=rs.getString(10);
                postalCode=rs.getString(11);
                country=rs.getString(12);
                homePhone=rs.getString(13);
                extension=rs.getString(14);
                photo=rs.getString(15);
                notes=rs.getString(16);
                report=rs.getInt(17);
                photoPath=rs.getString(18);
                return (new Employees(id, lname, fname, title, titleOfCourse, 
                        birthDate, hireDate, address, city, region, postalCode, 
                        country, homePhone, extension, notes, report, photoPath));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        DAOEmployees dao = new DAOEmployees();
//        int n = dao.addEmployees(new Employees("DEMO","DEMO","DEMO","DEMO",
//                "","","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO",
//                "DEMO","DEMO",2,"DEMO"));
//        if(n>0) System.out.println("Inserted Employees");
        
//        int n = dao.updateEmployees(new Employees(33,"2","DEMO2","DEMO2","DEMO2",
//                "","","DEMO2","DEMO2","DEMO2","DEMO2","DEMO2","DEMO2",
//                "DEMO","DEMO2",5,"DEMO2"));
//        if(n>0) System.out.println("Updated Employees");
        
        
//          int n = dao.deletebyID(22);
//          if(n>0)System.out.println("Deleted ");
        
//        Vector<Employees> select = dao.listAllEmp();
//        for (Employees em : select) System.out.println(em);
    }

   

    
}
