/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Employees;
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
import model.DAOEmployees;
//import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerAddEmployee", urlPatterns = {"/ControllerAddEmployee"})
public class ControllerAddEmployee extends HttpServlet {

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

            String service = request.getParameter("do");
            DAOEmployees dao = new DAOEmployees();
            if (service == null) {
                service = "listAllEmployees";
            }

            if (service.equals("insertEmployee")) {

                try {
                    String lastName = request.getParameter("lastName");
                    String firstName = request.getParameter("firstName");
                    String title = request.getParameter("title");
                    String titleOfCourtesy = request.getParameter("titleOfCourtesy");
                    String birthDate = request.getParameter("birthDate");
                    String hireDate = request.getParameter("hireDate");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String country = request.getParameter("country");
                    String homePhone = request.getParameter("homePhone");
                    String extension = request.getParameter("extension");
                    String photo = request.getParameter("photo");
                    String notes = request.getParameter("notes");
                    String reportsTo = request.getParameter("reportsTo");
                    String photoPath = request.getParameter("photoPath");

                    dao.addEmployees(new entity.Employees(lastName, firstName, title,
                            titleOfCourtesy, birthDate, hireDate, address, city,
                            region, postalCode, country, homePhone, extension,
                            notes, (Integer.parseInt(reportsTo)), photoPath));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
            if (service.equals("listAllEmployees")) {

                Vector<Employees> vector = dao.listAllEmp();

                String titlePage = "Employee Manager";
                String titleTable = "List of Employees";

                request.setAttribute("list", vector);
                request.setAttribute("titlePage", titlePage);
                request.setAttribute("titleTable", titleTable);

                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listEmployee.jsp");
                //run
                dispath.forward(request, response);

            }
            if (service.equals("updateEmployee")) {
                String isUpdate = request.getParameter("submit");
                String employeeId = request.getParameter("id");

                if (isUpdate == null) {
                    ResultSet rs = dao.getData("select * from Employees where EmployeeID = " + employeeId);
                    String title = "Update Employee";
                    
                    request.setAttribute("title", title);
                    request.setAttribute("list", rs);

                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateEmployee.jsp");
                    dispath.forward(request, response);
                    
                } else {
                    String empId = request.getParameter("employeeID");
                    String lastName = request.getParameter("lastName");
                    String firstName = request.getParameter("firstName");
                    String title = request.getParameter("title");
                    String titleOfCourtesy = request.getParameter("titleOfCourtesy");
                    String birthDate = request.getParameter("birthDate");
                    String hireDate = request.getParameter("hireDate");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String country = request.getParameter("country");
                    String homePhone = request.getParameter("homePhone");
                    String extension = request.getParameter("extension");
                    String photo = request.getParameter("photo");
                    String notes = request.getParameter("notes");
                    String reportsTo = request.getParameter("reportsTo");
                    String photoPath = request.getParameter("photoPath");

                    int n = dao.updateEmployees(new Employees((Integer.parseInt(empId)), lastName, firstName, title,
                            titleOfCourtesy, birthDate, hireDate, address, city, region,
                            postalCode, country, homePhone, extension, notes, (Integer.parseInt(reportsTo)), photoPath));
                    out.print(new Employees((Integer.parseInt(empId)), lastName, firstName, title,
                            titleOfCourtesy, birthDate, hireDate, address, city, region,
                            postalCode, country, homePhone, extension, notes, (Integer.parseInt(reportsTo)), photoPath));
                    if(n>0) response.sendRedirect("ControllerAddEmployee");
                    else out.print("Cannot Update Employee");
                }
            }
            if(service.equals("deleteEmployee")){
                int id= Integer.parseInt(request.getParameter("id"));
                int n = dao.deletebyID(id);
                if(n>0){
                    response.sendRedirect("ControllerAddEmployee");
                }
                else out.print("Cannot Delete Empoyee");
                
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
