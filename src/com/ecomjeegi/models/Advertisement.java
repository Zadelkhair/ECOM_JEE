package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Advertisement extends Model {

    //table name
    public String tableName() {
        return "advertisements";
    }

    //felds
	private String image;
	private String description;

    //Constructors
    public Advertisement(){}

    public Advertisement(int id,String image,String description) {
        this.id = id;
		this.image = image;
		this.description = description;
    }

    //Geter and seters
	public String getImage() {
	   return image;
	}
	public void setImage(String image) {
	   this.image = image;
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
		this.image = (String) row.get("image");
		this.description = (String) row.get("description");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("image",image);
		row.put("description",description);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Advertisement();
    }

    //Custom methods

}
