/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DAOCategories extends ConnectDB{
    public int addCategories(Categories cat){
        int n=0;
//        String sql ="insert into Categories(CategoryName,Description,Picture) values ("+
//"'"+cat.getCategoryName()+"','"+cat.getDescription()+"','"+cat.getPicture()+"')";
        String sql="insert into Categories(CategoryName,Description) values(?,?) ";
        try {
            //        Connections DB, Create Statement,Run
//            Statement state = conn.createStatement();
//            n=state.executeUpdate(sql);
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cat.getCategoryName());
            pre.setString(2, cat.getDescription());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int  updateCategories(Categories cat){
        int n=0;
        String sql = "UPDATE [Categories] SET [CategoryName] =?  ,[Description] =? " +
        " WHERE CategoryID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cat.getCategoryName());
            pre.setString(2, cat.getDescription());
            pre.setInt(3, cat.getCategoryID());
//            pre.execute(); creat, drop, alter
//                    pre.executeQuery(): select
//                            n=pre.executeUpdate(): insert, delete, update
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Categories> listAllCategories(){
        Vector<Categories> vec = new Vector<>();
        String sql="select * from Categories";
        ResultSet rs =getData(sql);
        int id;
        String name,des,picture;
        try {
            while(rs.next()){
                id = rs.getInt(1);
                name=rs.getString(2);
                des=rs.getString(3);
                picture=rs.getString(4);
                vec.add(new Categories(id, name, des, picture));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }
    
    public int deletebyID(int id){
        int n=0;
        String sql="delete from Categories where CategoryID="+id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public static void main(String[] args) {
//        DAOCategories dao = new DAOCategories();
//        int n=dao.updateCategories(new Categories(1, "a", "a"));
//        if(n>0)System.out.println("Updated Categories");

//        int n =dao.addCategories(new Categories("DEMO", "DEMO"));
//        if(n>0) System.out.println("Inserted Categories");

//          int n = dao.deletebyID(11);
//          if(n>0)System.out.println("Deleted Categories");
        
//        Vector<Categories> select = dao.listAllCategories();
//        for (Categories categories : select) System.out.println(categories);
        
        
    }
    
}
