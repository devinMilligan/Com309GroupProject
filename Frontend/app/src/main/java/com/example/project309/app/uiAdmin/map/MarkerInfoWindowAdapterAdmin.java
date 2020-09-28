package com.example.project309.app.uiAdmin.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.project309.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MarkerInfoWindowAdapterAdmin implements GoogleMap.InfoWindowAdapter {
    private Context context;
    public MarkerInfoWindowAdapterAdmin(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.map_marker_info_window_admin, null);

        LatLng latLng = arg0.getPosition();
        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat_admin);
        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng_admin);
        TextView restName = (TextView) v.findViewById(R.id.rest_name_admin);
        TextView restHours = (TextView) v.findViewById(R.id.rest_hours_admin);
        tvLat.setText("Latitude:" + latLng.latitude);
        tvLng.setText("Longitude:"+ latLng.longitude);
        restName.setText("Dining Center: Conversations");
        restHours.setText("Hours of Operation: 8am - 5pm");
        return v;
    }
}
