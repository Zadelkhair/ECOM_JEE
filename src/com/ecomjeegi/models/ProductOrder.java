package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductOrder extends Model {

    //table name
    public String tableName() {
        return "product_orders";
    }

    //felds
	private int quantity;
	private int status;
	private java.sql.Date delivery_date;
	private int order_id;
	private int product_id;


    //Constructors
    public ProductOrder(){}

    public ProductOrder(int id,int quantity,int status,java.sql.Date delivery_date,int order_id,int product_id) {
        this.id = id;
		this.quantity = quantity;
		this.status = status;
		this.delivery_date = delivery_date;
		this.order_id = order_id;
		this.product_id = product_id;

    }

    //Geter and seters
	public int getQuantity() {
	   return quantity;
	}
	public void setQuantity(int quantity) {
	   this.quantity = quantity;
	}


	public int getStatus() {
	   return status;
	}
	public void setStatus(int status) {
	   this.status = status;
	}


	public java.sql.Date getDelivery_date() {
	   return delivery_date;
	}
	public void setDelivery_date(java.sql.Date delivery_date) {
	   this.delivery_date = delivery_date;
	}


	public int getOrder_id() {
	   return order_id;
	}
	public void setOrder_id(int order_id) {
	   this.order_id = order_id;
	}


	public int getProduct_id() {
	   return product_id;
	}
	public void setProduct_id(int product_id) {
	   this.product_id = product_id;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.quantity = (int) row.get("quantity");
		this.status = (int) row.get("status");
		this.delivery_date = (java.sql.Date) row.get("delivery_date");
		this.order_id = (int) row.get("order_id");
		this.product_id = (int) row.get("product_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("quantity",quantity);
		row.put("status",status);
		row.put("delivery_date",delivery_date);
		row.put("order_id",order_id);
		row.put("product_id",product_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new ProductOrder();
    }

    //Custom methods

}
