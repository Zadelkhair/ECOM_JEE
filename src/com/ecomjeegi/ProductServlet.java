package com.ecomjeegi;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ecomjeegi.models.Categorie;
import com.ecomjeegi.models.Product;
import com.ecomjeegi.models.Supplier;

@MultipartConfig
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object id = request.getParameter("edit");
		
		if(id != null) {
			Product product = new Product();
			product.setId(Integer.parseInt(id.toString()));
			boolean state = product.read();
			
			Categorie categorie = new Categorie();
			List<Categorie> categories = categorie.getAllAsModels(true);
			
			request.setAttribute("categories", categories);
			
			Supplier supplier = new Supplier();
			List<Supplier> suppliers = supplier.getAllAsModels(true);
			
			request.setAttribute("suppliers", suppliers);
			
			request.setAttribute("product", product);
		}
		else {
			
			Product product = new Product();
			List<Product> products = product.getAllAsModels(true);
			
			request.setAttribute("products", products);
		}
		
		request.getRequestDispatcher("product.jsp").forward(request, response);
	
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
			System.out.println("error "+error.getKey()+" : " +error.getValue() );
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
		
		Object product_name = request.getParameter("product_name");
		
		Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
		
		Object price = request.getParameter("price");
		Object size = request.getParameter("size");
		Object color = request.getParameter("color");
		Object garmentType = request.getParameter("garmentType");
		Object rating = request.getParameter("rating");
		Object supplier_id  = request.getParameter("supplier_id");
		Object category_id   = request.getParameter("category_id");
		
		
		if(product_name == null) {
			errors.put("product_name", "product_name is required");
		}
		
		/*if(filePart == null) { //image
			errors.put("image", "image is required");
		}*/
		
		if(price == null) {
			errors.put("price", "price is required");
		}
		
		if(size == null) {
			errors.put("size", "size is required");
		}
		
		if(color == null) {
			errors.put("color", "color is required");
		}
		
		if(garmentType == null) {
			errors.put("garmentType", "garmentType is required");
		}
		
		if(rating == null) {
			errors.put("rating", "rating is required");
		}
		
		if(supplier_id == null) {
			errors.put("supplier_id", "supplier_id is required");
		}
		
		if(category_id == null) {
			errors.put("category_id", "category_id is required");
		}
		
		
		for (Map.Entry<String, String> error : errors.entrySet()) {
			System.out.println("error "+error.getKey()+" : " +error.getValue() );
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
		
		Product product = new Product();
		
		product.setProduct_name(product_name.toString());


		String imagePath = "img"+File.separator+"prod.jpg";
		
		if(filePart != null) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    
			if(fileName != null && fileName != "") {
				InputStream fileContent = filePart.getInputStream();
			    imagePath = "uploads"+File.separator+"images"+File.separator+product_name.toString()+"_"+generateString()+"_"+fileName;
			    imagePath = imagePath.replace(' ', '_');
			    String localStorageImagePath = getServletContext().getRealPath(File.separator+imagePath);
		        File file = new File(localStorageImagePath);
		        copyInputStreamToFile(fileContent, file);
			}
			
		}
		
        product.setImage(imagePath);
		
		product.setPrice(Float.parseFloat(price.toString()));
		product.setSize(size.toString());
		product.setColor(color.toString());
		product.setGarmentType(garmentType.toString());
		product.setRating(Integer.parseInt(rating.toString()));
		product.setSupplier_id(Integer.parseInt(supplier_id.toString()));
		product.setCategory_id(Integer.parseInt(category_id.toString()));
		
		Boolean state = product.create();
		
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
		Object product_name = request.getParameter("product_name");
		
		Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
		
		Object price = request.getParameter("price");
		Object size = request.getParameter("size");
		Object color = request.getParameter("color");
		Object garmentType = request.getParameter("garmentType");
		Object rating = request.getParameter("rating");
		Object supplier_id  = request.getParameter("supplier_id");
		Object category_id   = request.getParameter("category_id");
		
		if(id == null) {
			errors.put("id_categorie", "id categorie is required");
		}
		
		if(product_name == null) {
			errors.put("product_name", "product_name is required");
		}
		
		if(price == null) {
			errors.put("price", "price is required");
		}
		
		if(size == null) {
			errors.put("size", "size is required");
		}
		
		if(color == null) {
			errors.put("color", "color is required");
		}
		
		if(garmentType == null) {
			errors.put("garmentType", "garmentType is required");
		}
		
		if(rating == null) {
			errors.put("rating", "rating is required");
		}
		
		if(supplier_id == null) {
			errors.put("supplier_id", "supplier_id is required");
		}
		
		if(category_id == null) {
			errors.put("category_id", "category_id is required");
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
		
		Product product = new Product();
		product.setId(Integer.parseInt(id.toString()));
		Boolean state = product.read();
		
		if(state) {
			product.setProduct_name(product_name.toString());
			
			String imagePath = "img"+File.separator+"prod.jpg";
			
			if(filePart != null) {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			    
				if(fileName != null && fileName != "") {
					InputStream fileContent = filePart.getInputStream();
				    imagePath = "uploads"+File.separator+"images"+File.separator+product_name.toString()+"_"+generateString()+"_"+fileName;
				    imagePath = imagePath.replace(' ', '_');
				    String localStorageImagePath = getServletContext().getRealPath(File.separator+imagePath);
			        File file = new File(localStorageImagePath);
			        copyInputStreamToFile(fileContent, file);
			        
			        product.setImage(imagePath);
				}
				
				
				
			}
			
			product.setPrice(Float.parseFloat(price.toString()));
			product.setSize(size.toString());
			product.setColor(color.toString());
			product.setGarmentType(garmentType.toString());
			product.setRating(Integer.parseInt(rating.toString()));
			product.setSupplier_id(Integer.parseInt(supplier_id.toString()));
			product.setCategory_id(Integer.parseInt(category_id.toString()));
			state = product.update();
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
		
		Product product = new Product();
		product.setId(Integer.parseInt(id.toString()));
		Boolean state = product.read();
		
		if(state) {
			state = product.delete();
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
	
	public String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
	
	public void copyInputStreamToFile(InputStream inputStream,File file )
            throws IOException {

		FileOutputStream outputStream = new FileOutputStream(file, false);
        int read;
        byte[] bytes = new byte[8192];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

    }


}
