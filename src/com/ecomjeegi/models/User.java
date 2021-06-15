package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecomjeegi.app.Database;


public class User extends Model {

    //table name
    public String tableName() {
        return "users";
    }

    //felds
	private String username;
	private String address;
	private String city;
	private String country;
	private String phone;
	private String email;
	private String password;
	private String ship_address;
	private int role_id;


    //Constructors
    public User(){}

    public User(int id,String username,String address,String city,String country,String phone,String email,String password,String ship_address,int role_id) {
        this.id = id;
		this.username = username;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.ship_address = ship_address;
		this.role_id = role_id;

    }

    //Geter and seters
	public String getUsername() {
	   return username;
	}
	public void setUsername(String username) {
	   this.username = username;
	}


	public String getAddress() {
	   return address;
	}
	public void setAddress(String address) {
	   this.address = address;
	}


	public String getCity() {
	   return city;
	}
	public void setCity(String city) {
	   this.city = city;
	}


	public String getCountry() {
	   return country;
	}
	public void setCountry(String country) {
	   this.country = country;
	}


	public String getPhone() {
	   return phone;
	}
	public void setPhone(String phone) {
	   this.phone = phone;
	}


	public String getEmail() {
	   return email;
	}
	public void setEmail(String email) {
	   this.email = email;
	}


	public String getPassword() {
	   return password;
	}
	public void setPassword(String password) {
	   this.password = password;
	}


	public String getShip_address() {
	   return ship_address;
	}
	public void setShip_address(String ship_address) {
	   this.ship_address = ship_address;
	}


	public int getRole_id() {
	   return role_id;
	}
	public void setRole_id(int role_id) {
	   this.role_id = role_id;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.username = (String) row.get("username");
		this.address = (String) row.get("address");
		this.city = (String) row.get("city");
		this.country = (String) row.get("country");
		this.phone = (String) row.get("phone");
		this.email = (String) row.get("email");
		this.password = (String) row.get("password");
		this.ship_address = (String) row.get("ship_address");
		this.role_id = (int) row.get("role_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("username",username);
		row.put("address",address);
		row.put("city",city);
		row.put("country",country);
		row.put("phone",phone);
		row.put("email",email);
		row.put("password",password);
		row.put("ship_address",ship_address);
		row.put("role_id",role_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new User();
    }

	
    //Custom methods
    public long countByPasswordAndUsername() {
		List<Object> params = new ArrayList<>();
		params.add(this.getUsername());
		params.add(this.getPassword());
		Object o = Database.getInstance().executeScalar("SELECT count(*) FROM users WHERE username = ? AND password = ?",params );

        if(o==null)
            return 0;

        return (long)o;
	}
    

    public boolean readByUtilisateur() {

		List<Object> params = new ArrayList<>();
    	params.add(this.getUsername());
        List<Map<String, Object>> rows = Database.getInstance().executeQuery("SELECT * FROM users WHERE username  = ? limit 1;",params);

        if(rows != null){
            if(rows.size()==1){
                readRow(rows.get(0));
                return true;
            }
        }
        
        return false;
    }
    
    public Role role() {
    	
    	Role role = new Role();
    	role.setId(this.getRole_id());
    	role.read();
    	
    	return role;
    }

}
