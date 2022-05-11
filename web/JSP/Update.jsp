<%-- 
    Document   : Update
    Created on : Mar 10, 2022, 10:37:49 AM
    Author     : Admin
--%>

<%@page import="entity.DetailOrder"%>
<%@page import="entity.Customers"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.Categories"%>
<%@page import="entity.Suppliers"%>
<%@page import="entity.Products"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description"
              content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Update</title>

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

        <!-- breadcrumb-section -->
        <div class="breadcrumb-section breadcrumb-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="breadcrumb-text">
                            <p>Fresh and Organic</p>
                            <h1>Update</h1>
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
                            <li class="update <%=service.equals("updateProduct") ? " active" : ""%>">
                                <a href="ControllerManager?do=managerProducts">Manage Products</a></li>
                            <li class="update <%=service.equals("updateCustomer") ? " active" : ""%>">
                                <a href="ControllerManager?do=managerCustomers">Manage Customers</a></li>
                            <li class="update <%=service.equals("updateOrder") ? " active" : ""%>">
                                <a href="ControllerManager?do=managerOrders">Manage Orders</a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <!--UpdateProduct-->
        <%if (service != null && service.equals("updateProduct")) {
                Products pro = (Products) request.getAttribute("product");
                Vector<Categories> cate = (Vector<Categories>) request.getAttribute("categories");
                Vector<Suppliers> sup = (Vector<Suppliers>) request.getAttribute("suppliers");
        %>
        <!-- Update-->
        <form action="ControllerUpdate" method="POST">
            <input type="hidden" name="do" value="upP">
            <div class="container ">
                <div class="row">
                    <div class="col-md-5">
                        <div class="single-product-img">
                            <img src="assets/img/products/product-img-1.jpg" alt="">
                        </div>
                    </div>
                    <div class="col-md-7">
                        <table class="order-details">
                            <tbody>
                                <tr>
                                    <th>Product ID</th>
                                    <td><input  name="productID" readonly value="<%=pro.getProductID()%>"></td>
                                </tr>
                                <tr>
                                    <th>Product Name</th>
                                    <td><input name="productName" type="text" placeholder="Product Name" value="<%=pro.getProductName()%>"></td>
                                </tr>
                                <tr>
                                    <th>Supplier Name</th>
                                    <td>
                                        <select name="supplierID">
                                            <%for (Suppliers ele : sup) {%>
                                            <option value="<%=ele.getSupplierID()%>" <%=ele.getSupplierID() == pro.getSupplierID() ? " selected" : ""%> 
                                                    ><%=ele.getCompanyName()%></option>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Category Name</th>
                                    <td>
                                        <select name="categoryID">
                                            <%for (Categories ele : cate) {%>
                                            <option value="<%=ele.getCategoryID()%>" <%=ele.getCategoryID() == pro.getCategoryID() ? " selected" : ""%> 
                                                    ><%=ele.getCategoryName()%></option>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Quantity Per Unit</th>
                                    <td><input name="quantityPerUnit" type="text" value="<%=pro.getQuantityPerUnit()%>"></td>
                                </tr>
                                <tr>
                                    <th>Units Price</th>
                                    <td><input name="unitPrice" type="text" value="<%=pro.getUnitPrice()%>"></td>
                                </tr>
                                <tr>
                                    <th>Units In Stock</th>
                                    <td><input name="unitsInStock" type="text" value="<%=pro.getUnitsInStock()%>"></td>
                                </tr>
                                <tr>
                                    <th>Units In Order</th>
                                    <td><input name="unitsInOrder" type="text" value="<%=pro.getUnitsOnOrder()%>"></td>
                                </tr>
                                <tr>
                                    <th>Reorder Level</th>
                                    <td><input name="reorderLevel" type="text" value="<%=pro.getReorderLevel()%>"></td>
                                </tr>
                                <tr>
                                    <th>Discontinued</th>
                                    <td>
                                        <input type="radio" name="discontinued" value="1" 
                                               <%=pro.getDiscontinued() == 1 ? "checked" : ""%> /> Yes
                                        <input type="radio" name="discontinued" value="0" 
                                               <%=pro.getDiscontinued() == 0 ? "checked" : ""%>/> No
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <td><input type="submit" value="Update" class="boxed-btn"></td>
                    </div>
                </div>
            </div>
        </form>

        <!-- Update Customer-->
        <%} else if (service != null && service.equals("updateCustomer")) {
            Customers pro = (Customers) request.getAttribute("customer");%>
        <!-- Update-->
        <form action="ControllerUpdate" method="POST">
            <input type="hidden" name="do" value="upC">
            <div class="container ">
                <div class="row">
                    <div class="col-md-5">
                        <div class="single-product-img">
                            <img src="assets/img/Avatar-Facebook.jpg" alt="">
                        </div>
                    </div>
                    <div class="col-md-7">
                        <table class="order-details">
                            <tbody>
                                <tr>
                                    <th>Customer ID</th>
                                    <td><input  name="customerID" readonly value="<%=pro.getCustomerID()%>"></td>
                                </tr>
                                <tr>
                                    <th>Company Name</th>
                                    <td><input name="companyName" type="text" placeholder="Company Name" value="<%=pro.getCompanyName()%>"></td>
                                </tr>
                                <tr>
                                    <th>Contact Name</th>
                                    <td><input name="contactName" type="text" placeholder="Contact Name" value="<%=pro.getContactName()%>"></td>
                                </tr>
                                <tr>
                                    <th>Contact Title</th>
                                    <td><input name="contactTitle" type="text" placeholder="Contact Title" value="<%=pro.getContactTitle()%>"></td>
                                </tr>
                                <tr>
                                    <th>Address</th>
                                    <td><input name="address" type="text" value="<%=pro.getAddress()%>"></td>
                                </tr>
                                <tr>
                                    <th>City</th>
                                    <td><input name="city" type="text" value="<%=pro.getCity()%>"></td>
                                </tr>
                                <tr>
                                    <th>Region</th>
                                    <td><input name="region" type="text" value="<%=pro.getRegion()%>"></td>
                                </tr>
                                <tr>
                                    <th>Postal Code</th>
                                    <td><input name="postalCode" type="text" value="<%=pro.getPostalCode()%>"></td>
                                </tr>
                                <tr>
                                    <th>Phone</th>
                                    <td><input name="phone" type="text" value="<%=pro.getPhone()%>"></td>
                                </tr>
                                <tr>
                                    <th>Fax</th>
                                    <td><input name="fax" type="text" value="<%=pro.getFax()%>"></td>
                                </tr>
                                <tr>
                                    <th>User Name</th>
                                    <td><input name="userName" type="text" value="<%=pro.getUsername()%>"></td>
                                </tr>
                            </tbody>
                        </table>
                        <td><input type="submit" value="Update" class="boxed-btn"></td>
                    </div>
                </div>
            </div>
        </form>
        <!-- Update -->
        <%} else if (service != null && service.equals("detail")) {
            Vector<DetailOrder> orders = (Vector<DetailOrder>) request.getAttribute("orders");%>
        <!-- Update-->
        <div class="container ">
            <div class="row">
                <div class="col-md-5">
                    <div class="single-product-img">
                        <img src="assets/img/Avatar-Facebook.jpg" alt="">
                    </div>
                </div>

                <div class="col-md-7 ">
                    <h3>Order ID : <%=orders.get(0).getOrderID()%> </h3>
                    <h3>Customer Name : <%=orders.get(0).getCustomerName()%> </h3>
                    <h3>Status : 
                        <form action="ControllerUpdate">
                            <input type="hidden" name="do" value="updateStatus">
                            <input type="hidden" name="oid" value="<%=orders.get(0).getOrderID()%>">
                            <select onchange="this.form.submit()" name="status">
                                <option <%= orders.get(0).getStatus() == 1 ? "selected" : ""%> value="1" >Wait</option>
                                <option <%= orders.get(0).getStatus() == 2 ? "selected" : ""%> value="2">Processing</option>
                                <option <%= orders.get(0).getStatus() == 3 ? "selected" : ""%> value="3">Done</option>
                            </select>
                        </form>
                    </h3>
                    <div class="row justify-content-center">
                        <div class=" col-lg-12 col-md-12">
                            <div class="cart-table-wrap">
                                <table class="cart-table ">
                                    <thead class="cart-table-head">
                                        <tr class="table-head-row">
                                            <th class="product">Product ID</th>
                                            <th class="product">Product Name</th>
                                            <th class="product-name">Quantity</th>
                                            <th class="product">Order Date</th>
                                            <th class="product">Ship Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (DetailOrder elem : orders) {%>   
                                        <tr class="table-body-row">
                                            <td class="product-name"><%=elem.getProductID()%></td>
                                            <td class="product-price"><%=elem.getProductName()%></td>
                                            <td class="product-quantity"><%=elem.getQuantity()%></td>
                                            <td class="product-total"><%=elem.getOrderDate()%></td>
                                            <td class="product-total"><%=elem.getShippedDate()%></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Update -->
        <%}%>


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
                            <p>Ut enim ad minim veniam perspiciatis unde omnis iste natus error sit voluptatem accusantium
                                doloremque laudantium, totam rem aperiam, eaque ipsa quae.</p>
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
                        <p>Copyrights &copy; 2019 - <a href="https://imransdesign.com/">Imran Hossain</a>, All Rights
                            Reserved.</p>
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
