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
import com.ecomjeegi.models.User;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object auth_id = session.getAttribute("auth_id");
		
		if(session != null) {
			session.removeAttribute("auth_id");
			App.getInstance().auth.setAuthentificatedUser(new User());
		}
		
		response.sendRedirect(MyConfig.getHost());
		
	}

}
