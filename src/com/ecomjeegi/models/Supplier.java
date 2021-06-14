package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Supplier extends Model {

    //table name
    public String tableName() {
        return "suppliers";
    }

    //felds
	private String supplier_name;
	private String address;
	private String country;


    //Constructors
    public Supplier(){}

    public Supplier(int id,String supplier_name,String address,String country) {
        this.id = id;
		this.supplier_name = supplier_name;
		this.address = address;
		this.country = country;

    }

    //Geter and seters
	public String getSupplier_name() {
	   return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
	   this.supplier_name = supplier_name;
	}


	public String getAddress() {
	   return address;
	}
	public void setAddress(String address) {
	   this.address = address;
	}


	public String getCountry() {
	   return country;
	}
	public void setCountry(String country) {
	   this.country = country;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.supplier_name = (String) row.get("supplier_name");
		this.address = (String) row.get("address");
		this.country = (String) row.get("country");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("supplier_name",supplier_name);
		row.put("address",address);
		row.put("country",country);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Supplier();
    }

    //Custom methods

}
