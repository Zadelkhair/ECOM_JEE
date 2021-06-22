<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<%@ page import="com.ecomjeegi.MyConfig" %>

		<%@ page import="com.ecomjeegi.app.App" %>

			<%@ include file="components/header.jsp" %>
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



					<!-- Breadcrumb Start -->
					<div class="breadcrumb-wrap">
						<div class="container-fluid">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item"><a href="#">Products</a></li>
								<li class="breadcrumb-item active">Checkout</li>
							</ul>
						</div>
					</div>
					<!-- Breadcrumb End -->

					<!-- Checkout Start -->
					<div class="checkout">
						<div class="container-fluid">
							<form action="" method="POST" >
								
								<input type="text" name="products" id="products-order" class="d-none">
								<script>
									let productsOrder = document.querySelector('#products-order');
									productsOrder.value = Cookies.get('shoppingList_${App.getInstance().auth.authentificatedUser.id}');
								</script>
								<div class="row">
									<div class="col-lg-8">
										<div class="checkout-inner">
											<div>
												<h2>Shipping Address <a class="mr-4" href="#">edit <i
															class="fas fa-edit"></i></a> </h2>
												<div class="row">
													<div class="col-md-12">
														<label>Username</label>
														<input class="form-control"
															value="${App.getInstance().auth.authentificatedUser.username}"
															name="username" type="text" readonly>
													</div>
													<div class="col-md-12">
														<label>E-mail</label>
														<input class="form-control"
															value="${App.getInstance().auth.authentificatedUser.email}"
															name="email" type="text" readonly>
													</div>
													<div class="col-md-12">
														<label>Mobile No</label>
														<input class="form-control"
															value="${App.getInstance().auth.authentificatedUser.phone}"
															name="phone" type="text" readonly>
													</div>
													<div class="col-md-12">
														<label>ship_address</label>
														<textarea class="form-control"
															value="${App.getInstance().auth.authentificatedUser.address}"
															name="ship_address" readonly type="text">
															</textarea>
													</div>
													<div class="col-md-12">
														<label>Country</label>
														<input class="form-control"
															value="${App.getInstance().auth.authentificatedUser.country}"
															name="country" role_id readonly type="text">
													</div>
													<div class="col-md-12">
														<label>City</label>
														<input class="form-control"
															value="${App.getInstance().auth.authentificatedUser.city}"
															name="city" readonly type="text">
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="checkout-inner">
											<div class="checkout-summary">
												<h1>Cart Total</h1>
												<p class="sub-total">Sub Total<span id="subTotal"></span></p>
												<p class="ship-cost">Shipping Cost<span id="shippingCost"></span></p>
												<h2>Grand Total<span id="grandTotal"></span></h2>
											</div>
											<script>

												let products = [
													<c:forEach items="${products}" var="product">
														{ id:${product.id} , price:${product.price} , quantity : shoppingList.find(${product.id}).quantity },
													</c:forEach>
												];

												// priceProdQt
												let subTotalElem = document.querySelector('#subTotal');
												let shippingCostElem = document.querySelector('#shippingCost');
												let grantTotalElem = document.querySelector('#grandTotal');

												function calcPrice() {

													let subTotal = 0;
													let shippingCost = 0;
													let grantTotal = 0;

													products.forEach((v) => {

														let price = parseFloat(v.price) * v.quantity;
														subTotal += price;
													});

													grantTotal = subTotal - shippingCost;

													subTotalElem.innerHTML = subTotal;
													shippingCostElem.innerHTML = shippingCost;
													grantTotalElem.innerHTML = grantTotal;
												}

												calcPrice();

											</script>
											<div class="checkout-payment">
												<div class="payment-methods">
													<h1>Payment Methods</h1>
													<div class="payment-method">
														<div class="custom-control custom-radio">
															<input name="clear_shoppinglist_cookies" id="clear_shoppinglist_cookies" class="d-none" value="false" >
															<script>
																if(window.location.href.indexOf("shoppinglist")!=-1){
																	document.querySelector('#clear_shoppinglist_cookies').value = true;
																}
															</script>
															<input type="radio" class="custom-control-input"
																id="payment-1" name="payment">
															<label class="custom-control-label"
																for="payment-1">Paypal</label>
														</div>
														<div class="payment-content" id="payment-1-show">
															<p>
																Lorem ipsum dolor sit amet, consectetur adipiscing elit.
																Cras
																tincidunt orci ac
																eros volutpat maximus lacinia quis diam.
															</p>
														</div>
													</div>
													<div class="payment-method">
														<div class="custom-control custom-radio">
															<input type="radio" class="custom-control-input"
																id="payment-2" name="payment">
															<label class="custom-control-label"
																for="payment-2">Payoneer</label>
														</div>
														<div class="payment-content" id="payment-2-show">
															<p>
																Lorem ipsum dolor sit amet, consectetur adipiscing elit.
																Cras
																tincidunt orci ac
																eros volutpat maximus lacinia quis diam.
															</p>
														</div>
													</div>
													<div class="payment-method">
														<div class="custom-control custom-radio">
															<input type="radio" class="custom-control-input"
																id="payment-3" name="payment">
															<label class="custom-control-label" for="payment-3">Check
																Payment</label>
														</div>
														<div class="payment-content" id="payment-3-show">
															<p>
																Lorem ipsum dolor sit amet, consectetur adipiscing elit.
																Cras
																tincidunt orci ac
																eros volutpat maximus lacinia quis diam.
															</p>
														</div>
													</div>
													<div class="payment-method">
														<div class="custom-control custom-radio">
															<input type="radio" class="custom-control-input"
																id="payment-4" name="payment">
															<label class="custom-control-label" for="payment-4">Direct
																Bank
																Transfer</label>
														</div>
														<div class="payment-content" id="payment-4-show">
															<p>
																Lorem ipsum dolor sit amet, consectetur adipiscing elit.
																Cras
																tincidunt orci ac
																eros volutpat maximus lacinia quis diam.
															</p>
														</div>
													</div>
													<div class="payment-method">
														<div class="custom-control custom-radio">
															<input type="radio" class="custom-control-input"
																id="payment-5" name="payment">
															<label class="custom-control-label" for="payment-5">Cash on
																Delivery</label>
														</div>
														<div class="payment-content" id="payment-5-show">
															<p>
																Lorem ipsum dolor sit amet, consectetur adipiscing elit.
																Cras
																tincidunt orci ac
																eros volutpat maximus lacinia quis diam.
															</p>
														</div>
													</div>
												</div>
												<div class="cart-btn">
													<input type="submit" id="btn-checkout"  class="btn btn-reverse w-100 my-2 p-2" value="Checkout" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>

						</div>
					</div>
					<!-- Checkout End -->


					<%@ include file="components/footer.jsp" %>