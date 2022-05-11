/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import entity.Employees;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOCustomers;
import model.DAOEmployees;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin"})
public class ControllerLogin extends HttpServlet {

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
            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Login.jsp");
            request.setAttribute("service", service);
            if (service.equals("logoutC")) {
//                session.removeAttribute("user");
                session.invalidate();
                response.sendRedirect("ControllerHome");
            } else if (service.equals("inC")) {
                DAOCustomers daoC = new DAOCustomers();
                String user = request.getParameter("usernameC");
                String password = request.getParameter("passwordC");
                Customers customer = daoC.checkUser(user, password);
                if (customer != null) {
                    session.setAttribute("user", customer);
                    response.sendRedirect("ControllerCart?do=");
                } else {
                    request.setAttribute("service", "loginC");
                    request.setAttribute("service2", "ero");
                    dispath.forward(request, response);
                }

            } else if (service.equals("regisC")) {
                String customerID = request.getParameter("customerID");
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                
                Customers cus = new Customers(customerID, "DEMO", "DEMO", "DEMO",
                        "DEMO", "DEMO", "DEMO", "DEMO", "DEMO", "DEMO", "DEMO",username,password);
                int n = new DAOCustomers().register(cus);
                if (n != 0) {
                    session.setAttribute("user", cus);
                    response.sendRedirect("ControllerCart?do=");
                }else{
                    request.setAttribute("service", "registerC");
                    request.setAttribute("service2", "ero");
                    dispath.forward(request, response);
                }
            }else if (service.equals("logoutE")) {
                session.removeAttribute("admin");
                response.sendRedirect("ControllerManager?do=");
            } else if (service.equals("inE")) {
                String user = request.getParameter("usernameE");
                String password = request.getParameter("passwordE");
                Employees employee = new DAOEmployees().checkUser(user, password);
                if (employee != null) {
                    session.setAttribute("admin",employee);
                    response.sendRedirect("ControllerManager?do=inforE");
                } else {
                    request.setAttribute("service", "loginE");
                    request.setAttribute("service2", "ero");
                    dispath.forward(request, response);
                }

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
