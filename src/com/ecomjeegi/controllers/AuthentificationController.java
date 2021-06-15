package com.ecomjeegi.controllers;

import com.ecomjeegi.app.App;
import com.ecomjeegi.models.User;

public class AuthentificationController {
	
	private User user;

    public User getUser() {
        return user;
    }

    public boolean registring(String username, String password){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user.create();

    }

    public boolean login(String username, String password){

        this.user = new User();
        this.user.setUsername(username);
        this.user.setPassword(password);

        if(this.user.countByPasswordAndUsername()<=0)
            return false;
        
        App.getInstance().auth.setAuthentificatedUser(user);

        this.user.readByUtilisateur();

        return true;
    }
    
    
}
