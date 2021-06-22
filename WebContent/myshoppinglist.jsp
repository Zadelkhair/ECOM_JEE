<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<%@ page import="com.ecomjeegi.MyConfig" %>


		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<%@ include file="components/header.jsp" %>


				<!-- Breadcrumb Start -->
				<div class="breadcrumb-wrap">
					<div class="container-fluid">
						<ul class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">Products</a></li>
							<li class="breadcrumb-item active">Cart</li>
						</ul>
					</div>
				</div>
				<!-- Breadcrumb End -->

				<!-- Cart Start -->
				<div class="cart-page">
					<div class="container-fluid">
						<div class="row">
							<div class="col-lg-8">
								<div class="cart-page-inner">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead class="thead-dark">
												<tr>
													<th>Product</th>
													<th>Price</th>
													<th>Quantity</th>
													<th>Total</th>
													<th>Remove</th>
												</tr>
											</thead>
											<tbody class="align-middle">

												<c:forEach items="${products}" var="product">
													<tr id="prodRow${product.id}" >
														<td>
															<div class="img">
																<a href="#"><img
																		src="${MyConfig.getHost()}${product.image}"
																		alt="Image"></a>
																<p>${product.product_name}</p>
															</div>
														</td>
														<td id="priceProd${product.id}">${product.price}</td>
														<td>
															<div >
																<button class="btn-minus"
																	onclick="minusQuant${product.id}()"><i
																		class="fa fa-minus"></i></button>
																<input id="prodQuant${product.id}" type="text" value="1"
																	onchange="changeQuant${product.id}()" >
																<button class="btn-plus"
																	onclick="plusQuant${product.id}()"><i
																		class="fa fa-plus"></i></button>
															</div>
														</td>
														<td class="priceProdQt" id="priceProdQt${product.id}"></td>
														<td><button onclick="removeProd${product.id}()" ><i class="fa fa-trash"></i></button></td>

														<script>

															//setQuant${product.id}();
															loadProdRow${product.id}();

															function loadProdRow${ product.id } (){
																setQuant${product.id}();
																
																let prod = shoppingList.find(${product.id});

																let price = ${product.price};

																let priceQt = price * prod.quantity;

																document.querySelector('#priceProdQt${product.id}').innerHTML = priceQt;
																
																if(typeof calcPrice === "function")
																	calcPrice();
															}

															function setQuant${product.id}(){
																document.querySelector('#prodQuant${product.id}').value = shoppingList.find(${ product.id }).quantity;
															}

															function plusQuant${product.id}(){
																let prod = shoppingList.find(${product.id});
																shoppingList.save({ product_id: ${product.id}, quantity: parseInt(prod.quantity) + 1});
																loadProdRow${ product.id } ();
															}

															function minusQuant${ product.id } (){
																let prod = shoppingList.find(${product.id});
																
																if(prod.quantity<=0)
																	return;

																shoppingList.save({ product_id: ${product.id}, quantity: parseInt(prod.quantity) - 1});
																loadProdRow${product.id}();
															}

															function changeQuant${product.id}(){
																let prod = shoppingList.find(${product.id});
																prod.quantity = parseInt(document.querySelector('#prodQuant${product.id}').value);
																loadProdRow${product.id}();
															}

															function removeProd${product.id}(){
																shoppingList.remove(${product.id});
																document.querySelector('#prodRow${product.id}').remove()
															}

														</script>

													</tr>

												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="cart-page-inner">
									<div class="row">
										<div class="col-md-12">
											<div class="coupon">
												<input type="text" placeholder="Coupon Code">
												<button>Apply Code</button>
											</div>
										</div>
										<div class="col-md-12">
											<div class="cart-summary">
												<div class="cart-content">
													<h1>Cart Summary</h1>
													<p>Sub Total<span id="subTotal" ></span></p>
													<p>Shipping Cost<span id="shippingCost" ></span></p>
													<h2  >Grand Total<span id="grantTotal" ></span></h2>
												</div>
												<script>
													// priceProdQt
													let subTotalElem = document.querySelector('#subTotal');
													let shippingCostElem = document.querySelector('#shippingCost');
													let grantTotalElem = document.querySelector('#grantTotal');

													function calcPrice(){
														let priceProdQtElems = document.querySelectorAll('.priceProdQt');
														
														let subTotal = 0;
														let shippingCost = 0;
														let grantTotal = 0;

														priceProdQtElems.forEach((v)=>{
															let price = parseFloat(v.innerHTML);
															subTotal += price;
														});

														grantTotal = subTotal - shippingCost;
														
														subTotalElem.innerHTML = subTotal;
														shippingCostElem.innerHTML = shippingCost;
														grantTotalElem.innerHTML = grantTotal;
													}

													calcPrice();

												</script>
												<div class="cart-btn">
													<a id="btn-checkout" href="#" class="btn btn-reverse w-100 my-2 p-2" >Checkout</a>
												</div>
												<script>
                                                    //shoppingList.subscribe(document.querySelector('#shoppingListSize'));

                                                    shoppingList.subscribeCallback((list) => {
                                                        document.querySelector('#btn-checkout').href = '${MyConfig.getHost()}makeorder?shoppinglist='+shoppingList.toString();
                                                    });
                                                    
                                                </script>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Cart End -->


				<%@ include file="components/footer.jsp" %>