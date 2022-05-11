<%-- 
    Document   : Cart
    Created on : Mar 4, 2022, 9:14:55 PM
    Author     : Admin
--%>

<%@page import="entity.Customers"%>
<%@page import="entity.Products"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">
        <!-- title -->
        <title>Cart</title>

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
                                    <li ><a href="ControllerHome">Home</a></li>
                                    <c:choose>
                                    <c:when test="${user}!=null">
                                    <li><a href="ControllerLogin?do=logoutC">LogOut</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="ControllerLogin?do=loginC" >LogIn</a></li>
                                    <li><a href="ControllerLogin?do=registerC">Register</a></li>
                                    </c:otherwise>
                                    </c:choose>
                                    <li><a href="ControllerHome">Shop</a></li>
                                    <li>
                                        <div class="header-icons">
                                            <a class="shopping-cart" href="ControllerCart?do="><i class="fas fa-shopping-cart"></i></a>
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

        <!-- breadcrumb-section -->
        <div class="breadcrumb-section breadcrumb-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="breadcrumb-text">
                            <p>Fresh and Organic</p>
                            <h1>Cart</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->

        <%Customers customer = (Customers)session.getAttribute("user");
        if(customer!=null){%>
        <!-- single product -->
	<div class="single-product mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<div class="single-product-img">
						<img src="assets/img/Avatar-Facebook.jpg" alt="">
					</div>
				</div>
				<div class="col-md-7">
					<div class="single-product-content">
						<h3>CustomerID:&ensp;<%=customer.getCustomerID()%></h3>
						<h3>CompanyName:&ensp;<%=customer.getCompanyName()%></h3>
                                                <h4>Address:&ensp;<%=customer.getAddress()+" "+customer.getCity()+" "+customer.getCountry()%></h4>
						<h4>PhoneNumber:&ensp;<%=customer.getPhone()%></h4>
						<h4>FAX:&ensp;<%=customer.getFax()%></h4>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
                                                    Dicta sint dignissimos, rem commodi cum voluptatem quae 
                                                    reprehenderit repudiandae ea tempora incidunt ipsa, 
                                                    quisquam animi perferendis eos eum modi! Tempora, earum.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
        <%}%>
        <!-- end single product -->

        
        <!-- cart -->
        <div class="cart-section mt-150 mb-150">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-12">
                        <div class="cart-table-wrap">
                            <%
                                
                                try {
                                   String  ero = (String)request.getAttribute("eror");%>
                                    <h2><%=ero%></h2>
                                <%}catch (Exception e) {%>
                               <% }
                            %>
                            
                            <table class="cart-table">
                                <thead class="cart-table-head">
                                    <tr class="table-head-row">
                                        <th></th>
                                        <th>Product Image</th>
                                        <th>Product ID</th>
                                        <th>Name</th>
                                        <th>Unit Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% java.util.Enumeration em = session.getAttributeNames();
                                        double total = 0;
                                        int a =0;
                                        while (em.hasMoreElements()) {
                                            String key = em.nextElement().toString();
                                            if (!key.equals("user") && !key.equals("admin")) {
                                                if(a!=6) a++;
                                                else a=1;
                                                Products value = (Products) session.getAttribute(key);%>
                                    <tr class="table-body-row">
                                        <td class="product-remove"><a href="ControllerCart?do=remove&id=<%=value.getProductID()%>"><i class="far fa-window-close"></i></a></td>
                                        <td ><img class="product-image" src="assets/img/products/product-img-<%=a%>.jpg" alt=""></td>
                                        <td ><%=value.getProductID()%></td>
                                        <td ><%=value.getProductName()%></td>
                                        <td >$<%=value.getUnitPrice()%></td>
                                        <td>
                                            <form action="ControllerCart?do=updateCart&productID=<%=value.getProductID()%>" 
                                                  method="post" id="<%=value.getProductID()%>">
                                                <input type="number" min="0" max="<%=value.getUnitsInStock()%>"
                                                       value="<%=value.getUnitsOnOrder()%>" 
                                                       onchange="document.getElementById(<%=value.getProductID()%>).submit()" name="quantity">
                                            </form>
                                        </td>
                                        <td ><%=(value.getUnitPrice()) * (value.getUnitsOnOrder())%></td>
                                        <% total += ((value.getUnitPrice()) * (value.getUnitsOnOrder()));%>
                                    </tr>
                                    <%}
                                        }%>
                                </tbody>
                            </table>
                            <%if (total != 0) {%>
                            <a href="ControllerCart?do=delete" class="boxed-btn">Remove ALL</a><%}%>
                            <a href="ControllerHome" class="boxed-btn">Continue Shopping</a>
                        </div>
                    </div>

                    <div class="col-lg-4">
                        <div class="total-section">
                            <table class="total-table">
                                <thead class="total-table-head">
                                    <tr class="table-total-row">
                                        <th>Total</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="total-data">
                                        <td><strong>Subtotal: </strong></td>
                                        <td>$<%=total%></td>
                                    </tr>
                                    <tr class="total-data">
                                        <td><strong>Shipping: </strong></td>
                                        <td>$<%= total != 0 ? 45 : 0%></td>
                                    </tr>
                                    <tr class="total-data">
                                        <td><strong>Total: </strong></td>
                                        <td>$<%= total != 0 ? (45 + total) : 0%></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="cart-buttons">
                                <a href="ControllerCart?do=checkOut" class="boxed-btn black">Check Out</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end cart -->

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
