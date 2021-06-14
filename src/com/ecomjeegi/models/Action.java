package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Action extends Model {

    //table name
    public String tableName() {
        return "actions";
    }

    //felds
	private String name;


    //Constructors
    public Action(){}

    public Action(int id,String name) {
        this.id = id;
		this.name = name;

    }

    //Geter and seters
	public String getName() {
	   return name;
	}
	public void setName(String name) {
	   this.name = name;
	}




    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.name = (String) row.get("name");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("name",name);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Action();
    }

    //Custom methods

}
