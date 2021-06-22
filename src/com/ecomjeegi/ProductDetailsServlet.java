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

import com.ecomjeegi.app.App;
import com.ecomjeegi.models.Categorie;
import com.ecomjeegi.models.Product;
import com.ecomjeegi.models.Review;


public class ProductDetailsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object id = req.getParameter("id");
		
		if(id==null) {
			errors.put("id", "id is required");
		}
		
		
		if(errors.size()>0) {
			
			resp.sendRedirect(MyConfig.getHost()+"?error");
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
		
		Product product = new Product();
		product.setId(Integer.parseInt(id.toString()));
		boolean state = product.read();
		
		if(!state) {
			resp.sendRedirect(MyConfig.getHost()+"?error");
			return;
		}
		
		req.setAttribute("product", product);
		
		Categorie categorie = new Categorie();
		List<Categorie> categories= categorie.getAllAsModels(true);
		req.setAttribute("categories", categories);
		
		
		Review review = new Review();
		List<Review> productReviews = review.getAllProductReviewsAsModel(id);
		req.setAttribute("productReviews", productReviews);
		
		
		
		req.getRequestDispatcher("productDetails.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
				
		Object action = req.getParameter("action");
		
		
		if(action == null) {
			errors.put("action", "action is required");
		}

		if(errors.size()>0) {
			
			resp.sendRedirect(MyConfig.getHost()+"?error");
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
		
		
		
		if(action.toString().equals("review")) {
			doPostReview(req,resp);
			return;
		}
		
	}

	private void doPostReview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		Object product_id = req.getParameter("product_id");
		Object rate = req.getParameter("rate");
		Object description = req.getParameter("description");
		
		if(errors.size()>0) {
			
			resp.sendRedirect(MyConfig.getHost()+"?error");
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
		
		Review review = new Review();
		review.setRate(Integer.parseInt(rate.toString()));
		review.setUser_id(App.getInstance().getAuth().getAuthentificatedUser().id);
		review.setDescription(description.toString());
		review.setProduct_id(Integer.parseInt(product_id.toString()));
		
		if(!review.createAndDeletePrev()) {
			resp.sendRedirect(MyConfig.getHost()+"?error");
			return;
		}
		
		resp.sendRedirect(req.getRequestURI()+"?id="+product_id.toString());
		
	}

}
