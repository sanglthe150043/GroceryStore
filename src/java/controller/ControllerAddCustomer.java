/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerAddCustomer", urlPatterns = {"/ControllerAddCustomer"})
public class ControllerAddCustomer extends HttpServlet {

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
            DAOCustomers dao = new DAOCustomers();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllCustomer";
            }
            if (service.equals("insertCustomer")) {
                String cID = request.getParameter("cID");
                String cpName = request.getParameter("cpName");
                String ctName = request.getParameter("ctName");
                String ctTitle = request.getParameter("ctTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String pCode = request.getParameter("pCode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");

                if (cID.isEmpty()) {
                    out.print("<h3 style='color:red'>CustomerID cannot Null </h3>");
                    return;
                }
                if (cpName.isEmpty()) {
                    out.print("<h3 style='color:red'>Company Name cannot Null </h3>");
                    return;
                }
                if (ctName.isEmpty()) {
                    out.print("<h3 style='color:red'>Contact Name cannot Null </h3>");
                    return;
                }
                if (ctTitle.isEmpty()) {
                    out.print("<h3 style='color:red'>Contact Title cannot Null </h3>");
                    return;
                }
                if (address.isEmpty()) {
                    out.print("<h3 style='color:red'>Address cannot Null </h3>");
                    return;
                }
                if (city.isEmpty()) {
                    out.print("<h3 style='color:red'>City cannot Null </h3>");
                    return;
                }
                if (region.isEmpty()) {
                    out.print("<h3 style='color:red'>Region cannot Null </h3>");
                    return;
                }
                if (pCode.isEmpty()) {
                    out.print("<h3 style='color:red'>Postal Code cannot Null </h3>");
                    return;
                }
                if (country.isEmpty()) {
                    out.print("<h3 style='color:red'>Country cannot Null </h3>");
                    return;
                }
                if (fax.isEmpty()) {
                    out.print("<h3 style='color:red'>Fax cannot Null </h3>");
                    return;
                }
                String reg = "^[0-9]+$";
                if (phone.isEmpty() || !phone.matches(reg)) {
                    out.print("<h3 style='color:red'> Phone Number must only contain number </h3>");
                    return;
                }
                Customers cus = new Customers(cID, cpName, ctName, ctTitle,
                        address, city, region, pCode, country, phone, fax);
                int n = dao.addCustomers(cus);
                if (n > 0) {
                    response.sendRedirect("ControllerAddCustomer");
                } else {
                    out.print("<h1>Not Inserted</h1>");
                }

            }

            if (service.equals("listAllCustomer")) {
                Vector<Customers> cus = dao.listAllCus();
                String title = "Customers Manager";
                String table = "List Customers";
                
                request.setAttribute("titlePage", title);
                request.setAttribute("titleTable", table);
                request.setAttribute("list", cus);
                
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listCustomer.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                    String id = request.getParameter("id");
                if (submit == null) {
                    ResultSet rs = dao.getData("select * from Customers where CustomerID ='" + id + "'");
                    String title = "Update Customer";
                    
                    request.setAttribute("title", title);
                    request.setAttribute("list", rs); 
                    
                     RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateCustomer.jsp");
                dispath.forward(request, response);
                    
                }else {
                String cID = request.getParameter("customerID");
                String cpName = request.getParameter("companyName");
                String ctName = request.getParameter("contactName");
                String ctTitle = request.getParameter("contactTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String pCode = request.getParameter("postalCode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");

                if (cID.isEmpty()) {
                    out.print("<h3 style='color:red'>CustomerID cannot Null </h3>");
                    return;
                }
                if (cpName.isEmpty()) {
                    out.print("<h3 style='color:red'>Company Name cannot Null </h3>");
                    return;
                }
                if (ctName.isEmpty()) {
                    out.print("<h3 style='color:red'>Contact Name cannot Null </h3>");
                    return;
                }
                if (ctTitle.isEmpty()) {
                    out.print("<h3 style='color:red'>Contact Title cannot Null </h3>");
                    return;
                }
                if (address.isEmpty()) {
                    out.print("<h3 style='color:red'>Address cannot Null </h3>");
                    return;
                }
                if (city.isEmpty()) {
                    out.print("<h3 style='color:red'>City cannot Null </h3>");
                    return;
                }
                if (region.isEmpty()) {
                    out.print("<h3 style='color:red'>Region cannot Null </h3>");
                    return;
                }
                if (pCode.isEmpty()) {
                    out.print("<h3 style='color:red'>Postal Code cannot Null </h3>");
                    return;
                }
                if (country.isEmpty()) {
                    out.print("<h3 style='color:red'>Country cannot Null </h3>");
                    return;
                }
                if (fax.isEmpty()) {
                    out.print("<h3 style='color:red'>Fax cannot Null </h3>");
                    return;
                }
//                String reg = "^[0-9]+$";
//                if (phone.isEmpty() || !phone.matches(reg)) {
//                    out.print("<h3 style='color:red'> Phone Number must only contain number </h3>");
//                    return;
//                }
                int n = dao.updateCustomers(new Customers(cID, cpName, ctName, ctTitle,
                        address, city, region, pCode, country, phone, fax));
                if (n > 0) {
                    response.sendRedirect("ControllerAddCustomer");
                } else {
                    out.print("Cannot Update Customer");
                }
            }}
            if (service.equals("deleteCustomer")) {
                String id = (request.getParameter("id"));
                int n = dao.deletebyID(id);
                if(n>0) response.sendRedirect("ControllerAddCustomer");
                else out.print("Cannot Delete Customer");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
