package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecomjeegi.app.Database;


public class Review extends Model {

    //table name
    public String tableName() {
        return "reviews";
    }

    //felds
	private String description;
	private int rate;
	private int product_id;
	private int user_id;


    //Constructors
    public Review(){}

    public Review(int id,String description,int rate,int product_id,int user_id) {
        this.id = id;
		this.description = description;
		this.rate = rate;
		this.product_id = product_id;
		this.user_id = user_id;

    }

    //Geter and seters
	public String getDescription() {
	   return description;
	}
	public void setDescription(String description) {
	   this.description = description;
	}


	public int getRate() {
	   return rate;
	}
	public void setRate(int rate) {
	   this.rate = rate;
	}


	public int getProduct_id() {
	   return product_id;
	}
	public void setProduct_id(int product_id) {
	   this.product_id = product_id;
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
		this.description = (String) row.get("description");
		this.rate = (int) row.get("rate");
		this.product_id = (int) row.get("product_id");
		this.user_id = (int) row.get("user_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("description",description);
		row.put("rate",rate);
		row.put("product_id",product_id);
		row.put("user_id",user_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Review();
    }

	public List<Map<String, Object>> getAllProductReviews(Object id) {
		 
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		this.all = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" WHERE product_id = ? ",params);

        return this.all;
        
	}
	
	public List<Review> getAllProductReviewsAsModel(Object id) {
		
		List<Review> models = new ArrayList();
		
		List<Map<String, Object>> rows = getAllProductReviews(id);
		
		if(rows!=null)
		for(Map<String, Object> row : rows) {
			Review model = (Review)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
		
	}

    //Custom methods
	
	public String getUsername() {
		
		User user = new User();
		user.setId(this.getUser_id());
		user.read();
		
		return user.getUsername();
	}

	public boolean createAndDeletePrev() {
		
		Map<String,Object> row = toRow();

        List<Object> params = new ArrayList<>();
        params.add(this.product_id);
        params.add(this.user_id);

        Database.getInstance().executeUpdate("DELETE FROM "+tableName()+" WHERE product_id=? AND user_id=? ;",params);
		
		return this.create();
	}

}
