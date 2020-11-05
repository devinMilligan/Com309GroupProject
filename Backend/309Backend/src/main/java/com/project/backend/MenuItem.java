package com.project.backend;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MenuItems")
public class MenuItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private double price;
	private String description;
	private int store;


    public int getId() {
        return id;
    }    
    public void setId(int id) {
        this.id = id;
    }
	
    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }
	
    public double getPrice() {
    	return this.price;
    }
    public void setPrice(double price) {
    	this.price = price;
    }
	
    public String getDescription() {
    	return this.description;
    }
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public int getStore() {
        return store;
    }    
    public void setStore(int store) {
        this.store = store;
    }
}
