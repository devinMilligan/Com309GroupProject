package com.example.project309.app;

import android.media.Image;

public class Store {

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


}
