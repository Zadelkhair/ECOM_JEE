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

import com.ecomjeegi.models.Categorie;
import com.ecomjeegi.models.Product;
import com.ecomjeegi.models.Role;
import com.ecomjeegi.models.User;

public class UserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object id = request.getParameter("edit");
		
		if(id != null) {
			User user = new User();
			user.setId(Integer.parseInt(id.toString()));
			Boolean state = user.read();
			
			if(state)
			if(user.getRole().equals("admin")) {
				response.sendRedirect(request.getRequestURI());
				return;
			}
			
			Role role = new Role();
			List<Role> roles = role.getAllAsModels(true);
			
			request.setAttribute("user", user);
			request.setAttribute("roles", roles);
		}
		else {
			
			User user = new User();
			List<User> users = user.getAllAsModels(true);
			
			request.setAttribute("users", users);
		}
		
		request.getRequestDispatcher("user.jsp").forward(request, response);
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost");
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		

		Object action = request.getParameter("action");
		Object id = request.getParameter("id");
		
		if(action == null) {
			errors.put("action", "action is required");
		}
		
		if(id == null) {
			errors.put("id", "id is required");
		}
		
		if(Integer.parseInt(id.toString()) == 1) {
			errors.put("id", "You can't edit an admin user");
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
		
		
		if(action.toString().equals("save") && Integer.parseInt(id.toString()) == -1) {
			
			this.create( request,  response);
			
		}
		else if (action.toString().equals("save") && Integer.parseInt(id.toString()) != -1) {
			
			this.update(request,  response);
			
		}
		else if (action.toString().equals("delete")) {
			
			this.delete(request,  response);
			
		}
		
	}

	
	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object username = request.getParameter("username");
		Object address = request.getParameter("address");
		Object city = request.getParameter("city");
		Object country = request.getParameter("country");
		Object phone = request.getParameter("phone");
		Object email = request.getParameter("email");
		Object password  = request.getParameter("password");
		Object ship_address   = request.getParameter("ship_address");
		Object role_id = request.getParameter("role_id");
		
		if(username == null) {
			errors.put("username", "username is required");
		}
		
		if(address == null) {
			errors.put("address", "address is required");
		}
		
		if(city == null) {
			errors.put("city", "city is required");
		}
		
		if(country == null) {
			errors.put("country", "country is required");
		}
		
		if(phone == null) {
			errors.put("phone", "phone is required");
		}
		
		if(email == null) {
			errors.put("email", "email is required");
		}
		
		if(password == null) {
			//errors.put("password", "password is required");
		}
		
		if(ship_address == null) {
			errors.put("ship_address", "ship_address is required");
		}
		
		if(role_id == null) {
			errors.put("role_id", "role_id is required");
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
		
		
		user.setUsername(username.toString());
		user.setAddress(address.toString());
		user.setCity(city.toString());
		user.setCountry(country.toString());
		user.setPhone(phone.toString());
		user.setEmail(email.toString());
		if(password!=null)
			user.setPassword(password.toString());
		user.setShip_address(ship_address.toString());
		user.setRole_id(Integer.parseInt(role_id.toString()));
		
		Boolean state = user.create();
		
		if(state) {
			response.sendRedirect(request.getRequestURI());
			return;
		}
		else {
			response.sendRedirect(request.getRequestURI()+"?error");
			return;
		}
	}

	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object id = request.getParameter("id");
		Object username = request.getParameter("username");
		Object address = request.getParameter("address");
		Object city = request.getParameter("city");
		Object country = request.getParameter("country");
		Object phone = request.getParameter("phone");
		Object email = request.getParameter("email");
		Object password  = request.getParameter("password");
		Object ship_address   = request.getParameter("ship_address");
		Object role_id = request.getParameter("role_id");
		
		if(id == null) {
			errors.put("id_categorie", "id categorie is required");
		}
		
		if(username == null) {
			errors.put("username", "username is required");
		}
		
		if(address == null) {
			errors.put("address", "address is required");
		}
		
		if(city == null) {
			errors.put("city", "city is required");
		}
		
		if(country == null) {
			errors.put("country", "country is required");
		}
		
		if(phone == null) {
			errors.put("phone", "phone is required");
		}
		
		if(email == null) {
			errors.put("email", "email is required");
		}
		
		if(password == null) {
			errors.put("password", "password is required");
		}
		
		if(ship_address == null) {
			errors.put("ship_address", "ship_address is required");
		}
		
		if(role_id == null) {
			errors.put("role_id", "role_id is required");
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
		
		User user = new User();
		user.setId(Integer.parseInt(id.toString()));
		Boolean state = user.read();
		
		if(state) {
			user.setUsername(username.toString());
			user.setAddress(address.toString());
			user.setCity(city.toString());
			user.setCountry(country.toString());
			user.setPhone(phone.toString());
			user.setEmail(email.toString());
			user.setPassword(password.toString());
			user.setShip_address(ship_address.toString());
			user.setRole_id(Integer.parseInt(role_id.toString()));
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
		
		User user = new User();
		user.setId(Integer.parseInt(id.toString()));
		Boolean state = user.read();
		
		if(state) {
			state = user.delete();
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
