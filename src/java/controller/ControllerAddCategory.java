/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categories;
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
import model.DAOCategories;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerAddCategory", urlPatterns = {"/ControllerAddCategory"})
public class ControllerAddCategory extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("do");
            DAOCategories dao = new DAOCategories();
            if (service == null) {
                service = "listAllCategory";
            }
            if (service.equals("insertCategory")) {
//            CategoryName,Description,Picture;
                String cName = request.getParameter("cName");
                String des = request.getParameter("des");
                String pic = request.getParameter("picture");

                if (cName == null || cName.isEmpty()) {
                    out.print("<h3 style='color:red'>Category Name cannot Empty </h3>");
                    return;
                }
                if (des == null || des.isEmpty()) {
                    out.print("<h3 style='color:red'>Description cannot Empty </h3>");
                    return;
                }
                if (pic == null || pic.isEmpty()) {
                    out.print("<h3 style='color:red'>Picture cannot Empty </h3>");
                    return;
                }
                if (cName.length() > 15) {
                    out.print("<h3 style='color:red'>Category Name is most 15 character</h3>");
                    return;
                }
                Categories cat = new Categories(cName, des, pic);
                int n = dao.addCategories(cat);
                if (n > 0) {
                    response.sendRedirect("ControllerAddCategory");
                } else {
                    out.print("<h1>Not Inserted</h1>");
                }

            }
            if (service.equals("listAllCategory")) {
                Vector<Categories> vector = dao.listAllCategories();

                String titlePage = "CategoriesManager";
                String titleTable = "List of Categories";
                //set data for request
                request.setAttribute("list", vector);
                request.setAttribute("titlepage", titlePage);
                request.setAttribute("titletable", titleTable);
                //select jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listCategories.jsp");
                //run
                dispath.forward(request, response);
            }
            if (service.equals("updateCategory")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ResultSet rs = dao.getData("select * from Categories where CategoryID=" + id);
                    String title="Update Category";
                            
                    request.setAttribute("title", title);
                    request.setAttribute("list", rs);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/JSP/updateCategories.jsp");
                    //run
                    dispath.forward(request, response);
                } else {
                    int id = Integer.parseInt(request.getParameter("categoryID"));
                    String cName = request.getParameter("categoryName");
                    String des = request.getParameter("description");
//                    String pic = request.getParameter("pic");

                    if (cName == null || cName.isEmpty()) {
                        out.print("<h3 style='color:red'>Category Name cannot Empty </h3>");
                        return;
                    }
                    if (des == null || des.isEmpty()) {
                        out.print("<h3 style='color:red'>Description cannot Empty </h3>");
                        return;
                    }
//                    if (pic == null || pic.isEmpty()) {
//                        out.print("<h3 style='color:red'>Picture cannot Empty </h3>");
//                        return;
//                    }
                    if (cName.length() > 15) {
                        out.print("<h3 style='color:red'>Category Name is most 15 character</h3>");
                        return;
                    }
                    int n = dao.updateCategories(new Categories(id, cName, des));
                    if(n>0){
                        response.sendRedirect("ControllerAddCategory");
                    }else {
                        out.print("Cannot update");
                    }
                }
            }
            if (service.equals("deleteCategory")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int n = dao.deletebyID(id);
                if (n > 0) {
                    response.sendRedirect("ControllerAddCategory");
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
