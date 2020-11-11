package com.example.project309.app;

import org.json.JSONObject;

import java.util.ArrayList;

public class Order {

    public static Order currentOrder;
    private Profile deliverer;
    private double oPrice;
    private int oNum;
    private String status;
    private int storeID;
    private ArrayList<MenuItem> items;

    public Order(int orderNumber, double orderPrice){

        oPrice = orderPrice;
        oNum = orderNumber;
        items = new ArrayList<>();

    }

    public Order(){

        items = new ArrayList<>();

    }

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
    public void addMenuItem(MenuItem item, int quantity){
        if(item!= null) {
            if(!items.contains(item)) {
                items.add(item);
            }
            oPrice += item.getPrice()*quantity;
        }
    }
    public void addMenuItem(MenuItem item){
        if(item!= null) {
            if(!items.contains(item)) {
                items.add(item);
            }
            oPrice += item.getPrice()*item.getQuantity();
        }
    }
    public void removeMenuItem(MenuItem item, int quantity){

        if(item!= null) {
            oPrice -= item.getPrice()*quantity;
            if(item.getQuantity() == 0){
                items.remove(item);
            }
        }

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
        for(int i = size; i>-1; i--){
            temp = MenuItem.getCopy(items.get(i));
            items.get(i).setQuantity(0);
            items.remove(i);
            arr.add(temp);
        }

        items.addAll(arr);

    }

    public static Order getOrderFromJSON(JSONObject response){

        return new Order();

    }

    //order can recommend stores to order from based on their menus
    //and their locations
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
