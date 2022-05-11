package model;

import entity.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DAOProducts extends ConnectDB {

    public int addProducts(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [Products]([ProductName],[SupplierID],[CategoryID],"
                + "[QuantityPerUnit],[UnitPrice],[UnitsInStock],[UnitsOnOrder],"
                + "[ReorderLevel],[Discontinued])"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//            set value for ?
//            ? in dex start 1
//            pre.setDataType(index,value)
//            dataType of dataType of field    
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.getDiscontinued());
//            Run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateProducts(Products pro) {
        int n = 0;
        String sql = "update [Products] set [ProductName]=?,[SupplierID]=?,[CategoryID]=?,"
                + "[QuantityPerUnit]=?,[UnitPrice]=?,[UnitsInStock]=?,[UnitsOnOrder]=?,"
                + "[ReorderLevel]=?,[Discontinued]=? where ProductID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//            set value for ?
//            ? in dex start 1
//            pre.setDataType(index,value)
//            dataType of dataType of field    
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.getDiscontinued());
            pre.setInt(10, pro.getProductID());
//            Run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int updatequantity(int pID, int quantity,int stock) {
        int n = 0;
        String sql = "update [Products] set [UnitsInStock]=? where ProductID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//            set value for ?
//            ? in dex start 1
//            pre.setDataType(index,value)
//            dataType of dataType of field    
            pre.setInt(1, stock-quantity);
            pre.setInt(2, pID);
//            Run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int changeQuantity(int id, int unitInStock) {
        int n = 0;
        String sql = "update Products set UnitsInStock=? where ProductID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, unitInStock);
            pre.setInt(2, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeProduct(int id) {
        int n = 0;
        String sql = "delete from Products where ProductID=" + id;
//        check foreign key constrain (if table is 1)
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Products> searchByPrice(double from, double to) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "select * from Products where UnitPrice between " + from
                + " and " + to;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int pId = rs.getInt("ProductID");
                String pname = rs.getString(2);
                int subid = rs.getInt(3);
                int cateID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double unitInPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReOrderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                Products pro = new Products(pId, pname, subid, cateID, QuantityPerUnit,
                        unitInPrice, UnitsInStock, UnitsOnOrder, ReOrderLevel, Discontinued);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> searchBySupplier(String id) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "select * from Products where SupplierID =" + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int pId = rs.getInt(1);
                String pname = rs.getString(2);
                int subid = rs.getInt(3);
                int cateID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double unitInPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReOrderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                Products pro = new Products(pId, pname, subid, cateID, QuantityPerUnit,
                        unitInPrice, UnitsInStock, UnitsOnOrder, ReOrderLevel, Discontinued);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> searchByCountry(String country) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "select p.ProductID,p.ProductName,p.SupplierID,p.CategoryID,p.QuantityPerUnit,p.UnitPrice,\n"
                + "p.UnitsInStock,p.UnitsOnOrder,p.ReorderLevel,p.Discontinued\n"
                + "from Products p join Suppliers s on p.SupplierID=s.SupplierID where p.Discontinued=0 "
                + " and s.Country='" + country + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int pId = rs.getInt(1);
                String pname = rs.getString(2);
                int subid = rs.getInt(3);
                int cateID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double unitInPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReOrderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                Products pro = new Products(pId, pname, subid, cateID, QuantityPerUnit,
                        unitInPrice, UnitsInStock, UnitsOnOrder, ReOrderLevel, Discontinued);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> searchByCategory(String id) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "select * from Products where CategoryID =" + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int pId = rs.getInt(1);
                String pname = rs.getString(2);
                int subid = rs.getInt(3);
                int cateID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double unitInPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReOrderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                Products pro = new Products(pId, pname, subid, cateID, QuantityPerUnit,
                        unitInPrice, UnitsInStock, UnitsOnOrder, ReOrderLevel, Discontinued);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

//    Select - ResultSet
    public Vector<Products> listAllProducts() {
        Vector<Products> vec = new Vector<>();
        String sql = "select * from Products where Discontinued=0";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
//                rs.getDataType(fieldName|Index=1);
                int pId = rs.getInt("ProductID");// rs.getInt(1)
                String pname = rs.getString(2);
//                rs.getString("ProductName");
                int subid = rs.getInt(3);
                int cateID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double unitInPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReOrderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                Products pro = new Products(pId, pname, subid, cateID, QuantityPerUnit,
                        unitInPrice, UnitsInStock, UnitsOnOrder, ReOrderLevel, Discontinued);
                vec.add(pro);
            }
//        ResultSet.TYPE_FORWARD_ONLY : default
//        ResultSet.TYPE_SCROLL_INSENSITIVE: no thread safe
//        ResultSet.TYPE_SCROLL_SENSITIVE: thread safe
//        ResultSet.CONCUR_READ_ONLY: default
//        ResultSet.CONCUR__UPDATABLE
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    public ResultSet join() {
        String sql = "select c.CustomerID,o.OrderID,e.EmployeeID,p.ProductID "
                + "from Customers c join Orders o "
                + "on c.CustomerID=o.CustomerID join Employees e "
                + "on e.EmployeeID=o.EmployeeID join [Order Details] ot "
                + "on ot.OrderID=o.OrderID join Products p "
                + "on p.ProductID=ot.ProductID";
        ResultSet rs = getData(sql);
        return rs;
    }

    public Vector<Products> searchbyName(String name) {
        Vector<Products> vec = new Vector<>();
        String sql = "select * from Products where ProductName like '%"+name+"%' and Discontinued=0";
        int pID, sID, cID, unitInStock, unitOnOrder, reorderLevel, discontinued;
        String pName, quantityPerUnit;
        double unitPrice;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                if ((rs.getString(2).toUpperCase()).contains(name.toUpperCase())) {
                    pID = rs.getInt(1);
                    pName = rs.getString(2);
                    sID = rs.getInt(3);
                    cID = rs.getInt(4);
                    quantityPerUnit = rs.getString(5);
                    unitPrice = rs.getDouble(6);
                    unitInStock = rs.getInt(7);
                    unitOnOrder = rs.getInt(8);
                    reorderLevel = rs.getInt(9);
                    discontinued = rs.getInt(10);
                    vec.add(new Products(pID, pName, sID, cID, quantityPerUnit,
                            unitPrice, unitInStock, unitOnOrder, reorderLevel,
                            discontinued));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Products searchByID(String id) {
        String sql = "select * from Products where ProductID=" + id;
        int pID, sID, cID, unitInStock, unitOnOrder, reorderLevel, discontinued;
        String pName, quantityPerUnit;
        double unitPrice;
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                pID = rs.getInt(1);
                pName = rs.getString(2);
                sID = rs.getInt(3);
                cID = rs.getInt(4);
                quantityPerUnit = rs.getString(5);
                unitPrice = rs.getDouble(6);
                unitInStock = rs.getInt(7);
                unitOnOrder = rs.getInt(8);
                reorderLevel = rs.getInt(9);
                discontinued = rs.getInt(10);
                return (new Products(pID, pName, sID, cID,
                        quantityPerUnit, unitPrice, unitInStock,
                        unitOnOrder, reorderLevel, discontinued));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
////        int n=dao.addProducts(new Products("DEMO", 11, 1, "DEMO", 99, 99, 99, 99, 99));
////        if(n>0)System.out.println("Inserted Products");
////        int n=dao.updateProducts(new Products(80,"DEMO", 12, 1, "DEMO", 99, 99, 99, 99, 99));
////        if(n>0)System.out.println("Updated Products");
////            dao.listAllProducts();
//            Vector<Products> vector = dao.searchByCategory("1");
//            for(Products pro: vector){
//                System.out.println(pro);
//            }
//        Vector<Products> vector = dao.searchbyName("ab");
//        for(Products pro: vector) System.out.println(pro);
            
//          dao.join();
//            
    }

    
}
