/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOOrderDetails;
import model.DAOOrders;
import model.DAOProducts;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerCart", urlPatterns = {"/ControllerCart"})
public class ControllerCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Cart.jsp");
            HttpSession session = request.getSession();
            String servie = request.getParameter("do");
            if (servie.equals("addToCart")) {
                DAOProducts dao = new DAOProducts();
                String id = request.getParameter("pid");// get Product ID Add to Cart
                Products product = dao.searchByID(id); // get Product by ID to Add to Cart
                product.setUnitsOnOrder(1);             // set QuantityinStock =1
                Products value = (Products) session.getAttribute(id);
                if (value == null) { // If this Product not contain in Session, Add Product to Session
                    session.setAttribute(id, product);
                } else { // else if this Product contain in Session, update quantity
                    value.setUnitsOnOrder(value.getUnitsOnOrder() + 1);
                    session.setAttribute(id, value);
                }
                dispath.forward(request, response);
            } else if (servie.equals("remove")) {
                String id = request.getParameter("id");
                session.removeAttribute(id);
                response.sendRedirect("ControllerCart?do=");
            } else if (servie.equals("delete")) {// Delete all Products in Cart
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("user") && !key.equals("admin")) {
                        session.removeAttribute(key);
                    }
                }
                response.sendRedirect("ControllerCart?do=");
            } else if (servie.equals("checkOut")) {// Check out
                if (session.getAttribute("user") == null) { // If the user is not Logged in, Login is required
                    response.sendRedirect("ControllerLogin?do=loginC");
                } else {// Check out and save Data to DB
                    Customers customer = (Customers)session.getAttribute("user");
                    DAOOrders dao1 = new DAOOrders();
                    dao1.addOrders(new Orders(customer.getCustomerID(),1, "", "", "", 1, 1, "",
                            "", "", "", "", "", 1));
                    int OrderID = dao1.getLastOrderID();
                    DAOOrderDetails dao2 = new DAOOrderDetails();
                    DAOProducts dao3 = new DAOProducts();
                    java.util.Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (!key.equals("user")) {
                            Products value = (Products) session.getAttribute(key);
                            if(value.getUnitsOnOrder()>value.getUnitsInStock()){
                                String eror ="Nhap lai so luong";
                                request.setAttribute("eror", eror);
                            }
                            else{
                            dao2.addOrderDetails(new OrderDetails(OrderID, value.getProductID(),
                                    value.getUnitPrice(), value.getUnitsOnOrder(), 0));
                            dao3.updatequantity(value.getProductID(),value.getUnitsOnOrder(),value.getUnitsInStock());
                            session.removeAttribute(key);}
                        }
                    }
                    response.sendRedirect("ControllerHome");
                }
            } else if (servie.equals("updateCart")) {
                String productID = request.getParameter("productID");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                Products product = (Products) session.getAttribute(productID);
                if (quantity == 0) {
                    session.removeAttribute(productID);
                } else if (product.getUnitsOnOrder() != quantity) {
                    if(quantity > product.getUnitsInStock()){
                        String eror = "Nhap lai so luong";
                        request.setAttribute("eror", eror);
                        dispath.forward(request, response);
                    }
                    else{
                    product.setUnitsOnOrder(quantity);
                        
                    }
                }
                response.sendRedirect("ControllerCart?do=");
            } else {
                dispath.forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
