/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.DetailOrder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOOrderDetails;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderList", urlPatterns = {"/OrderList"})
public class OrderList extends HttpServlet {

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

            DAOOrderDetails dao1 = new DAOOrderDetails();
            String service = request.getParameter("do");
            if (service == null) {
                service = "list";
            }
            if (service.equals("detail")) {
                String oID = request.getParameter("id");
                Vector<DetailOrder> vector2 = dao1.listDetailsbyOrderID(oID);

                request.setAttribute("list2", vector2);

                RequestDispatcher dispath2 = request.getRequestDispatcher("/JSP/OrderDetails_BT.jsp");
                dispath2.forward(request, response);
            }

            if (service.equals("list")) {
                Vector<DetailOrder> vector = dao1.listDetails();

                String title = "OrderList Manager";
                String table = "List Orders";

                request.setAttribute("listo", vector);
                request.setAttribute("titlePage", title);
                request.setAttribute("titleTable", table);

                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/OrderList.jsp");
                dispath.forward(request, response);
            }

            if (service.equals("updateStatus")) {
                    String oID = request.getParameter("oID");
                    int id = Integer.parseInt(oID);
                    int status = Integer.parseInt(request.getParameter("s"+oID));
                    int n = dao1.updateStatus(id, status);
                    if (n > 0) {
                        response.sendRedirect("OrderList");
                    } else {
                        out.print("Cannot update Status");
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
