package com.example.project309.app;

import java.util.ArrayList;

public class Order {

    public Profile deliverer;
    private double oPrice;
    private int oNum;
    public ArrayList<MenuItem> items;

    public Order(int orderNumber, double orderPrice){

        oPrice = orderPrice;
        oNum = orderNumber;
        items = new ArrayList<>();

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
    public void addMenuItem(MenuItem item){
        items.add(item);
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
