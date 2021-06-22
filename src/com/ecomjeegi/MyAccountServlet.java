package com.ecomjeegi;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomjeegi.app.App;
import com.ecomjeegi.models.Order;
import com.ecomjeegi.models.Role;
import com.ecomjeegi.models.User;

public class MyAccountServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myaccount/myaccount.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		

		Object action = request.getParameter("action");
		
		if(action == null) {
			errors.put("action", "method is action");
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
		
		
		if(action.toString().equals("updateAccount")) {
			this.updateAccount( request,  response);
			return;
		}
		else if (action.toString().equals("updatePassword")) {
			this.updatePassword(request,  response);
			return;
		}
		
		request.getRequestDispatcher("/myaccount/myaccount.jsp").forward(request, response);
	}

	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		//errors map will carre all request errors
				Map<String, String> errors = new HashMap<String, String>();
				
				
				Object currpassword = request.getParameter("currpassword");
				Object newpassword = request.getParameter("newpassword");
				
				
				if(currpassword == null) {
					errors.put("currpassword", "currpassword is required");
				}
				if(newpassword == null) {
					errors.put("newpassword", "newpassword is required");
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

				User user = new User();
				user.setId(App.getInstance().getAuth().getAuthentificatedUser().getId());
				
				Boolean state = user.read();
				
				if(state && currpassword.toString().equals(App.getInstance().auth.getAuthentificatedUser().getPassword())) {
					user.setPassword(newpassword.toString());
					state = user.update();
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

	private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object username = request.getParameter("username");
		Object phone = request.getParameter("phone");
		Object email = request.getParameter("email");
		Object country = request.getParameter("country");
		Object city = request.getParameter("city");
		Object address = request.getParameter("address");
		Object ship_address = request.getParameter("ship_address");
		
		
		if(username == null) {
			errors.put("username", "username is required");
		}
		if(phone == null) {
			errors.put("phone", "phone is required");
		}
		if(email == null) {
			errors.put("email", "email is required");
		}
		if(country == null) {
			errors.put("country", "country is required");
		}
		if(city == null) {
			errors.put("city", "city is required");
		}
		if(address == null) {
			errors.put("address", "address is required");
		}
		if(ship_address == null) {
			errors.put("ship_address", "ship_address is required");
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

		User user = new User();
		user.setId(App.getInstance().getAuth().getAuthentificatedUser().getId());
		
		Boolean state = user.read();
		
		if(state) {
			
			user.setUsername(username.toString());
			user.setAddress(address.toString());
			user.setShip_address(ship_address.toString());
			user.setCity(city.toString());
			user.setCountry(country.toString());
			user.setAddress(address.toString());
			user.setEmail(email.toString());
			user.setPhone(phone.toString());;
			
			state = user.update();
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
