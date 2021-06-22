<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <%@ page import="com.ecomjeegi.MyConfig" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <%@ include file="components/header.jsp" %>


                <div class="container-fluid mb-4">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 padding">
                        <div class="card">
                            <div class="card-header p-4">
                                <div class="d-flex">
                                    <h3 class="flex-grow-1" style="color: #FF6F61;" >EStore</h3>
                                    <button type="button" class="btn" onclick="PrintElem('receipt')">
                                        <i class="fa fa-print" aria-hidden="true"></i>
                                        print
                                    </button>
                                </div>
                            </div>
                            <div class="card-body">
                                <div id="receipt" style="display: flex;flex-direction:column;">
                                    <div class="rcpt-header">
                                        <div style="display: flex;" >
                                            <div class="rcpt-from" style="width: 300px;">
                                                <p style="font-size: 18px;" >
                                                    <span style="font-weight: bold;" >From:</span>
                                                    <br>
                                                    EStore
                                                    29, Singla Street
                                                    Sikeston,New Delhi 110034
                                                    Email: contact@bbbootstrap.com
                                                    Phone: +91 9897 989 989
                                                </p>
                                            </div>
                                            <div style="flex: 1;" ></div>
                                            <div class="rcpt-from" style="width: 300px;">
                                                <p style="font-size: 18px;" >
                                                    <span style="font-weight: bold;" >to:</span>
                                                    <br>
                                                    Username : ${order.user.username} 
                                                    <br>
                                                    Address : 
                                                    ${order.user.city},
                                                    ${order.user.country},
                                                    ${order.user.address}
                                                    <br>
                                                    Date : ${ order.order_date }
                                                    <br>
                                                    Email: ${order.user.email}
                                                    <br>
                                                    Phone: ${order.user.phone}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="rcpt-body">
                                        <table style="width: 100%;" >
                                            <thead>
                                                <tr style="border:solid;border-width: 0 0 1px 0;border-color: rgb(236 236 236);height:25px;" >
                                                    <th>#</th>
                                                    <th>Item</th>
                                                    <th>Price</th>
                                                    <th>Qty</th>
                                                    <th>Total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach items="${details}" var="orderDtl" varStatus="loop" >
	                                            	<tr style="border:solid;border-width: 0 0 1px 0;border-color: rgb(236 236 236);height:25px;" >
	                                                    <td>${ loop.index+1 }</td>
	                                                    <td>${ orderDtl.product.product_name }</td>
	                                                    <td>${ orderDtl.product.price }</td>
	                                                    <td>${ orderDtl.order_quantity }</td>
	                                                    <td>${ orderDtl.order_price }</td>
	                                                </tr>
                                            	</c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <hr>
                                    <div class="rcpt-footer">
                                        <div style="display: flex;flex-direction: row-reverse;" >
                                            <table style="width: 300px;" >
                                                <tr style="border:solid;border-width: 0 0 1px 0;border-color: rgb(236 236 236);height:25px;" >
                                                    <th>Subtotal</th>
                                                    <td>${ order.price - order.delivery_charges }</td>
                                                </tr>
                                                <tr style="border:solid;border-width: 0 0 1px 0;border-color: rgb(236 236 236);height:25px;" >
                                                    <th>Discount</th>
                                                    <td>0</td>
                                                </tr>
                                                <tr style="border:solid;border-width: 0 0 1px 0;border-color: rgb(236 236 236);height:25px;" >
                                                    <th>Shipping</th>
                                                    <td>${ order.delivery_charges }</td>
                                                </tr>
                                                <tr  >
                                                    <th>Total</th>
                                                    <td>${ order.price }</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    if(window.location.href.indexOf("clearshoppinglist")!=-1){
                        shoppingList.removeAll();
                    }
                    
                    function PrintElem(elem) {
                        var mywindow = window.open('', 'PRINT', 'height=400,width=600');

                        mywindow.document.write('<html><head><title>' + document.title + '</title>');
                        mywindow.document.write('</head><body >');
                        mywindow.document.write(document.getElementById(elem).innerHTML);
                        mywindow.document.write('</body></html>');

                        mywindow.document.close(); // necessary for IE >= 10
                        mywindow.focus(); // necessary for IE >= 10*/

                        mywindow.print();
                        mywindow.close();

                        return true;
                    }

                    PrintElem('receipt');
                </script>


                <%@ include file="components/footer.jsp" %>