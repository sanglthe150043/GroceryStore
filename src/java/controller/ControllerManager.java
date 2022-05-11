/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import entity.Orders;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOCustomers;
import model.DAOOrders;
import model.DAOProducts;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerManager", urlPatterns = {"/ControllerManager"})
public class ControllerManager extends HttpServlet {

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
            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Manager.jsp");
            HttpSession session = request.getSession();
            String service = request.getParameter("do");
            if (service == null) {
                service = "inforE";
            }
            request.setAttribute("service", service);
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("ControllerLogin?do=loginE");
            } else {
                int indexPage = 1, size = 0;
                if (service.equals("managerProducts")) {
                   Vector<Products> vector =null;
                    if(request.getParameter("name")!=null) vector = new DAOProducts().searchbyName(request.getParameter("name"));
                    else vector = new DAOProducts().listAllProducts();
                    List<Products> pro = null;

                    indexPage = 1;
                    size = (int) Math.ceil((double) vector.size() / 6);
                    if (size <= 1) {
                        pro = vector.subList(0, vector.size());
                    } else {
                        String page = request.getParameter("number");
                        if (page != null) {
                            indexPage = Integer.parseInt(page);
                            if (indexPage == size) {
                                pro = vector.subList(6 * (indexPage - 1), vector.size());
                            } else {
                                pro = vector.subList(6 * (indexPage - 1), 6 * indexPage);
                            }
                        } else {
                            pro = vector.subList(6 * (indexPage - 1), 6 * indexPage);
                        }
                    }

                    request.setAttribute("listProducts", pro);
                }
                if (service.equals("managerCustomers")) {
                    Vector<Customers> vector=null;
                    if(request.getParameter("name")!=null) vector = new DAOCustomers().getCustomerByName(request.getParameter("name"));
                    else vector = new DAOCustomers().listAllCus();
                    List<Customers> pro = null;

                    indexPage = 1;
                    size = (int) Math.ceil((double) vector.size() / 6);
                    if (size <= 1) {
                        pro = vector.subList(0, vector.size());
                    } else {
                        String page = request.getParameter("number");
                        if (page != null) {
                            indexPage = Integer.parseInt(page);
                            if (indexPage == size) {
                                pro = vector.subList(6 * (indexPage - 1), vector.size());
                            } else {
                                pro = vector.subList(6 * (indexPage - 1), 6 * indexPage);
                            }
                        } else {
                            pro = vector.subList(6 * (indexPage - 1), 6 * indexPage);
                        }
                    }

                    request.setAttribute("listCustomers", pro);
                }
                if (service.equals("managerOrders")) {
                    Vector<Orders> vector=null;
                    if(request.getParameter("name")!=null){
                        int s =0;
                        String status = (String)request.getParameter("name");
                        if(status.equals("wait")) s =1;
                        else if(status.equals("processing")) s=2;
                        else if(status.equals("done")) s=3;
                        vector = new DAOOrders().getOrderByName(s);
                    }
                    else vector = new DAOOrders().listAllOr();
                    List<Orders> pro = null;

                    indexPage = 1;
                    size = (int) Math.ceil((double) vector.size() / 6);
                    if (size <= 1) {
                        pro = vector.subList(0, vector.size());
                    } else {
                        String page = request.getParameter("number");
                        if (page != null) {
                            indexPage = Integer.parseInt(page);
                            if (indexPage == size) {
                                pro = vector.subList(6 * (indexPage - 1), vector.size());
                            } else {
                                pro = vector.subList(6 * (indexPage - 1), 6 * indexPage);
                            }
                        } else {
                            pro = vector.subList(6 * (indexPage - 1), 6 * indexPage);
                        }
                    }
                    request.setAttribute("listOrders", pro);
                }

                request.setAttribute("page", indexPage);
                request.setAttribute("size", size);
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
