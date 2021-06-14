package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Categorie extends Model {

    //table name
    public String tableName() {
        return "categories";
    }

    //felds
	private String name;
	private String description;


    //Constructors
    public Categorie(){}

    public Categorie(int id,String name,String description) {
        this.id = id;
		this.name = name;
		this.description = description;

    }

    //Geter and seters
	public String getName() {
	   return name;
	}
	public void setName(String name) {
	   this.name = name;
	}


	public String getDescription() {
	   return description;
	}
	public void setDescription(String description) {
	   this.description = description;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.name = (String) row.get("name");
		this.description = (String) row.get("description");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("name",name);
		row.put("description",description);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Categorie();
    }

    //Custom methods

}
