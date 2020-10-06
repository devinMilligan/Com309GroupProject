package com.project.backend;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Store {
    private long id;
    private String name;
    private String address;
    private int managerID;
    private double latitude;
    private double longitude;
    private LinkedList<List<Integer>> hours;

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

    public int getManager() {
        return managerID;
    }

    public void setManager(int managerID) {
        this.managerID = managerID;
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

    public LinkedList<List<Integer>> getHours() {
    	return hours;
    }

    public void setHours(LinkedList<List<Integer>> hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", manager id='" + managerID + '\'' +
                ", lat/long=('" + latitude + "','" + longitude + "')" +
                ", hours='" + hours + '\'' +
                '}';
    }

    public static Store create(String name, String address, int managerID, double latitude, double longitude, int sunOpen, int sunClose,
    		int monOpen, int monClose, int tuesOpen, int tuesClose, int wedOpen, int wedClose, int thursOpen, int thursClose,
    		int friOpen, int friClose, int satOpen, int satClose) {
        Store store = new Store();
        store.setName(name);
        store.setAddress(address);
        store.setManager(managerID);
        store.setLatitude(latitude);
        store.setLongitude(longitude);

        LinkedList<List<Integer>> hours = new LinkedList<List<Integer>>();
        List<Integer> sunHours = new ArrayList<Integer>();
        sunHours.add(sunOpen);
        sunHours.add(sunClose);
        List<Integer> monHours = new ArrayList<Integer>();
        monHours.add(monOpen);
        monHours.add(monClose);
        List<Integer> tuesHours = new ArrayList<Integer>();
        tuesHours.add(tuesOpen);
        tuesHours.add(tuesClose);
        List<Integer> wedHours = new ArrayList<Integer>();
        wedHours.add(wedOpen);
        wedHours.add(wedClose);
        List<Integer> thursHours = new ArrayList<Integer>();
        thursHours.add(thursOpen);
        thursHours.add(thursClose);
        List<Integer> friHours = new ArrayList<Integer>();
        friHours.add(friOpen);
        friHours.add(friClose);
        List<Integer> satHours = new ArrayList<Integer>();
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