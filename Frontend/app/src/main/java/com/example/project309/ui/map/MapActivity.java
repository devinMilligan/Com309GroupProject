package com.example.project309.ui.map;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.project309.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

//public class MapActivity
//
//        extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.fragment_map);
//            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//            .findFragmentById(R.id.map);
//            mapFragment.getMapAsync(this);
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//            mMap = googleMap;
//
//            // Add a marker in Sydney and move the camera
//            LatLng sydney = new LatLng(-34, 151);
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//}


//MapFragment extends Fragment {
//
//    private MapModel mapModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        mapModel =
//                ViewModelProviders.of(this).get(MapModel.class);
//        View root = inflater.inflate(R.layout.fragment_map, container, false);
//        final TextView textView = root.findViewById(R.id.text_map);
//        mapModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }
//}