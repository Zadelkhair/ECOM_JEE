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
import com.ecomjeegi.models.Supplier;
public class SupplierServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Object id_supplier= request.getParameter("edit");
		
		if(id_supplier != null) {
			Supplier supplier = new Supplier();
			supplier.setId(Integer.parseInt(id_supplier.toString()));
			Boolean state = supplier.read();
			request.setAttribute("supplier", supplier);
		}
		else {
			
			Supplier supplier = new Supplier();
			List<Supplier> suppliers = supplier.getAllAsModels(true);
			
			request.setAttribute("suppliers", suppliers);
		}
		
		request.getRequestDispatcher("supplier.jsp").forward(request, response);
	
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
		Object supplier_name = request.getParameter("supplier_name");
		Object address = request.getParameter("address");
		Object country = request.getParameter("country");
		
		if(supplier_name == null) {
			errors.put("supplier name", "supplier name is required");
		}
		
		if(address == null) {
			errors.put("address", "address is required");
		}
		if(country == null) {
			errors.put("country", "country is required");
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
		
		Supplier supplier = new Supplier();
		supplier.setAddress(address.toString());
		supplier.setSupplier_name(supplier_name.toString());
		supplier.setCountry(country.toString());
		Boolean state = supplier.create();
		
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
		Object supplier_name = request.getParameter("supplier_name");
		Object address = request.getParameter("address");
		Object country = request.getParameter("country");
		
		if(id == null) {
			errors.put("id_categorie", "id categorie is required");
		}
		
		if(supplier_name == null) {
			errors.put("supplier_name", "supplier_name is required");
		}
		
		if(address == null) {
			errors.put("address", "address is required");
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
		
		Supplier supplier = new Supplier();
		supplier.setId(Integer.parseInt(id.toString()));
		Boolean state = supplier.read();
		
		if(state) {
			supplier.setSupplier_name(supplier_name.toString());
			supplier.setAddress(address.toString());			
			supplier.setCountry(country.toString());
			state = supplier.update();
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
			errors.put("id", "id supplier is required");
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
		
		Supplier supplier = new Supplier();
		supplier.setId(Integer.parseInt(id.toString()));
		Boolean state = supplier.read();
		
		if(state) {
			state = supplier.delete();
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
