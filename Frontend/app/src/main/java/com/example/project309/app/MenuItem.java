package com.example.project309.app;

/**
 * Sets up and stores information about a menu item
 *
 * @author Ryan Hickok
 */
public class MenuItem{

    private int id;

    /**
     * Title of item
     */
    private String mTitle;
    /**
     * Price of item
     */
    private double mPrice;
    /**
     * Description of item
     */
    private String description;
    /**
     * Number of this item ordered
     */
    private int quantity;

    /**
     * Constructor for menu item
     *
     * @param title title of item
     * @param description description of item
     * @param price price of item
     */
    public MenuItem(String title, String description, double price){

        mTitle = title;
        mPrice = price;
        this.description = description;
        quantity = 0;

    }

    /**
     * Default Constructor
     */
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

    /**
     * Equals method for menu items
     *
     * @param o object to compare to
     * @return true if objects are equal
     */
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
