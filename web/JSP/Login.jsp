<%-- 
    Document   : Login
    Created on : Mar 4, 2022, 11:16:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

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
                            <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                            <div class="mobile-menu"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end header -->

        <!-- Login Customer area -->
        <%if (((String) request.getAttribute("service")).equals("loginC")) {%>

        <!-- Login Customer area -->
        <div class="hero-area hero-bg">
            <div class="container">
                <div class="row">
                    <div style="margin-left: 23%;margin-top: 20%;" class="col-lg-9 offset-lg-2 text-center ">
                        <div class="hero-text hero-text-tablecell ">
                            <p class="subtitle">Fresh & Organic</p>
                            <%try {
                                    String ero = (String) request.getAttribute("service2");%>
                            <%if (ero == null) {%>
                            <h1>  LOGIN CUSTOMER </h1><%} else {%>
                            <h1>LOGIN CUSTOMER AGAINT</h1><%}%>
                            <%} catch (Exception ex) {
                                }%>
                            <form action="ControllerLogin" method="post">
                                <input type="hidden" name="do" value="inC">
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="username" name="usernameC">

                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                                    </div>
                                    <input type="password" class="form-control" placeholder="password" name="passwordC">
                                </div>
                                <button class=" update" type="submit">Login</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- end Customer Login area -->
        <%}%>
        <!-- end Customer Login area -->

        <!-- Register Customer area -->
        <%if (((String) request.getAttribute("service")).equals("registerC")) {%>
        <!-- Register area -->
        <div class="hero-area hero-bg ">
            <div class="container ">
                <div class="row ">
                    <div class="col-lg-5 offset-lg-2 text-center mx-auto">
                        <div class="hero-text">
                            <div class="hero-text-tablecell">
                                <div class="hero-btns">
                                    <p class="subtitle">Fresh & Organic</p>
                                    <%try {
                                            String ero = (String) request.getAttribute("service2");%>
                                    <%if (ero == null) {%>
                                    <h1>  REGISTER CUSTOMER </h1><%} else {%>
                                    <h1>REGISTER CUSTOMER AGAINT</h1><%}%>
                                    <%} catch (Exception ex) {
                                        }%>
                                    <form action="ControllerLogin" method="post">
                                        <input type="hidden" name="do" value="regisC">
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Customer ID" name="customerID">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="User Name" name="username">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fas fa-key"></i></span>
                                            </div>
                                            <input type="password" class="form-control" placeholder="Pass Word" name="password">
                                        </div>
                                        <div class="input-group form-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fas fa-key"></i></span>
                                            </div>
                                            <input type="password" class="form-control" placeholder="Repass Word" name="repass">
                                        </div>
                                        <input class="boxed-btn" type="submit" name="Register">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end Register area -->

        <%}%>
        <!-- End Register Customer area -->

        <!-- Login Employee area -->
        <%if (((String) request.getAttribute("service")).equals("loginE")) {%>
        <div class="hero-area hero-bg">
            <div class="container">
                <div class="row">
                    <div style="margin-left: 25%;margin-top: 20%;" class="col-lg-9 offset-lg-2 text-center">
                        <div class="hero-text hero-text-tablecell">
                            <p class="subtitle">Fresh & Organic</p>
                            <%try {
                                                                    String ero = (String) request.getAttribute("service2");%>
                            <%if (ero == null) {%>
                            <h1>  LOGIN EMPLOYEE </h1><%} else {%>
                            <h1>LOGIN EMPLOYEE AGAINT</h1><%}%>
                            <%} catch (Exception ex) {
                                                                }%>

                            <form action="ControllerLogin" method="post">
                                <input type="hidden" name="do" value="inE">
                                <div class="hero-btns ">
                                    <div class="input-group form-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                                        </div>
                                        <input type="text" class="form-control" placeholder="username" name="usernameE">

                                    </div>
                                    <div class="input-group form-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                                        </div>
                                        <input type="password" class="form-control" placeholder="password" name="passwordE">
                                    </div>
                                    <button class="update">Login</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <!-- end Employee Login area -->

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
