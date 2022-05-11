package model;

import entity.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOOrders extends ConnectDB {

    public int addOrders(Orders ord){
        int n=0;
        String sql = "Insert into Orders(CustomerID,EmployeeID,OrderDate,RequiredDate,ShippedDate," +
"ShipVia,Freight,ShipName,ShipAddress,ShipCity,ShipRegion,ShipPostalCode,ShipCountry,status) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8, ord.getShipname());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            pre.setInt(14, ord.getStatus());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int updateOrders(Orders ord,int oid){
        int n=0;
        String sql = "Update Orders set CustomerID=?,EmployeeID=?,OrderDate=?,"
                + "RequiredDate=?,ShippedDate=?, ShipVia=?,Freight=?,ShipName=?,"
                + "ShipAddress=?,ShipCity=?,ShipRegion=?,ShipPostalCode=?,ShipCountry=?"
                + " where OrderID=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8, ord.getShipname());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            pre.setInt(14,oid);
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Orders> listAllOr(){
        Vector<Orders> vec = new Vector<>();
        String sql="select * from Orders order by status";
        ResultSet rs =getData(sql);
        int oId,eID,shipvia,status;
        String cId,orderDate,requireDate,shipDate,shipName,shipAddress,shipCity,shipRegion,shipPostalCode,shipCountry;
        double freight;
        try {
            while(rs.next()){
                oId=rs.getInt(1);
                cId=rs.getString(2);
                eID=rs.getInt(3);
                orderDate=rs.getString(4);
                requireDate=rs.getString(5);
                shipDate=rs.getString(6); 
                shipvia=rs.getInt(7);
                freight=rs.getDouble(8);
                shipName=rs.getString(9);
                shipAddress=rs.getString(10);
                shipCity=rs.getString(11);
                shipRegion=rs.getString(12);
                shipPostalCode=rs.getString(13);
                shipCountry=rs.getString(14);
                status=rs.getInt(15);
                 vec.add(new Orders(oId, cId, eID, orderDate, requireDate, 
                         shipDate, shipvia, freight, shipName,
                         shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry,status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }
    
    public int deletebyID(int id){
        int n=0;
        String sql="delete from Orders where OrderID="+id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Orders> searchByCID(String ID){
        Vector<Orders> vec = new Vector<>();
        String sql="select * from Orders";
        ResultSet rs =getData(sql);
        int oId,eID,shipvia;
        String cId,orderDate,requireDate,shipDate,shipName,shipAddress,shipCity,shipRegion,shipPostalCode,shipCountry;
        double freight;
        try {
            while(rs.next()){
                if((rs.getString(2).toUpperCase()).contains(ID.toUpperCase())){
                oId=rs.getInt(1);cId=rs.getString(2);eID=rs.getInt(3);orderDate=rs.getString(4);
                requireDate=rs.getString(5);shipDate=rs.getString(6); shipvia=rs.getInt(7);
                freight=rs.getDouble(8);shipName=rs.getString(9);shipAddress=rs.getString(10);
                shipCity=rs.getString(11);shipRegion=rs.getString(12);shipPostalCode=rs.getString(13);
                shipCountry=rs.getString(14);
                 vec.add(new Orders(oId, cId, eID, orderDate, requireDate, 
                         shipDate, shipvia, freight, shipName,
                         shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }
    
    public int getLastOrderID() {
        String sql="select top 1 OrderID from Orders ORDER BY OrderID DESC ";
        ResultSet rs =getData(sql);
        try {
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
     public Vector<Orders> getOrderByName(int a) {
        Vector<Orders> vec = new Vector<>();
        String sql="select * from Orders where status ="+a;
        ResultSet rs =getData(sql);
        int oId,eID,shipvia,status;
        String cId,orderDate,requireDate,shipDate,shipName,shipAddress,shipCity,shipRegion,shipPostalCode,shipCountry;
        double freight;
        try {
            while(rs.next()){
             
                oId=rs.getInt(1);cId=rs.getString(2);eID=rs.getInt(3);orderDate=rs.getString(4);
                requireDate=rs.getString(5);shipDate=rs.getString(6); shipvia=rs.getInt(7);
                freight=rs.getDouble(8);shipName=rs.getString(9);shipAddress=rs.getString(10);
                shipCity=rs.getString(11);shipRegion=rs.getString(12);shipPostalCode=rs.getString(13);
                shipCountry=rs.getString(14);
                status=rs.getInt(15);
                 vec.add(new Orders(oId, cId, eID, orderDate, requireDate, 
                         shipDate, shipvia, freight, shipName,
                         shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry,status));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Orders getOrderByID(String parameter) {
        String sql="select * from Orders where OrderID ="+parameter;
        ResultSet rs =getData(sql);
        int oId,eID,shipvia;
        String cId,orderDate,requireDate,shipDate,shipName,shipAddress,shipCity,shipRegion,shipPostalCode,shipCountry;
        double freight;
        try {
            while(rs.next()){
             
                oId=rs.getInt(1);cId=rs.getString(2);eID=rs.getInt(3);orderDate=rs.getString(4);
                requireDate=rs.getString(5);shipDate=rs.getString(6); shipvia=rs.getInt(7);
                freight=rs.getDouble(8);shipName=rs.getString(9);shipAddress=rs.getString(10);
                shipCity=rs.getString(11);shipRegion=rs.getString(12);shipPostalCode=rs.getString(13);
                shipCountry=rs.getString(14);
                return (new Orders(oId, cId, eID, orderDate, requireDate, 
                         shipDate, shipvia, freight, shipName,
                         shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();
        
//        int n = dao.addOrders(new Orders("RATTC", 1, "", "",
//                "", 2, 32.01, "", "", "", "", "", ""));
//        if(n>0) System.out.println("Inserted Orders");

//        int n = dao.updateOrders(new Orders("RATTC", 4, "", "",
//                "", 2, 32.01, "", "", "", "", "", ""),11080);
//        if(n>0) System.out.println("Update Orders");

//             int n = dao.deletebyID(11081);
//          if(n>0)System.out.println("Deleted ");
        
//        Vector<Orders> select = dao.listAllOr();
//        for (Orders em : select) System.out.println(em);

        Vector<Orders> select = dao.searchByCID("VINET");
        for (Orders em : select) System.out.println(em);
    }

   
    
    
}
