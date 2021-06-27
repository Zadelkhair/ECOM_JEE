package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecomjeegi.app.Database;


public class Order extends Model {

    //table name
    public String tableName() {
        return "orders";
    }

    //felds
	private float delivery_charges;
	private int transaction_status;
	private java.sql.Date ship_date;
	private java.sql.Date order_date;
	private int user_id;
	private float price;


    //Constructors
    public Order(){}

    public Order(int id,float delivery_charges,int transaction_status,java.sql.Date ship_date,java.sql.Date order_date,int user_id,float price) {
        this.id = id;
		this.delivery_charges = delivery_charges;
		this.transaction_status = transaction_status;
		this.ship_date = ship_date;
		this.order_date = order_date;
		this.user_id = user_id;
		this.price = price;

    }

    //Geter and seters
	public float getDelivery_charges() {
	   return delivery_charges;
	}
	public void setDelivery_charges(float delivery_charges) {
	   this.delivery_charges = delivery_charges;
	}


	public int getTransaction_status() {
	   return transaction_status;
	}
	public void setTransaction_status(int transaction_status) {
	   this.transaction_status = transaction_status;
	}


	public java.sql.Date getShip_date() {
	   return ship_date;
	}
	public void setShip_date(java.sql.Date ship_date) {
	   this.ship_date = ship_date;
	}


	public java.sql.Date getOrder_date() {
	   return order_date;
	}
	public void setOrder_date(java.sql.Date order_date) {
	   this.order_date = order_date;
	}


	public int getUser_id() {
	   return user_id;
	}
	public void setUser_id(int user_id) {
	   this.user_id = user_id;
	}


	public float getPrice() {
	   return price;
	}
	public void setPrice(float price) {
	   this.price = price;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.delivery_charges = (float) row.get("delivery_charges");
		this.transaction_status = (int) row.get("transaction_status");
		this.ship_date = (java.sql.Date) row.get("ship_date");
		this.order_date = (java.sql.Date) row.get("order_date");
		this.user_id = (int) row.get("user_id");
		this.price = (float) row.get("price");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("delivery_charges",delivery_charges);
		row.put("transaction_status",transaction_status);
		row.put("ship_date",ship_date);
		row.put("order_date",order_date);
		row.put("user_id",user_id);
		row.put("price",price);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Order();
    }
    
    public User getUser() {
    	User user = new User();
    	user.setId(user_id);
    	user.read();
    	
    	return user; 
    }

	public List<Map<String, Object>> getByUserId(boolean b) {
		
		if(this.all == null || b){

            this.all = new ArrayList<>();

            this.all = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" WHERE user_id = "+this.user_id);

        }

        return this.all;
		
	}
    
	public List<Order> getByUserIdAsModels(boolean b) {
		
		List<Order> models = new ArrayList();
		
		List<Map<String, Object>> rows = getByUserId(b);
		
		for(Map<String, Object> row : rows) {
			Order model = (Order)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
	}

}
