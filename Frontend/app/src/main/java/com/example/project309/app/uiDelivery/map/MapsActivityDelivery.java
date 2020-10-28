package com.example.project309.app.uiDelivery.map;

import android.Manifest;
import android.content.pm.PackageManager;
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
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivityDelivery extends AppCompatActivity implements OnMapReadyCallback, ViewListenerInter {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private ArrayList<Store> aStores;
    CharSequence text;
    JSONHandlerInter jsonH;

    public MapsActivityDelivery(){
        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map_delivery);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.nav_map_delivery, mapFragment).commit();
        mapFragment.getMapAsync(this);
        getListOfStores();
    }

    public void getListOfStores() {
        this.jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);
        aStores = Store.allStores;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoom = 17;

        // Add a marker at the campanile and move the camera
        LatLng campanile = new LatLng(42.025408, -93.646074);
        mMap.addMarker(new MarkerOptions().position(campanile).title("Campanile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campanile, zoom));

        for (int i =0; i<aStores.size(); i++){
            LatLng store_temp = new LatLng(aStores.get(i).getLatitude(), aStores.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(store_temp).title(aStores.get(i).getName()));
        }
//        double d = aStores.get(0).getLatitude();
//        String c = Double.toString(d);
//        text = "lat: " + ((CharSequence) c);
//        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

        //setMapLongClick(mMap); // Set a long click listener for the map;
        setPoiClick(mMap); // Set a click listener for points of interest.
        enableMyLocation(mMap); // Enable location tracking.

        // Setting a custom info window adapter for the google map
        MarkerInfoWindowAdapterDelivery markerInfoWindowAdapterDelivery = new MarkerInfoWindowAdapterDelivery(getApplicationContext());
        googleMap.setInfoWindowAdapter(markerInfoWindowAdapterDelivery);

        // Adding and showing marker when the map is touched
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {
                text = "map click";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(arg0);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
                Marker marker = mMap.addMarker(markerOptions);
                marker.showInfoWindow();
            }
        });

        setInfoWindowClickToPanorama(mMap);
    }

    private void setInfoWindowClickToPanorama(GoogleMap map) {
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == "poi") {
                    // Set the position to the position of the marker
                    StreetViewPanoramaOptions options =
                            new StreetViewPanoramaOptions().position(
                                    marker.getPosition());

                    SupportStreetViewPanoramaFragment streetViewFragment
                            = SupportStreetViewPanoramaFragment
                            .newInstance(options);

                    // Replace the fragment and add it to the backstack
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_map_delivery,
                                    streetViewFragment)
                            .addToBackStack(null).commit();
                }
            }
        });
    }

    private void enableMyLocation(GoogleMap map) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation(mMap);
                    break;
                }
        }
    }

    private void setPoiClick(GoogleMap map) {
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                text = "poi click";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                Marker poiMarker = mMap.addMarker(new MarkerOptions().position(poi.latLng).title(poi.name));
                poiMarker.showInfoWindow();
                poiMarker.setTag("poi");
            }
        });
    }

    private void setMapLongClick(final GoogleMap map) {
            map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    text = "long click";
                    //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                    map.addMarker(new MarkerOptions().position(latLng));
                }
            });
        }

    @Override
    public void onSuccess(JSONObject response) {
        Store s = Store.getStore(response);
        Store.addStoreToList(s);
    }

    @Override
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
    public void onError(VolleyError error) {
        text = "error: " + error.toString();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}


////package com.example.project309.app.uiDelivery.map;
////
////import android.Manifest;
////import android.content.Context;
////import android.content.Intent;
////import android.content.pm.PackageManager;
////import android.os.Bundle;
////import android.widget.Button;
////import android.widget.Toast;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.core.app.ActivityCompat;
////import androidx.core.content.ContextCompat;
////
////import com.android.volley.VolleyError;
////import com.example.project309.R;
////import com.example.project309.app.AppController;
////import com.example.project309.app.JSONHandlerInter;
////import com.example.project309.app.LoginActivity;
////import com.example.project309.app.MainNavigationScreenDelivery;
////import com.example.project309.app.MessageBoxInter;
////import com.example.project309.app.PointLocation;
////import com.example.project309.app.Store;
////import com.example.project309.app.ViewListenerInter;
////import com.example.project309.net_utils.Const;
////import com.google.android.gms.maps.CameraUpdateFactory;
////import com.google.android.gms.maps.GoogleMap;
////import com.google.android.gms.maps.OnMapReadyCallback;
////import com.google.android.gms.maps.StreetViewPanoramaOptions;
////import com.google.android.gms.maps.SupportMapFragment;
////import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
////import com.google.android.gms.maps.model.LatLng;
////import com.google.android.gms.maps.model.Marker;
////import com.google.android.gms.maps.model.MarkerOptions;
////import com.google.android.gms.maps.model.PointOfInterest;
////
////import org.json.JSONArray;
////import org.json.JSONException;
////import org.json.JSONObject;
////
////import java.util.ArrayList;
////
////public class MapsActivityDelivery extends AppCompatActivity implements ViewListenerInter, OnMapReadyCallback {
////
////    private GoogleMap mMap;
////    private static final int REQUEST_LOCATION_PERMISSION = 1;
////    private ArrayList<Store> aStores;
////    private JSONHandlerInter jsonH;
////    Context context = getApplicationContext();
////    CharSequence text;
////    Toast toast;
////    int duration = Toast.LENGTH_SHORT;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.fragment_map_delivery);
////        jsonH = AppController.getInstance().getJSONHandlerInstance();
////        jsonH.setListener(this);
////        aStores = new ArrayList<>();
////        //if(Store.allStores == null) {
////            loadStores();
////        //} else{
////        //    aStores = Store.allStores;
////        //}
////        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
////        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nav_map);
////        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
////        getSupportFragmentManager().beginTransaction().add(R.id.nav_map_delivery, mapFragment).commit();
////        mapFragment.getMapAsync(this);
////    }
////
////    private void loadStores() {
////       // jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);
////
////        Store clydes = new Store("Clyde's!!!", new LatLng(42.024983, -93.651205));
////        aStores.add(clydes);
////
////        Store windows = new Store("Windows!!!", new LatLng(42.024181, -93.650143));
////        aStores.add(windows);
////    }
////
////    @Override
////    public void onMapReady(GoogleMap googleMap) {
////        mMap = googleMap;
////        // Add a marker at the campanile and move the camera
////        LatLng campanile = new LatLng(42.025408, -93.646074);
////        float zoom = 17;
////
////        mMap.addMarker(new MarkerOptions().position(campanile).title("Campanile"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campanile, zoom));
////
////        for (int i =0; i<aStores.size(); i++){
////            mMap.addMarker(new MarkerOptions().position(aStores.get(i).getLatLong()).title(aStores.get(i).getName()));
////        }
////
////        setMapLongClick(mMap); // Set a long click listener for the map;
////        setPoiClick(mMap); // Set a click listener for points of interest.
////        enableMyLocation(mMap); // Enable location tracking.
////        // Enable going into StreetView by clicking on an InfoWindow from a
////        // point of interest.
////        //setInfoWindowClickToHome(mMap);
////
////        // Setting a custom info window adapter for the google map
////        MarkerInfoWindowAdapterDelivery markerInfoWindowAdapterDelivery = new MarkerInfoWindowAdapterDelivery(getApplicationContext());
////        googleMap.setInfoWindowAdapter(markerInfoWindowAdapterDelivery);
////
////        // Adding and showing marker when the map is touched
//////        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
////////            @Override
////////            public void onMapClick(LatLng arg0) {
////////                MarkerOptions markerOptions = new MarkerOptions();
////////                markerOptions.position(arg0);
////////                mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
////////                Marker marker = mMap.addMarker(markerOptions);
////////                marker.showInfoWindow();
////////            }
////////        });
////
////        setInfoWindowClickToPanorama(mMap);
////    }
////
////    private void setInfoWindowClickToPanorama(GoogleMap map) {
////        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
////                    @Override
////                    public void onInfoWindowClick(Marker marker) {
////                        if (marker.getTag() == "poi") {
////                            // Set the position to the position of the marker
////                            StreetViewPanoramaOptions options =
////                                    new StreetViewPanoramaOptions().position(
////                                            marker.getPosition());
////
////                            SupportStreetViewPanoramaFragment streetViewFragment
////                                    = SupportStreetViewPanoramaFragment
////                                    .newInstance(options);
////
////                            // Replace the fragment and add it to the backstack
////                            getSupportFragmentManager().beginTransaction()
////                                    .replace(R.id.nav_map_delivery,
////                                            streetViewFragment)
////                                    .addToBackStack(null).commit();
////                        }
////                    }
////                });
////    }
////
////    private void enableMyLocation(GoogleMap map) {
////        if (ContextCompat.checkSelfPermission(this,
////                Manifest.permission.ACCESS_FINE_LOCATION)
////                == PackageManager.PERMISSION_GRANTED) {
////            map.setMyLocationEnabled(true);
////        } else {
////            ActivityCompat.requestPermissions(this, new String[]
////                            {Manifest.permission.ACCESS_FINE_LOCATION},
////                    REQUEST_LOCATION_PERMISSION);
////        }
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode,
////                                           @NonNull String[] permissions,
////                                           @NonNull int[] grantResults) {
////        // Check if location permissions are granted and if so enable the
////        // location data layer.
////        switch (requestCode) {
////            case REQUEST_LOCATION_PERMISSION:
////                if (grantResults.length > 0
////                        && grantResults[0]
////                        == PackageManager.PERMISSION_GRANTED) {
////                    enableMyLocation(mMap);
////                    break;
////                }
////        }
////    }
////
////    private void setPoiClick(GoogleMap map) {
////            map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
////                @Override
////                public void onPoiClick(PointOfInterest poi) {
////                    Marker poiMarker = mMap.addMarker(new MarkerOptions()
////                            .position(poi.latLng)
////                            .title(poi.name));
////
//////                    int a = aStores.size();
//////                    String b = Integer.toString(a);
//////                    text = ((CharSequence) b);
//////                    toast = Toast.makeText(context, text, duration);
////                    poiMarker.showInfoWindow();
////                    poiMarker.setTag("poi");
////                }
////            });
////        }
////
////        private void setMapLongClick(final GoogleMap map) {
////            map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
////                @Override
////                public void onMapLongClick(LatLng latLng) {
////                    map.addMarker(new MarkerOptions().position(latLng));
////                }
////            });
////        }
////
////    @Override
////    public void onSuccess(JSONObject response) {
////        Store s = Store.getStore(response);
////        Store.addStoreToList(s);
////    }
////
////    @Override
////    public void onSuccess(JSONArray response) {
////        for(int i = 0; i<response.length();i++){
////            try {
////                onSuccess(response.getJSONObject(i));
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////        }
////        aStores = Store.allStores;
////    }
////
////    @Override
////    public void onSuccess(String response) {
//////        text = "Success: " + response.toString();
//////        toast = Toast.makeText(context, text, duration);
//////        toast.show();
//////        return;
////    }
////
////    @Override
////    public void onError(VolleyError error) {
//////      Intent intent = new Intent(MapsActivityDelivery.this, MainNavigationScreenDelivery.class);
//////      startActivity(intent);
//////        text = "Error: " + error.toString();
//////        toast = Toast.makeText(context, text, duration);
//////        toast.show();
//////        return;
////    }
////}