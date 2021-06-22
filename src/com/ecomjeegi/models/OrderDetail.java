package com.ecomjeegi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecomjeegi.app.Database;


public class OrderDetail extends Model {

    //table name
    public String tableName() {
        return "order_details";
    }

    //felds
	private int order_quantity;
	private float order_price;
	private int product_id;
	private int order_id;


    //Constructors
    public OrderDetail(){}

    public OrderDetail(int id,int order_quantity,float order_price,int product_id,int order_id) {
        this.id = id;
		this.order_quantity = order_quantity;
		this.order_price = order_price;
		this.product_id = product_id;
		this.order_id = order_id;

    }

    //Geter and seters
	public int getOrder_quantity() {
	   return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
	   this.order_quantity = order_quantity;
	}


	public double getOrder_price() {
	   return order_price;
	}
	public void setOrder_price(float order_price) {
	   this.order_price = order_price;
	}


	public int getProduct_id() {
	   return product_id;
	}
	public void setProduct_id(int product_id) {
	   this.product_id = product_id;
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
		this.order_quantity = (int) row.get("order_quantity");
		this.order_price = (float) row.get("order_price");
		this.product_id = (int) row.get("product_id");
		this.order_id = (int) row.get("order_id");


        return true;
    }

    @Override
    public Map<String,Object> toRow() {

        Map<String,Object> row = new HashMap<>();
		row.put("order_quantity",order_quantity);
		row.put("order_price",order_price);
		row.put("product_id",product_id);
		row.put("order_id",order_id);


        return row;
    }

    @Override
    public Model getInstance() {
        return new OrderDetail();
    }
    
    public List<Map<String, Object>> getAllByOrder(int order_id) {
    	 

        this.all = new ArrayList<>();

        List<Object> params = new ArrayList<Object>();
        params.add(order_id);
		this.all = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" WHERE order_id = ?",params);

         return this.all;
    }

	public List<OrderDetail> getAllByOrderAsModels(int id) {
		List<OrderDetail> models = new ArrayList();
		
		List<Map<String, Object>> rows = getAllByOrder(id);
		
		
		for(Map<String, Object> row : rows) {
			OrderDetail model = (OrderDetail)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
	}
	
	
	public Product getProduct() {
		
		Product product = new Product();
		product.setId(product_id);
		product.read();
		
		return product;
	}

    //Custom methods

}
