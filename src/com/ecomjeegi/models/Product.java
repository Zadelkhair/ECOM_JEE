package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Product extends Model {

    //table name
    public String tableName() {
        return "products";
    }

    //felds
	private String product_name;
	private double price;
	private String size;
	private String color;
	private String garmentType;
	private int Rating;
	private int supplier_id;
	private int category_id;


    //Constructors
    public Product(){}

    public Product(int id,String product_name,double price,String size,String color,String garmentType,int Rating,int supplier_id,int category_id) {
        this.id = id;
		this.product_name = product_name;
		this.price = price;
		this.size = size;
		this.color = color;
		this.garmentType = garmentType;
		this.Rating = Rating;
		this.supplier_id = supplier_id;
		this.category_id = category_id;

    }

    //Geter and seters
	public String getProduct_name() {
	   return product_name;
	}
	public void setProduct_name(String product_name) {
	   this.product_name = product_name;
	}


	public double getPrice() {
	   return price;
	}
	public void setPrice(double price) {
	   this.price = price;
	}


	public String getSize() {
	   return size;
	}
	public void setSize(String size) {
	   this.size = size;
	}


	public String getColor() {
	   return color;
	}
	public void setColor(String color) {
	   this.color = color;
	}


	public String getGarmentType() {
	   return garmentType;
	}
	public void setGarmentType(String garmentType) {
	   this.garmentType = garmentType;
	}


	public int getRating() {
	   return Rating;
	}
	public void setRating(int Rating) {
	   this.Rating = Rating;
	}


	public int getSupplier_id() {
	   return supplier_id;
	}
	public void setSupplier_id(int supplier_id) {
	   this.supplier_id = supplier_id;
	}


	public int getCategory_id() {
	   return category_id;
	}
	public void setCategory_id(int category_id) {
	   this.category_id = category_id;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.product_name = (String) row.get("product_name");
		this.price = (double) row.get("price");
		this.size = (String) row.get("size");
		this.color = (String) row.get("color");
		this.garmentType = (String) row.get("garmentType");
		this.Rating = (int) row.get("Rating");
		this.supplier_id = (int) row.get("supplier_id");
		this.category_id = (int) row.get("category_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("product_name",product_name);
		row.put("price",price);
		row.put("size",size);
		row.put("color",color);
		row.put("garmentType",garmentType);
		row.put("Rating",Rating);
		row.put("supplier_id",supplier_id);
		row.put("category_id",category_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Product();
    }

    //Custom methods

}
