package com.ecomjeegi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
import com.ecomjeegi.models.User;
*/

public class HomeServlet extends HttpServlet {

	public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException, ServletException {
		
		RequestDispatcher rqDispatcher = rq.getRequestDispatcher("home.jsp");
		rqDispatcher.forward(rq, rs);
		
	}
	
}
