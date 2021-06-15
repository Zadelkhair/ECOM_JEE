package com.ecomjeegi.app;

public class App {
	
	private static App app;
	
	//database object helper, based on JDBC 
    //this class allow us to interract with the database
    public Database db;
    
    //configuration object
    //public static Config config;
    
    //Authentificated user , his name , password , roles ...
    //functions like logout , login ...
    public Auth auth;
    
    public Auth getAuth() {
    	return this.auth;
    }
    
    private App() {
    	this.db = Database.getInstance();
    	this.auth = new Auth();
    }
    
    public static App getInstance() {
    	
    	if(app!=null)
    		return app;
    	
    	app = new App();
    	return app;
    	
    }
}
