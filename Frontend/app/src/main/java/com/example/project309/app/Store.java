package com.example.project309.app;

import android.media.Image;

import java.util.ArrayList;

public class Store {

    public static ArrayList<Store> allStores = new ArrayList<>();
    public static Store currentStore;

    private Image imStore;
    private String name;
    private String location;
    private Menu menu;

    public Store(String mName, String mLocation){

        name = mName;
        location = mLocation;
        menu = new Menu();

    }

    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }

    public void setName(String n) {
        name = n;
    }
    public void setLocation(String loc) {
        location = loc;
    }

    public static void addStoreToList(Store s){
        allStores.add(s);
    }
}
