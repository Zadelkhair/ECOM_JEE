package com.ecomjeegi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomjeegi.app.App;
import com.ecomjeegi.models.Order;
import com.ecomjeegi.models.OrderDetail;
import com.ecomjeegi.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MakeOrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object shoppinglist = req.getParameter("shoppinglist");
		Object product_id = req.getParameter("product_id");
		
		List<Integer> id_prods = new ArrayList();
	
		if(product_id!=null) {
			id_prods.add(Integer.parseInt(product_id.toString()));
		}
		else if(shoppinglist != null) {
			List<String> id_prods_str = Arrays.asList(shoppinglist.toString().split(","));
			for(String s : id_prods_str) id_prods.add(Integer.valueOf(s));
		}
		
		Product product = new Product();
		List<Product> products = new ArrayList<Product>();
		
		if(id_prods.size()>0) {
			products = product.getByArrayOfIdAsModels(true,id_prods);
		}
		
		if(products.size()<=0) {
			resp.sendRedirect(MyConfig.getHost());
			return;
		}
		
		req.setAttribute("products", products);
		
		req.setAttribute("", products);
		
		RequestDispatcher rqDispatcher = req.getRequestDispatcher("makeorder.jsp");
		rqDispatcher.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object products = request.getParameter("products");
		Object clear_shoppinglist_cookies = request.getParameter("clear_shoppinglist_cookies");
		
		List<OrderDetail> orderDetails = new ArrayList();
		
		Type listType = new TypeToken<List<JSONProduct>>() {}.getType();
		List<JSONProduct> jsonProds = new Gson().fromJson(products.toString(), listType);
		for(JSONProduct jpro : jsonProds) {
			Product prod = new Product();
			prod.setId(jpro.getProduct_id());
			prod.read();
			float price = prod.getPrice() * jpro.getQuantity();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder_price(price);
			orderDetail.setOrder_quantity(jpro.getQuantity());
			orderDetail.setProduct_id(jpro.getProduct_id());
			
			orderDetails.add(orderDetail);
		}
		
		Order order = new Order();
		order.setDelivery_charges(0);
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());
		order.setOrder_date(sqlDate);
		
		float sumPrice = 0;
		for(OrderDetail orderDetail: orderDetails) {
			sumPrice += orderDetail.getOrder_price();
		}
		order.setPrice(sumPrice);
		
		//order.setShip_date(sqlDate);
		order.setTransaction_status(0);
		order.setUser_id(App.getInstance().getAuth().getAuthentificatedUser().getId());
		
		boolean state = order.create();
		if(state) {
			for(OrderDetail orderDetail: orderDetails) {
				orderDetail.setOrder_id(order.getId());
				orderDetail.create();
			}
			if(clear_shoppinglist_cookies!=null) {
				if(Boolean.parseBoolean(clear_shoppinglist_cookies.toString())) {
					response.sendRedirect(MyConfig.getHost()+"receipt?id_order="+order.getId()+"&clearshoppinglist");
				}
				else {
					response.sendRedirect(MyConfig.getHost()+"receipt?id_order="+order.getId());
				}
			}
			else
				response.sendRedirect(MyConfig.getHost()+"receipt?id_order="+order.getId());
			return;
		}

	}
	
	class JSONProduct{
		
		private int product_id;
		private int quantity;
		
		
		
		public int getProduct_id() {
			return product_id;
		}
		public void setProduct_id(int product_id) {
			this.product_id = product_id;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
		@Override
		public String toString() {
			return "JSONProduct [product=" + product_id + ", quantity=" + quantity + "]";
		}
		
		
	}

}
