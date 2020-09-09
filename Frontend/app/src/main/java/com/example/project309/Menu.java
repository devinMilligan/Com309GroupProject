package com.example.project309;

import java.util.ArrayList;

public class Menu {

    protected ArrayList<MenuItem> arrItems = new ArrayList<>();

    protected boolean addMenuItem(MenuItem mItem){

        if(!arrItems.contains(mItem)){
            arrItems.add(mItem);
            return true;
        }
        return false;

    }

}
