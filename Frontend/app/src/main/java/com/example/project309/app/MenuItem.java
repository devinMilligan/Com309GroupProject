package com.example.project309.app;

public class MenuItem{

    private int id;
    private String mTitle;
    private double mPrice;
    private String description;
    private int quantity;

    public MenuItem(String title, String description, double price){

        mTitle = title;
        mPrice = price;
        this.description = description;
        quantity = 0;

    }

    public MenuItem(){

    }

    public int getId(){return id;}
    public int getQuantity() {
        return quantity;
    }
    public String getTitle(){
        return mTitle;
    }
    public double getPrice(){
        return mPrice;
    }
    public String getDescription(){
        return description;
    }

    public void setId(int id){ this.id = id;}
    public void setDescription(String description){
        this.description = description;
    }
    public void setTitle(String t) { mTitle = t; }
    public void setPrice(double p) { mPrice = p; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object o){

        if(o.getClass().equals(MenuItem.class)){

            MenuItem temp = (MenuItem)o;
            if(temp.mTitle.equals(this.mTitle)){
                return true;
            }
            return false;
        }
        return false;
    }

    public static MenuItem getCopy(MenuItem itemToCopy){

        MenuItem temp = new MenuItem();

        temp.mPrice = itemToCopy.mPrice;
        temp.quantity = itemToCopy.quantity;
        temp.description = itemToCopy.description;
        temp.mTitle = temp.mTitle;

        return temp;
    }
}
