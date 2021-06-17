package com.ecomjeegi.controllers;

import com.ecomjeegi.models.Categorie;
import com.ecomjeegi.models.Product;

public class CategorieController {
	
	
	public Categorie update(int id,String name,String description) {
		
		//get object data by id to check if it's exist
		Categorie categorie = new Categorie();
		categorie.setId(id);
		Boolean isExist = categorie.read();
		
		//return null if not exist
		if(!isExist) {
			return null;
		}
		
		//update categorie by prams who not's null ( You can ignore updating a categorie column by set its value to null )
		if(name != null)
			categorie.setName(name);
		if(description != null)
			categorie.setDescription(description);
		
		Boolean isUpdated = categorie.update();
		
		//check the state of updating 
		if(!isUpdated) {
			return null;
		}
		
		// read the updated data ( Not important! )
		categorie.read();
		
		// return the updated object
		return categorie;
	}
	
}
