package com.example.project309.app.uiDelivery.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.app.AppController;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.MainNavigationScreenDelivery;
import com.example.project309.app.PlaceOrder;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.app.uiDelivery.map.MarkerInfoWindowAdapterDelivery;
import com.example.project309.net_utils.Const;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class defines the app's map page's functionality
 *
 * @author Allison Finger
 */
public class MapsActivityDelivery extends AppCompatActivity implements OnMapReadyCallback, ViewListenerInter {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private ArrayList<Store> aStores;
    CharSequence text;
    JSONHandlerInter jsonH;

    /**
     * A constructor for the class which sets the JSON handler variable
     */
    public MapsActivityDelivery(){
        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);
    }

    @Override
    /**
     * An overridden onCreate method which calls multiple methods to prepare the map's creation
     *
     * @param  savedInstanceState  the saved instance state used for onCreate
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map_delivery);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.nav_map_delivery, mapFragment).commit();
        mapFragment.getMapAsync(this);
        getListOfStores();
    }

    /**
     * A void method which sets aStores using a JSON array request to the backend and some manual store additions
     */
    public void getListOfStores() {
        this.jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);
        aStores = Store.allStores;

        Store s = new Store();
        s.setName("Season's");
        s.setLatitude(42.023858);
        s.setLongitude(-93.638253);
        s.setSundayOpen("03:45:00");
        s.setSundayClose("03:45:00");
        s.setMondayOpen("03:45:00");
        s.setMondayClose("03:45:00");
        s.setTuesdayOpen("03:45:00");
        s.setTuesdayClose("03:45:00");
        s.setWednesdayOpen("03:45:00");
        s.setWednesdayClose("03:45:00");
        s.setThursdayOpen("03:45:00");
        s.setThursdayClose("03:45:00");
        s.setFridayOpen("03:45:00");
        s.setFridayClose("03:45:00");
        s.setSaturdayOpen("03:45:00");
        s.setSaturdayClose("03:45:00");
        s.setOrders(2);
        aStores.add(s);

        Store st = new Store();
        st.setName("Convos");
        st.setLatitude(42.025289);
        st.setLongitude(-93.640330);
        st.setSundayOpen("03:45:00");
        st.setSundayClose("03:45:00");
        st.setMondayOpen("03:45:00");
        st.setMondayClose("03:45:00");
        st.setTuesdayOpen("03:45:00");
        st.setTuesdayClose("03:45:00");
        st.setWednesdayOpen("03:45:00");
        st.setWednesdayClose("03:45:00");
        st.setThursdayOpen("03:45:00");
        st.setThursdayClose("03:45:00");
        st.setFridayOpen("03:45:00");
        st.setFridayClose("03:45:00");
        st.setSaturdayOpen("03:45:00");
        st.setSaturdayClose("03:45:00");
        st.setOrders(27);
        aStores.add(st);
    }

    @Override
    /**
     * An overridden onMapReady method which calls multiple methods after the map is ready
     *
     * @param  googleMap the google map object used to edit the scene for the map activity
     */
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoom = 15;

        mMap.clear();

        // Add a marker at the campanile and move the camera
        LatLng campanile = new LatLng(42.025408, -93.646074);
//        mMap.addMarker(new MarkerOptions().position(campanile).title("Campanile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campanile, zoom));

        addMarkersToMap();

        //setMapLongClick(mMap); // Set a long click listener for the map;
        setPoiClick(mMap); // Set a click listener for points of interest.
        enableMyLocation(mMap); // Enable location tracking.

        // Setting a custom info window adapter for the google map
        MarkerInfoWindowAdapterDelivery markerInfoWindowAdapterDelivery = new MarkerInfoWindowAdapterDelivery(getApplicationContext(), aStores);
        googleMap.setInfoWindowAdapter(markerInfoWindowAdapterDelivery);

        setInfoWindowClickToPanorama(mMap);
    }

    /**
     * A void method which adds markers to the map based on the stores in the aStores list
     * It does so based on the wait time of each individual store
     */
    public void addMarkersToMap() {
//        CharSequence cs = (CharSequence) Integer.toString(aStores.size());
//        Toast.makeText(getApplicationContext(), cs, Toast.LENGTH_LONG).show();

        for (int i =0; i<aStores.size(); i++){
            LatLng store_temp = new LatLng(aStores.get(i).getLatitude(), aStores.get(i).getLongitude());
            int a = aStores.get(i).getWaitTimeMin();
            if (a <= 5){
                mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker
                        (BitmapDescriptorFactory.HUE_GREEN)));
            }
            else if (a <= 30){
                mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker
                        (BitmapDescriptorFactory.HUE_YELLOW)));
            }
            else{
                mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker
                        (BitmapDescriptorFactory.HUE_RED)));
            }
        }
    }

    /**
     * A method which opens the PlaceOrder activity on click of the info window
     *
     * @param  map the google map that the info window click is referring to
     */
    private void setInfoWindowClickToPanorama(GoogleMap map) {
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapsActivityDelivery.this, PlaceOrder.class);
                startActivity(intent);
            }
        });
    }

    /**
     * A method which seeks to enable the user's location based on user permissions
     *
     * @param  map the google map for this activity
     */
    private void enableMyLocation(GoogleMap map) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    /**
     * A method which checks if location permissions are granted and if so enables the location data layer
     *
     * @param requestCode the code for the request
     * @param permissions the string array for the permissions
     * @param grantResults the int array for granting the results
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation(mMap);
                    break;
                }
        }
    }

    /**
     * A method which shows the info window if the marker is a poi and a store in the list
     *
     * @param map the google map the method is using
     */
    private void setPoiClick(GoogleMap map) {
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                text = "poi click";
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                for (int i=0; i<aStores.size(); i++){
                    if (poi.name.equals(aStores.get(i).getName())){
                        Marker poiMarker = mMap.addMarker(new MarkerOptions().position(poi.latLng).title(poi.name));
                        poiMarker.showInfoWindow();
                        poiMarker.setTag("poi");
                        text = "poi click";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    /**
     * A method which adds stores to the list of stores
     *
     * @param response the JSON object response for the store
     */
    public void onSuccess(JSONObject response) {
        Store s = Store.getStore(response);
        Store.addStoreToList(s);
    }

    @Override
    /**
     * A method which tries to add stores to the list of stores
     *
     * @param response the JSON array of stores
     */
    public void onSuccess(JSONArray response) {
        for(int i = 0; i<response.length();i++){
            try {
                onSuccess(response.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        aStores = Store.allStores;
    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    /**
     * A method which sends a toast with the error in case of a volley error
     *
     * @param error the volley error from the JSON request
     */
    public void onError(VolleyError error) {
        text = "error: " + error.toString();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}