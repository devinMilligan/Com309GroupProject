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

public class MarkerInfoWindowAdapterDelivery implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private ArrayList<Store> allStores;
    public MarkerInfoWindowAdapterDelivery(Context context, ArrayList<Store> allS) {
        this.context = context.getApplicationContext();
        allStores = allS;
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.map_marker_info_window_delivery, null);

//        LatLng latLng = arg0.getPosition();
//        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat_delivery);
//        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng_delivery);
//        tvLat.setText("Latitude:" + latLng.latitude);
//        tvLng.setText("Longitude:"+ latLng.longitude);

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

        if (i!=allStores.size()) {
            restName.setText(restaurantName + "     (" + allStores.get(i).getOrders() + ")");
            sun.setText("S  " + allStores.get(i).getSundayOpen().substring(0, 5) + " - " + allStores.get(i).getSundayClose().substring(0, 5));
            mon.setText("M  " + allStores.get(i).getMondayOpen().substring(0, 5) + " - " + allStores.get(i).getMondayClose().substring(0, 5));
            tue.setText("T  " + allStores.get(i).getTuesdayOpen().substring(0, 5) + " - " + allStores.get(i).getTuesdayClose().substring(0, 5));
            wed.setText("W  " + allStores.get(i).getWednesdayOpen().substring(0, 5) + " - " + allStores.get(i).getWednesdayClose().substring(0, 5));
            thu.setText("T  " + allStores.get(i).getThursdayOpen().substring(0, 5) + " - " + allStores.get(i).getThursdayClose().substring(0, 5));
            fri.setText("F  " + allStores.get(i).getFridayOpen().substring(0, 5) + " - " + allStores.get(i).getFridayClose().substring(0, 5));
            sat.setText("S  " + allStores.get(i).getSaturdayOpen().substring(0, 5) + " - " + allStores.get(i).getSaturdayClose().substring(0, 5));
        }
        return v;
    }
}