package com.example.project309.app;

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
    private ArrayList<MenuItem> items;

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

    public int getOrderNumber() {
        return oNum;
    }
    public double getOrderPrice(){
        return oPrice;
    }
    public Profile getDeliverer(){
        return deliverer;
    }

    public int getNumItems(){
        if(items != null) {
            return items.size();
        }
        return 0;
    }

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
