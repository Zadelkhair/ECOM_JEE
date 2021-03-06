package com.ecomjeegi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomjeegi.controllers.AuthentificationController;
import com.ecomjeegi.models.User;

public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//errors map will carre all request errors
		Map<String, Object> errors = new HashMap<String, Object>();
		
		//get request data
		Object username = req.getParameter("username");
		Object password = req.getParameter("password");
		Object email = req.getParameter("email");
		Object city = req.getParameter("city");
		Object phone = req.getParameter("phone");
		Object address = req.getParameter("address");
		
		if(username==null){
			errors.put("username", "username is required");
		}
		else {
			if(username.toString().length()<=3) {
				errors.put("username", "length should be more than 3 chars");
			}
		}
		
		if(password==null){
			errors.put("password", "password is required");
			
		}
		else {
			if(password.toString().length()<=3) {
				errors.put("password", "length should be more than 3 chars");
			}
		}
		
		if(email==null){
			errors.put("email", "email is required");
		}
		else {
			if(email.toString().length()<=3) {
				errors.put("email", "length should be more than 3 chars");
			}
		}

		if(city==null){
			errors.put("city", "city is required");
		}
		else {
			if(email.toString().length()<=3) {
				errors.put("city", "length should be more than 3 chars");
			}
		}
		
		if(phone==null){
			errors.put("phone", "phone is required");
		}
		else {
			if(phone.toString().length()<=9) {
				errors.put("phone", "length should be more than 9 chars");
			}
		}
		
		if(address==null){
			errors.put("address", "address is required");
		}
		else {
			if(address.toString().length()<=9) {
				errors.put("address", "length should be more than 9 chars");
			}
		}

		// validation
		if(errors.size()>0){
			String error = "";
			for (Map.Entry<String, Object> er : errors.entrySet()) {
			    error+=er.getKey()+" : "+er.getValue().toString()+";";
			}
			resp.sendRedirect(req.getRequestURI()+"?error="+error);
			return;
		}
		
		
		User user = new User();
		user.setUsername((String)username);
		user.setPassword((String)password);
		user.setAddress((String)address);
		user.setEmail((String)email);
		user.setPhone((String)phone);
		user.setRole_id(7);
		
		if(user.create()) {
			resp.sendRedirect(MyConfig.getHost()+"login");
		}
		else {
			errors.put("onCreate","can't create this user");
			req.setAttribute("errors", errors);
		}
		
	}
}
