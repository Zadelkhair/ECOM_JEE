package com.ecomjeegi.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ecomjeegi.app.Database;

public abstract class Model {

    public int id = -1;
    protected List<Map<String,Object>> all = null;

    public void setId(int id){
        this.id =id;
    }

    public int getId(){
        return this.id;
    }
    
    //
    public abstract boolean readRow(Map<String, Object> row);

    public abstract Map<String,Object> toRow();

    public abstract Model getInstance();

    protected abstract String tableName();

    public boolean create(){

        Map<String,Object> row = toRow();

        String cols = "";
        String prepareVals = "";
        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> cell : row.entrySet()) {
            cols += cell.getKey() + ",";
            prepareVals += "?,";
            params.add(cell.getValue());
        }

        cols = cols.substring(0,cols.length()-1);
        prepareVals = prepareVals.substring(0,prepareVals.length()-1);

        Database.getInstance().executeUpdate("INSERT INTO "+tableName()+"("+cols+") VALUES ("+prepareVals+");",params);
        Object id = lastId();

        System.out.println(id);

        if(id==null)
            return false;

        this.id = (int)id;

        return true;

    }

    public boolean read(){

        List<Map<String, Object>> rows = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" WHERE id="+getId()+";");
        if(rows != null){
            if(rows.size()==1){
                readRow(rows.get(0));
                return true;
            }
        }

        return false;
    }

    public  boolean update(){
        Map<String,Object> row = toRow();

        String prepareVals = "";
        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> cell : row.entrySet()) {
            prepareVals += cell.getKey() + "=?,";
            params.add(cell.getValue());
        }
        prepareVals = prepareVals.substring(0,prepareVals.length()-1);
        params.add(this.id);

        int i = Database.getInstance().executeUpdate("UPDATE "+tableName()+" SET "+prepareVals+" WHERE id=?",params);

        return i>0;
    }

    public  boolean delete(){
        Map<String,Object> row = toRow();

        List<Object> params = new ArrayList<>();
        params.add(this.id);

        int i = Database.getInstance().executeUpdate("DELETE FROM "+tableName()+" WHERE id=?;",params);

        return i>0;
    }

    public List<Map<String, Object>> getAll(boolean forceload){

        if(this.all == null || forceload){

            this.all = new ArrayList<>();

            this.all = Database.getInstance().executeQuery("SELECT * FROM "+tableName());

        }

        return this.all;
    }

	public <T extends Model> List<T> getAllAsModels(boolean b) {
		
		List<T> models = new ArrayList();
		
		List<Map<String, Object>> rows = getAll(b);
		
		for(Map<String, Object> row : rows) {
			T model = (T)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
	}
    
	public List<Map<String, Object>> getAllPagination(boolean forceload,int page,int numberofitems){

        if(this.all == null || forceload){

            this.all = new ArrayList<>();

            List<Object> params = new ArrayList();
            params.add(page);
            params.add(numberofitems);
			this.all = Database.getInstance().executeQuery("SELECT * FROM "+tableName()+" LIMIT ?,?",params);

        }

        return this.all;
    }

	public <T extends Model> Pagination<T> getAllPaginationAsModels(boolean b,int page,int numberofitemsperpage) {
		
		List<T> models = new ArrayList();
		
		List<Map<String, Object>> rows = getAllPagination(b, page, numberofitemsperpage);
		
		for(Map<String, Object> row : rows) {
			T model = (T)getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		T model = (T)getInstance();
		int numberofitems = Math.toIntExact(model.count());
		
		Pagination<T> modelPagination = new Pagination<T>(models, page, numberofitems, numberofitemsperpage, 10);
		
		
		return modelPagination;
	}
	
    public boolean isExist(){

        List<Object> params = new ArrayList<>();
        params.add(this.id);

        Object o = Database.getInstance().executeScalar("SELECT count(*) FROM "+tableName()+" WHERE id=?",params);

        if(o==null)
            return false;

        return (long)o>0;

    }

    public long count(){

        Object o = Database.getInstance().executeScalar("SELECT count(*) FROM "+tableName());

        if(o==null)
            return 0;

        return (long)o;
    }

    public int lastId(){

        Object o = Database.getInstance().executeScalar("select id from "+tableName()+" ORDER BY id DESC LIMIT 1;");

        if(o==null)
            return 0;

        return (int)o;

    }
    
    class Pagination<T extends Model>{
    	
    	private List<T> items;
    	private int page;
    	private int lastpage;
    	private int numberofitemsperpage;
    	private int numberofitems;
    	private int range;
    	private int firstpageinrange;
    	private int lastpageinrange;
    	
    	public Pagination(List<T> items,int page,int numberofitems,int numberofitemsperpage,int range) {
			items = items;
			page = page;
			lastpage = -1;
			numberofitemsperpage = numberofitemsperpage;
			numberofitems = numberofitems;
			range = range;
			firstpageinrange = -1;
			lastpageinrange = -1;
			calcRange();
		}
    	
    	public Pagination(List<T> items,int page,int numberofitems,int numberofitemsperpage) {
			items = items;
			page = page;
			lastpage = -1;
			numberofitemsperpage = -1;
			numberofitems = -1;
			range = -1;
			firstpageinrange = -1;
			lastpageinrange = -1;
			calcRange();
		}

		public List<T> getItems() {
			return items;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}
		
		
		public int getNumberofitems() {
			return numberofitems;
		}

		public int getLastpage() {
			return lastpage;
		}

		public int getNumberofitemsperpage() {
			return numberofitemsperpage;
		}

		public int getRange() {
			return range;
		}

		public int getFirstpageinrange() {
			return firstpageinrange;
		}

		public int getLastpageinrange() {
			return lastpageinrange;
		}

		private void calcRange() {
			
			if(this.page != -1 && this.numberofitems != -1 && numberofitemsperpage != -1 && range != -1 ) {
				
				lastpage = numberofitems/numberofitemsperpage + 1;
				
				int marge = range;
				
				firstpageinrange = page - marge/2;
				marge -= marge/2;
				
				if(firstpageinrange<=0) {
					int canceledMarge = firstpageinrange * -1 + 1;
					firstpageinrange += canceledMarge;
					marge += canceledMarge;
				}
				
				lastpageinrange = page + marge;
				marge -= marge;
				
				if(lastpageinrange>lastpage) {
					int canceledMarge = lastpageinrange-lastpage;
					lastpageinrange -= canceledMarge;
					marge += canceledMarge;
				}
				
				if(marge>0 && firstpageinrange>0 && marge<firstpageinrange) {
					firstpageinrange -= marge;
					marge -= marge;
				}
				
			}
		}
    }

}
