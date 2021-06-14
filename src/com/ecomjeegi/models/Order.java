package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Order extends Model {

    //table name
    public String tableName() {
        return "orders";
    }

    //felds
	private int delivery_charges;
	private int transaction_status;
	private java.sql.Date ship_date;
	private java.sql.Date order_date;
	private int user_id;


    //Constructors
    public Order(){}

    public Order(int id,int delivery_charges,int transaction_status,java.sql.Date ship_date,java.sql.Date order_date,int user_id,int order_id) {
        this.id = id;
		this.delivery_charges = delivery_charges;
		this.transaction_status = transaction_status;
		this.ship_date = ship_date;
		this.order_date = order_date;
		this.user_id = user_id;

    }

    //Geter and seters
	public int getDelivery_charges() {
	   return delivery_charges;
	}
	public void setDelivery_charges(int delivery_charges) {
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




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.delivery_charges = (int) row.get("delivery_charges");
		this.transaction_status = (int) row.get("transaction_status");
		this.ship_date = (java.sql.Date) row.get("ship_date");
		this.order_date = (java.sql.Date) row.get("order_date");
		this.user_id = (int) row.get("user_id");


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


        return row;
    }

    @Override
    public Model getInstance() {
        return new Order();
    }

    //Custom methods

}
