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
						
								<c:if test="${categorie != null}">

									<div class="col-12">
										<h6>Categorie : </h6>
										<hr>
										<form method="post" id="editCategorieForm" action="">
											<div class="row">
												
												<input type="text" value="save" 
													name="method" class="d-none" />
												<input type="text" value="${categorie.id}"
													name="id" class="d-none" />

												<div class="col-12">
													<div class="form-group">
														<label for="name">Name</label>
														<input type="text" name="name" id="name" class="form-control"
															value="${categorie.name}" placeholder="name"
															aria-describedby="namehelpId">
													</div>
												</div>

												<div class="col-12">
													<div class="form-group">
														<label for="fa_icon">Fontawsom icon</label>
														<input type="text" name="fa_icon" id="fa_icon" class="form-control"
															value="${categorie.fa_icon}" placeholder="fa_icon"
															aria-describedby="namehelpId">
													</div>
												</div>

												<div class="col-12">
													<div class="form-group">
														<label for="description">Description</label>
														<input type="text" name="description" id="description"
															class="form-control" placeholder="description"
															value="${categorie.description}"
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

								<c:if test="${categories != null}">

									<div class="table-responsive col-12">
										<div class="d-flex align-items-center" >
											<h6 class="flex-grow-1" >Categories : </h6>
											<a class="btn btn-primary" href="?edit=-1" role="button">Ajouter</a>
										</div>
										<hr>
										<table id="tableCategorie" class="table table-bordered">
											<thead class="thead-dark">
												<tr>
													<th>No</th>
													<th>Icon</th>
													<th>Name</th>
													<th>Description</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${categories}" var="cat">	
													<tr>
														<td>${cat.id}</td>
														<td><i class="${cat.fa_icon}" ></i></td>
														<td>${cat.name}</td>
														<td>${cat.description}</td>
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