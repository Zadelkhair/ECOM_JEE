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

public class ProductListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//errors map will carre all request errors
		Map<String, String> errors = new HashMap<String, String>();
		
		//categorie
		Object category_id = req.getParameter("category_id");
		Object product_name = req.getParameter("product_name");
		Object sortby = req.getParameter("sortby");
		Object priceBetweenA = req.getParameter("pricebetweena");
		Object priceBetweenB = req.getParameter("pricebetweenb");
		Object page = req.getParameter("page");
		Object numberOfPage = req.getParameter("numberofpage");
		
		
		String category_id_str = null;
		String product_name_str = null;
		String sortby_str = null;
		String priceBetweenA_str = null;
		String priceBetweenB_str = null;
		String page_str = null;
		String numberOfPage_str = null;
		
		if(category_id != null) {
			category_id_str = category_id.toString();
		}
		
		if(product_name != null) {
			product_name_str = product_name.toString();
		}
		
		if(sortby != null) {
			sortby_str = sortby.toString();
		}
		
		if(priceBetweenA != null) {
			priceBetweenA_str = priceBetweenA.toString();
		}
		
		if(priceBetweenB != null) {
			priceBetweenB_str = priceBetweenB.toString();
		}
		
		if(page != null) {
			page_str = page.toString();
		}
		else {
			page_str = "1";
		}
		
		if(numberOfPage != null) {
			numberOfPage_str = numberOfPage.toString();
		}
		else {
			numberOfPage_str = "9";
		}
		
		
		Product product = new Product();
		
		List<Product> products = product.getAllFiltredPaginationAsModels(category_id_str , product_name_str,  sortby_str,  priceBetweenA_str,
				 priceBetweenB_str ,  page_str ,  numberOfPage_str);
		

		System.out.println(product_name_str);
		
		req.setAttribute("products", products);
		
		if(page_str != null && numberOfPage_str != null ) {
			long countProds = product.count();
			
			int lastpage = Math.toIntExact(countProds)/Integer.parseInt(numberOfPage_str) + 1;
			
			int marge = 2; 
			
			int startpaginationbtn = Integer.parseInt(page_str) - marge/2;
			marge -= marge/2;
			
			if(startpaginationbtn<=0) {
				int canceledMarge = startpaginationbtn * -1 + 1;
				startpaginationbtn += canceledMarge;
				marge += canceledMarge;
			}
			
			int endpaginationbtn = Integer.parseInt(page_str) + marge;
			marge -= marge;
			
			if(endpaginationbtn>lastpage) {
				int canceledMarge = endpaginationbtn-lastpage;
				endpaginationbtn -= canceledMarge;
				marge += canceledMarge;
			}
			
			if(marge>0 && startpaginationbtn>0 && marge<startpaginationbtn) {
				startpaginationbtn -= marge;
				marge -= marge;
			}
			
			System.out.println("lastpage"+ lastpage);
			System.out.println("startpaginationbtn"+ startpaginationbtn);
			System.out.println("endpaginationbtn"+ endpaginationbtn);
			System.out.println("currpage"+ Integer.parseInt(page_str));
			
			req.setAttribute("lastpage", lastpage);
			req.setAttribute("startpaginationbtn", startpaginationbtn );
			req.setAttribute("endpaginationbtn", endpaginationbtn);
			req.setAttribute("currpage", Integer.parseInt(page_str));
		}
		
		Categorie categorie = new Categorie();
		List<Categorie> categories = categorie.getAllAsModels(true);
		req.setAttribute("categories", categories);
		
		if(category_id_str != null) {
			Categorie categorieProds = new Categorie();
			categorieProds.setId(Integer.parseInt(category_id_str));
			if(categorieProds.read()) {
				req.setAttribute("categorieProds", categorieProds);
			}
		}
		
		req.getRequestDispatcher("productList.jsp").forward(req, resp);
		
	}

}
