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

									<c:if test="${order != null}">

										<div class="col-12">
											<div class="d-flex">
												<h6>Order : </h6>
												<div class="flex-grow-1"></div>

												<form action="" method="post">
													<input type="text" class="d-none" name="method" value="confirm">
													<input type="text" class="d-none" name="id" value="${order.id}">
													<button type="submit" class="btn mr-2">Confirm</button>
												</form>
												<form action="" method="post">
													<input type="text" class="d-none" name="method" value="cancel">
													<input type="text" class="d-none" name="id" value="${order.id}">
													<button type="submit" class="btn">Cancel</button>
												</form>


											</div>
											<hr>
											<div class="row">
												<c:forEach items="${orderDetails}" var="orderDet">
													<div class="col-3">
														<div class="order-prof-item">
															<div class="order-prod-image">
																<img src="${MyConfig.getHost()}${orderDet.product.image}"
																	alt="">
															</div>
															<div class="order-prod-details">
																<span class="font-weight-bold">Name
																	:</span>${orderDet.product.product_name}</br>
																<span class="font-weight-bold">Price
																	:</span>${orderDet.product.price}</br>
																<span class="font-weight-bold">Quantity
																	:</span>${orderDet.order_quantity}</br>
																<span class="font-weight-bold">Total price
																	:</span>${orderDet.order_quantity *
																orderDet.product.price}</br>
															</div>
														</div>
													</div>

												</c:forEach>
											</div>

										</div>

									</c:if>

									<c:if test="${orders != null}">

										<div class="table-responsive col-12">
											<div class="d-flex align-items-center">
												<h6 class="flex-grow-1">Orders : </h6>
												<a class="btn btn-primary" href="?edit=-1" role="button">Ajouter</a>
											</div>
											<hr>
											<table id="tableOrder" class="table table-bordered">
												<thead class="thead-dark">
													<tr>
														<th>No</th>
														<th>delivery_charges</th>
														<th>transaction_status</th>
														<th>ship_date</th>
														<th>order_date</th>
														<th>username</th>
														<th>price</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${orders}" var="order">
														<tr>
															<td>${order.id}</td>
															<td>${order.delivery_charges}</td>
															<td>
																<c:if test="${order.transaction_status == -1}">
																	<span
																		class="status-order canceled-status bg-danger">canceled</span>
																</c:if>
																<c:if test="${order.transaction_status == 0}">
																	<span
																		class="status-order in-progress-status bg-warning">in
																		progress</span>
																</c:if>
																<c:if test="${order.transaction_status == 1}">
																	<span
																		class="status-order confirmed-status bg-success">confirmed</span>
																</c:if>
																<c:if test="${order.transaction_status == 4}">
																	<span
																		class="status-order delevred-status bg-primary">delevred</span>
																</c:if>
															</td>
															<td>${order.ship_date}</td>
															<td>${order.order_date}</td>
															<td>${order.user.username}</td>
															<td>${order.price}</td>
															<td>
																<a id="editBtn" style="background: none;border: none;"
																	class="mr-2 p-0 m-0 text-primary"
																	href="?edit=${order.id}">
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