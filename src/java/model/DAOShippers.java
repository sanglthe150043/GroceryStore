package model;

import entity.Shippers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DAOShippers extends ConnectDB{
    
    public int addShippers(Shippers shp){
        int n=0;
        String sql="insert into Shippers(CompanyName,Phone) values (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, shp.getCompany());
            pre.setString(2, shp.getPhone());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateShippers(Shippers shp){
        int n=0;
        String sql="update Shippers set CompanyName=?,Phone=? where ShipperID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, shp.getCompany());
            pre.setString(2, shp.getPhone());
            pre.setInt(3, shp.getShipperID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Shippers> listAllShi(){
        Vector<Shippers> vec = new Vector<>();
        String sql="select * from Shippers";
        ResultSet rs =getData(sql);
        int id;
        String cName,phone;
        try {
            while(rs.next()){
                id=rs.getInt(1);
                cName=rs.getString(2);
                phone=rs.getString(3);
                vec.add(new Shippers(id, cName, phone));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }
    
    public int deletebyID(int id){
        int n=0;
        String sql="delete from Shippers where ShipperID="+id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public static void main(String[] args) {
        DAOShippers dao = new DAOShippers();
//        int n= dao.addShippers(new Shippers( "DEMO", "DEMO"));
//        if(n>0)System.out.println("Inserted Shippers");
//        int n= dao.updateShippers(new Shippers(7,"DEMO2", "DEMO2"));
//        if(n>0)System.out.println("Updated Shippers");
        
//          int n = dao.deletebyID(8);
//          if(n>0)System.out.println("Deleted ");
//        
//        Vector<Shippers> select = dao.listAllShi();
//        for (Shippers em : select) System.out.println(em);   
    }
}
