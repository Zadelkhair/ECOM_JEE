package com.ecomjeegi.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomjeegi.MyConfig;
import com.ecomjeegi.app.App;
import com.ecomjeegi.models.User;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/authfilter")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
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
		
		System.out.println("Auth filter");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		Object auth_id = session.getAttribute("auth_id");
		//if session containe's auth 
		if(auth_id != null) {
			
			User user = new User();
			user.setId( Integer.parseInt(auth_id.toString()));
			user.read();
			
			Cookie cookies[] = req.getCookies();
			
			for(Cookie c : cookies) {
				
				
				if(c.equals("shoopingList")) {
					JsonParser jsonParser = new JsonParser();
					JsonArray jsonArr = (JsonArray)jsonParser.parse(c.getValue().toString());
					Gson googleJson = new Gson();
		            ArrayList jsonObjList = googleJson.fromJson(jsonArr, ArrayList.class);
		            System.out.println("List size is : "+jsonObjList.size());
		                    System.out.println("List Elements are  : "+jsonObjList.toString());
				}
				
			}
			
			System.out.println(user);
			
			App.getInstance().auth.setAuthentificatedUser(user);
			
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
