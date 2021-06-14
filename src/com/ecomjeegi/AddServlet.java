package com.ecomjeegi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomjeegi.models.User;

public class AddServlet extends HttpServlet {
	
	public void service(HttpServletRequest rq,HttpServletResponse rs) throws IOException, ServletException {
		
		User user = new User();
		user.setId(2);
		user.read();
		
		rq.setAttribute("user", user);
		RequestDispatcher rqDispatcher = rq.getRequestDispatcher("index.jsp");
		rqDispatcher.forward(rq, rs);
		
	}

}
