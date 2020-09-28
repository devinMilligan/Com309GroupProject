package com.example.project309.app.uiStore.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.project309.R;
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

public class MapsActivityStore extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private Button infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map_admin);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nav_map);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.nav_map_admin, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker at the campanile and move the camera
        LatLng campanile = new LatLng(42.025408, -93.646074);
        float zoom = 17;

        mMap.addMarker(new MarkerOptions().position(campanile).title("Campanile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campanile, zoom));

        setMapLongClick(mMap); // Set a long click listener for the map;
        setPoiClick(mMap); // Set a click listener for points of interest.
        enableMyLocation(mMap); // Enable location tracking.
        // Enable going into StreetView by clicking on an InfoWindow from a
        // point of interest.
        //setInfoWindowClickToHome(mMap);

        // Setting a custom info window adapter for the google map
        MarkerInfoWindowAdapterStore markerInfoWindowAdapterStore = new MarkerInfoWindowAdapterStore(getApplicationContext());
        googleMap.setInfoWindowAdapter(markerInfoWindowAdapterStore);

        // Adding and showing marker when the map is touched
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {
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
                                    .replace(R.id.nav_map_admin,
                                            streetViewFragment)
                                    .addToBackStack(null).commit();
                        }
                    }
                });
    }

    private void enableMyLocation(GoogleMap map) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation(mMap);
                    break;
                }
        }
    }

    private void setPoiClick(GoogleMap map) {
            map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
                @Override
                public void onPoiClick(PointOfInterest poi) {
                    Marker poiMarker = mMap.addMarker(new MarkerOptions()
                            .position(poi.latLng)
                            .title(poi.name));
                    poiMarker.showInfoWindow();
                    poiMarker.setTag("poi");
                }
            });
        }

        private void setMapLongClick(final GoogleMap map) {
            map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    map.addMarker(new MarkerOptions().position(latLng));
                }
            });
        }
}