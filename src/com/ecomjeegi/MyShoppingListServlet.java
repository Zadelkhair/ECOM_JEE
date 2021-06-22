package com.ecomjeegi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomjeegi.models.Product;

public class MyShoppingListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object shoppinglist = req.getParameter("shoppinglist");
		
		List<Integer> id_prods = new ArrayList();
	
		if(shoppinglist != null) {
			List<String> id_prods_str = Arrays.asList(shoppinglist.toString().split(","));
			for(String s : id_prods_str) id_prods.add(Integer.valueOf(s));
		}
		
		Product product = new Product();
		List<Product> products = product.getByArrayOfIdAsModels(true,id_prods);
		
		req.setAttribute("products", products);
		
		RequestDispatcher rqDispatcher = req.getRequestDispatcher("myshoppinglist.jsp");
		rqDispatcher.forward(req, resp);
		
	}
	
}
