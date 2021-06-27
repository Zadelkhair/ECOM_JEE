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

									<c:if test="${advertisement != null}">

										<div class="col-12">
											<h6>Advertisement : </h6>
											<hr>
											<form method="post" id="editCategorieForm" action=""
												enctype="multipart/form-data">
												<div class="row">

													<input type="text" value="save" name="method" class="d-none" />
													<input type="text" value="${advertisement.id}" name="id"
														class="d-none" />

													<div class="col-12 mb-4">

														<div style="height: 400px;">

															<c:if test="${(advertisement.image!=null)}">
																<img src="${MyConfig.getHost()}${advertisement.image}"
																	id="imgAds"
																	style="width: 100%;height: 100%;object-fit: cover;"
																	class="img-fluid" alt="Sheep">
															</c:if>
															<c:if test="${!(advertisement.image!=null)}">
																<img src="${MyConfig.getHost()}img/prod.jpg" id="imgAds"
																	style="width: 100%;height: 100%;object-fit: cover;" class="img-fluid" alt="Sheep">
															</c:if>

														</div>

													</div>

													<div class="col-12 ">
														<div class="form-group">
															<label for="">Image</label>
															<input type="file" class="custom-file-input form-control"
																id="image"
																onchange="readURL(document.querySelector('#image'),'imgAds')"
																style="opacity: 1;" name="image" placeholder="image">
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="description">description</label>
															<textarea name="description" class="form-control" id=""
																name="description" rows="10"
																placeholder="description">${advertisement.description}</textarea>
														</div>
													</div>

													<div class="col-12">
														<div class="form-group">
															<label for="position">position</label>
															<input type="number" name="position" class="form-control"
																placeholder="position" value="${advertisement.position}" />
														</div>
													</div>

													<div class="col-12">
														<input type="submit" class="btn btn-primary" />
													</div>

												</div>
											</form>
										</div>

									</c:if>

									<c:if test="${advertisements != null}">

										<div class="table-responsive col-12">
											<div class="d-flex align-items-center">
												<h6 class="flex-grow-1">Advertisements : </h6>
												<a class="btn btn-primary" href="?edit=-1" role="button">Ajouter</a>
											</div>
											<hr>
											<table id="tableCategorie" class="table table-image">
												<thead class="thead-dark">
													<tr>
														<th scope="col">No</th>
														<th scope="col">image</th>
														<th scope="col">description</th>
														<th scope="col">position</th>
														<th scope="col">Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${advertisements}" var="adv">
														<tr>
															<td scope="row">${adv.id}</td>
															<td class="w-25">
																<c:if
																	test="${(adv.image!=null && not empty adv.image)}">
																	<img src="${MyConfig.getHost()}${adv.image}"
																		class="img-fluid img-thumbnail" alt="Sheep">
																</c:if>
																<c:if
																	test="${!(adv.image!=null && not empty adv.image)}">
																	<img src="${MyConfig.getHost()}img/prod.jpg"
																		class="img-fluid img-thumbnail" alt="Sheep">
																</c:if>
															</td>
															<td>${adv.description}</td>
															<td>
																<c:if test="${adv.position != -1}" >
																	<div class="adv-pos" >${adv.position}</div>
																</c:if>	
																<c:if test="${adv.position == -1}" >
																	<div class="adv-pos bg-secondary" >-</div>
																</c:if>	
															</td>
															<td>
																<a id="editBtn" style="background: none;border: none;"
																	class="mr-2 p-0 m-0 text-success"
																	href="?edit=${adv.id}"><i
																		class="fas fa-edit"></i></a>
																<a id="deleteBtn" style="background: none;border: none;"
																	class="mr-2 p-0 m-0 text-primary"><i
																		class="fas fa-eye"></i></a>
																<form action="" method="post" class="d-inline m-0 p-0">

																	<input type="text" name="method" value="delete"
																		class="d-none">
																	<input type="text" name="id" value="${adv.id}"
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