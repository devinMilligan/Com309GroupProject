package com.example.project309.app;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store {

    public static ArrayList<Store> allStores = new ArrayList<>();
    public static Store currentStore;

    private Image imStore;
    private int id;
    private String name;
    private String address;
    private int manager;
    private String sundayOpen;
    private String sundayClose;
    private String mondayOpen;
    private String mondayClose;
    private String tuesdayOpen;
    private String tuesdayClose;
    private String wednesdayOpen;
    private String wednesdayClose;
    private String thursdayOpen;
    private String thursdayClose;
    private String fridayOpen;
    private String fridayClose;
    private String saturdayOpen;
    private String saturdayClose;


    private Menu menu;

    public Store(String mName, String mLocation){

        name = mName;
        address = mLocation;
        menu = new Menu();

    }

    public Store (){

    }

    public String getName(){
        return name;
    }
    public int getID(){
        return id;
    }
    public String getAddress(){
        return address;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getFridayClose() {
        return fridayClose;
    }

    public String getFridayOpen() {
        return fridayOpen;
    }

    public String getSaturdayClose() {
        return saturdayClose;
    }
    public String getSaturdayOpen(){
        return saturdayOpen;
    }

    public String getSundayClose() {
        return sundayClose;
    }

    public String getSundayOpen() {
        return sundayOpen;
    }

    public String getMondayClose() {
        return mondayClose;
    }

    public String getMondayOpen() {
        return mondayOpen;
    }

    public String getTuesdayClose() {
        return tuesdayClose;
    }

    public String getTuesdayOpen() {
        return tuesdayOpen;
    }

    public String getWednesdayClose() {
        return wednesdayClose;
    }

    public String getWednesdayOpen() {
        return wednesdayOpen;
    }

    public String getThursdayClose() {
        return thursdayClose;
    }

    public String getThursdayOpen() {
        return thursdayOpen;
    }


    public void setID(int id){
        this.id = id;
    }
    public void setFridayOpen(String fridayOpen) {
        this.fridayOpen = fridayOpen;
    }

    public void setFridayClose(String fridayClose) {
        this.fridayClose = fridayClose;
    }

    public void setSaturdayClose(String saturdayClose) {
        this.saturdayClose = saturdayClose;
    }

    public void setSaturdayOpen(String saturdayOpen) {
        this.saturdayOpen = saturdayOpen;
    }

    public void setSundayClose(String sundayClose) {
        this.sundayClose = sundayClose;
    }

    public void setSundayOpen(String sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public void setMondayClose(String mondayClose) {
        this.mondayClose = mondayClose;
    }

    public void setMondayOpen(String mondayOpen) {
        this.mondayOpen = mondayOpen;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public void setTuesdayClose(String tuesdayClose) {
        this.tuesdayClose = tuesdayClose;
    }

    public void setTuesdayOpen(String tuesdayOpen) {
        this.tuesdayOpen = tuesdayOpen;
    }

    public void setWednesdayClose(String wednesdayClose) {
        this.wednesdayClose = wednesdayClose;
    }

    public void setWednesdayOpen(String wednesdayOpen) {
        this.wednesdayOpen = wednesdayOpen;
    }

    public void setThursdayClose(String thursdayClose) {
        this.thursdayClose = thursdayClose;
    }

    public void setThursdayOpen(String thursdayOpen) {
        this.thursdayOpen = thursdayOpen;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getManager() {
        return manager;
    }

    public void setName(String n) {
        name = n;
    }
    public void setAddress(String add) {
        address = add;
    }

    public static void addStoreToList(Store s){
        allStores.add(s);
    }

    public static PointLocation getLocationFromAddress(String strAddress, Context c){

        double longi = 0;
        double lat = 0;
        Geocoder coder = new Geocoder(c);
        List<Address> address;



        try {
            address = coder.getFromLocationName(strAddress,5);

            if (address==null || address.isEmpty()) {
                return null;
            }
            Address location=address.get(0);
            longi = location.getLatitude();
            lat = location.getLongitude();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }

        return new PointLocation(longi, lat);

    }

    public static void copyStore(Store copyInto, Store copyFrom){

        copyInto.setAddress(copyFrom.getAddress());
        copyInto.setManager(copyFrom.getManager());
        copyInto.setName(copyFrom.getName());
        copyInto.setID(copyFrom.getID());
        copyInto.setSundayOpen(copyFrom.getSundayOpen());
        copyInto.setSundayClose(copyFrom.getSundayClose());
        copyInto.setMondayOpen(copyFrom.getMondayOpen());
        copyInto.setMondayClose(copyFrom.getMondayClose());
        copyInto.setTuesdayOpen(copyFrom.getTuesdayOpen());
        copyInto.setTuesdayClose(copyFrom.getTuesdayClose());
        copyInto.setWednesdayOpen(copyFrom.getWednesdayOpen());
        copyInto.setWednesdayClose(copyFrom.getWednesdayClose());
        copyInto.setThursdayOpen(copyFrom.getThursdayOpen());
        copyInto.setThursdayClose(copyFrom.getThursdayClose());
        copyInto.setFridayOpen(copyFrom.getFridayOpen());
        copyInto.setFridayClose(copyFrom.getFridayClose());
        copyInto.setSaturdayClose(copyFrom.getSaturdayClose());
        copyInto.setSaturdayOpen(copyFrom.getSaturdayOpen());

    }

    public static Store getStore(JSONObject json){

        Store temp = new Store();

        try {

            temp.setID(Integer.parseInt(json.getString("id")));
            temp.setAddress(json.getString("address"));
            temp.setFridayClose(json.getString("closes_friday"));
            temp.setFridayOpen(json.getString("opens_friday"));
            temp.setSaturdayClose(json.getString("closes_saturday"));
            temp.setSaturdayOpen(json.getString("opens_saturday"));
            temp.setSundayClose(json.getString("closes_sunday"));
            temp.setSundayOpen(json.getString("opens_sunday"));
            temp.setMondayClose(json.getString("closes_monday"));
            temp.setMondayOpen(json.getString("opens_monday"));
            temp.setTuesdayClose(json.getString("closes_tuesday"));
            temp.setTuesdayOpen(json.getString("opens_tuesday"));
            temp.setWednesdayClose(json.getString("closes_wednesday"));
            temp.setWednesdayOpen(json.getString("opens_wednesday"));
            temp.setThursdayClose(json.getString("closes_thursday"));
            temp.setThursdayOpen(json.getString("opens_thursday"));
            temp.setManager(json.getInt("manager"));
            temp.setName(json.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;

    }

}
