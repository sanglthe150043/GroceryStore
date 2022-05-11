/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categories;
import entity.Customers;
import entity.Products;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOCategories;
import model.DAOCustomers;
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author Admin
 */
//@WebServlet(name = "ControllerAdd", urlPatterns = {"/ControllerAdd"})
public class ControllerAdd extends HttpServlet {

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
            String service = request.getParameter("do");
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("ControllerLogin?do=loginE");
            } else {
                if (service != null && service.equals("addProduct")) {
                    DAOCategories dao1 = new DAOCategories();
                    DAOSuppliers dao2 = new DAOSuppliers();
                    Vector<Categories> cate = dao1.listAllCategories();
                    Vector<Suppliers> sup = dao2.listAllSup();
                    request.setAttribute("categories", cate);
                    request.setAttribute("suppliers", sup);
                    request.setAttribute("service", service);
                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Add.jsp");
                    dispath.forward(request, response);
                }else if (service != null && service.equals("addC")) {
                    String customerID = request.getParameter("customerID");
                    String companyName = request.getParameter("companyName");
                    String contactName = request.getParameter("contactName");
                    String contactTitle = request.getParameter("contactTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String country = request.getParameter("country");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    Customers customer = new Customers(customerID, companyName, contactName, 
                            contactTitle, address, city, region, postalCode, country, phone, fax);
                    DAOCustomers dao = new DAOCustomers();
                    dao.addCustomers(customer);
                    response.sendRedirect("ControllerManager?do=managerCustomers");
                }else if (service != null && service.equals("addP")) {
                    String productName = request.getParameter("productName");
                    String supplierID = request.getParameter("supplierID");
                    String categoryID = request.getParameter("categoryID");
                    String quantityPerUnit = request.getParameter("quantityPerUnit");
                    String unitPrice = request.getParameter("unitPrice");
                    String unitInStock = request.getParameter("unitInStock");
                    String unitOnOrder = request.getParameter("unitOnOrder");
                    String reorderLevel = request.getParameter("reorderLevel");
                    String discontinued = request.getParameter("discontinued");
                    Products product = new Products(productName, Integer.parseInt(supplierID),
                            Integer.parseInt(categoryID), quantityPerUnit, Double.parseDouble(unitPrice),
                            Integer.parseInt(unitInStock), Integer.parseInt(unitOnOrder),
                            Integer.parseInt(reorderLevel), Integer.parseInt(discontinued));
                    DAOProducts dao = new DAOProducts();
                    dao.addProducts(product);
                    response.sendRedirect("ControllerManager?do=managerProducts");
                }else{
                    request.setAttribute("service", service);
                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Add.jsp");
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







//private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
//    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z    
//    private static final String ALPHA_NUMERIC = alphaUpperCase;
//    
//    private static Random generator = new Random();
//    
//    public String randomString(int numberOfCharactor) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < numberOfCharactor; i++) {
//            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
//            char ch = ALPHA_NUMERIC.charAt(number);
//            sb.append(ch);
//        }
//        return sb.toString();
//    }
//    
//    public static int randomNumber(int min, int max) {
//        return generator.nextInt((max - min) + 1) + min;
//    }
//    
//    public String makeNewId() {
//        while(true){
//            String newId = randomString(5);
//            String checkID = checkCustomerID(newId);
//            if(checkID==null) return newId;
//        }
//    }
