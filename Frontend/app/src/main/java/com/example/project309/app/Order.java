package com.example.project309.app;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class holds the information for the order made by a customer. This information will be saved on server
 *
 * @author Devin Milligan
 */
public class Order {

    /**
     * The order that is currently being viewed
     */
    public static Order currentOrder;
    private Profile deliverer;
    private double oPrice;
    private int oNum;
    private String status;
    private int storeID;
    private ArrayList<MenuItem> items;
    private Store store;

    private int userOrderID;

    /**
     * Sets up a new order object based on provided information
     *
     * @param orderNumber
     * @param orderPrice
     */
    public Order(int orderNumber, double orderPrice){

        oPrice = orderPrice;
        oNum = orderNumber;
        items = new ArrayList<>();

    }

    /**
     * Default Constructor
     */
    public Order(){

        items = new ArrayList<>();

    }

    public void setUserOrderID(int userOrderID) {
        this.userOrderID = userOrderID;
    }

    public void setStore(Store s){this.store = s;}
    public void setStatus(String status){this.status = status;}
    public void setStoreID(int storeID){this.storeID = storeID;}
    public void setDeliverer(Profile deliverer){
        this.deliverer = deliverer;
    }
    public void setPrice(double price){
        this.oPrice = price;
    }
    public void setOrderNumber(int orderNumber){
        oNum = orderNumber;
    }
    public void setOrderedItems(ArrayList<MenuItem> items){
        if(items != null) {
            this.items = items;
        }
    }

    /**
     * Adds a menu item to the order based on provided item and quantity information
     *
     * @param item
     * @param quantity
     */
    public void addMenuItem(MenuItem item, int quantity){
        if(item!= null) {
            if(!items.contains(item)) {
                items.add(item);
            }
            oPrice += item.getPrice()*quantity;
        }
    }

    /**
     * Adds a menu item to the order based on provided item information
     *
     * @param item
     */
    public void addMenuItem(MenuItem item){
        if(item!= null) {
            if(!items.contains(item)) {
                items.add(item);
            }
            oPrice += item.getPrice()*item.getQuantity();
        }
    }

    /**
     * Removes a menu item from the order based on the item type and quantity
     *
     * @param item
     * @param quantity
     */
    public void removeMenuItem(MenuItem item, int quantity){

        if(item!= null) {
            oPrice -= item.getPrice()*quantity;
            if(item.getQuantity() == 0){
                items.remove(item);
            }
        }

    }

    public Store getStore(){return store;}
    public int getUserOrderID() {
        return userOrderID;
    }

    public MenuItem getItem(int position){
        return items.get(position);
    }
    public int getOrderNumber() {
        return oNum;
    }
    public double getOrderPrice(){
        return oPrice;
    }
    public Profile getDeliverer(){
        return deliverer;
    }
    public String getStatus(){return status;}
    public int getStoreID(){return storeID;}

    public int getNumItems(){
        if(items != null) {
            return items.size();
        }
        return 0;
    }


    public void resetMenu(){
        MenuItem temp;
        ArrayList<MenuItem> arr = new ArrayList<>();
        int size = items.size();
        for(int i = size-1; i>-1; i--){
            temp = MenuItem.getCopy(items.get(i));
            items.get(i).setQuantity(0);
            items.remove(i);
            arr.add(temp);
        }

        items.addAll(arr);

    }

    public static Order getOrderFromJSON(JSONObject response){

        Order temp = new Order();
        try{

            temp.setOrderNumber(response.getInt("id"));
            temp.setUserOrderID(response.getInt("orderingUser"));
            Profile deliver = new Profile();
            deliver.setId(response.getInt("deliveringUser"));
            temp.setDeliverer(deliver);
            temp.setStoreID(response.getInt("store"));
            temp.setStatus(response.getString("status"));
            temp.setPrice(response.getDouble("total"));

            if(Store.allStores != null && Store.allStores.size() != 0){

                for(int i = 0; i<Store.allStores.size();i++){

                    if(Store.allStores.get(i).getID() == temp.getStoreID()){
                        temp.setStore(Store.allStores.get(i));
                    }

                }

            }

        }catch (JSONException e){
            Log.d("ORDER", e.toString());
        }

        return temp;

    }

    //order can recommend stores to order from based on their menus
    //and their locations

    /**
     * Order can recommend stores to order from based on menu and location
     *
     * @param storeList
     * @return
     */
    public ArrayList<Store> getThreeStoreRecommendations(ArrayList<Store> storeList){
        ArrayList<Store> allStores = storeList;
        for (int i =0; i < allStores.size(); i++){
            for (int j=0; j<items.size(); j++){
                if (!(allStores.get(i).getMenu().contains(items.get(j)))){
                    allStores.remove(i);
                    i--;
                    break;
                }
            }
        }
        ArrayList<Double> distances = new ArrayList<>();
        for (int i = 0; i < allStores.size(); i++){
            distances.add(allStores.get(i).getDistanceFromCurrentLocation());
        }
        Double minimumDistance = Double.POSITIVE_INFINITY;
        int min_index = 0;
        //get first recommendation
        for (int i = 0; i<distances.size(); i++){
            if (distances.get(i)  < minimumDistance){
                minimumDistance = distances.get(i);
                min_index = i;
            }
        }
        ArrayList<Store> recommendations = new ArrayList<>();
        if(allStores.isEmpty()){
            return recommendations;
        }
        recommendations.add(allStores.get(min_index));
        allStores.remove(allStores.get(min_index));
        distances.remove(min_index);
        //get second recommendation
        minimumDistance = Double.POSITIVE_INFINITY;
        min_index = 0;
        for (int i = 0; i<distances.size(); i++){
            if (distances.get(i)  < minimumDistance){
                minimumDistance = distances.get(i);
                min_index = i;
            }
        }
        if(allStores.isEmpty()){
            return recommendations;
        }
        recommendations.add(allStores.get(min_index));
        allStores.remove(allStores.get(min_index));
        distances.remove(min_index);
        //get third recommendation
        minimumDistance = Double.POSITIVE_INFINITY;
        min_index = 0;
        for (int i = 0; i<distances.size(); i++){
            if (distances.get(i)  < minimumDistance){
                minimumDistance = distances.get(i);
                min_index = i;
            }
        }
        if(allStores.isEmpty()){
            return recommendations;
        }
        recommendations.add(allStores.get(min_index));
        return recommendations;
    }

}
