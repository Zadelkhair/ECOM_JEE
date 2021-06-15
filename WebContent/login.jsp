<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>


    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%@ include file="components/header.jsp" %>

            <!-- Login Start -->
            <div class="login">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <form action="login" method="post">
                                <div class="login-form">
                                    <div class="row">
                                        <div class="col-12">
                                            <h2 class="mb-5">Login</h2>
                                        </div>
                                        <div class="col-12">
                                            <label>E-mail / Username</label>
                                            <input class="form-control" type="text" name="username"
                                                placeholder="E-mail / Username">
                                        </div>
                                        <div class="col-12">
                                            <label>Password</label>
                                            <input class="form-control" type="password" name="password"
                                                placeholder="Password">
                                        </div>
                                        <div class="col-12">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" name="keep_me_signed_in"
                                                    class="custom-control-input" id="newaccount">
                                                <label class="custom-control-label" for="newaccount">Keep me signed
                                                    in</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <button class="btn">Submit</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <!-- Login End -->

            <%@ include file="components/footer.jsp" %>