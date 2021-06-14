package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Payment extends Model {

    //table name
    public String tableName() {
        return "payments";
    }

    //felds
	private int shipping_fees;
	private int total_price;
	private int order_id;


    //Constructors
    public Payment(){}

    public Payment(int id,int shipping_fees,int total_price,int order_id) {
        this.id = id;
		this.shipping_fees = shipping_fees;
		this.total_price = total_price;
		this.order_id = order_id;

    }

    //Geter and seters
	public int getShipping_fees() {
	   return shipping_fees;
	}
	public void setShipping_fees(int shipping_fees) {
	   this.shipping_fees = shipping_fees;
	}


	public int getTotal_price() {
	   return total_price;
	}
	public void setTotal_price(int total_price) {
	   this.total_price = total_price;
	}


	public int getOrder_id() {
	   return order_id;
	}
	public void setOrder_id(int order_id) {
	   this.order_id = order_id;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.shipping_fees = (int) row.get("shipping_fees");
		this.total_price = (int) row.get("total_price");
		this.order_id = (int) row.get("order_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("shipping_fees",shipping_fees);
		row.put("total_price",total_price);
		row.put("order_id",order_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Payment();
    }

    //Custom methods

}
