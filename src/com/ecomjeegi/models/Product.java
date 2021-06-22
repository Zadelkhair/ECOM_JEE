package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecomjeegi.app.Database;


public class Product extends Model {

    //table name
    public String tableName() {
        return "products";
    }

    //felds
	private String product_name;
	private String image;
	private float price;
	private String size;
	private String color;
	private String garmentType;
	private int rating;
	private int supplier_id;
	private int category_id;


    //Constructors
    public Product(){}

    public Product(int id,String product_name,String image,float price,String size,String color,String garmentType,int rating,int supplier_id,int category_id) {
        this.id = id;
		this.product_name = product_name;
		this.image = image;
		this.price = price;
		this.size = size;
		this.color = color;
		this.garmentType = garmentType;
		this.rating = rating;
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
	
	public String getImage() {
	   return image;
	}
	public void setImage(String image) {
	   this.image = image;
	}


	public float getPrice() {
	   return price;
	}
	public void setPrice(float price) {
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
	   return rating;
	}
	public void setRating(int rating) {
	   this.rating = rating;
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
		this.image = (String) row.get("image");
		this.price = (float) row.get("price");
		this.size = (String) row.get("size");
		this.color = (String) row.get("color");
		this.garmentType = (String) row.get("garmentType");
		this.rating = (int) row.get("rating");
		this.supplier_id = (int) row.get("supplier_id");
		this.category_id = (int) row.get("category_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("product_name",product_name);
		row.put("image",image);
		row.put("price",price);
		row.put("size",size);
		row.put("color",color);
		row.put("garmentType",garmentType);
		row.put("rating",rating);
		row.put("supplier_id",supplier_id);
		row.put("category_id",category_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new Product();
    }

	public List<Product> getFeaturedProducts() {
		return getAllAsModels(true);
	}

	public List<Product> getRecentProduct() {
		return getAllAsModels(true);
	}

	public List<Product> getByArrayOfIdAsModels(boolean b,List<Integer> ids) {
		
		List<Product> models = new ArrayList();
		
		List<Map<String, Object>> rows = getByArrayOfId(b, ids);
		
		for(Map<String, Object> row : rows) {
			Product model = (Product)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
        
	}

	private List<Map<String, Object>> getByArrayOfId(boolean b,List<Integer> ids) {
		
		if(this.all == null || b){

            this.all = new ArrayList<>();
            
            String ids_str = "-1";
        	for (Integer id : ids) {
				ids_str += "," + id;
			}
            

            this.all = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" WHERE id IN ("+ids_str+")");

        }

        return this.all;
        
	}

	public List<Map<String, Object>> getAllFiltredPagination(String category_id , String product_name, String sortby, String priceBetweenA,
			String priceBetweenB , String page , String numberOfPage ) {
		

		String query = 
				" SELECT * FROM "+tableName() + 
				" WHERE 1 = 1 ";
		
		if(category_id  != null) {
			query += 
					" AND category_id = "+category_id+" ";
		}
		
		if(product_name != null) {
			query += 
					" AND product_name LIKE '%"+product_name+"%' ";
		}

		if(priceBetweenA != null && priceBetweenB != null) {
			query += 
					" AND price BETWEEN "+priceBetweenA+" AND "+priceBetweenB+" ";
		}
		
		if(priceBetweenA != null && priceBetweenB != null) {
			query += 
					" AND price BETWEEN "+priceBetweenA+" AND "+priceBetweenB+" ";
		}
		
		if(page != null && numberOfPage != null) {
			query += 
					" LIMIT "+(Integer.parseInt(page)-1)*(Integer.parseInt(numberOfPage))+","+numberOfPage+" ";
		}
		
		this.all = Database.getInstance().executeQuery(query);

        return this.all;
	}

	public List<Product> getAllFiltredPaginationAsModels(String category_id , String product_name, String sortby, String priceBetweenA,
			String priceBetweenB , String page , String numberOfPage) {
		
		List<Product> models = new ArrayList();
		
		List<Map<String, Object>> rows = getAllFiltredPagination(category_id , product_name,  sortby,  priceBetweenA,
				 priceBetweenB ,  page ,  numberOfPage);
		
		
		for(Map<String, Object> row : rows) {
			Product model = (Product)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
		
	}
	
	public int getProductRate() {
		Review review = new Review();
		List<Review> productReviews = review.getAllProductReviewsAsModel(getId());
		
		int avgReviews = 0;
		
		for (Review rvw : productReviews) {
			avgReviews += rvw.getRate();
		}
		
		if(productReviews.size()<=0)
			return 0;
		
		return avgReviews/productReviews.size();
	}

}
