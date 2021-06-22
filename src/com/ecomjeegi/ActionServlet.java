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

import com.ecomjeegi.models.Action;
import com.ecomjeegi.models.Categorie;
import com.ecomjeegi.models.Product;
import com.ecomjeegi.models.Role;
import com.ecomjeegi.models.User;

public class ActionServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object id = request.getParameter("edit");
		
		if(id != null) {
			Action action = new Action();
			action.setId(Integer.parseInt(id.toString()));
			Boolean state = action.read();
			
			request.setAttribute("action", action);
		}
		else {
			
			Action action = new Action();
			List<Action> actions = action.getAllAsModels(true);
			
			request.setAttribute("actions", actions);
		}
		
		request.getRequestDispatcher("action.jsp").forward(request, response);
	
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
			
			this.create( request,  response);
			
		}
		else if (method.toString().equals("save") && Integer.parseInt(id.toString()) != -1) {
			
			this.update(request,  response);
			
		}
		else if (method.toString().equals("delete")) {
			
			this.delete(request,  response);
			
		}
		
	}

	
	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object name = request.getParameter("name");
		
		if(name == null) {
			errors.put("name", "name is required");
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
		
		Action action = new Action();
		
		
		action.setName(name.toString());
		
		Boolean state = action.create();
		
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
		Object name = request.getParameter("name");
		
		if(id == null) {
			errors.put("id_categorie", "id categorie is required");
		}
		
		if(name == null) {
			errors.put("name", "name is required");
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
		
		Action action = new Action();
		action.setId(Integer.parseInt(id.toString()));
		Boolean state = action.read();
		
		if(state) {
			action.setName(name.toString());
			state = action.update();
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
		
		Action action = new Action();
		action.setId(Integer.parseInt(id.toString()));
		Boolean state = action.read();
		
		if(state) {
			state = action.delete();
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
