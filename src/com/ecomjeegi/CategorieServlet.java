package com.ecomjeegi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomjeegi.controllers.CategorieController;
import com.ecomjeegi.models.Categorie;

import jdk.jfr.Description;

public class CategorieServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object id_categorie = request.getParameter("edit");
		
		if(id_categorie != null) {
			Categorie categorie = new Categorie();
			categorie.setId(Integer.parseInt(id_categorie.toString()));
			Boolean state = categorie.read();
			
			request.setAttribute("categorie", categorie);
		}
		else {
			
			Categorie categorie = new Categorie();
			List<Categorie> categories = categorie.getAllAsModels(true);
			
			request.setAttribute("categories", categories);
		}
		
		request.getRequestDispatcher("categorie.jsp").forward(request, response);
	
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
		Object description = request.getParameter("description");
		Object fa_icon = request.getParameter("fa_icon");
		
		if(name == null) {
			errors.put("name", "name is required");
		}
		
		if(description == null) {
			errors.put("description", "description is required");
		}
		
		if(fa_icon == null) {
			//errors.put("fa_icon", "fa_icon is required");
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
		
		Categorie categorie = new Categorie();
		categorie.setDescription(description.toString());
		categorie.setName(name.toString());
		categorie.setFa_icon(fa_icon.toString());
		Boolean state = categorie.create();
		
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
		Object description = request.getParameter("description");
		Object fa_icon = request.getParameter("fa_icon");
		
		if(id == null) {
			errors.put("id_categorie", "id categorie is required");
		}
		
		if(name == null) {
			errors.put("name", "name is required");
		}
		
		if(description == null) {
			errors.put("description", "description is required");
		}
		
		if(description == null) {
			//errors.put("fa_icon", "fa_icon is required");
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
		
		Categorie categorie = new Categorie();
		categorie.setId(Integer.parseInt(id.toString()));
		Boolean state = categorie.read();
		
		if(state) {
			categorie.setName(name.toString());
			categorie.setDescription(description.toString());
			categorie.setFa_icon(fa_icon.toString());
			state = categorie.update();
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
		
		Categorie categorie = new Categorie();
		categorie.setId(Integer.parseInt(id.toString()));
		Boolean state = categorie.read();
		
		if(state) {
			state = categorie.delete();
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
