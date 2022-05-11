/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categories;
import entity.Customers;
import entity.DetailOrder;
import entity.Products;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOCategories;
import model.DAOCustomers;
import model.DAOOrderDetails;
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerUpdate", urlPatterns = {"/ControllerUpdate"})
public class ControllerUpdate extends HttpServlet {

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
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("ControllerLogin?do=loginE");
            } else {
                String service = request.getParameter("do");
                if (service != null && service.equals("updateProduct")) {
                    String pid = request.getParameter("pid");
                    DAOProducts dao = new DAOProducts();
                    DAOCategories dao1 = new DAOCategories();
                    DAOSuppliers dao2 = new DAOSuppliers();
                    Vector<Categories> cate = dao1.listAllCategories();
                    Vector<Suppliers> sup = dao2.listAllSup();
                    Products product = dao.searchByID(pid);
                    request.setAttribute("product", product);
                    request.setAttribute("categories", cate);
                    request.setAttribute("suppliers", sup);
                }
                if (service != null && service.equals("updateCustomer")) {
                    String pid = request.getParameter("cid");
                    DAOCustomers dao = new DAOCustomers();
                    Customers customer = dao.getCustomerByID(pid);
                    request.setAttribute("customer", customer);
                }
                if (service != null && service.equals("detail")) {
                    String oid = request.getParameter("oid");
                    DAOOrderDetails dao = new DAOOrderDetails();
                    Vector<DetailOrder> orders = dao.listDetailsbyOrderID(oid);
                    request.setAttribute("orders", orders);
                }
                if (service != null && service.equals("upP")) {
                    String productID = request.getParameter("productID");
                    String productName = request.getParameter("productName");
                    String supplierID = request.getParameter("supplierID");
                    String categoryID = request.getParameter("categoryID");
                    String quantityPerUnit = request.getParameter("quantityPerUnit");
                    String unitPrice = request.getParameter("unitPrice");
                    String unitsInStock = request.getParameter("unitsInStock");
                    String unitsInOrder = request.getParameter("unitsInOrder");
                    String reorderLevel = request.getParameter("reorderLevel");
                    String discontinued = request.getParameter("discontinued");
                    DAOProducts dao3 = new DAOProducts();
                    dao3.updateProducts(new Products(Integer.parseInt(productID), productName, Integer.parseInt(supplierID),
                            Integer.parseInt(categoryID), quantityPerUnit, Double.parseDouble(unitPrice),
                            Integer.parseInt(unitsInStock), Integer.parseInt(unitsInOrder),
                            Integer.parseInt(reorderLevel), Integer.parseInt(discontinued)));
                    response.sendRedirect("ControllerUpdate?do=updateProduct&pid=" + productID + "");
                }
                if (service != null && service.equals("upC")) {
                    String customerID = request.getParameter("customerID");
                    String companyName = request.getParameter("companyName");
                    String contactName = request.getParameter("contactName");
                    String contactTitle = request.getParameter("contactTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    DAOCustomers dao = new DAOCustomers();
                    dao.updateCustomers(new Customers(customerID, companyName, contactName,
                            contactTitle, address, city, region, postalCode, city, phone, fax));
                    response.sendRedirect("ControllerUpdate?do=updateCustomer&cid=" + customerID + "");
                }
                if (service != null && service.equals("updateStatus")) {
                    String status = request.getParameter("status");
                    String oid = request.getParameter("oid");
                    DAOOrderDetails dao = new DAOOrderDetails();
                    dao.updateStatus(Integer.parseInt(oid), Integer.parseInt(status));
                    response.sendRedirect("ControllerUpdate?do=detail&oid=" + oid + "");
                } else {
                    request.setAttribute("service", service);
                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Update.jsp");
                    dispath.forward(request, response);
                }

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
