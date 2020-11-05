package com.project.backend;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int orderingUser;
	private int deliveringUser;
	private int store;
	private String status;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "OrderItems", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "orderID") })
    private Set<MenuItem> order = new HashSet<>();
	
    public int getId() {
        return id;
    }    
    public void setId(int id) {
        this.id = id;
    }

    public int getOrderingUser() {
        return orderingUser;
    }    
    public void setOrderingUser(int orderingUser) {
        this.orderingUser = orderingUser;
    }

    public int getDeliveringUser() {
        return deliveringUser;
    }    
    public void setDeliveringUser(int deliveringUser) {
        this.deliveringUser = deliveringUser;
    } 
    
    public int getStore() {
        return store;
    }    
    public void setStore(int store) {
        this.store = store;
    }
	
    public String getStatus() {
    	return this.status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    
}
