package com.ecomjeegi.app;

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

    public String getUsername() {

//        if(!isLoggedIn())
//            return null;

        return authentificated_user.getUsername();
    }
    
    public User getAuthentificatedUser() {
    	return authentificated_user;
    }

}
