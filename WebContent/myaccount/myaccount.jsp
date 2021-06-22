<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ page import="com.ecomjeegi.app.App" %>
		<%@ page import="com.ecomjeegi.MyConfig" %>
			<%@ include file="../components/header.jsp" %>
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

					<!-- Breadcrumb Start -->
					<div class="breadcrumb-wrap">
						<div class="container-fluid">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item"><a href="#">Products</a></li>
								<li class="breadcrumb-item active">My Account</li>
							</ul>
						</div>
					</div>
					<!-- Breadcrumb End -->

					<!-- My Account Start -->
					<div class="my-account">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-3">
									<%@ include file="./components/sidebar.jsp" %>
								</div>
								<div class="col-md-9">
									<div class="tab-content">
										<h4>Account Details</h4>
										<form action="" method="post">
											<div class="row mb-4">
												<input type="text" class="d-none" name="action" value="updateAccount">
												<div class="col-md-12">
													<input class="form-control" type="text" name="username"
														placeholder="Usernam"
														value="${App.getInstance().auth.authentificatedUser.username}">
												</div>
												<div class="col-md-12">
													<input class="form-control" type="text" name="phone"
														placeholder="Mobile"
														value="${App.getInstance().auth.authentificatedUser.phone}">
												</div>
												<div class="col-md-12">
													<input class="form-control" type="text" name="email"
														placeholder="Email"
														value="${App.getInstance().auth.authentificatedUser.email}">
												</div>
												<div class="col-md-6">
													<input class="form-control" type="text" name="country"
														placeholder="Country"
														value="${App.getInstance().auth.authentificatedUser.country}">
												</div>
												<div class="col-md-6">
													<input class="form-control" type="text" name="city"
														placeholder="City"
														value="${App.getInstance().auth.authentificatedUser.city}">
												</div>
												<div class="col-md-12">
													<textarea class="form-control" name="address" placeholder="Address">${App.getInstance().auth.authentificatedUser.address}</textarea>
												</div>
												<div class="col-md-12">
													<textarea class="form-control" name="ship_address"
														placeholder="Shipping address">${App.getInstance().auth.authentificatedUser.ship_address}</textarea>
												</div>
												<div class="col-md-12">
													<button type="submit" class="btn">Update Account</button>
												</div>
											</div>
										</form>

										<h4>Password change</h4>
										<form action="" method="post">
											<div class="row">
												<input type="text" class="d-none" name="action" value="updatePassword">
												<div class="col-md-12">
													<input class="form-control" name="currpassword" type="password"
														placeholder="Current Password">
												</div>
												<div class="col-md-6">
													<input class="form-control" name="newpassword" type="password" placeholder="New Password">
												</div>
												<div class="col-md-6">
													<input class="form-control" type="password"
														placeholder="Confirm Password">
												</div>
												<div class="col-md-12">
													<button type="submit" class="btn">Save Changes</button>
												</div>
											</div>
										</form>

									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- My Account End -->

					<%@ include file="../components/footer.jsp" %>