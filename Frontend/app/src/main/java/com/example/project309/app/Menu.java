package com.example.project309.app;

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

    protected boolean removeMenuItem(MenuItem mItem) {
        if(arrItems.contains(mItem))
        {
            arrItems.remove(mItem);
            return true;
        }
        return false;
    }

    protected boolean changeMenuItemName(MenuItem mItem, String title) {
        for(int i = 0; i < arrItems.size(); i++) {
            if (arrItems.get(i).equals(mItem)) {
                arrItems.get(i).setTitle(title);
                return true;
            }
        }
        return false;
    }
    protected boolean changeMenuItemPrice(MenuItem mItem, double price) {
        for(int i = 0; i < arrItems.size(); i++) {
            if (arrItems.get(i).equals(mItem)) {
                arrItems.get(i).setPrice(price);
                return true;
            }
        }
        return false;
    }

}
