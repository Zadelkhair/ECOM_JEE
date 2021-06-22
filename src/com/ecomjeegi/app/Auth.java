package com.ecomjeegi.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomjeegi.MyConfig;
import com.ecomjeegi.models.Product;
import com.ecomjeegi.models.User;

public class Auth {

    User authentificated_user;
    
    List<Product> shoppingList;
    List<Product> wishList;

    Auth(){
        this.authentificated_user = new User();
        shoppingList = new ArrayList();
        wishList = new ArrayList();
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

    public void addProductToShoppingList(Product product) {
    	shoppingList.add(product);
    }
    
    public void addProductToWishList(Product product) {
    	shoppingList.add(product);
    }
    
    public List<Product> getShoppingList(Product product) {
    	return this.shoppingList;
    }
    
    public List<Product> getWishList(Product product) {
    	return this.wishList;
    }
    
    
}
