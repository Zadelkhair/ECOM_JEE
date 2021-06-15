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

	public List<Model> getAllAsModels(boolean b) {
		
		List<Model> models = new ArrayList();
		
		List<Map<String, Object>> rows = getAll(b);
		
		for(Map<String, Object> row : rows) {
			Model model = getInstance();
			model.readRow(row);
			models.add(model);
		}
		
		return models;
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

}
