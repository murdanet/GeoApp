package com.example.geoapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.geoapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Button btnMoveCamera = findViewById(R.id.my_button);
        btnMoveCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LatLng punto1= new LatLng(10.4965259,-66.8562321);
                mMap.addMarker(new MarkerOptions().position(punto1).title("Caracas-Venezuela"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto1, 15));
            }
        });

        final LatLng punto1= new LatLng(10.4965259,-66.8562321);
        mMap.addMarker(new MarkerOptions().position(punto1).title("Caracas-Venezuela"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto1, 15));
    }

    }