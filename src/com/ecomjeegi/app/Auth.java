package com.ecomjeegi.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomjeegi.MyConfig;
import com.ecomjeegi.models.User;

public class Auth {

    User authentificated_user;

    Auth(){
        this.authentificated_user = new User();
    }

    public void setAuthentificatedUser(User auth) {
        System.out.println(authentificated_user.getUsername());
        this.authentificated_user = auth;
    }

    public boolean isLoggedIn(){
        System.out.println(authentificated_user.getId());
        return authentificated_user.getId() != -1;
    }
    
    public boolean isLoggedIn(HttpSession session ){
    	
    	if(session != null) {
        	Object auth_id = session.getAttribute("auth_id");
    		//if session containe's auth 
    		if(auth_id != null) {
    			User user = new User();
    			user.setId((int)auth_id);
    			user.read();
    			setAuthentificatedUser(user);
    		}
    	}
		
        return isLoggedIn();
    }
    
    public String getUsername() {

//        if(!isLoggedIn())
//            return null;

        return authentificated_user.getUsername();
    }
    
    public User getAuthentificatedUser() {
    	return authentificated_user;
    }

}
