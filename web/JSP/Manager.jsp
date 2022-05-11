<%-- 
    Document   : Manager
    Created on : Mar 6, 2022, 10:04:58 AM
    Author     : Admin
--%>

<%@page import="entity.OrderDetails"%>
<%@page import="entity.Orders"%>
<%@page import="entity.Customers"%>
<%@page import="java.util.List"%>
<%@page import="entity.Products"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.Employees"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Manager</title>

        <!-- favicon -->
        <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
        <!-- google font -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
        <!-- fontawesome -->
        <link rel="stylesheet" href="assets/css/all.min.css">
        <!-- bootstrap -->
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <!-- owl carousel -->
        <link rel="stylesheet" href="assets/css/owl.carousel.css">
        <!-- magnific popup -->
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <!-- animate css -->
        <link rel="stylesheet" href="assets/css/animate.css">
        <!-- mean menu css -->
        <link rel="stylesheet" href="assets/css/meanmenu.min.css">
        <!-- main style -->
        <link rel="stylesheet" href="assets/css/main.css">
        <!-- responsive -->
        <link rel="stylesheet" href="assets/css/responsive.css">

    </head>
    <body>

        <!--PreLoader-->
        <div class="loader">
            <div class="loader-inner">
                <div class="circle"></div>
            </div>
        </div>
        <!--PreLoader Ends-->

        <!-- header -->
        <div class="top-header-area" id="sticker">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-sm-12 text-center">
                        <div class="main-menu-wrap">
                            <!-- logo -->
                            <div class="site-logo">
                                <a href="ControllerManager?do=inforE">
                                    <img src="assets/img/logo.png" alt="">
                                </a>
                            </div>
                            <!-- logo -->
                            <%String service = (String) request.getAttribute("service");%>
                            <!-- menu start -->
                            <nav class="main-menu">
                                <ul>
                                    <li class="current-list-item"><a href="ControllerManager?do=inforE">Home</a>
                                    </li>
                                    <%if (session.getAttribute("admin") != null) {%>
                                    <li><a href="ControllerLogin?do=logoutE">LogOut</a></li>
                                        <%} else {%>
                                    <li><a href="ControllerLogin?do=loginE" >LogIn</a></li>
                                    <li><a href="ControllerLogin?do=registerE">Register</a></li>
                                        <%}%>  
                                        <%if (!service.equals("inforE")) {%>
                                    <li><a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                                        <!--                                        <ul class="sub-menu">
                                                                                    <li><a  href="">Search By Name</a></li>
                                                                                    <li><a href="">Search By ID</a></li>
                                                                                </ul>-->
                                    </li><%}%>
                                </ul>
                            </nav>
                            <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                            <div class="mobile-menu"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end header -->

        <!-- search area -->
        <div class="search-area">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <span class="close-btn"><i class="fas fa-window-close"></i></span>
                        <div class="search-bar">
                            <div class="search-bar-tablecell">
                                <form action="ControllerManager" method="post">
                                    <input type="hidden" name="do" value="<%=service%>">
                                        <h3><%=service.equals("managerOrders") ? "Search By CustomerID of Order"
                                                : service.equals("managerProducts") ? "Search By Product Name" : "Search Customer Name"%></h3>
                                    <input type="text" placeholder="Name" name="name">
                                    <input type="submit" value="Search">
                                </form> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end search arewa -->

        <!-- breadcrumb-section -->
        <div class="breadcrumb-section breadcrumb-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="breadcrumb-text">
                            <p>Fresh and Organic</p>
                            <h1>MANAGE</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->


        <div class="container ">

            <div class="row">
                <div class="col-md-12">
                    <div class="product-filters">
                        <ul>
                            <li class="update <%=service.equals("managerProducts") ? " active" : ""%>">
                                <a href="ControllerManager?do=managerProducts">Manage Products</a></li>
                            <li class="update <%=service.equals("managerCustomers") ? " active" : ""%>">
                                <a href="ControllerManager?do=managerCustomers">Manage Customers</a></li>
                            <li class="update <%=service.equals("managerOrders") ? " active" : ""%>">
                                <a href="ControllerManager?do=managerOrders">Manage Orders</a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <%if (service.equals("inforE")) {
                if (session.getAttribute("admin") != null) {
                    Employees employee = (Employees) session.getAttribute("admin");
        %>
        <!-- In4 Employee-->
        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <div class="single-product-img">
                        <img src="assets/img/Avatar-Facebook.jpg" alt="">
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="single-product-content">
                        <h2>EmployeeID: &ensp;<%=employee.getEmployeeID()%></h2>
                        <h2>Name: &ensp;<%=employee.getFirstName() + employee.getLastName()%></h2>
                        <h4>BirthDate: &ensp;<%=employee.getBirthDate()%></h4>
                        <h4>HireDate: &ensp;<%=employee.getHireDate()%></h4>
                        <h4>Address: &ensp;<%=employee.getHomePhone() + " " + employee.getAddress() + " " + employee.getCity() + " " + employee.getRegion()%></h4>
                        <h4>Note: &ensp;<p><%=employee.getNote()%></p></h4>
                    </div>
                </div>
            </div>
        </div>
        <%} else {%>
        <h1>Login to .....</h1>
        <!-- end In4 Employee --> 
        <%}
        } else if (service.equals("managerProducts")) {
            List<Products> product = (List<Products>) request.getAttribute("listProducts");
        %>
        <!--<div class="container d-flex justify-content-center">-->
        <div class="row justify-content-center">
            <div class=" col-lg-12 col-md-12">
                <div class="cart-table-wrap">
                    <div class="d-flex justify-content-center"><a class="boxed-btn" href="ControllerAdd?do=addProduct"><i class="fa fa-plus-square"></i>AddProduct</a></div>
                    <table class="cart-table">
                        <thead class="cart-table-head">
                            <tr class="table-head-row">
                                <th class="product">Product ID</th>
                                <th class="product-name">Product Name</th>
                                <th class="product">Supplier ID</th>
                                <th class="product">Category ID</th>
                                <th class="product">Quantity PerUnit</th>
                                <th class="product">Units In Stock</th>
                                <th class="product">Units In Order</th>
                                <th class="product">Reorder Level</th>
                                <th class="product">Discontinued</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Products elem : product) {%>   
                            <tr class="table-body-row">
                                <td class="product-name"><%=elem.getProductID()%></td>
                                <td class="product-price"><%=elem.getProductName()%></td>
                                <td class="product-quantity"><%=elem.getSupplierID()%></td>
                                <td class="product-quantity"><%=elem.getCategoryID()%></td>
                                <td class="product-total"><%=elem.getQuantityPerUnit()%></td>
                                <td class="product-total"><%=elem.getUnitsInStock()%></td>
                                <td class="product-total"><%=elem.getUnitsOnOrder()%></td>
                                <td class="product-total"><%=elem.getReorderLevel()%></td>
                                <td class="product-total"><%=elem.getDiscontinued()%></td>
                                <td class="product-remove"><a class="boxed-btn" href="ControllerUpdate?do=updateProduct&pid=<%=elem.getProductID()%>"><i class="fa fa-address-book"></i></a></td>
                                <td class="product-remove"><a class="boxed-btn" href="ControllerDelete?do=delProduct&pid=<%=elem.getProductID()%>"><i class="far fa-window-close"></i></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="pagination-wrap">
                    <ul>
                        <%int size = (Integer) request.getAttribute("size"),
                                    index = (Integer) request.getAttribute("page");
                            if (size > 1) {
                                if (size >= 2) {%>
                        <li><a href="ControllerManager?page=previous">Prev</a></li> 
                            <%}%>
                            <%for (int i = 1; i <= size; i++) {%>
                        <li><a class="<%= i == index ? "active" : ""%>" href="ControllerManager?do=managerProducts&number=<%=i%>"><%=i%></a></li>
                            <%}%>
                            <%if (size >= 2) {%> 
                        <li><a href="ControllerManager?page=next">Next</a></li> 
                            <%}
                                }%>
                    </ul>
                </div>
            </div>
        </div>
        <!--</div>-->
        <%} else if (service.equals("managerCustomers")) {
            List<Customers> product = (List<Customers>) request.getAttribute("listCustomers");
        %>
        <!--<div class="container d-flex justify-content-center">-->
        <div class="row justify-content-center">
            <div class=" col-lg-12 col-md-12">
                <div class="cart-table-wrap">
                    <div class="d-flex justify-content-center"><a class="boxed-btn" href="ControllerAdd?do=addCustomer"><i class="fa fa-plus-square"></i>AddCustomer</a></div>
                    <table class="cart-table">
                        <thead class="cart-table-head">
                            <tr class="table-head-row">
                                <th class="product">Customer ID</th>
                                <th class="product-name">Company Name</th>
                                <th class="product">Contact Name</th>
                                <th class="product">Contact Title</th>
                                <th class="product">Address</th>
                                <th class="product">City</th>
                                <th class="product">Region</th>
                                <th class="product">PostalCode</th>
                                <th class="product">Country</th>
                                <th class="product">Phone</th>
                                <th class="product">Fax</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Customers elem : product) {%>   
                            <tr class="table-body-row">
                                <td class="product-name"><%=elem.getCustomerID()%></td>
                                <td class="product-price"><%=elem.getCompanyName()%></td>
                                <td class="product-quantity"><%=elem.getContactName()%></td>
                                <td class="product-quantity"><%=elem.getContactTitle()%></td>
                                <td class="product-total"><%=elem.getAddress()%></td>
                                <td class="product-total"><%=elem.getCity()%></td>
                                <td class="product-total"><%=elem.getRegion()%></td>
                                <td class="product-total"><%=elem.getPostalCode()%></td>
                                <td class="product-total"><%=elem.getCountry()%></td>
                                <td class="product-total"><%=elem.getPhone()%></td>
                                <td class="product-total"><%=elem.getFax()%></td>
                                <td class="product-remove"><a class="boxed-btn" href="ControllerUpdate?do=updateCustomer&cid=<%=elem.getCustomerID()%>"><i class="fa fa-address-book"></i></a></td>
                                <td class="product-remove"><a class="boxed-btn" href="ControllerDelete?do=delCustomer&cid=<%=elem.getCustomerID()%>"><i class="far fa-window-close"></i></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="pagination-wrap">
                    <ul>
                        <%int size = (Integer) request.getAttribute("size"),
                                    index = (Integer) request.getAttribute("page");
                            if (size > 1) {
                                if (size >= 2) {%>
                        <li><a href="ControllerManager?page=previous">Prev</a></li> 
                            <%}%>
                            <%for (int i = 1; i <= size; i++) {%>
                        <li><a class="<%= i == index ? "active" : ""%>" href="ControllerManager?do=managerCustomers&number=<%=i%>"><%=i%></a></li>
                            <%}%>
                            <%if (size >= 2) {%> 
                        <li><a href="ControllerManager?page=next">Next</a></li> 
                            <%}
                                }%>
                    </ul>
                </div>
            </div>
        </div>
        <%} else if (service.equals("managerOrders")) {
            List<Orders> product = (List<Orders>) request.getAttribute("listOrders");
        %>
        <!--<div class="container d-flex justify-content-center">-->
        <div class="row justify-content-center">
            <div class=" col-lg-12 col-md-12">
                <div class="cart-table-wrap">
                    <table class="cart-table">
                        <thead class="cart-table-head">
                            <tr class="table-head-row">
                                <th class="product">Order ID</th>
                                <th class="product-name">Customer ID</th>
                                <th class="product">Order Date</th>
                                <th class="product">Status</th>
                                <th>View</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Orders elem : product) {%>   
                            <tr class="table-body-row">
                                <td class="product-name"><%=elem.getOrderID()%></td>
                                <td class="product-price"><%=elem.getCustomerID()%></td>
                                <td class="product-quantity"><%=elem.getOrderDate()%></td>
                                <td class="product-total"><%=elem.getStatus() == 1 ? "Wait" : elem.getStatus() == 2 ? "Process" : "Done"%></td>
                                <td class="product-remove"><a class="boxed-btn" href="ControllerUpdate?do=detail&oid=<%=elem.getOrderID()%>"><i class="fa fa-eye"></i></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="pagination-wrap">
                    <ul>
                        <%int size = (Integer) request.getAttribute("size"),
                                    index = (Integer) request.getAttribute("page");
                            if (size > 1) {
                                if (size >= 2) {%>
                        <li><a href="ControllerManager">Prev</a></li> 
                            <%}%>
                            <%for (int i = 1; i <= size; i++) {%>
                        <li><a class="<%= i == index ? "active" : ""%>" href="ControllerManager?do=managerOrders&number=<%=i%>"><%=i%></a></li>
                            <%}%>
                            <%if (size >= 2) {%> 
                        <li><a href="ControllerManager">Next</a></li> 
                            <%}
                                }%>
                    </ul>
                </div>
            </div>
        </div>
        <!--</div>-->
        <%}%>
        <!-- end Table -->

        <!-- logo carousel -->
        <div class="logo-carousel-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="logo-carousel-inner">
                            <div class="single-logo-item">
                                <img src="assets/img/Germany-logo.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/viet-nam-logo.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/usa-logo.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/UK-logo.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/Japan-logo.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end logo carousel -->

        <!-- footer -->
        <div class="footer-area">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box about-widget">
                            <h2 class="widget-title">About us</h2>
                            <p>Ut enim ad minim veniam perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box get-in-touch">
                            <h2 class="widget-title">Get in Touch</h2>
                            <ul>
                                <li>34/8, East Hukupara, Gifirtok, Sadan.</li>
                                <li>support@fruitkha.com</li>
                                <li>+00 111 222 3333</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box pages">
                            <h2 class="widget-title">Pages</h2>
                            <ul>
                                <li><a href="#">Home</a></li>
                                <li><a href="#">About</a></li>
                                <li><a href="#">Shop</a></li>
                                <li><a href="#">News</a></li>
                                <li><a href="#">Contact</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-box subscribe">
                            <h2 class="widget-title">Subscribe</h2>
                            <p>Subscribe to our mailing list to get the latest updates.</p>
                            <form action="#">
                                <input type="email" placeholder="Email">
                                <button type="submit"><i class="fas fa-paper-plane"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end footer -->

        <!-- copyright -->
        <div class="copyright">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <p>Copyrights &copy; 2019 - <a href="https://imransdesign.com/">Imran Hossain</a>,  All Rights Reserved.</p>
                    </div>
                    <div class="col-lg-6 text-right col-md-12">
                        <div class="social-icons">
                            <ul>
                                <li><a href="#" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-instagram"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-linkedin"></i></a></li>
                                <li><a href="#" target="_blank"><i class="fab fa-dribbble"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end copyright -->

        <!-- jquery -->
        <script src="assets/js/jquery-1.11.3.min.js"></script>
        <!-- bootstrap -->
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <!-- count down -->
        <script src="assets/js/jquery.countdown.js"></script>
        <!-- isotope -->
        <script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
        <!-- waypoints -->
        <script src="assets/js/waypoints.js"></script>
        <!-- owl carousel -->
        <script src="assets/js/owl.carousel.min.js"></script>
        <!-- magnific popup -->
        <script src="assets/js/jquery.magnific-popup.min.js"></script>
        <!-- mean menu -->
        <script src="assets/js/jquery.meanmenu.min.js"></script>
        <!-- sticker js -->
        <script src="assets/js/sticker.js"></script>
        <!-- main js -->
        <script src="assets/js/main.js"></script>

    </body>
</html>
