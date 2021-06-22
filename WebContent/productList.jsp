<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<%@ page import="com.ecomjeegi.MyConfig" %>

		<%@ include file="components/header.jsp" %>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<!-- Breadcrumb Start -->
				<div class="breadcrumb-wrap">
					<div class="container-fluid">
						<ul class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">Products</a></li>
							<li class="breadcrumb-item active">Product List</li>
						</ul>
					</div>
				</div>
				<!-- Breadcrumb End -->



				<!-- Product List Start -->
				<div class="product-view">
					<div class="container-fluid">
						<div class="row">
							<div class="col-lg-8">
								<div class="row">
									<div class="col-md-12">
										<div class="product-view-top">
											<div class="row">
												<div class="col-md-3">
													<div class="product-search">
														<input type="search" class="form-control" id="nameProd"
															placeholder="search">
														<button
															onclick="searchByName(document.querySelector('#nameProd').value)"><i
																class="fa fa-search"></i></button>
													</div>
												</div>
												<div class="col-md-3">
													<div class="product-short">
														<div class="dropdown">
															<div class="form-group">
																<select class="form-control" id="sortBy"
																	onchange="sortBySelectOnChage()">
																	<option value="none">sort by</option>
																	<option value="name">name</option>
																	<option value="price">price</option>
																	<option value="views">by views</option>
																	<option value="rating">by rating</option>
																	<option value="seller">best seller</option>
																</select>
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-3">
													<div class="product-price-range">
														<div class="dropdown">
															<div class="form-group">
																<select class="form-control" id="fromPrice"
																	onchange="priceOnChage('from')">
																	<option value="-1">from -</option>
																	<c:forEach begin="100" end="1000" step="100"
																		var="i">
																		<option value="${i}">from ${i}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-3">
													<div class="product-price-range">
														<div class="dropdown">
															<div class="form-group">
																<select class="form-control" id="toPrice"
																	onchange="priceOnChage('to')">
																	<option value="-1">to -</option>
																	<c:forEach begin="100" end="1000" step="100"
																		var="i">
																		<option value="${i}">to ${i}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<c:forEach items="${products}" var="prod">
										<div class="col-md-4">
											<div class="product-item">
												<div class="product-title">
													<a href="${MyConfig.getHost()}product?id=${prod.id}">${prod.product_name}</a>
													<div class="ratting">
														<c:forEach var="i" begin="1" end="${prod.productRate}">
															<i class="fa fa-star"></i>
														</c:forEach>
														<c:forEach var="i" begin="1" end="${5 - prod.productRate}">
															<i class="fa fa-star n-stars-icon text-white"></i>
														</c:forEach>
													</div>
												</div>
												<div class="product-image">
													<a href="product-detail.html">
														<img src="${MyConfig.getHost()}${prod.image}"
															alt="Product Image">
													</a>
													<div class="product-action">
														<button onclick="shoppingList.save({product_id:${prod.id},quantity:1})" href="#"><i class="fa fa-cart-plus"></i></button>
														<button href="#"><i class="fa fa-heart"></i></button>
														<button onclick="(()=>{ window.location = '${MyConfig.getHost()}product?id=${prod.id}'; })()"><i class="fa fa-search"></i></button>
													</div>
												</div>
												<div class="product-price">
													<h3>${prod.price}</h3>
													<button onclick="(()=>{ window.location='${MyConfig.getHost()}makeorder?product_id=${product.id}'; })();" class="btn" href=""><i class="fa fa-shopping-cart"></i>
														Buy Now</button>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>

								<!-- Pagination Start -->
								<c:if test="${lastpage != null}">
									<div class="col-md-12 ${currpage>lastpage? 'd-none':''}">
										<nav aria-label="Page navigation example">
											<ul class="pagination justify-content-center">
												<li class="page-item ">
													<a class="page-link btn ${currpage<=0? 'disabled':''}" href="#"
														tabindex="-1">Previous</a>
												</li>

												<c:forEach var="i" begin="${startpaginationbtn}"
													end="${endpaginationbtn}">
													<li class="page-item ${currpage==i? 'active' : ''}"><a
															class="page-link" href="#">${i}</a></li>
												</c:forEach>

												<li class="page-item">
													<a class="page-link btn ${currpage>=lastpage? 'disabled':''}"
														href="#">Next</a>
												</li>
											</ul>
										</nav>
									</div>
								</c:if>
								<!-- Pagination Start -->
							</div>

							<!-- Side Bar Start -->
							<div class="col-lg-4 sidebar">
								<div class="sidebar-widget category">
									<h2 class="title">Category</h2>
									<nav class="navbar bg-light">
										<ul class="navbar-nav">

											<c:set var="currCate" value=""></c:set>

											<c:if test="${ categorieProds == null }">
												<c:set var="currCate" value="currentcat"></c:set>
											</c:if>

											<li class="nav-item">
												<button onclick="searchByCategorie(-1)" class="nav-link ${currCate}"
													href="#">
													<i class="fa fa-female"></i>
													all categories
												</button>
											</li>

											<c:forEach items="${categories}" var="cat">
												<li class="nav-item">

													<c:set var="currCate" value=""></c:set>

													<c:catch var="categorieProds">
														<c:if test="${categorieProds.id ==  cat.id}">
															<c:set var="currCate" value="currentcat"></c:set>
														</c:if>
													</c:catch>

													<button onclick="searchByCategorie(${cat.id})"
														class="nav-link ${currCate}">
														<i class="fa fa-female"></i>
														${cat.name}
													</button>
												</li>
											</c:forEach>

										</ul>
									</nav>
								</div>

								<div class="sidebar-widget widget-slider">
									<div class="sidebar-slider normal-slider">
										<div class="product-item">
											<div class="product-title">
												<a href="#">Product Name</a>
												<div class="ratting">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
											</div>
											<div class="product-image">
												<a href="product-detail.html">
													<img src="img/product-10.jpg" alt="Product Image">
												</a>
												<div class="product-action">
													<a href="#"><i class="fa fa-cart-plus"></i></a>
													<a href="#"><i class="fa fa-heart"></i></a>
													<a href="#"><i class="fa fa-search"></i></a>
												</div>
											</div>
											<div class="product-price">
												<h3><span>$</span>99</h3>
												<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy Now</a>
											</div>
										</div>
										<div class="product-item">
											<div class="product-title">
												<a href="#">Product Name</a>
												<div class="ratting">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
											</div>
											<div class="product-image">
												<a href="product-detail.html">
													<img src="img/product-9.jpg" alt="Product Image">
												</a>
												<div class="product-action">
													<a href="#"><i class="fa fa-cart-plus"></i></a>
													<a href="#"><i class="fa fa-heart"></i></a>
													<a href="#"><i class="fa fa-search"></i></a>
												</div>
											</div>
											<div class="product-price">
												<h3><span>$</span>99</h3>
												<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy Now</a>
											</div>
										</div>
										<div class="product-item">
											<div class="product-title">
												<a href="#">Product Name</a>
												<div class="ratting">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
											</div>
											<div class="product-image">
												<a href="product-detail.html">
													<img src="img/product-8.jpg" alt="Product Image">
												</a>
												<div class="product-action">
													<a href="#"><i class="fa fa-cart-plus"></i></a>
													<a href="#"><i class="fa fa-heart"></i></a>
													<a href="#"><i class="fa fa-search"></i></a>
												</div>
											</div>
											<div class="product-price">
												<h3><span>$</span>99</h3>
												<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy Now</a>
											</div>
										</div>
									</div>
								</div>

								<div class="sidebar-widget brands">
									<h2 class="title">Our Brands</h2>
									<ul>
										<li><a href="#">Nulla </a><span>(45)</span></li>
										<li><a href="#">Curabitur </a><span>(34)</span></li>
										<li><a href="#">Nunc </a><span>(67)</span></li>
										<li><a href="#">Ullamcorper</a><span>(74)</span></li>
										<li><a href="#">Fusce </a><span>(89)</span></li>
										<li><a href="#">Sagittis</a><span>(28)</span></li>
									</ul>
								</div>

								<div class="sidebar-widget tag">
									<h2 class="title">Tags Cloud</h2>
									<a href="#">Lorem ipsum</a>
									<a href="#">Vivamus</a>
									<a href="#">Phasellus</a>
									<a href="#">pulvinar</a>
									<a href="#">Curabitur</a>
									<a href="#">Fusce</a>
									<a href="#">Sem quis</a>
									<a href="#">Mollis metus</a>
									<a href="#">Sit amet</a>
									<a href="#">Vel posuere</a>
									<a href="#">orci luctus</a>
									<a href="#">Nam lorem</a>
								</div>
							</div>
							<!-- Side Bar End -->
						</div>
					</div>
				</div>
				<!-- Product List End -->

				<!-- Brand Start -->
				<div class="brand">
					<div class="container-fluid">
						<div class="brand-slider">
							<div class="brand-item"><img src="img/brand-1.png" alt=""></div>
							<div class="brand-item"><img src="img/brand-2.png" alt=""></div>
							<div class="brand-item"><img src="img/brand-3.png" alt=""></div>
							<div class="brand-item"><img src="img/brand-4.png" alt=""></div>
							<div class="brand-item"><img src="img/brand-5.png" alt=""></div>
							<div class="brand-item"><img src="img/brand-6.png" alt=""></div>
						</div>
					</div>
				</div>
				<!-- Brand End -->

				<script>
					query = getQueryParams(document.location.search);

					loadInputs();

					function loadInputs() {
						if (query.product_name)
							document.querySelector('#nameProd').value = query.product_name;

						if (query.sortby)
							document.querySelector('#sortBy').value = query.sortby;

						if (query.pricebetweena)
							document.querySelector('#fromPrice').value = query.pricebetweena;

						if (query.pricebetweenb)
							document.querySelector('#toPrice').value = query.pricebetweenb;
					}

					function getQueryParams(qs) {
						qs = qs.split('+').join(' ');

						var params = {},
							tokens,
							re = /[?&]?([^=]+)=([^&]*)/g;

						while (tokens = re.exec(qs)) {
							params[decodeURIComponent(tokens[1])] = decodeURIComponent(tokens[2]);
						}

						return params;
					}

					function search() {

						let link = "${MyConfig.getHost()}productlist?";

						for (const [key, value] of Object.entries(query)) {
							if (key && value !== null)
								link += key + "=" + value + "&";
						}
						link = link.substr(0, link.length - 1)

						window.location = link;
					}

					function searchByCategorie(id_cat) {

						if (id_cat != -1)
							query.category_id = id_cat
						else {
							query.category_id = null;
						}

						search();

					}

					function searchByName(name) {

						if (name!==null)
							query.product_name = name
						else {
							query.product_name = null;
						}

						search();

					}

					function sortBy(sortby) {

						if (sortby != "none")
							query.sortby = sortby;
						else {
							query.sortby = null;
						}

						search();

					}

					function searchByFromPrice(pricebetweena) {

						if (pricebetweena != -1) {
							query.pricebetweena = pricebetweena;
						} else {
							query.pricebetweena = null;
						}

						if (!query.pricebetweena && query.pricebetweenb)
							search();

					}

					function searchByToPrice(pricebetweenb) {

						if (pricebetweenb != -1) {
							query.pricebetweenb = pricebetweenb;
						} else {
							query.pricebetweenb = null;
						}

						if (query.pricebetweena || (!query.pricebetweena && query.pricebetweenb))
							search();

					}

					function page(page) {

						if (page) {
							query.page = page;
						} else {
							query.page = null;
						}

						search();

					}

					function numberofpage(numberofpage) {

						if (numberofpage) {
							query.numberofpage = numberofpage;
						} else {
							query.numberofpage = null;
						}

						search();

					}

					function sortBySelectOnChage() {
						sortBy(document.querySelector('#sortBy').value);
					}

					function priceOnChage(type) {
						if (type == 'from') {
							let fromPrice = document.querySelector('#fromPrice').value;
							searchByFromPrice(fromPrice);
						}
						else if (type == 'to') {
							let toPrice = document.querySelector('#toPrice').value;
							searchByToPrice(toPrice);
						}
					}

				</script>


				<%@ include file="components/footer.jsp" %>