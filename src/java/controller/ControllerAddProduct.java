/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Products;
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
import model.DAOProducts;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerAddProduct", urlPatterns = {"/ControllerAddProduct"})
public class ControllerAddProduct extends HttpServlet {

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
            DAOProducts dao = new DAOProducts();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllProduct";
            }
            if (service.equals("InsertProduct")) {

                String pName = request.getParameter("pName");
                String spID = request.getParameter("spID");
                String ctID = request.getParameter("ctID");
                String quantity = request.getParameter("quantity");
                String unitInPrice = request.getParameter("uIP");
                String unitsOnStock = request.getParameter("uIS");
                String unitsOnOrder = request.getParameter("uOD");
                String reorder = request.getParameter("pCode");
                String dis = request.getParameter("discontinued");

                if (pName == "") {
                    out.print("<h3 style='color:red'>Product Name cannot Empty </h3>");
                    return;
                }

                if (spID == "") {
                    out.print("<h3 style='color:red'>Supplier Name cannot Empty </h3>");
                    return;
                }

                if (ctID == "") {
                    out.print("<h3 style='color:red'>Category Name cannot Empty </h3>");
                    return;
                }
                if (quantity == "") {
                    out.print("<h3 style='color:red'>Quantity Per Unit cannot Empty </h3>");
                    return;
                }
                String regu1 = "^[0-9]+,[0-9]+$";
                if (unitInPrice == "" || unitInPrice.matches(regu1)) {
                    out.print("<h3 style='color:red'>Unit Price not confom</h3>");
                    return;
                }
                String regu2 = "^[0-9]+$";
                if (unitsOnStock == "" || unitsOnStock.matches(regu1)) {
                    out.print("<h3 style='color:red'>Unit Price not confom</h3>");
                    return;
                }
                if (unitsOnOrder == "" || unitsOnOrder.matches(regu1)) {
                    out.print("<h3 style='color:red'>Unit On Order not confom</h3>");
                    return;
                }
                if (reorder == "" || reorder.matches(regu1)) {
                    out.print("<h3 style='color:red'>Reorder Level not confom</h3>");
                    return;
                }
                if (dis == "" || dis.matches(regu1)) {
                    out.print("<h3 style='color:red'>Reorder Level not confom</h3>");
                    return;
                }

                int reorderValue = 0, unitS = 0, unitO = 0, supplierID, categoryID, disValue;
                double unitp;

                unitp = Double.parseDouble(unitInPrice);
                unitS = Integer.parseInt(unitsOnStock);
                unitO = Integer.parseInt(unitsOnOrder);
                reorderValue = Integer.parseInt(reorder);

                supplierID = Integer.parseInt(spID);
                categoryID = Integer.parseInt(ctID);
                disValue = Integer.parseInt(dis);
                Products pro = new Products(pName, supplierID, categoryID, quantity,
                        unitp, unitS, unitO, reorderValue, disValue);
                int n = dao.addProducts(pro);
                if (n > 0) {
                    response.sendRedirect("ControllerAddProduct");
                } else {
                    out.print("<h1>Not Inserted </h1>");
                }
            }
            if (service.equals("listAllProduct")) {
//                out.print("listAll");
//                list All
//                pre data for jsp

                Vector<Products> vector = dao.listAllProducts();
                String titlePage = "Product Manager";
                String titleTable = "List of Product";
                //set data for request
                request.setAttribute("list", vector);
                request.setAttribute("titlepage", titlePage);
                request.setAttribute("titletable", titleTable);
                //select jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listProduct.jsp");
                //run
                dispath.forward(request, response);

            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
//                out.print("Updated");
                    // get record -> display form
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    ResultSet rs = dao.getData("select * from Products where ProductID =" + pid);
                    ResultSet rs1 = dao.getData("Select * from Suppliers");
                    ResultSet rs2 = dao.getData("Select * from Categories");
                    String titlePage = "Update Products";

                    request.setAttribute("title", titlePage);
                    request.setAttribute("rs", rs);
                    request.setAttribute("rs1", rs1);
                    request.setAttribute("rs2", rs2);

                     RequestDispatcher dispath
                            = request.getRequestDispatcher("/JSP/updateProduct.jsp");
                    // run
                    dispath.forward(request, response);
                } else {
                    // step2: update
                    int spid = Integer.parseInt(request.getParameter("pID"));
                    String pName = request.getParameter("pName");
                    String spID = request.getParameter("spID");
                    int ctID = Integer.parseInt(request.getParameter("ctID"));
                    String quantity = request.getParameter("quantity");
                    double unitInPrice = Double.parseDouble(request.getParameter("uIP"));
                    int unitsOnStock = Integer.parseInt(request.getParameter("uIS"));
                    int unitsOnOrder = Integer.parseInt(request.getParameter("uOD"));
                    int reorder = Integer.parseInt(request.getParameter("pCode"));
                    int dis = Integer.parseInt(request.getParameter("discontinued"));

                    int n = dao.updateProducts(new Products(dis, pName, spid, ctID, quantity, unitInPrice, unitsOnStock, unitsOnOrder, reorder, dis));
                    response.sendRedirect("ControllerAddProduct");
                }
            }
            if (service.equals("deleteProduct")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                int n = dao.removeProduct(pid);
                if (n > 0) {
                    response.sendRedirect("ControllerAddProduct");
                } else {
                    out.print("Cannot Delete");
                }
            }
            if (service.equals("search by name")) {
                String name = request.getParameter("pname").trim();
                String pname = "";

                for (int i = 0; i < name.length(); i++) {
                    if ((name.charAt(i) + "").equals("'")) {
                        pname += "''";
                    } else {
                        pname += name.charAt(i) + "";
                    }
                }
                ResultSet pro = dao.getData("select * from Products where ProductName like '%" + pname + "%'");

            }
            if (service.equals("search by price")) {
                double from = Double.parseDouble(request.getParameter("from"));
                double to = Double.parseDouble(request.getParameter("to"));
                ResultSet pro = dao.getData("select * from Products where  UnitPrice >= " + from + " and UnitPrice <= " + to);

            }
            if (service.equals("join")) {
                String sql = "select c.CustomerID,o.OrderID,e.EmployeeID,p.ProductID "
                        + "from Customers c join Orders o "
                        + "on c.CustomerID=o.CustomerID join Employees e "
                        + "on e.EmployeeID=o.EmployeeID join [Order Details] ot "
                        + "on ot.OrderID=o.OrderID join Products p "
                        + "on p.ProductID=ot.ProductID";
                ResultSet join = dao.join();

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
