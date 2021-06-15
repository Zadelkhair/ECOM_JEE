package com.ecomjeegi.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomjeegi.MyConfig;

/**
 * Servlet Filter implementation class LogoutFilter
 */
@WebFilter("/LogoutFilter")
public class LogoutFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogoutFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		Object auth_id = session.getAttribute("auth_id");
		//if session containe's auth 
		
		System.out.println("Logout auth: "+ ( auth_id != null && ((int)auth_id != -1) ));
		
		if(auth_id != null &&((int)auth_id != -1)) {
			
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(MyConfig.getHost()+"");
			
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
