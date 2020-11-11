package com.example.project309.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Sets up and stores information about a store's menu
 *
 * @author Ryan Hickok
 */
public class Menu {

    /**
     * Array of items on the menu
     */
    protected ArrayList<MenuItem> menuItems = new ArrayList<>();

    /**
     * Adds a menu item to the menu
     *
     * @param mItem item to add
     * @return true if add is successful
     */
    public boolean addMenuItem(MenuItem mItem){

        if(!menuItems.contains(mItem)){
            menuItems.add(mItem);
            return true;
        }
        return false;

    }

    /**
     * Searches the menu for a specific menu item
     *
     * @param item item to find
     * @return true if the item is contained in the menu
     */
    protected boolean contains(MenuItem item){
        for (int i =0; i<menuItems.size(); i++){
            if (menuItems.get(i).equals(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an item from the menu
     *
     * @param mItem item to remove
     * @return true if the item is removed
     */
    protected boolean removeMenuItem(MenuItem mItem) {
        if(menuItems.contains(mItem))
        {
            menuItems.remove(mItem);
            return true;
        }
        return false;
    }

    /**
     * Changes the name of a menu item
     *
     * @param mItem item to change
     * @param title new name of item
     * @return true if item name is successfully changed
     */
    protected boolean changeMenuItemName(MenuItem mItem, String title) {
        for(int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).equals(mItem)) {
                menuItems.get(i).setTitle(title);
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the price of a menu item
     *
     * @param mItem item to change
     * @param price new price of item
     * @return true if item price is successfully changed
     */
    protected boolean changeMenuItemPrice(MenuItem mItem, double price) {
        for(int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).equals(mItem)) {
                menuItems.get(i).setPrice(price);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the list of items in the menu
     *
     * @return array of items in current menu
     */
    protected ArrayList<MenuItem> getMenuItems(){

        return menuItems;

    }

    /**
     * Set up a menu with JSON
     *
     * @param arrJ array of menu items to add to the menu
     * @return new menu object
     */
    public static Menu getMenuJSON(JSONArray arrJ){

        Menu temp = new Menu();
        JSONObject jObj;

        try {

            for (int i = 0; i < arrJ.length(); i++) {

                jObj = arrJ.getJSONObject(i);
                MenuItem item = new MenuItem();

                item.setId(jObj.getInt("id"));
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
