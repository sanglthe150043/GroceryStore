/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.DAOProducts;
import model.DAOSuppliers;


/**
 *
 * @author Admin
 */
@WebServlet(name = "ControllerHome", urlPatterns = {"/ControllerHome"})
public class ControllerHome extends HttpServlet {

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
            Vector<Products> products = null;
            List<Products> pro ;
            
            //Get Data from DB
            if(service==null || service.equals("all")){
                service="all";
                products = new DAOProducts().listAllProducts();
            }
            else if(!service.equals("searchByName") && !service.equals("all")){
                products = new DAOProducts().searchByCountry(service);
            }else if(service.equals("searchByName")){
                String name = request.getParameter("pName");
                request.setAttribute("name", name);
                products = new DAOProducts().searchbyName(name);
            }
            
            // Xử lý Page Products
            int indexPage=1;
            int size = (int)Math.ceil((double)products.size()/6);
            if(size<=1){
                pro =products.subList(0,products.size());  
            }
            else{
                String page = request.getParameter("page");
                if(page==null ) indexPage =1;
                else indexPage=Integer.parseInt(page);
                if(indexPage==size) pro =products.subList(6*(size-1),products.size());
                else pro =products.subList(6*(indexPage-1),6*indexPage);
            }
            
            request.setAttribute("service",service);
            request.setAttribute("index", indexPage);
            request.setAttribute("size", size);
            request.setAttribute("listProducts",pro);
            Vector<String> listType = new DAOSuppliers().top5Country();
            request.setAttribute("type", listType);
            
            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Shop.jsp");
            dispath.forward(request, response);
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
