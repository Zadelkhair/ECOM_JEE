package com.ecomjeegi;

public abstract class MyConfig {
	
	public static String getHost() {
		return "http://localhost:8081/ecom_jee_gi/";
	}
	
	public static String getDbName() {
		return "e_comerce_db"; 
	}
	public static String getDbUrl() {
			return "jdbc:mysql://127.0.0.1";
		}
	public static String getDbUserName() {
		return "root";
	}
	public static String getDbPassword() {
		return "";
	}
}
