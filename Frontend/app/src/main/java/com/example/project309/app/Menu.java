package com.example.project309.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Menu {

    protected ArrayList<MenuItem> menuItems = new ArrayList<>();

    public boolean addMenuItem(MenuItem mItem){

        if(!menuItems.contains(mItem)){
            menuItems.add(mItem);
            return true;
        }
        return false;

    }

    protected boolean contains(MenuItem item){
        for (int i =0; i<menuItems.size(); i++){
            if (menuItems.get(i).equals(item)){
                return true;
            }
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

    public static Menu getMenuJSON(JSONArray arrJ){

        Menu temp = new Menu();
        JSONObject jObj;

        try {

            for (int i = 0; i < arrJ.length(); i++) {

                jObj = arrJ.getJSONObject(i);
                MenuItem item = new MenuItem();

                item.setTitle(jObj.getString("name"));
                item.setDescription(jObj.getString("description"));
                item.setPrice(jObj.getDouble("price"));

                temp.addMenuItem(item);

            }

        }
        catch (JSONException e){
            return null;
        }

        return temp;

    }

}
