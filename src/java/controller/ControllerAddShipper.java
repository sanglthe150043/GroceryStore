/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Shippers;
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
import model.DAOShippers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerAddShipper", urlPatterns = {"/ControllerAddShipper"})
public class ControllerAddShipper extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {

            DAOShippers dao = new DAOShippers();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllShipper";
            }

            if (service.equals("insertShipper")) {
                String cpName = request.getParameter("cpName");
                String phone = request.getParameter("phone");

                if (cpName == "") {
                    out.print("<h3 style='color:red'>Company Name cannot Empty </h3>");
                    return;
                }
                String reg = "^[0-9]+$";
                if (phone.isEmpty() || !phone.matches(reg)) {
                    out.print("<h3 style='color:red'> Phone Number must only contain number </h3>");
                    return;
                }
                Shippers ship = new Shippers(cpName, phone);
                int n = dao.addShippers(ship);
                if (n > 0) {
                    response.sendRedirect("ControllerAddShipper");
                } else {
                    out.print("<h1>Not Inserted</h1>");
                }
            }
            if (service.equals("listAllShipper")) {

                Vector<Shippers> ship = dao.listAllShi();
                String titlePage = "Shippers Manager";
                String titleTable = "List of Shippers";

                request.setAttribute("list", ship);
                request.setAttribute("titlePage", titlePage);
                request.setAttribute("titleTable", titleTable);

                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listShippers.jsp");
                //run
                dispath.forward(request, response);
            }

            if (service.equals("updateShipper")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String id = request.getParameter("id");
                    ResultSet rs = dao.getData("select * from Shippers where ShipperID=" + id);
                    String title = "Update Shipper";
                    request.setAttribute("title", title);
                    request.setAttribute("list", rs);
                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateShipper.jsp");
                    dispath.forward(request, response);
                } else {
                    int id = Integer.parseInt(request.getParameter("shipperID"));
                    String cpName = request.getParameter("companyName");
                    String phone = request.getParameter("phone");

                    if (cpName == "") {
                        out.print("<h3 style='color:red'>Company Name cannot Empty </h3>");
                        return;
                    }
//                    String reg = "^[0-9]+$";
//                    if (phone.isEmpty() || !phone.matches(reg)) {
//                        out.print("<h3 style='color:red'> Phone Number must only contain number </h3>");
//                        return;
//                    }
                    Shippers ship = new Shippers(id, cpName, phone);
                    int n = dao.updateShippers(ship);
                    if (n > 0) {
                        response.sendRedirect("ControllerAddShipper");
                    } else {
                        out.print("<h1>Not Updated</h1>");
                    }
                }
            }

            if (service.equals("deleteShipper")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int n = dao.deletebyID(id);
                if (n > 0) {
                    response.sendRedirect("ControllerAddShipper");
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
