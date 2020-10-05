package com.project.backend;

import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Store {
    private long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private LinkedList<List<Time>> hours;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LinkedList<List<Time>> getHours() {
    	return hours;
    }

    public void setHours(LinkedList<List<Time>> hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", lat/long=('" + latitude + "','" + longitude + "')" +
                ", hours='" + hours + '\'' +
                '}';
    }

    public static Store create(String name, String address, double latitude, double longitude, Time sunOpen, Time sunClose,
    		Time monOpen, Time monClose, Time tuesOpen, Time tuesClose, Time wedOpen, Time wedClose, Time thursOpen, Time thursClose,
    		Time friOpen, Time friClose, Time satOpen, Time satClose) {
        Store store = new Store();
        store.setName(name);
        store.setAddress(address);
        store.setLatitude(latitude);
        store.setLongitude(longitude);

        LinkedList<List<Time>> hours = new LinkedList<List<Time>>();
        List<Time> sunHours = new ArrayList<Time>();
        sunHours.add(sunOpen);
        sunHours.add(sunClose);
        List<Time> monHours = new ArrayList<Time>();
        monHours.add(monOpen);
        monHours.add(monClose);
        List<Time> tuesHours = new ArrayList<Time>();
        tuesHours.add(tuesOpen);
        tuesHours.add(tuesClose);
        List<Time> wedHours = new ArrayList<Time>();
        wedHours.add(wedOpen);
        wedHours.add(wedClose);
        List<Time> thursHours = new ArrayList<Time>();
        thursHours.add(thursOpen);
        thursHours.add(thursClose);
        List<Time> friHours = new ArrayList<Time>();
        friHours.add(friOpen);
        friHours.add(friClose);
        List<Time> satHours = new ArrayList<Time>();
        satHours.add(satOpen);
        satHours.add(satClose);
        hours.add(sunHours);
        hours.add(monHours);
        hours.add(tuesHours);
        hours.add(thursHours);
        hours.add(friHours);
        hours.add(satHours);        
        store.setHours(hours);
        
        return store;
    }
}