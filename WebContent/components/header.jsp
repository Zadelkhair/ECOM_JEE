<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

        <%@ page import="com.ecomjeegi.app.App" %>
            <%@ page import="com.ecomjeegi.MyConfig" %>

                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="utf-8">
                        <title>E Store - eCommerce HTML Template</title>
                        <meta content="width=device-width, initial-scale=1.0" name="viewport">
                        <meta content="eCommerce HTML Template Free Download" name="keywords">
                        <meta content="eCommerce HTML Template Free Download" name="description">

                        <!-- Favicon -->
                        <link href="i${MyConfig.getHost()}mg/favicon.ico" rel="icon">

                        <!-- Google Fonts -->
                        <link
                            href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"
                            rel="stylesheet">

                        <!-- CSS Libraries -->
                        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
                            rel="stylesheet">
                        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
                            rel="stylesheet">
                        <link href="${MyConfig.getHost()}lib/slick/slick.css" rel="stylesheet">
                        <link href="${MyConfig.getHost()}lib/slick/slick-theme.css" rel="stylesheet">

                        <!-- Template Stylesheet -->
                        <link href="${MyConfig.getHost()}css/style.css" rel="stylesheet">

                        

                        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>

                        <script>
                            function submit(path, params, method) {

                                // The rest of this code assumes you are not using a library.
                                // It can be made less verbose if you use one.
                                const form = document.createElement('form');
                                form.method = method;
                                form.action = path;

                                for (const key in params) {
                                    if (params.hasOwnProperty(key)) {
                                        const hiddenField = document.createElement('input');
                                        hiddenField.type = 'hidden';
                                        hiddenField.name = key;
                                        hiddenField.value = params[key];

                                        form.appendChild(hiddenField);
                                    }
                                }

                                form.submit();
                            }


                            let shoppingList = {
                                list: [],
                                user: -1,
                                elements: [],
                                callbacks: [],
                                save({ product_id, quantity }) {
                                    let prod = this.find(product_id);
                                    if (!prod) {
                                        this.add({ product_id, quantity });
                                    }
                                    else {
                                        this.update({ product_id, quantity });
                                    }

                                    $.notify("Save product to shopping list", "success");

                                    return this.list;
                                },
                                add: function ({ product_id, quantity }) {
                                    let prod = this.find(product_id);
                                    if (!prod) {
                                        this.list.push({ product_id, quantity });
                                        this.updateCookies();
                                        this.updateSubscribers();
                                    }
                                },
                                update: function ({ product_id, quantity }) {
                                    let prod = this.find(product_id);
                                    if (prod) {
                                        prod.quantity = quantity;
                                        this.updateCookies();
                                        this.updateSubscribers();
                                    }
                                },
                                remove: function (product_id) {
                                    let prod = this.find(product_id);
                                    if (prod) {
                                        this.list.splice(this.list.indexOf(prod), 1);
                                        this.updateCookies();
                                        this.updateSubscribers();
                                    }
                                },
                                removeAll() {
                                    this.list = [];
                                    this.updateCookies();
                                    this.updateSubscribers();
                                },
                                find(product_id) {
                                    let prod = null;
                                    this.list.forEach((v) => {
                                        if (v.product_id == product_id) {
                                            prod = v;
                                            return;
                                        }
                                    });

                                    return prod;
                                },
                                updateCookies: function () {
                                    if (this.user != -1) {
                                        Cookies.set('shoppingList_' + this.user, this.list);
                                        return;
                                    }
                                    Cookies.set('shoppingList', this.list);
                                },
                                loadCookies: function () {

                                    <c:if test="${App.getInstance().auth.isLoggedIn()}">

                                        this.user = ${App.getInstance().auth.authentificatedUser.id};

                                    </c:if >

                                    if (this.user != -1) {
                                        this.list = Cookies.getJSON(`shoppingList_` + this.user) ?? [];

                                        this.updateSubscribers();

                                        return;
                                    }

                                    this.list = Cookies.getJSON('shoppingList') ?? [];

                                    this.updateSubscribers();


                                },
                                updateSubscribers() {
                                    this.elements.forEach((elem) => {
                                        elem.innerHTML = '(' + this.list.length + ')';
                                    });
                                    this.callbacks.forEach((calbk) => {
                                        calbk(this.list);
                                    });
                                },
                                subscribe(elem) {
                                    this.elements.push(elem);
                                    this.updateSubscribers();
                                },
                                subscribeCallback(callback) {
                                    this.callbacks.push(callback);
                                    this.updateSubscribers(callback);
                                },
                                toString() {
                                    if (this.list.length == 0) return 0;
                                    return this.list.reduce((p, c) => { return { product_id: p.product_id + ',' + c.product_id } }).product_id;
                                }
                            }

                            shoppingList.loadCookies();
                            

                        </script>

                    </head>

                    <body>

                        <!-- Top bar Start -->
                        <div class="top-bar">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <i class="fa fa-envelope"></i>
                                        support@email.com
                                    </div>
                                    <div class="col-sm-6">
                                        <i class="fa fa-phone-alt"></i>
                                        +012-345-6789
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Top bar End -->

                        <!-- Nav Bar Start -->
                        <div class="nav">
                            <div class="container-fluid">
                                <nav class="navbar navbar-expand-md bg-dark navbar-dark">
                                    <a href="#" class="navbar-brand">MENU</a>
                                    <button type="button" class="navbar-toggler" data-toggle="collapse"
                                        data-target="#navbarCollapse">
                                        <span class="navbar-toggler-icon"></span>
                                    </button>

                                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                                        <div class="navbar-nav mr-auto">
                                            <a href="${MyConfig.getHost()}"
                                                class="header-nav-link nav-item nav-link">Home</a>
                                            <a href="${MyConfig.getHost()}productlist"
                                                class="header-nav-link nav-item nav-link">Products</a>
                                            <a id="shoppinglistlink2" class="header-nav-link nav-item nav-link">My
                                                shoppinglist</a>
                                            <a href="${MyConfig.getHost()}myaccount/details"
                                                class="header-nav-link nav-item nav-link">My Account</a>
                                        </div>
                                        <script>

                                            let headerNavLinks = document.querySelectorAll('.header-nav-link');

                                            headerNavLinks.forEach((link) => {

                                                let linkHref = link.href.split('?')[0];
                                                let locationHref = window.location.href.split('?')[0];

                                                if (linkHref[linkHref.length - 1] == '/') {
                                                    linkHref = linkHref.substr(0, linkHref.length - 1);
                                                }

                                                if (locationHref[locationHref.length - 1] == '/') {
                                                    locationHref = locationHref.substr(0, locationHref.length - 1);
                                                }

                                                console.log(linkHref, '\\', locationHref, '\\', window.location.href.split('?')[0]);

                                                if (linkHref == locationHref)
                                                    link.classList.add('active');

                                            })

                                            if (window.location.href.indexOf('shoppinglist') != -1) {
                                                document.querySelector('#shoppinglistlink2').classList.add('active');
                                            }

                                        </script>
                                        <c:if test="${ !App.getInstance().auth.isLoggedIn() }">
                                            <div class="navbar-nav ml-auto">
                                                <div class="nav-item dropdown">
                                                    <a href="#" class="nav-link dropdown-toggle"
                                                        data-toggle="dropdown">User
                                                        Account</a>
                                                    <div class="dropdown-menu">
                                                        <a href="${MyConfig.getHost()}login"
                                                            class="dropdown-item">Login</a>
                                                        <a href="${MyConfig.getHost()}register"
                                                            class="dropdown-item">Register</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${App.getInstance().auth.isLoggedIn()}">
                                            <div class="navbar-nav ml-auto">
                                                <div class="nav-item dropdown">
                                                    <a href="#" class="nav-link dropdown-toggle"
                                                        data-toggle="dropdown">${App.getInstance().auth.authentificatedUser.username}</a>
                                                    <div class="dropdown-menu">
                                                        <c:if
                                                            test="${App.getInstance().auth.authentificatedUser.role == 'admin'}">
                                                            <a href="${MyConfig.getHost()}dashboard"
                                                                class="dropdown-item">Dashboard</a>
                                                        </c:if>
                                                        <a href="${MyConfig.getHost()}myaccount/details"
                                                            class="dropdown-item">My
                                                            account</a>
                                                        <form id="logoutForm" action="${MyConfig.getHost()}logout"
                                                            method="post">
                                                        </form>
                                                        <a href="#" class="dropdown-item"
                                                            onclick="document.getElementById('logoutForm').submit();">Logout</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </nav>
                            </div>
                        </div>
                        <!-- Nav Bar End -->

                        <!-- Bottom Bar Start -->
                        <div class="bottom-bar">
                            <div class="container-fluid">
                                <div class="row align-items-center">
                                    <div class="col-md-3">
                                        <div class="logo">
                                            <a href="${MyConfig.getHost()}">
                                                <img src="${MyConfig.getHost()}img/logo.png" alt="Logo">
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="search">
                                            <input type="text" placeholder="Search">
                                            <button><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="user">
                                            <a href="wishlist.html" class="btn wishlist">
                                                <i class="fa fa-heart"></i>
                                                <span>(0)</span>
                                            </a>
                                            <a id="shoppinglistlink" href="cart.html" class="btn cart">
                                                <i class="fa fa-shopping-cart"></i>
                                                <span id="shoppingListSize"></span>
                                                <script>

                                                    //shoppingList.subscribe(document.querySelector('#shoppingListSize'));

                                                    shoppingList.subscribeCallback((list) => {
                                                        document.querySelector('#shoppingListSize').innerHTML = '(' + list.length + ')';
                                                        document.querySelector('#shoppinglistlink').href = '${MyConfig.getHost()}myshoppinglist?shoppinglist=' + shoppingList.toString();
                                                        document.querySelector('#shoppinglistlink2').href = '${MyConfig.getHost()}myshoppinglist?shoppinglist=' + shoppingList.toString();
                                                    });
													
                                                    
                                                </script>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Bottom Bar End -->