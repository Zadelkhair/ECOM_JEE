package com.ecomjeegi;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
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

import com.ecomjeegi.app.Database;
import com.ecomjeegi.models.Advertisement;
import com.ecomjeegi.models.Categorie;
import com.ecomjeegi.models.Model;
import com.ecomjeegi.models.Product;

@MultipartConfig
public class AdvertisementServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object id = request.getParameter("edit");
		
		if(id != null) {
			Advertisement advertisement = new Advertisement();
			advertisement.setId(Integer.parseInt(id.toString()));
			boolean state = advertisement.read();
			
			request.setAttribute("advertisement", advertisement);
		}
		else {
			
			Advertisement advertisement = new Advertisement();
			List<Advertisement> advertisements = advertisement.getAllOrderedByPositionAsModels(true);
			
			request.setAttribute("advertisements", advertisements);
		}
		
		request.getRequestDispatcher("advertisement.jsp").forward(request, response);
	
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
		
		
		Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
		
		Object description = request.getParameter("description");
		Object position = request.getParameter("position");
		
		
		
		/*if(filePart == null) { //image
			errors.put("image", "image is required");
		}*/
		
		if(description == null) {
			errors.put("description", "description is required");
		}
		
		if(position == null) {
			//errors.put("position", "position is required");
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
		
		Advertisement advertisement = new Advertisement();
		
		String imagePath = "img"+File.separator+"prod.jpg";
		
		if(filePart != null) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    
			if(fileName != null && fileName != "") {
				InputStream fileContent = filePart.getInputStream();
			    imagePath = "uploads"+File.separator+"images"+File.separator+"ads"+"_"+generateString()+"_"+fileName;
			    imagePath = imagePath.replace(' ', '_');
			    String localStorageImagePath = getServletContext().getRealPath(File.separator+imagePath);
			    System.out.println("imgpath : "+localStorageImagePath);
		        File file = new File(localStorageImagePath);
		        copyInputStreamToFile(fileContent, file);
			}
			
		}
		
        advertisement.setImage(imagePath);
		

		advertisement.setDescription(description.toString());
		
		if(position!=null) {
			int pos = Integer.parseInt(position.toString());
			advertisement.clearAnyModalHasePosition(pos);
			advertisement.setPosition(pos);
		}
		
		Boolean state = advertisement.create();
		
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
		
		Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
		
		Object description = request.getParameter("description");
		Object position = request.getParameter("position");
		
		if(id == null) {
			errors.put("id_categorie", "id categorie is required");
		}
		
		if(description == null) {
			errors.put("description", "description is required");
		}
		
		if(position == null) {
			//errors.put("position", "position is required");
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
		
		Advertisement advertisement = new Advertisement();
		advertisement.setId(Integer.parseInt(id.toString()));
		Boolean state = advertisement.read();
		
		if(state) {
			String imagePath = "img"+File.separator+"prod.jpg";
			
			if(filePart != null) {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			    
				if(fileName != null && fileName != "") {
					InputStream fileContent = filePart.getInputStream();
				    imagePath = "uploads"+File.separator+"images"+File.separator+"ads"+"_"+generateString()+"_"+fileName;
				    imagePath = imagePath.replace(' ', '_');
				    String localStorageImagePath = getServletContext().getRealPath(File.separator+imagePath);
				    System.out.println("imgpath : "+localStorageImagePath);
			        File file = new File(localStorageImagePath);
			        copyInputStreamToFile(fileContent, file);
			        
			        advertisement.setImage(imagePath);
				}
				
				
			}
			
			advertisement.setDescription(description.toString());
			
			if(position!=null) {
				int pos = Integer.parseInt(position.toString());
				advertisement.clearAnyModalHasePosition(pos);
				advertisement.setPosition(pos);
			}
			
			state = advertisement.update();
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
			errors.put("id", "id ads is required");
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
		
		Advertisement advertisement = new Advertisement();
		advertisement.setId(Integer.parseInt(id.toString()));
		Boolean state = advertisement.read();
		
		if(state) {
			state = advertisement.delete();
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
