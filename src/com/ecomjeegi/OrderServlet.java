package com.ecomjeegi;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomjeegi.models.Order;
import com.ecomjeegi.models.OrderDetail;
import com.ecomjeegi.models.Role;

public class OrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object id = request.getParameter("edit");
		
		if(id != null) {
			Order order = new Order();
			order.setId(Integer.parseInt(id.toString()));
			Boolean state = order.read();
			
			request.setAttribute("order", order);
			
			OrderDetail orderDetail = new OrderDetail();
			List<OrderDetail> details = orderDetail.getAllByOrderAsModels(order.id);
			
			request.setAttribute("orderDetails", details);
		}
		else {
			
			Order order = new Order();
			List<Order> orders = order.getAllAsModels(true);
			
			request.setAttribute("orders", orders);

		}
		
		request.getRequestDispatcher("/dashboard/order.jsp").forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost");
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		

		Object method = request.getParameter("method");
		Object id = request.getParameter("id");
		
		if(method == null) {
			errors.put("method", "method is required");
		}
		
		if(id == null) {
			errors.put("id", "id is required");
		}
		
		
		for (Map.Entry<String, String> error : errors.entrySet()) {
			System.out.print("error "+error.getKey()+" : " +error.getValue() );
		}
		
		
		if(errors.size()>0) {
			
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
			
			/*
			RequestDispatcher errorRequestDispatcher = request.getRequestDispatcher("myaccount.jsp");
			
			for (Map.Entry<String, String> error : errors.entrySet()) {
				request.setAttribute(error.getKey()+"_error", error.getValue());
			}
			
			errorRequestDispatcher.forward(request, response);
			return;
			*/
		}
		
		
		if(method.toString().equals("save") && Integer.parseInt(id.toString()) == -1) {
			
			try {
				this.create( request,  response);
			} catch (IOException | ServletException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (method.toString().equals("save") && Integer.parseInt(id.toString()) != -1) {
			
			try {
				this.update(request,  response);
			} catch (IOException | ServletException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (method.toString().equals("delete")) {
			
			this.delete(request,  response);
			
		}
		else if (method.toString().equals("confirm")) {
					
					this.confirm(request,  response);
					
		}
		else if (method.toString().equals("cancel")) {
			
			this.cancel(request,  response);
			
		}
		
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {

		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object delivery_charges = request.getParameter("delivery_charges");
		Object transaction_status = request.getParameter("transaction_status");
		Object ship_date = request.getParameter("ship_date");
		Object order_date = request.getParameter("order_date");
		Object user_id = request.getParameter("user_id");
		
		if(delivery_charges == null) {
			errors.put("delivery_charges", "delivery_charges is required");
		}
		if(transaction_status == null) {
			errors.put("transaction_status", "transaction_status is required");
		}
		if(order_date == null) {
			errors.put("order_date", "order_date is required");
		}
		if(order_date == null) {
			errors.put("order_date", "order_date is required");
		}
		if(user_id == null) {
			errors.put("user_id", "user_id is required");
		}
		
		if(errors.size()>0) {
			
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
			
			/*
			RequestDispatcher errorRequestDispatcher = request.getRequestDispatcher("myaccount.jsp");
			
			for (Map.Entry<String, String> error : errors.entrySet()) {
				request.setAttribute(error.getKey()+"_error", error.getValue());
			}
			
			errorRequestDispatcher.forward(request, response);
			return;
			*/
		}
		
		Order order = new Order();
		
		order.setDelivery_charges(Integer.parseInt(delivery_charges.toString()));
		order.setTransaction_status(Integer.parseInt(transaction_status.toString()));
		
		java.util.Date dt_ship_date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ship_date.toString());
		order.setShip_date(new java.sql.Date(dt_ship_date.getTime()));

		java.util.Date dt_order_date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(order_date.toString());
		order.setOrder_date(new java.sql.Date(dt_order_date.getTime()));
		
		order.setUser_id(Integer.parseInt(user_id.toString()));
		
		
		Boolean state = order.create();
		
		if(state) {
			response.sendRedirect(request.getRequestURI());
			return;
		}
		else {
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object id = request.getParameter("id");
		Object delivery_charges = request.getParameter("delivery_charges");
		Object transaction_status = request.getParameter("transaction_status");
		Object ship_date = request.getParameter("ship_date");
		Object order_date = request.getParameter("order_date");
		Object user_id = request.getParameter("user_id");
		
		if(id == null) {
			errors.put("id_categorie", "id categorie is required");
		}
		
		if(delivery_charges == null) {
			errors.put("delivery_charges", "delivery_charges is required");
		}
		if(transaction_status == null) {
			errors.put("transaction_status", "transaction_status is required");
		}
		if(order_date == null) {
			errors.put("order_date", "order_date is required");
		}
		if(order_date == null) {
			errors.put("order_date", "order_date is required");
		}
		if(user_id == null) {
			errors.put("user_id", "user_id is required");
		}
		
		if(errors.size()>0) {
			
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
			
			/*
			RequestDispatcher errorRequestDispatcher = request.getRequestDispatcher("myaccount.jsp");
			
			for (Map.Entry<String, String> error : errors.entrySet()) {
				request.setAttribute(error.getKey()+"_error", error.getValue());
			}
			
			errorRequestDispatcher.forward(request, response);
			return;
			*/
		}
		
		Order order = new Order();
		order.setId(Integer.parseInt(id.toString()));
		
		Boolean state = order.read();
		
		if(state) {
			
			order.setDelivery_charges(Integer.parseInt(delivery_charges.toString()));
			order.setTransaction_status(Integer.parseInt(transaction_status.toString()));
			
			java.util.Date dt_ship_date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ship_date.toString());
			order.setShip_date(new java.sql.Date(dt_ship_date.getTime()));

			java.util.Date dt_order_date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(order_date.toString());
			order.setOrder_date(new java.sql.Date(dt_order_date.getTime()));
			
			order.setUser_id(Integer.parseInt(user_id.toString()));
			
			state = order.update();
		}
		
		if(state) {
			response.sendRedirect(request.getRequestURI());
			return;
		}
		else {
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object id = request.getParameter("id");
		
		if(id == null) {
			errors.put("id", "id categorie is required");
		}
		
		
		if(errors.size()>0) {
			
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
			
			/*
			RequestDispatcher errorRequestDispatcher = request.getRequestDispatcher("myaccount.jsp");
			
			for (Map.Entry<String, String> error : errors.entrySet()) {
				request.setAttribute(error.getKey()+"_error", error.getValue());
			}
			
			errorRequestDispatcher.forward(request, response);
			return;
			*/
		}
		
		Order order = new Order();
		order.setId(Integer.parseInt(id.toString()));
		Boolean state = order.read();
		
		if(state) {
			state = order.delete();
		}
		
		if(state) {
			response.sendRedirect(request.getRequestURI());
			return;
		}
		else {
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
		}
		
	}
	
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object id = request.getParameter("id");
		
		if(id == null) {
			errors.put("id", "id categorie is required");
		}
		
		
		if(errors.size()>0) {
			
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
			
			/*
			RequestDispatcher errorRequestDispatcher = request.getRequestDispatcher("myaccount.jsp");
			
			for (Map.Entry<String, String> error : errors.entrySet()) {
				request.setAttribute(error.getKey()+"_error", error.getValue());
			}
			
			errorRequestDispatcher.forward(request, response);
			return;
			*/
		}
		
		Order order = new Order();
		order.setId(Integer.parseInt(id.toString()));
		Boolean state = order.read();
		
		if(state) {
			order.setTransaction_status(-1);
			state = order.update();
		}
		
		if(state) {
			response.sendRedirect(request.getRequestURI());
			return;
		}
		else {
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
		}

	}

	private void confirm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object id = request.getParameter("id");
		
		if(id == null) {
			errors.put("id", "id categorie is required");
		}
		
		
		if(errors.size()>0) {
			
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
			
			/*
			RequestDispatcher errorRequestDispatcher = request.getRequestDispatcher("myaccount.jsp");
			
			for (Map.Entry<String, String> error : errors.entrySet()) {
				request.setAttribute(error.getKey()+"_error", error.getValue());
			}
			
			errorRequestDispatcher.forward(request, response);
			return;
			*/
		}
		
		Order order = new Order();
		order.setId(Integer.parseInt(id.toString()));
		Boolean state = order.read();
		
		if(state) {
			order.setTransaction_status(1);
			state = order.update();
		}
		
		if(state) {
			response.sendRedirect(request.getRequestURI());
			return;
		}
		else {
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
		}
	
	}

}
