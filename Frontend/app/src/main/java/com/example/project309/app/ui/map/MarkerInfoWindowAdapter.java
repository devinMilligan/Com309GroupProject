package com.example.project309.app.ui.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project309.R;
import com.example.project309.app.SignUpActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.snackbar.Snackbar;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;
    public MarkerInfoWindowAdapter(Context context) {
        this.context = context.getApplicationContext();
    }

    public void onCreate(Bundle savedInstanceState){

        //see this link: https://stackoverflow.com/questions/18567563/google-map-v2-custom-infowindow-with-two-clickable-buttons-or-imageview
        //also,  see: https://developer.android.com/reference/android/widget/Button
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.map_marker_info_window);
//
//        final Button button = findViewById(R.id.button_id);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Code here executes on main thread after user presses button
//            }
//        });

//        Button order_button = v.findViewById(R.id.login_create_account_button);
//        order_button.setOnClickListener(this);
//        Intent createAccount = new Intent(this, SignUpActivity.class);
//        startActivity(createAccount);
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.map_marker_info_window, null);
        LatLng latLng = arg0.getPosition();
        String title = arg0.getTitle();
        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
        TextView restName = (TextView) v.findViewById(R.id.rest_name);
        TextView restHours = (TextView) v.findViewById(R.id.rest_hours);
        if (title == null){
            tvLat.setText("Latitude:" + latLng.latitude);
            tvLng.setText("Longitude:"+ latLng.longitude);
        }
        else {
            restName.setText("Dining Center: " + title);
            restHours.setText("Hours of Operation: 8am - 9pm");
        }
        return v;
    }
}
