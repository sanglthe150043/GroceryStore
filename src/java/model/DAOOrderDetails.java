package model;

import entity.DetailOrder;
import entity.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DAOOrderDetails extends ConnectDB {

    public int addOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = "insert into [Order Details] (OrderID,ProductID,UnitPrice,Quantity,Discount) values"
                + "(?,?,?,?,?) ";
        try {
            //        Connect DB
//        Create Pre
//        Run
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateOrderDetails(OrderDetails od, int oid, int pid) {
        int n = 0;
        String sql = "update [Order Details] set OrderID=?,ProductID=?,UnitPrice=?,Quantity=?,Discount=?"
                + " where (OrderID=? and ProductID=?)";
        try {
            //        Connect DB
//        Create Pre
//        Run
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            pre.setInt(6, oid);
            pre.setInt(7, pid);

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<OrderDetails> listAllOrt() {
        Vector<OrderDetails> vec = new Vector<>();
        String sql = "select * from [Order Details]";
        ResultSet rs = getData(sql);
        int oid, pid, quantity;
        double unitPrice, discount;
        try {
            while (rs.next()) {
                oid = rs.getInt(1);
                pid = rs.getInt(2);
                unitPrice = rs.getDouble(3);
                quantity = rs.getInt(4);
                discount = rs.getDouble(5);
                vec.add(new OrderDetails(oid, pid, unitPrice, quantity, discount));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<DetailOrder> listDetails() {
        Vector<DetailOrder> vec = new Vector<>();
        String sql = "select o.OrderID, c.CustomerID as 'CustomerName' ,o.OrderDate, \n"
                + "(e.FirstName + e.LastName)as 'EmployeeName',o.ShippedDate,p.ProductID,p.ProductName,\n"
                + "ot.Quantity,ot.UnitPrice,o.status \n"
                + "from Customers c join Orders o \n"
                + "on c.CustomerID=o.CustomerID join Employees e \n"
                + "on e.EmployeeID=o.EmployeeID join [Order Details] ot \n"
                + "on ot.OrderID=o.OrderID join Products p \n"
                + "on p.ProductID=ot.ProductID";
        ResultSet rs = getData(sql);
        int oID, pID,quantity,status;
        String cName, oDate, eName, sDate, pName;
        double unit;
        try {
            while (rs.next()) {
                oID = rs.getInt(1);
                cName = rs.getString(2);
                oDate = rs.getString(3);
                eName = rs.getString(4);
                sDate = rs.getString(5);
                pID = rs.getInt(6);
                pName = rs.getString(7);
                quantity=rs.getInt(8);
                unit=rs.getDouble(9);
                status = rs.getInt(10);
                vec.add(new DetailOrder(oID, eName, oDate, eName, sDate, pID, pName,quantity,unit,status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int updateStatus(int orderID, int status){
        if(status<1 || status>3) return 0;
        
        int n = 0;
        String sql = "update Orders set status=? where OrderID=?" ;
        try {
            //        Connect DB
//        Create Pre
//        Run
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, orderID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<DetailOrder> listDetailsbyOrderID(String id) {
        Vector<DetailOrder> vec = new Vector<>();
        String sql = "select o.OrderID, c.CustomerID as 'CustomerName' ,o.OrderDate, \n"
                + "(e.FirstName + e.LastName)as 'EmployeeName',o.ShippedDate,p.ProductID,p.ProductName,\n"
                + "ot.Quantity,ot.UnitPrice, o.status \n"
                + "from Customers c join Orders o \n"
                + "on c.CustomerID=o.CustomerID join Employees e \n"
                + "on e.EmployeeID=o.EmployeeID join [Order Details] ot \n"
                + "on ot.OrderID=o.OrderID join Products p \n"
                + "on p.ProductID=ot.ProductID where o.OrderID=" + id;
        ResultSet rs = getData(sql);
        int oID, pID,quantity,status;
        String cName, oDate, eName, sDate, pName;
        double unit;
        try {
            while (rs.next()) {
                oID = rs.getInt(1);
                cName = rs.getString(2);
                oDate = rs.getString(3);
                eName = rs.getString(4);
                sDate = rs.getString(5);
                pID = rs.getInt(6);
                pName = rs.getString(7);
                 quantity=rs.getInt(8);
                unit=rs.getDouble(9);
                status = rs.getInt(10);
                vec.add(new DetailOrder(oID, eName, oDate, eName, sDate, pID, pName,quantity,unit,status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int deletebyID(int oid, int pid) {
        int n = 0;
        String sql = "delete from [Order Details] where (OrderID='" + oid + "' and "
                + "ProductID=" + pid;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

//    public void displayOrderDetails() {
//        String sql = "select \n"
//                + "	 * \n"
//                + "from Customers\n"
//                + "join Orders on Customers.CustomerID = Orders.CustomerID\n"
//                + "join [Order Details] on [Order Details].OrderID = Orders.OrderID\n"
//                + "join Products on [Order Details].ProductID = Products.ProductID\n"
//                + "join Employees on Orders.EmployeeID = Employees.EmployeeID";
//
//        ResultSet rs = getData(sql);
//
//        try {
//            ResultSetMetaData rsMeta = rs.getMetaData();
//            int colNumber = rsMeta.getColumnCount();
//
//            for (int i = 1; i <= colNumber; i++) {
//                System.out.printf("%-20s", rsMeta.getColumnName(i));
//            }
//            System.out.println("");
//            
//            while (rs.next()) {
//                for (int i = 1; i <= colNumber; i++) {
//                    System.out.printf("%-20.10s", rs.getString(i));
//                }
//                System.out.println("");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        DAOOrderDetails dao = new DAOOrderDetails();
//        int n = dao.addOrderDetails(new OrderDetails(11077, 76, 99.99, 99, 0.99));
//        if(n>0) System.out.println("Inserted OrderDetails");
//        int n = dao.updateOrderDetails(new OrderDetails(11077, 74, 99.99, 99, 0.99),11077,76);
//        if(n>0) System.out.println("Updated OrderDetails");

//                 int n = dao.deletebyID();
//          if(n>0)System.out.println("Deleted ");
        Vector<DetailOrder> select = dao.listDetails();
        for (DetailOrder em : select) {
            System.out.println(em);
        }
    }
}
