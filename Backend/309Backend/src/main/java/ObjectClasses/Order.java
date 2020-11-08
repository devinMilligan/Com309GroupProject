package ObjectClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private double total;
	
	public Order(){ }	

	public Order(int user, int store, String status){ 
		orderingUser = user;
		deliveringUser = 0;
		this.store = store;
		this.status = status;
		total = 0.00;
	}
	
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
	
    public double getTotal() {
    	return this.total;
    }
    public void setTotal(double total) {
    	this.total = total;
    }
}
