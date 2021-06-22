<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
								<div class="tab-content row" id="categories-tab" role="tabpanel"
									aria-labelledby="categories-nav">

									<c:if test="${user != null}">

										<div class="col-12">
											<div class="tab-content">
												<h4>Account Details</h4>
												<form action="" method="post">
													<div class="row">

														<input type="text" class="d-none" name="action" value="save">
														<input type="text" class="d-none" name="id" value="${user.id}">

														<div class="col-md-12">
															<input class="form-control" type="text" name="username"
																placeholder="Usernam" value="${user.username}">
														</div>
														<div class="col-md-12">
															<div class="form-group">

																<select class="form-control" name="role_id">
																	<c:forEach items="${roles}" var="role">

																		<c:set var="selected" value=""></c:set>

																		<c:if test="${ user.role_id == role.id }">
																			<c:set var="selected" value="selected"></c:set>
																		</c:if>

																		<option ${ selected } value="${role.id}">${
																			role.name }</option>

																	</c:forEach>
																</select>

															</div>
														</div>



														<div class="col-md-12">
															<input class="form-control" type="text" name="phone"
																placeholder="Mobile" value="${user.phone}">
														</div>
														<div class="col-md-12">
															<input class="form-control" type="text" name="email"
																placeholder="Email" value="${user.email}">
														</div>
														<div class="col-md-6">
															<input class="form-control" type="text" name="country"
																placeholder="Country" value="${user.country}">
														</div>
														<div class="col-md-6">
															<input class="form-control" type="text" name="city"
																placeholder="City" value="${user.city}">
														</div>
														<div class="col-md-12">
															<textarea class="form-control" name="address"
																placeholder="Address">${user.address}</textarea>
														</div>
														<div class="col-md-12">
															<textarea class="form-control" name="ship_address"
																placeholder="Shipping address">${user.ship_address}</textarea>
														</div>
														<div class="col-md-12">
															<input class="form-control" name="password" type="password"
																placeholder="Password">
														</div>
														<div class="col-md-12">
															<button type="submit" class="btn">Save Changes</button>
														</div>
													</div>
												</form>

											</div>
										</div>

									</c:if>

									<c:if test="${users != null}">

										<div class="table-responsive col-12">
											<div class="d-flex align-items-center">
												<h6 class="flex-grow-1">Users : </h6>
												<a class="btn btn-primary" href="?edit=-1" role="button">Ajouter</a>
											</div>
											<hr>
											<table id="tableOrder" class="table table-bordered">
												<thead class="thead-dark">
													<tr>
														<th>No</th>
														<th>username</th>
														<th>role</th>
														<th>city</th>
														<th>phone</th>
														<th>email</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${users}" var="user">
														<tr>
															<td>${user.id}</td>
															<td>${user.username}</td>
															<td>
																<c:if test="${user.role_id == null}">
																	<span
																		class="status-order canceled-status bg-danger">-</span>
																</c:if>
																<c:if test="${user.role_id == 1}">
																	<span
																		class="status-order in-progress-status bg-success">admin</span>
																</c:if>
																<c:if test="${user.role_id == 7}">
																	<span
																		class="status-order confirmed-status bg-primary">user</span>
																</c:if>
															</td>
															<td>${user.city}</td>
															<td>${user.phone}</td>
															<td>${user.email}</td>
															<td>
																<a id="editBtn" style="background: none;border: none;"
																	class="mr-2 p-0 m-0 text-primary"
																	href="?edit=${user.id}">
																	<i class="fas fa-eye"></i>
																</a>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</div>

									</c:if>

								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- My Account End -->

				<%@ include file="../components/footer.jsp" %>