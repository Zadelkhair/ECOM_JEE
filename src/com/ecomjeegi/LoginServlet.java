package com.ecomjeegi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomjeegi.app.App;
import com.ecomjeegi.controllers.AuthentificationController;
import com.ecomjeegi.models.User;

/*
import com.ecomjeegi.app.App;
import com.ecomjeegi.controllers.AuthentificationController;
import com.ecomjeegi.models.User;
*/

public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get request data
		Object username = req.getParameter("username");
		Object password = req.getParameter("password");
		Object keepMeSignedIn = req.getParameter("keep_me_signed_in");
		
		
		if(username==null)
			return;
		
		if(password==null)
			return;
		
		// TODO check if request data are valid
		
		//check username and password
		AuthentificationController authController = new AuthentificationController();
		Boolean state = authController.login((String)username,(String)password);
		
		
		if(state) {
			
			// TODO if request has keepMeSignedIn in true state
			// i will save the logged user in the browser cookies
			if(keepMeSignedIn != null)
			if(((String)keepMeSignedIn) == "on" ) {
				// TODO CODE
				System.out.println((String)keepMeSignedIn);
			}
			
			User user = new User();
			user.setUsername((String)username);
			user.readByUtilisateur();
			
			System.out.print("LoginServ: " + user.getId());
			
			HttpSession session = req.getSession();
			session.setAttribute("auth_id", user.getId());
			
			resp.sendRedirect(MyConfig.getHost()+"");
			return;
		}
		else {
			resp.sendRedirect(req.getRequestURI()+"?error");
			return;
		}
		
	}
	
}
