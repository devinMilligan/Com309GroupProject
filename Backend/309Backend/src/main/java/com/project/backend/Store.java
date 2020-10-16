package com.project.backend;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stores")
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private int manager;
    private double latitude;
    private double longitude;
    private Time hours;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int managerID) {
        this.manager = managerID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Time getHours() {
    	return hours;
    }

    public void setHours(Time hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", manager id='" + manager + '\'' +
                ", lat/long=('" + latitude + "','" + longitude + "')" +
                ", hours='" + hours + '\'' +
                '}';
    }

    public static Store create(String name, String address, int managerID, double latitude, double longitude, Time hours) {
        Store store = new Store();
        store.setName(name);
        store.setAddress(address);
        store.setManager(managerID);
        store.setLatitude(latitude);
        store.setLongitude(longitude);       
        store.setHours(hours);
        
        return store;
    }
}