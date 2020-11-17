package com.example.project309.app.uiDelivery.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project309.R;
import com.example.project309.app.Store;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * This class defines the app's map page's info window adapter
 *
 * @author Allison Finger
 */
public class MarkerInfoWindowAdapterDelivery implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private ArrayList<Store> allStores;
    /**
     * A constructor for the adapter
     *
     * @param context the context of the map
     * @param allS the list of all stores
     */
    public MarkerInfoWindowAdapterDelivery(Context context, ArrayList<Store> allS) {
        this.context = context.getApplicationContext();
        allStores = allS;
    }

    @Override
    /**
     * The method which returns the view of the info window
     *
     * @param arg0 the marker which the info window belongs to
     */
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    /**
     * The method which returns the view of the custom info window contents
     *
     * @param arg0 the marker which the info window belongs to
     */
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.map_marker_info_window_delivery, null);

        String restaurantName = arg0.getTitle();
        TextView restName = v.findViewById(R.id.rest_name_delivery);
        TextView sun = v.findViewById(R.id.sunday);
        TextView mon = v.findViewById(R.id.monday);
        TextView tue = v.findViewById(R.id.tuesday);
        TextView wed = v.findViewById(R.id.wednesday);
        TextView thu = v.findViewById(R.id.thursday);
        TextView fri = v.findViewById(R.id.friday);
        TextView sat = v.findViewById(R.id.saturday);

        int i;
        for (i =0; i<allStores.size(); i++){
            if (allStores.get(i).getName().equals(arg0.getTitle())){
                break;
            }
        }

        String sunOpen;
        if (!allStores.get(i).getSundayOpen().equals("null")){
            sunOpen = allStores.get(i).getSundayOpen().substring(0, 5) + " - ";
        }
        else {
            sunOpen = "CLOSED       ";
        }
        String monOpen;
        if (allStores.get(i).getMondayOpen() != null){
            monOpen = allStores.get(i).getMondayOpen().substring(0, 5) + " - ";
        }
        else {
            monOpen = "CLOSED       ";
        }
        String tuesOpen;
        if (allStores.get(i).getTuesdayOpen() != null){
            tuesOpen = allStores.get(i).getTuesdayOpen().substring(0, 5) + " - ";
        }
        else {
            tuesOpen = "CLOSED       ";
        }
        String wedOpen;
        if (allStores.get(i).getWednesdayOpen() != null){
            wedOpen = allStores.get(i).getWednesdayOpen().substring(0, 5) + " - ";
        }
        else {
            wedOpen = "CLOSED       ";
        }
        String thurOpen;
        if (allStores.get(i).getThursdayOpen() != null){
            thurOpen = allStores.get(i).getThursdayOpen().substring(0, 5) + " - ";
        }
        else {
            thurOpen = "CLOSED       ";
        }
        String friOpen;
        if (allStores.get(i).getFridayOpen() != null){
            friOpen = allStores.get(i).getFridayOpen().substring(0, 5) + " - ";
        }
        else {
            friOpen = "CLOSED       ";
        }
        String satOpen;
        if (!allStores.get(i).getSaturdayOpen().equals("null")){
            satOpen = allStores.get(i).getSaturdayOpen().substring(0, 5) + " - ";
        }
        else {
            satOpen = "CLOSED       ";
        }
//////////
        String sunClose;
        if (!allStores.get(i).getSundayOpen().equals("null")){
            sunClose = allStores.get(i).getSundayClose();
        }
        else {
            sunClose = "       ";
        }
        String monClose;
        if (allStores.get(i).getMondayClose() != null){
            monClose = allStores.get(i).getMondayClose();
        }
        else {
            monClose = "       ";
        }
        String tuesClose;
        if (allStores.get(i).getTuesdayClose() != null){
            tuesClose = allStores.get(i).getTuesdayClose();
        }
        else {
            tuesClose = "       ";
        }
        String wedClose;
        if (allStores.get(i).getWednesdayClose() != null){
            wedClose = allStores.get(i).getWednesdayClose();
        }
        else {
            wedClose = "       ";
        }
        String thurClose;
        if (allStores.get(i).getThursdayClose() != null){
            thurClose = allStores.get(i).getThursdayClose();
        }
        else {
            thurClose = "       ";
        }
        String friClose;
        if (allStores.get(i).getFridayClose() != null){
            friClose = allStores.get(i).getFridayClose();
        }
        else {
            friClose = "       ";
        }
        String satClose;
        if (!allStores.get(i).getSaturdayClose().equals("null")){
            satClose = allStores.get(i).getSaturdayClose();
        }
        else {
            satClose = "       ";
        }

        if (i!=allStores.size()) {
            restName.setText(restaurantName + " (" + allStores.get(i).getOrders() + ")");
            sun.setText("S  " + sunOpen + sunClose.substring(0, 5));
            mon.setText("M  " + monOpen + monClose.substring(0, 5));
            tue.setText("T  " + tuesOpen + tuesClose.substring(0, 5));
            wed.setText("W  " + wedOpen + wedClose.substring(0, 5));
            thu.setText("R  " + thurOpen + thurClose.substring(0, 5));
            fri.setText("F  " + friOpen + friClose.substring(0, 5));
            sat.setText("S  " + satOpen + satClose.substring(0, 5));
        }
        return v;
    }
}