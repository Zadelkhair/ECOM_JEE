<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

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
						
								<c:if test="${supplier != null}">

									<div class="col-12">
										<h6>Supplier : </h6>
										<hr>
										<form method="post" id="editCategorieForm" action="">
											<div class="row">
												
												<input type="text" value="save" 
													name="method" class="d-none" />
												<input type="text" value="${supplier.id}"
													name="id" class="d-none" />

												<div class="col-12">
													<div class="form-group">
														<label for="name">Supplier Name</label>
														<input type="text" name="supplier_name" id="supplier_name" class="form-control"
															value="${supplier.supplier_name}" placeholder="supplier name"
															aria-describedby="supplier_namehelpId">
													</div>
												</div>

												<div class="col-12">
													<div class="form-group">
														<label for="description">Address</label>
														<input type="text" name="address" id="address"
															class="form-control" placeholder="address"
															value="${supplier.address}"
															aria-describedby="addresshelpId">
													</div>
												</div>
                                                    <div class="col-12">
													<div class="form-group">
														<label for="description">Country</label>
														<input type="text" name="country" id="country"
															class="form-control" placeholder="country"
															value="${supplier.country}"
															aria-describedby="countryhelpId">
													</div>
												</div>
												<div class="col-12">
													<input type="submit" class="btn btn-primary" />
												</div>

											</div>
										</form>
									</div>

								</c:if>

								<c:if test="${suppliers != null}">

									<div class="table-responsive col-12">
										<div class="d-flex align-items-center" >
											<h6 class="flex-grow-1" >Suppliers : </h6>
											<a class="btn btn-primary" href="?edit=-1" role="button">Ajouter</a>
										</div>
										<hr>
										<table id="tableCategorie" class="table table-bordered">
											<thead class="thead-dark">
												<tr>
													<th>No</th>
													<th>Supplier Name</th>
													<th>Address</th>
												     <th>Country</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${suppliers}" var="cat">	
													<tr>
														<td>${cat.id}</td>
														<td>${cat.supplier_name}</td>
														<td>${cat.address}</td>
														<td>${cat.country}</td>
														<td>
															<a id="editBtn" style="background: none;border: none;"
																class="mr-2 p-0 m-0 text-success"
																href="?edit=${cat.id}"><i class="fas fa-edit"></i></a>
															<a id="deleteBtn" style="background: none;border: none;"
																class="mr-2 p-0 m-0 text-primary"><i
																	class="fas fa-eye"></i></a>
															<form action="" method="post" class="d-inline m-0 p-0">
																
																<input type="text" name="method"
																	value="delete" class="d-none">
																<input type="text" name="id"
																	value="${cat.id}" class="d-none">


																<button style="background: none;border: none;"
																	type="submit"
																	class="mr-2 p-0 m-0 text-danger">
																	<i class="fa fa-trash" aria-hidden="true"></i>
																</button>
															</form>
														</td>
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