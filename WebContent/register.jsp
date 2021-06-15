<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>


    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%@ include file="components/header.jsp" %>

        <!-- Login Start -->
        <div class="login">
            <div class="container-fluid">
                <form action="register" method="post">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="register-form">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Username</label>
                                        <input class="form-control" name="username" type="text"
                                            placeholder="First Name">
                                    </div>
                                    <div class="col-md-6">
                                        <label>Email</label>
                                        <input class="form-control" name="email" type="email" placeholder="Email">
                                    </div>
                                    <div class="col-md-6">
                                        <label>Password</label>
                                        <input class="form-control" name="password" type="password"
                                            placeholder="Password">
                                    </div>
                                    <div class="col-md-6">
                                        <label>Retype Password</label>
                                        <input class="form-control" name="retype_password" type="password"
                                            placeholder="Password">
                                    </div>

                                    <div class="col-md-6">
                                        <label>City</label>
                                        <input class="form-control" name="city" type="text" placeholder="city">
                                    </div>
                                    <div class="col-md-6">
                                        <label>Phone</label>
                                        <input class="form-control" name="phone" type="text" placeholder="Phone">
                                    </div>
                                    <div class="col-md-6">
                                        <label>Addresse</label>
                                        <input class="form-control" name="address" type="text"
                                            placeholder="Addresse">
                                    </div>
                                    <div class="col-md-12">
                                        <button class="btn">Submit</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Login End -->

        <%@ include file="components/footer.jsp" %>