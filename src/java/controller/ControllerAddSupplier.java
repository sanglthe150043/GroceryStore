/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Suppliers;
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
import model.DAOSuppliers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerAddSupplier", urlPatterns = {"/ControllerAddSupplier"})
public class ControllerAddSupplier extends HttpServlet {

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
            DAOSuppliers dao = new DAOSuppliers();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllSupplier";
            }
            if (service.equals("insertSupplier")) {
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
                String homePage = request.getParameter("homePage");

                if (cpName == "") {
                    out.print("<h3 style='color:red'>Company Name cannot Null </h3>");
                    return;
                }
                if (ctName == "") {
                    out.print("<h3 style='color:red'>Contact Name cannot Null </h3>");
                    return;
                }
                if (ctTitle == "") {
                    out.print("<h3 style='color:red'>Contact Title cannot Null </h3>");
                    return;
                }
                if (address == "") {
                    out.print("<h3 style='color:red'>Address cannot Null </h3>");
                    return;
                }
                if (city == "") {
                    out.print("<h3 style='color:red'>City cannot Null </h3>");
                    return;
                }
                if (region == "") {
                    out.print("<h3 style='color:red'>Region cannot Null </h3>");
                    return;
                }
                if (country == "") {
                    out.print("<h3 style='color:red'>Country cannot Null </h3>");
                    return;
                }
                if (fax == "") {
                    out.print("<h3 style='color:red'>Fax cannot Null </h3>");
                    return;
                }
                if (homePage == "") {
                    out.print("<h3 style='color:red'>Home Page cannot Null </h3>");
                    return;
                }

                String reg = "^[0-9]+$";
                if (phone.isEmpty() || !phone.matches(reg)) {
                    out.print("<h3 style='color:red'> Phone Number must only contain number </h3>");
                    return;
                }

                Suppliers sup = new Suppliers(cpName, ctName, ctTitle, address,
                        city, region, pCode, country, phone, fax, homePage);
                int n = dao.addSuppliers(sup);
                if (n > 0) {
                    response.sendRedirect("ControllerAddSupplier");
                } else {
                    out.print("<h1>Not Inserted</h1>");
                }
            }
            if (service.equals("listAllSupplier")) {
                Vector<Suppliers> sup = dao.listAllSup();
                String titlePage = "Suppliers Manager";
                String titleTable = "List of Suppliers";

                request.setAttribute("list", sup);
                request.setAttribute("titlePage", titlePage);
                request.setAttribute("titleTable", titleTable);

                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listSuppliers.jsp");
                //run
                dispath.forward(request, response);
            }

            if (service.equals("updateSupplier")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String id = request.getParameter("id");
                    ResultSet rs = dao.getData("select * from Suppliers where SupplierID=" + id);
                    String title = "Update Supplier";
                    request.setAttribute("list", rs);
                    request.setAttribute("title", title);

                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateSupplier.jsp");
                    dispath.forward(request, response);
                } else {

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
                    String homePage = request.getParameter("homePage");

                    if (cpName == "") {
                        out.print("<h3 style='color:red'>Company Name cannot Null </h3>");
                        return;
                    }
                    if (ctName == "") {
                        out.print("<h3 style='color:red'>Contact Name cannot Null </h3>");
                        return;
                    }
                    if (ctTitle == "") {
                        out.print("<h3 style='color:red'>Contact Title cannot Null </h3>");
                        return;
                    }
                    if (address == "") {
                        out.print("<h3 style='color:red'>Address cannot Null </h3>");
                        return;
                    }
                    if (city == "") {
                        out.print("<h3 style='color:red'>City cannot Null </h3>");
                        return;
                    }
                    if (region == "") {
                        out.print("<h3 style='color:red'>Region cannot Null </h3>");
                        return;
                    }
                    if (country == "") {
                        out.print("<h3 style='color:red'>Country cannot Null </h3>");
                        return;
                    }
                    if (fax == "") {
                        out.print("<h3 style='color:red'>Fax cannot Null </h3>");
                        return;
                    }
                    if (homePage == "") {
                        out.print("<h3 style='color:red'>Home Page cannot Null </h3>");
                        return;
                    }

//                    String reg = "^[0-9]+$";
//                    if (phone.isEmpty() || !phone.matches(reg)) {
//                        out.print("<h3 style='color:red'> Phone Number must only contain number </h3>");
//                        return;
//                    }
                    int id = Integer.parseInt(request.getParameter("supplierID"));
                    int n = dao.updateSuppliers(new Suppliers(id, cpName, ctName, ctTitle, address,
                            city, region, pCode, country, phone, fax, homePage));
                    if (n > 0) {
                        response.sendRedirect("ControllerAddSupplier");
                    } else {
                        out.print("<h1>Not Update</h1>");
                    }
                }
            }

            if (service.equals("deleteSupplier")) {
                int n = dao.deletebyID(Integer.parseInt(request.getParameter("id")));
                if (n > 0) {
                    response.sendRedirect("ControllerAddSupplier");
                } else {
                    out.print("Cannot Delete");
                }
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
