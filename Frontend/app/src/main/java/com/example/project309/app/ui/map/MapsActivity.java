package com.example.project309.app.ui.map;

import android.Manifest;
import android.content.ClipData;
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
import com.example.project309.app.Menu;
import com.example.project309.app.MenuItem;
import com.example.project309.app.OrderPickStore;
import com.example.project309.app.OrderingScreen;
import com.example.project309.app.PlaceOrder;
import com.example.project309.app.Profile;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * This class defines the app's map page's functionality
 *
 * @author Allison Finger
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, ViewListenerInter {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private ArrayList<Store> aStores;
    CharSequence text;
    JSONHandlerInter jsonH;

    /**
     * A constructor for the class which sets the JSON handler variable
     */
    public MapsActivity(){
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
    }

    /**
     * A void method which sets aStores using a JSON array request to the backend and some manual store additions
     */
    public void getListOfStores() {
        this.jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);
        aStores = Store.allStores;
//        Store s = new Store();
//        s.setName("Season's");
//        s.setLongitude(42.023858);
//        s.setLatitude(-93.638253);
//        s.setSundayOpen("03:45:00");
//        s.setSundayClose("03:45:00");
//        s.setMondayOpen("03:45:00");
//        s.setMondayClose("03:45:00");
//        s.setTuesdayOpen("03:45:00");
//        s.setTuesdayClose("03:45:00");
//        s.setWednesdayOpen("03:45:00");
//        s.setWednesdayClose("03:45:00");
//        s.setThursdayOpen("03:45:00");
//        s.setThursdayClose("03:45:00");
//        s.setFridayOpen("03:45:00");
//        s.setFridayClose("03:45:00");
//        s.setSaturdayOpen("03:45:00");
//        s.setSaturdayClose("03:45:00");
//        s.setOrders(2);
//        aStores.add(s);
//
//        Store st = new Store();
//        st.setName("Convos");
//        st.setLongitude(42.025289);
//        st.setLatitude(-93.640330);
//        st.setSundayOpen("03:45:00");
//        st.setSundayClose("03:45:00");
//        st.setMondayOpen("03:45:00");
//        st.setMondayClose("03:45:00");
//        st.setTuesdayOpen("03:45:00");
//        st.setTuesdayClose("03:45:00");
//        st.setWednesdayOpen("03:45:00");
//        st.setWednesdayClose("03:45:00");
//        st.setThursdayOpen("00:00:00");
//        st.setThursdayClose("00:00:00");
//        st.setFridayOpen("03:45:00");
//        st.setFridayClose("03:45:00");
//        st.setSaturdayOpen("03:45:00");
//        st.setSaturdayClose("03:45:00");
//        st.setOrders(27);
//        aStores.add(st);
    }

    @Override
    /**
     * An overridden onMapReady method which calls multiple methods after the map is ready
     *
     * @param  googleMap the google map object used to edit the scene for the map activity
     */
    public void onMapReady(GoogleMap googleMap) {
        getListOfStores();
        mMap = googleMap;
        float zoom = 15;

        mMap.clear();

        // Add a marker at the campanile and move the camera
        LatLng campanile = new LatLng(42.025408, -93.646074);
//        mMap.addMarker(new MarkerOptions().position(campanile).title("Campanile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campanile, zoom));

        addMarkersToMap();

        //setMapLongClick(mMap); // Set a long click listener for the map;
        //setPoiClick(mMap); // Set a click listener for points of interest.
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
//        String cs = aStores.get(3).getName();
//        Toast.makeText(getApplicationContext(), cs, Toast.LENGTH_LONG).show();

//        int j =2;
//        LatLng store_temp = new LatLng(aStores.get(j).getLongitude(), aStores.get(j).getLatitude());
//        String cs = "name: "  + aStores.get(j).getName();
//        Toast.makeText(getApplicationContext(), cs, Toast.LENGTH_LONG).show();
//        String cs1 = "lat: " + Double.toString(store_temp.latitude);
//        Toast.makeText(getApplicationContext(), cs1, Toast.LENGTH_LONG).show();
//        String cs2 = "long: " + Double.toString(store_temp.longitude);
//        Toast.makeText(getApplicationContext(), cs2, Toast.LENGTH_LONG).show();
//        mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(j).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        int i;
        for (i=0; i<aStores.size(); i++){
            LatLng store_temp = new LatLng(aStores.get(i).getLongitude(), aStores.get(i).getLatitude());
            int a = aStores.get(i).getWaitTimeMin();
            if (storeClosed(aStores.get(i))){
                mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
            else if (a <= 5){
                mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }
            else{
                mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            }
        }
    }

    private boolean storeClosed(Store store){
        Date date = Calendar.getInstance().getTime();
        String day = getDayStringOld(date);
        boolean closed = false;
        switch(day){
            case "Monday":
                if(store.getMondayOpen().equals("null") || isClosed(store.getMondayClose()) || notYetOpen(store.getMondayOpen())){
                    closed = true;
                }
                break;
            case "Tuesday":
                if(store.getTuesdayOpen().equals("null") || isClosed(store.getTuesdayClose()) || notYetOpen(store.getTuesdayOpen())){
                    closed = true;
                }
                break;
            case "Wednesday":
                if(store.getWednesdayOpen().equals("null") || isClosed(store.getWednesdayClose()) || notYetOpen(store.getWednesdayOpen())){
                    closed = true;
                }
                break;
            case "Thursday":
                if(store.getThursdayOpen().equals("null") || isClosed(store.getThursdayClose()) || notYetOpen(store.getThursdayOpen())){
                    closed = true;
                }
                break;
            case "Friday":
                if(store.getFridayOpen().equals("null") || isClosed(store.getFridayClose()) || notYetOpen(store.getFridayOpen())){
                    closed = true;
                }
                break;
            case "Saturday":
                if(store.getSaturdayOpen().equals("null") || isClosed(store.getSaturdayClose()) || notYetOpen(store.getSaturdayOpen())){
                    closed = true;
                }
                break;
            case "Sunday":
                if(store.getSundayOpen().equals("null") || isClosed(store.getSundayClose()) || notYetOpen(store.getSundayOpen())){
                    closed = true;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + day);
        }
        return closed;
    }

    private boolean notYetOpen(String open_time) {
        int hour = Integer.parseInt(open_time.substring(0,2));
        int minute = Integer.parseInt(open_time.substring(3,5));
        Calendar rightNow = Calendar.getInstance();
        int now_hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int now_minute = rightNow.get(Calendar.MINUTE);
        if (now_hour < hour || (now_hour == hour && now_minute < minute)){
//            text = "not yet open";
//            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private boolean isClosed(String close_time){
        int hour = Integer.parseInt(close_time.substring(0,2));
        int minute = Integer.parseInt(close_time.substring(3,5));
        Calendar rightNow = Calendar.getInstance();
        int now_hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int now_minute = rightNow.get(Calendar.MINUTE);
        if (now_hour > hour || (now_hour == hour && now_minute > minute)){
//            text = "after hours";
//            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private static String getDayStringOld(Date date) {
        DateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return formatter.format(date);
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
                int i;
                for (i =0; i<aStores.size(); i++){
                    if (aStores.get(i).getName().equals(marker.getTitle())){
                        break;
                    }
                }
                if (storeClosed(aStores.get(i))){
                    text = "Sorry, this store is closed right now!";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }
                else {
                    Store.currentStore = aStores.get(i);
                    Intent startOrder = new Intent(MapsActivity.this, OrderingScreen.class);
                    startActivity(startOrder);
                }
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
        for(int i = 0; i<response.length();i++) {
            try {
                onSuccess(response.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        aStores = Store.allStores;
//        CharSequence cs = (CharSequence) Integer.toString(aStores.size());
//        Toast.makeText(getApplicationContext(), cs, Toast.LENGTH_LONG).show();
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