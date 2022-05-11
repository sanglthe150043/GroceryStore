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
public class DetailOrder {
    int orderID,productID,quantity;
    String customerName,orderDate,employeeName,shippedDate,productName;
    double unit;
    int status;
    
    public DetailOrder() {
    }

    public DetailOrder(int orderID, String customerName, 
            String orderDate, String employeeName, String shippedDate,
            int productID,String productName,int quantity,double unit,int status) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.employeeName = employeeName;
        this.shippedDate = shippedDate;
        this.productID = productID;
        this.productName = productName;
        this.quantity=quantity;
        this.unit=unit;
        this.status=status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DetailOrder{" + "orderID=" + orderID + ", customerName=" + customerName + ", orderDate=" + orderDate + ", employeeName=" + employeeName + ", shippedDate=" + shippedDate + '}';
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }
    
    
    
}
