<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <%@ page import="com.ecomjeegi.MyConfig" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <%@ include file="components/header.jsp" %>


                <!-- Main Slider Start -->
                <div class="header">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-3">
                                <nav class="navbar bg-light">
                                    <ul class="navbar-nav">

                                        <c:forEach items="${categories}" var="cat">
                                            <li class="nav-item">
                                                <a class="nav-link" href="#"><i class="fa fa-female"></i>
                                                    ${cat.name}
                                                </a>
                                            </li>
                                        </c:forEach>

                                    </ul>
                                </nav>
                            </div>
                            <div class="col-md-6">
                                <div class="header-slider normal-slider">

                                    <div class="header-slider-item">
                                        <img src="${advertisements[0].image}" alt="Slider Image" />
                                        <div class="header-slider-caption">
                                            <p>${advertisements[0].description}</p>
                                            <a class="btn" href=""><i class="fa fa-shopping-cart"></i>Shop Now</a>
                                        </div>
                                    </div>
                                    <div class="header-slider-item">
                                        <img src="${advertisements[1].image}" alt="Slider Image" />
                                        <div class="header-slider-caption">
                                            <p>${advertisements[1].description}</p>
                                            <a class="btn" href=""><i class="fa fa-shopping-cart"></i>Shop Now</a>
                                        </div>
                                    </div>
                                    <div class="header-slider-item">
                                        <img src="${advertisements[2].image}" alt="Slider Image" />
                                        <div class="header-slider-caption">
                                            <p>${advertisements[2].description}</p>
                                            <a class="btn" href=""><i class="fa fa-shopping-cart"></i>Shop Now</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="header-img">
                                    <div class="img-item">
                                        <img src="${advertisements[3].image}" />
                                        <a class="img-text" href="">
                                            <p>${advertisements[3].description}</p>
                                        </a>
                                    </div>
                                    <div class="img-item">
                                        <img src="${advertisements[4].image}" />
                                        <a class="img-text" href="">
                                            <p>${advertisements[4].description}</p>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Main Slider End -->

                <!-- Brand Start -->
                <div class="brand">
                    <div class="container-fluid">
                        <div class="brand-slider">
                            <div class="brand-item"><img src="img/brand-1.png" alt=""></div>
                            <div class="brand-item"><img src="img/brand-2.png" alt=""></div>
                            <div class="brand-item"><img src="img/brand-3.png" alt=""></div>
                            <div class="brand-item"><img src="img/brand-4.png" alt=""></div>
                            <div class="brand-item"><img src="img/brand-5.png" alt=""></div>
                            <div class="brand-item"><img src="img/brand-6.png" alt=""></div>
                        </div>
                    </div>
                </div>
                <!-- Brand End -->

                <!-- Feature Start-->
                <div class="feature">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col-lg-3 col-md-6 feature-col">
                                <div class="feature-content">
                                    <i class="fab fa-cc-mastercard"></i>
                                    <h2>Secure Payment</h2>
                                    <p>
                                        Lorem ipsum dolor sit amet consectetur elit
                                    </p>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 feature-col">
                                <div class="feature-content">
                                    <i class="fa fa-truck"></i>
                                    <h2>Worldwide Delivery</h2>
                                    <p>
                                        Lorem ipsum dolor sit amet consectetur elit
                                    </p>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 feature-col">
                                <div class="feature-content">
                                    <i class="fa fa-sync-alt"></i>
                                    <h2>90 Days Return</h2>
                                    <p>
                                        Lorem ipsum dolor sit amet consectetur elit
                                    </p>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 feature-col">
                                <div class="feature-content">
                                    <i class="fa fa-comments"></i>
                                    <h2>24/7 Support</h2>
                                    <p>
                                        Lorem ipsum dolor sit amet consectetur elit
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Feature End-->

                <!-- Category Start-->
                <div class="category">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="category-item ch-400">
                                    <img src="img/category-3.jpg" />
                                    <a class="category-name" href="">
                                        <p>Some text goes here that describes the image</p>
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="category-item ch-250">
                                    <img src="img/category-4.jpg" />
                                    <a class="category-name" href="">
                                        <p>Some text goes here that describes the image</p>
                                    </a>
                                </div>
                                <div class="category-item ch-150">
                                    <img src="img/category-5.jpg" />
                                    <a class="category-name" href="">
                                        <p>Some text goes here that describes the image</p>
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="category-item ch-150">
                                    <img src="img/category-6.jpg" />
                                    <a class="category-name" href="">
                                        <p>Some text goes here that describes the image</p>
                                    </a>
                                </div>
                                <div class="category-item ch-250">
                                    <img src="img/category-7.jpg" />
                                    <a class="category-name" href="">
                                        <p>Some text goes here that describes the image</p>
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="category-item ch-400">
                                    <img src="img/category-8.jpg" />
                                    <a class="category-name" href="">
                                        <p>Some text goes here that describes the image</p>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Category End-->

                <!-- Call to Action Start -->
                <div class="call-to-action">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col-md-6">
                                <h1>call us for any queries</h1>
                            </div>
                            <div class="col-md-6">
                                <a href="tel:0123456789">+012-345-6789</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Call to Action End -->

                <!-- Featured Product Start -->
                <div class="featured-product product">
                    <div class="container-fluid">
                        <div class="section-header">
                            <h1>Featured Product</h1>
                        </div>
                        <div class="row align-items-center product-slider product-slider-4">
                            <c:forEach items="${featuredProducts}" var="prod">
                                <div class="col-lg-3">
                                    <div class="product-item">
                                        <div class="product-title">
                                            <a href="${MyConfig.getHost()}product?id=${prod.id}">${prod.product_name}</a>
                                            <div class="ratting">
                                                <c:forEach var="i" begin="1" end="${prod.productRate}">
                                                    <i class="fa fa-star"></i>
                                                </c:forEach>
                                                <c:forEach var="i" begin="1" end="${5 - prod.productRate}">
                                                    <i class="fa fa-star n-stars-icon text-white"></i>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="product-image">
                                            <a href="product-detail.html">
                                                <img src="${MyConfig.getHost()}${prod.image}" alt="Product Image">
                                            </a>
                                            <div class="product-action">
                                                <button onclick="shoppingList.save({product_id:${prod.id},quantity:1})"
                                                    href="#"><i class="fa fa-cart-plus"></i></button>
                                                <button href="#"><i class="fa fa-heart"></i></button>
                                                <button onclick="(()=>{ window.location = '${MyConfig.getHost()}product?id=${prod.id}'; })()"><i class="fa fa-search"></i></button>
                                            </div>
                                        </div>
                                        <div class="product-price">
                                            <h3>${prod.price}</h3>
                                            <a class="btn" href="${MyConfig.getHost()}makeorder?product_id=${prod.id}"><i class="fa fa-shopping-cart"></i>Buy Now</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
                <!-- Featured Product End -->

                <!-- Newsletter Start -->
                <div class="newsletter">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6">
                                <h1>Subscribe Our Newsletter</h1>
                            </div>
                            <div class="col-md-6">
                                <div class="form">
                                    <input type="email" value="Your email here">
                                    <button>Submit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Newsletter End -->

                <!-- Recent Product Start -->
                <div class="recent-product product">
                    <div class="container-fluid">
                        <div class="section-header">
                            <h1>Recent Product</h1>
                        </div>
                        <div class="row align-items-center product-slider product-slider-4">
                            <c:forEach items="${recentProduct}" var="prod">
                                <div class="col-lg-3">
                                    <div class="product-item">
                                        <div class="product-title">
                                            <a href="${MyConfig.getHost()}product?id=${prod.id}">${prod.product_name}</a>
                                            <div class="ratting">
                                                <c:forEach var="i" begin="1" end="${prod.productRate}">
                                                    <i class="fa fa-star"></i>
                                                </c:forEach>
                                                <c:forEach var="i" begin="1" end="${5 - prod.productRate}">
                                                    <i class="fa fa-star n-stars-icon text-white"></i>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="product-image">
                                            <a href="product-detail.html">
                                                <img src="${MyConfig.getHost()}${prod.image}" alt="Product Image">
                                            </a>
                                            <div class="product-action">
                                                <button onclick="shoppingList.save({product_id:${prod.id},quantity:1})"
                                                    ><i class="fa fa-cart-plus"></i></button>
                                                <button ><i class="fa fa-heart"></i></button>
                                                <button onclick="(()=>{ window.location = '${MyConfig.getHost()}product?id=${prod.id}'; })()"><i class="fa fa-search"></i></button>
                                            </div>
                                        </div>
                                        <div class="product-price">
                                            <h3>${prod.price}</h3>
                                            <a class="btn"  href="${MyConfig.getHost()}makeorder?product_id=${prod.id}" ><i class="fa fa-shopping-cart"></i>Buy Now</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <!-- Recent Product End -->

                <!-- Review Start -->
                <div class="review">
                    <div class="container-fluid">
                        <div class="row align-items-center review-slider normal-slider">
                            <div class="col-md-6">
                                <div class="review-slider-item">
                                    <div class="review-img">
                                        <img src="img/review-1.jpg" alt="Image">
                                    </div>
                                    <div class="review-text">
                                        <h2>Customer Name</h2>
                                        <h3>Profession</h3>
                                        <div class="ratting">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur vitae
                                            nunc
                                            eget leo finibus luctus et vitae lorem
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="review-slider-item">
                                    <div class="review-img">
                                        <img src="img/review-2.jpg" alt="Image">
                                    </div>
                                    <div class="review-text">
                                        <h2>Customer Name</h2>
                                        <h3>Profession</h3>
                                        <div class="ratting">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur vitae
                                            nunc
                                            eget leo finibus luctus et vitae lorem
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="review-slider-item">
                                    <div class="review-img">
                                        <img src="img/review-3.jpg" alt="Image">
                                    </div>
                                    <div class="review-text">
                                        <h2>Customer Name</h2>
                                        <h3>Profession</h3>
                                        <div class="ratting">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur vitae
                                            nunc
                                            eget leo finibus luctus et vitae lorem
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Review End -->

                <%@ include file="components/footer.jsp" %>