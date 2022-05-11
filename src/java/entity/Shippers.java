/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class Shippers {
    private int ShipperID;
    private String Company,Phone;

    public Shippers() {
    }

    public Shippers(int ShipperID, String Company, String Phone) {
        this.ShipperID = ShipperID;
        this.Company = Company;
        this.Phone = Phone;
    }
    public Shippers( String Company, String Phone) {
        this.Company = Company;
        this.Phone = Phone;
    }

    public int getShipperID() {
        return ShipperID;
    }

    public void setShipperID(int ShipperID) {
        this.ShipperID = ShipperID;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return "Shippers{" + "ShipperID=" + ShipperID + ", Company=" + Company + ", Phone=" + Phone + '}';
    }
    
    
}
