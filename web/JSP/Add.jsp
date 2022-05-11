<%-- 
    Document   : Add.jsp
    Created on : Mar 17, 2022, 6:04:37 PM
    Author     : Admin
--%>

<%@page import="entity.Suppliers"%>
<%@page import="entity.Categories"%>
<%@page import="java.util.Vector"%>
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
        <title>Fruitkha</title>

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

                            <!-- menu start -->
                            <nav class="main-menu">
                                <ul>
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
                            <!-- menu end -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end header -->

        <!-- search area -->

        <!-- end search area -->
        <%String service = (String) request.getAttribute("service");
            if (service.equals("addCustomer")) {
        %>
        <!-- Add Product area -->
        <div class="hero-area hero-bg ">
            <div class="container ">
                <div class="row ">
                    <div class="col-lg-5 offset-lg-2 text-center mx-auto">
                        <div class="hero-text">
                            <div class="hero-text-tablecell">
                                <div class="hero-btns">
                                    <p class="subtitle">Fresh & Organic</p>
                                    <h1>Add Customer</h1>
                                    <form action="ControllerAdd" method="post">
                                        <input type="hidden" name="do" value="addC">
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-info-circle"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Customer ID" name="customerID">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-building"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Company Name" name="companyName">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-info-circle"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Contact Name" name="contactName">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-info-circle"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Contact Title" name="contactTitle">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-map-marker"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Address" name="address">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-globe"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="City" name="city">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-globe"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Region" name="region">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-code"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Postal Code" name="postalCode">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-globe"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Country" name="country">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-phone-square"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Phone" name="phone">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-fax"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Fax" name="fax">
                                        </div>
                                        <input type="submit" value="Add" class="boxed-btn">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end add Product area -->
        <%} else {
            Vector<Categories> cate = (Vector<Categories>) request.getAttribute("categories");
            Vector<Suppliers> sup = (Vector<Suppliers>) request.getAttribute("suppliers");%>
        <div class="hero-area hero-bg ">
            <div class="container ">
                <div class="row ">
                    <div class="col-lg-5 offset-lg-2 text-center mx-auto">
                        <div class="hero-text">
                            <div class="hero-text-tablecell">
                                <div class="hero-btns">
                                    <p class="subtitle">Fresh & Organic</p>
                                    <h1>Add Product</h1>
                                    <form action="ControllerAdd" method="post">
                                        <input type="hidden" name="do" value="addP">
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-info-circle"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Product Name" name="productName">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-building"></i></span>
                                            </div>
                                            <select class="form-control" name="supplierID">
                                                <%for (Suppliers elem : sup) {%>
                                                <option value="<%=elem.getSupplierID()%>"><%=elem.getCompanyName()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-building"></i></span>
                                            </div>
                                            <select class="form-control" name="categoryID">
                                                <%for (Categories elem : cate) {%>
                                                <option value="<%=elem.getCategoryID()%>"><%=elem.getCategoryName()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-calculator"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Quantity Per Unit" name="quantityPerUnit">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-info-circle"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Unit Price" name="unitPrice">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-university"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Units In Stock" name="unitInStock">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-shopping-cart"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Units On Order" name="unitOnOrder">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-info-circle"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Reorder Level" name="reorderLevel">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fa fa-info-circle"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Discontinued" name="discontinued">
                                        </div>
                                        <input type="submit" value="Add" class="boxed-btn">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%}%>


        <!-- logo carousel -->
        <div class="logo-carousel-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="logo-carousel-inner">
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
                            <div class="single-logo-item">
                                <img src="assets/img/Germany-logo.png" alt="">
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
