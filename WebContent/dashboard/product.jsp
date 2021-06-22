<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<%@ page import="com.ecomjeegi.MyConfig" %>
		<%@ include file="../components/header.jsp" %>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<script>
					function readURL(input, imgId) {
						if (input.files && input.files[0]) {
							var reader = new FileReader();

							reader.onload = function (e) {
								$('#' + imgId)
									.attr('src', e.target.result);
							};

							reader.readAsDataURL(input.files[0]);
						}
					}
				</script>
				
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

									<c:if test="${product != null}">

										<div class="col-12">
											<h6>Product : </h6>
											<hr>
											<form method="post" id="editCategorieForm" action=""
												enctype="multipart/form-data">
												<div class="row">

													<input type="text" value="save" name="method" class="d-none" />
													<input type="text" value="${product.id}" name="id" class="d-none" />

													<div class="col-12">
														<div class="row">
															<div class="col-8">
																<div class="row">
																	<div class="col-12">
																		<div class="form-group">
																			<label for="name">Name</label>
																			<input type="text" name="product_name"
																				id="name" class="form-control"
																				value="${product.product_name}"
																				placeholder="name"
																				aria-describedby="namehelpId">
																		</div>
																	</div>

																	<div class="col-12">
																		<div class="form-group">
																			<label for="">Image</label>
																			<input type="file"
																				class="custom-file-input form-control"
																				id="image"
																				onchange="readURL(document.querySelector('#image'),'imgProd')"
																				style="opacity: 1;" name="image"
																				placeholder="image">
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-4">

																<div style="height: 166px; position: relative;">

																	<c:if test="${(product.image!=null)}">
																		<img src="${MyConfig.getHost()}${product.image}"
																			id="imgProd"
																			style="position: absolute; transform: translateY(-50%); top: 50%;right: 0;height: 100%;max-width: 100%;"
																			class="img-fluid" alt="Sheep">
																	</c:if>
																	<c:if test="${!(product.image!=null)}">
																		<img src="${MyConfig.getHost()}img/prod.jpg"
																			id="imgProd"
																			style="position: absolute; transform: translateY(-50%); top: 50%;right: 0;height: 100%;max-width: 100%;"
																			class="img-fluid" alt="Sheep">
																	</c:if>

																</div>



															</div>
														</div>
													</div>



													<div class="col-12">
														<div class="form-group">
															<label for="description">price</label>
															<input type="number" name="price" id="description"
																class="form-control" placeholder="price"
																value="${product.price}"
																aria-describedby="descriptionhelpId">
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="description">size</label>
															<input type="text" name="size" id="description"
																class="form-control" placeholder="size"
																value="${product.size}"
																aria-describedby="descriptionhelpId">
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="description">color</label>
															<input type="text" name="color" id="description"
																class="form-control" placeholder="color"
																value="${product.color}"
																aria-describedby="descriptionhelpId">
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="description">garmentType</label>
															<input type="text" name="garmentType" id="description"
																class="form-control" placeholder="garmentType"
																value="${product.garmentType}"
																aria-describedby="descriptionhelpId">
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="description">rating</label>
															<input type="number" name="rating" id="description"
																class="form-control" placeholder="rating"
																value="${product.rating}"
																aria-describedby="descriptionhelpId">
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="description">supplier_id </label>
															<input type="number" name="supplier_id" id="description"
																class="form-control" placeholder="supplier_id"
																value="${product.supplier_id }"
																aria-describedby="descriptionhelpId">
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="description">category_id </label>
															<input type="number" name="category_id" id="description"
																class="form-control" placeholder="category_id"
																value="${product.category_id  }"
																aria-describedby="descriptionhelpId">
														</div>
													</div>

													<div class="col-12">
														<input type="submit" class="btn btn-primary" />
													</div>

												</div>
											</form>
										</div>

									</c:if>

									<c:if test="${products != null}">

										<div class="table-responsive col-12">
											<div class="d-flex align-items-center">
												<h6 class="flex-grow-1">Products : </h6>
												<a class="btn btn-primary" href="?edit=-1" role="button">Ajouter</a>
											</div>
											<hr>
											<table id="tableCategorie" class="table table-image">
												<thead class="thead-dark">
													<tr>
														<th scope="col">No</th>
														<th scope="col">image</th>
														<th scope="col">product_name</th>
														<th scope="col">price</th>
														<th scope="col">size</th>
														<th scope="col">color</th>
														<th scope="col">garmentType</th>
														<th scope="col">rating</th>
														<th scope="col">supplier_id</th>
														<th scope="col">category_id </th>
														<th scope="col">Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${products}" var="prod">
														<tr>
															<td scope="row">${prod.id}</td>
															<td class="w-25">
																<c:if
																	test="${(prod.image!=null && not empty prod.image)}">
																	<img src="${MyConfig.getHost()}${prod.image}"
																		class="img-fluid img-thumbnail" alt="Sheep">
																</c:if>
																<c:if
																	test="${!(prod.image!=null && not empty prod.image)}">
																	<img src="${MyConfig.getHost()}img/prod.jpg"
																		class="img-fluid img-thumbnail" alt="Sheep">
																</c:if>
															</td>
															<td>${prod.product_name}</td>
															<td>${prod.price}</td>
															<td>${prod.size}</td>
															<td>${prod.color}</td>
															<td>${prod.garmentType}</td>
															<td>${prod.rating}</td>
															<td>${prod.supplier_id}</td>
															<td>${prod.category_id}</td>
															<td>
																<a id="editBtn" style="background: none;border: none;"
																	class="mr-2 p-0 m-0 text-success"
																	href="?edit=${prod.id}"><i
																		class="fas fa-edit"></i></a>
																<a id="deleteBtn" style="background: none;border: none;"
																	class="mr-2 p-0 m-0 text-primary"><i
																		class="fas fa-eye"></i></a>
																<form action="" method="post" class="d-inline m-0 p-0">

																	<input type="text" name="method" value="delete"
																		class="d-none">
																	<input type="text" name="id" value="${prod.id}"
																		class="d-none">


																	<button style="background: none;border: none;"
																		type="submit" class="mr-2 p-0 m-0 text-danger">
																		<i class="fa fa-trash" aria-hidden="true"></i>
																	</button>
																</form>
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