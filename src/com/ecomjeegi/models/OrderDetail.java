package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderDetail extends Model {

    //table name
    public String tableName() {
        return "order_details";
    }

    //felds
	private int order_quantity;
	private int order_price;
	private int product_id;


    //Constructors
    public OrderDetail(){}

    public OrderDetail(int id,int order_quantity,int order_price,int product_id) {
        this.id = id;
		this.order_quantity = order_quantity;
		this.order_price = order_price;
		this.product_id = product_id;

    }

    //Geter and seters
	public int getOrder_quantity() {
	   return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
	   this.order_quantity = order_quantity;
	}


	public int getOrder_price() {
	   return order_price;
	}
	public void setOrder_price(int order_price) {
	   this.order_price = order_price;
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
		this.order_quantity = (int) row.get("order_quantity");
		this.order_price = (int) row.get("order_price");
		this.product_id = (int) row.get("product_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("order_quantity",order_quantity);
		row.put("order_price",order_price);
		row.put("product_id",product_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new OrderDetail();
    }

    //Custom methods

}
