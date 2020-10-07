package com.example.project309.app.uiDelivery.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.project309.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MarkerInfoWindowAdapterDelivery implements GoogleMap.InfoWindowAdapter {
    private Context context;
    public MarkerInfoWindowAdapterDelivery(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.map_marker_info_window_delivery, null);

        LatLng latLng = arg0.getPosition();
        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat_delivery);
        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng_delivery);
        TextView restName = (TextView) v.findViewById(R.id.rest_name_delivery);
        TextView restHours = (TextView) v.findViewById(R.id.rest_hours_delivery);
        tvLat.setText("Latitude:" + latLng.latitude);
        tvLng.setText("Longitude:"+ latLng.longitude);
        restName.setText("Dining Center: Conversations");
        restHours.setText("Hours of Operation: 8am - 5pm");
        return v;
    }
}
