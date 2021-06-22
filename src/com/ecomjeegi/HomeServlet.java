package com.ecomjeegi;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomjeegi.models.Advertisement;
import com.ecomjeegi.models.Categorie;
import com.ecomjeegi.models.Product;
import com.ecomjeegi.models.User;

/*
import com.ecomjeegi.models.User;
*/

public class HomeServlet extends HttpServlet {

	public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException, ServletException {
		
		Advertisement advertisement = new Advertisement();

		List<Advertisement> advertisements = advertisement.getAllAsModels(true);
		rq.setAttribute("advertisements", advertisements);
		
		Product product = new Product();
		
		List<Product> featuredProducts = product.getFeaturedProducts();
		rq.setAttribute("featuredProducts", featuredProducts);
		
		List<Product> recentProduct = product.getRecentProduct();
		rq.setAttribute("recentProduct", recentProduct);
		
		User user = new User();
		
		List<User> customers = user.getCustomers();
		rq.setAttribute("customers", recentProduct);
		
		Categorie categorie = new Categorie();
		
		List<Categorie> categories = categorie.getAllAsModels(true);
		rq.setAttribute("categories", categories);
		
		RequestDispatcher rqDispatcher = rq.getRequestDispatcher("home.jsp");
		rqDispatcher.forward(rq, rs);
		
	}
	
}
