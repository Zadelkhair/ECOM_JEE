package com.ecomjeegi;

import java.io.IOException;
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

public class ReceiptServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
				
		
		Object id_order = req.getParameter("id_order");
		
		
		if(id_order==null) {
			errors.put("id_order", "id is required");
			return;
		}
		
		
		if(errors.size()>0) {
			
			resp.sendRedirect(req.getRequestURI()+"?error");
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
		order.setId(Integer.parseInt(id_order.toString()));
		order.read();
		
		OrderDetail orderDetail = new OrderDetail();
		List<OrderDetail> details = orderDetail.getAllByOrderAsModels(Integer.parseInt(id_order.toString()));
		
		req.setAttribute("order", order);
		req.setAttribute("details", details);
		
		req.getRequestDispatcher("receipt.jsp").forward(req, resp);
		
	}

}
