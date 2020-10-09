package com.example.project309.app;

import java.util.ArrayList;

public class Menu {

    protected ArrayList<MenuItem> menuItems = new ArrayList<>();

    protected boolean addMenuItem(MenuItem mItem){

        if(!menuItems.contains(mItem)){
            menuItems.add(mItem);
            return true;
        }
        return false;

    }

    protected boolean removeMenuItem(MenuItem mItem) {
        if(menuItems.contains(mItem))
        {
            menuItems.remove(mItem);
            return true;
        }
        return false;
    }

    protected boolean changeMenuItemName(MenuItem mItem, String title) {
        for(int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).equals(mItem)) {
                menuItems.get(i).setTitle(title);
                return true;
            }
        }
        return false;
    }
    protected boolean changeMenuItemPrice(MenuItem mItem, double price) {
        for(int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).equals(mItem)) {
                menuItems.get(i).setPrice(price);
                return true;
            }
        }
        return false;
    }
    protected ArrayList<MenuItem> getMenuItems(){

        return menuItems;

    }

}
