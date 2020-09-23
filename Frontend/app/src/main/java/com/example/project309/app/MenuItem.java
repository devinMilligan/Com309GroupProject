package com.example.project309.app;

public class MenuItem extends Menu {

    private String mTitle;
    private double mPrice;

    public MenuItem(String title, double price){

        mTitle = title;
        mPrice = price;
        addMenuItem(this);

    }

    public String getTitle(){
        return mTitle;
    }

    public double getPrice(){
        return mPrice;
    }

    public void setTitle(String t) { mTitle = t; }
    public void setPrice(double p) { mPrice = p; }

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
}
