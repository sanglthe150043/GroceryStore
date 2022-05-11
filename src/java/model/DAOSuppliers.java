package model;

import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DAOSuppliers extends ConnectDB {

    public int addSuppliers(Suppliers sp) {
        int n = 0;
        String sql = "insert into Suppliers(CompanyName,ContactName,ContactTitle,Address,City,Region,PostalCode,Country,Phone,Fax,HomePage)"
                + " values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sp.getCompanyName());
            pre.setString(2, sp.getContactName());
            pre.setString(3, sp.getContactTitle());
            pre.setString(4, sp.getAddress());
            pre.setString(5, sp.getCity());
            pre.setString(6, sp.getRegion());
            pre.setString(7, sp.getPostalCode());
            pre.setString(8, sp.getCountry());
            pre.setString(9, sp.getPhone());
            pre.setString(10, sp.getFax());
            pre.setString(11, sp.getHomePage());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateSuppliers(Suppliers sp) {
        int n = 0;
        String sql = "update Suppliers set CompanyName=?,ContactName=?,"
                + "ContactTitle=?,Address=?,City=?,Region=?,PostalCode=?,"
                + "Country=?,Phone=?,Fax=?,HomePage=? where SupplierID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sp.getCompanyName());
            pre.setString(2, sp.getContactName());
            pre.setString(3, sp.getContactTitle());
            pre.setString(4, sp.getAddress());
            pre.setString(5, sp.getCity());
            pre.setString(6, sp.getRegion());
            pre.setString(7, sp.getPostalCode());
            pre.setString(8, sp.getCountry());
            pre.setString(9, sp.getPhone());
            pre.setString(10, sp.getFax());
            pre.setString(11, sp.getHomePage());
            pre.setInt(12, sp.getSupplierID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Suppliers> listAllSup() {
        Vector<Suppliers> vec = new Vector<>();
        String sql = "select * from Suppliers";
        ResultSet rs = getData(sql);
        int id;
        String cpName, ctName, ctTitle, address, city, region, postalCode, country, phone, fax, homepage;
        try {
            while (rs.next()) {
                id = rs.getInt(1);
                cpName = rs.getString(2);
                ctName = rs.getString(3);
                ctTitle = rs.getString(4);
                address = rs.getString(5);
                city = rs.getString(6);
                region = rs.getString(7);
                postalCode = rs.getString(8);
                country = rs.getString(9);
                phone = rs.getString(10);
                fax = rs.getString(11);
                homepage = rs.getString(12);
                vec.add(new Suppliers(id, cpName, ctName, ctTitle, address, city,
                        region, postalCode, country, phone, fax, homepage));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public Vector<String> top5Country() {
        Vector<String> vec = new Vector<>();
        String sql = "select top 5 count(s.Country) as dem,s.Country \n"
                + "from Products p join Suppliers s on p.SupplierID = s.SupplierID\n"
                + "group by s.Country\n"
                + "order by dem desc";
        ResultSet rs = getData(sql);
        
        try {
            while (rs.next()) {
                vec.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    public int deletebyID(int id) {
        int n = 0;
        String sql = "delete from Suppliers where SupplierID=" + id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOSuppliers dao = new DAOSuppliers();
//        int n = dao.addSuppliers(new Suppliers("DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO"));
//        if(n>0)System.out.println("Inserted Suppliers");
//        int n = dao.updateSuppliers(new Suppliers(32,"DEMO2","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO"));
//        if(n>0)System.out.println("Updated Suppliers");

        int n = dao.deletebyID(33);
        if (n > 0) {
            System.out.println("Deleted ");
        }

//        Vector<Suppliers> select = dao.listAllSup();
//        for (Suppliers em : select) System.out.println(em);
    }
}
