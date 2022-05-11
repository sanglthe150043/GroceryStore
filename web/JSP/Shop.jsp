<%-- 
    Document   : Shop
    Created on : Mar 4, 2022, 7:16:46 PM
    Author     : Admin
--%>

<%@page import="entity.Customers"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="entity.Products"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Shop</title>

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
                                <a href="ControllerHome">
                                    <img src="assets/img/logo.png" alt="">
                                </a>
                            </div>
                            <!-- logo -->

                            <!-- menu start -->
                            <nav class="main-menu">
                                <ul>
                                    <li><a href="ControllerHome">Home</a></li>
                                        <%if (session.getAttribute("user") != null) {
                                        Customers customer = (Customers)session.getAttribute("user");
                                        %>
                                    <li> <a>Welcom:<%=customer.getUsername()%></a> </li>
                                    <li><a href="ControllerLogin?do=logoutC">LogOut</a></li>
                                        <%} else {%>
                                    <li><a href="ControllerLogin?do=loginC" >LogIn</a></li>
                                    <li><a href="ControllerLogin?do=registerC">Register</a></li>
                                        <%}%>         
                                    <li>
                                        <div class="header-icons">
                                            <a class="shopping-cart" href="ControllerCart?do=" ><i class="fas fa-shopping-cart"></i></a>
                                            <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                                        </div>
                                    </li>
                                </ul>
                            </nav>
                            <!-- menu end -->
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
                                <form action="ControllerHome" method="post">
                                    <input type="hidden" name="do" value="searchByName">
                                    <h3>Search By Name:</h3>
                                    <input type="text" placeholder="ProductName" name="pName">
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
                            <h1>Shop</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->
        <%String service = (String) request.getAttribute("service");%>
        <!-- products -->
        <div class="product-section mt-150 mb-150">
            <div class="container">

                <div class="row">
                    <div class="col-md-12">
                        <div class="product-filters">
                            <ul>
                                <li class="<%=service.equals("all")?"active":""%>"><a href="ControllerHome">All</a></li>
                                    <% Vector<String> type = (Vector<String>) request.getAttribute("type");
                                        for (int i = 0; i < type.size(); i++) {%>
                                <li class="<%=service.equals(type.get(i))?"active":""%>"><a href="ControllerHome?do=<%=type.get(i)%>"><%=type.get(i)%></a></li>
                                    <%}%>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row product-lists">
                    <%List<Products> products = (List<Products>) request.getAttribute("listProducts");
                        int a=0;
                        for (Products pro : products) {
                            if(a!=6) a++;
                            else a=1;
                    %>
                    <div class="col-lg-4 col-md-6 text-center strawberry">
                        <div class="single-product-item">
                            <div class="product-image">
                                <a href="#"><img src="assets/img/products/product-img-<%=a%>.jpg" alt=""></a>
                            </div>
                            <h3><%=pro.getProductName()%></h3>
                            <p class="product-price"><span><%=pro.getQuantityPerUnit()%></span><%=pro.getUnitPrice()%>$</p>
                            <a href="ControllerCart?do=addToCart&pid=<%=pro.getProductID()%>" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
                        </div>
                    </div>
                    <%}%>
                </div>

                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="pagination-wrap">
                            <ul>
                                <%int size = (Integer) request.getAttribute("size"),
                                            index = (Integer) request.getAttribute("index");
                                    if (size > 1) {
                                        if (size >= 2) {%>
                                <li><a href="ControllerHome">Prev</a></li> 
                                    <%}%>
                                    <%for (int i = 1; i <= size; i++) {%>
                                <li><a class="<%= index == i ? "active" : ""%>" href="ControllerHome?do=<%=service!=null ? service : "all"%>&page=<%=i%>"><%=i%></a></li>
                                    <%}%>
                                    <%if (size >= 2) {%> 
                                <li><a href="ControllerHome">Next</a></li> 
                                    <%}
                                        }%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
        <!-- end products -->

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
