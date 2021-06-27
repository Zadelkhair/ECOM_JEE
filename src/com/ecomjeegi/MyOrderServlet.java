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
import com.ecomjeegi.models.Supplier;
import com.ecomjeegi.app.App;

public class MyOrderServlet extends HttpServlet {
	
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
			order.setUser_id(App.getInstance().getAuth().getAuthentificatedUser().getId());
			List<Order> userOrders = order.getByUserIdAsModels(true);
			request.setAttribute("orders", userOrders);

		}
		
		request.getRequestDispatcher("/myaccount/myorders.jsp").forward(request, response);

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
	else if (method.toString().equals("delete")) {
			
			this.delete(request,  response);
			
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
	


}
