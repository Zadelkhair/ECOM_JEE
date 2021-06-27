package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecomjeegi.app.Database;


public class Advertisement extends Model {

    //table name
    public String tableName() {
        return "advertisements";
    }

    //felds
	private String image;
	private String description;
	private int position;

    //Constructors
    public Advertisement(){}

    public Advertisement(int id,String image,String description) {
        this.id = id;
		this.image = image;
		this.description = description;
		this.position = position;
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
	
	public int getPosition() {
	   return position;
	}
	public void setPosition(int position) {
	   this.position = position;
	}

    //
    @Override
    public boolean readRow(Map<String, Object> row) {

        this.id = (int) row.get("id");
		this.image = (String) row.get("image");
		this.description = (String) row.get("description");
		this.position = (int) row.get("position");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("image",image);
		row.put("description",description);
		row.put("position",position);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Advertisement();
    }
    
    public boolean clearAnyModalHasePosition(int position) {
    	
    	String prepareVals = "";
        List<Object> params = new ArrayList<>();
        params.add(position);
        

        int i = Database.getInstance().executeUpdate("UPDATE "+tableName()+" SET position=-1 WHERE position=?",params);

        return i>0;	
    }
    
    public List<Map<String, Object>> getAllOrderedByPosition(boolean forceload){

        if(this.all == null || forceload){

            this.all = new ArrayList<>();

            this.all = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" WHERE position!=-1 order by position ");
            List<Map<String, Object>> nonPositionalAds  = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" WHERE position=-1 order by id desc ");
            
            if(nonPositionalAds != null)
            for(Map<String, Object> ads : nonPositionalAds) {
            	this.all.add(ads);
            }
            
        }

        return this.all;
    }

	public List<Advertisement> getAllOrderedByPositionAsModels(boolean b) {
		
		List<Advertisement> models = new ArrayList();
		
		List<Map<String, Object>> rows = getAllOrderedByPosition(b);
		
		for(Map<String, Object> row : rows) {
			Advertisement model = (Advertisement)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
	}

}
