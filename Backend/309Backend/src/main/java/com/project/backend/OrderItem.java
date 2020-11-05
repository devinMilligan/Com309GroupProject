package com.project.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderItems")
public class OrderItem {

	private int orderID;
	private int foodID;
	
	private int quantity;
	

    public int getOrderId() {
        return orderID;
    }    
    public void setOrderId(int orderID) {
        this.orderID = orderID;
    }
    
    public int getFoodId() {
        return foodID;
    }    
    public void setFoodId(int foodID) {
        this.foodID = foodID;
    }
    
    public int getQuantity() {
        return quantity;
    }    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
