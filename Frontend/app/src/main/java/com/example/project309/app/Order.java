package com.example.project309.app;

public class Order {

    private double oPrice;
    private int oNum;

    public Order(int orderNumber, double orderPrice){

        oPrice = orderPrice;
        oNum = orderNumber;

    }

    public int getOrderNumber() {
        return oNum;
    }
    public double getOrderPrice(){
        return oPrice;
    }


}
