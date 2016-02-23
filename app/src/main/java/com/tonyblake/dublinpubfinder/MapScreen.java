package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapScreen extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Context context;
    private GoogleApiClient client;
    private GoogleMap mMap;
    double user_latitude, user_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_location);

        context = this;

        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onStart() {
        client.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        client.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {

        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(client);

            if (mLastLocation != null) {
                user_latitude = Double.valueOf(mLastLocation.getLatitude());
                user_longitude = Double.valueOf(mLastLocation.getLongitude());
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng user_coordinates = new LatLng(user_latitude, user_longitude);

        LatLng pub_coordinates = new LatLng(53.345474, -6.264215);  // the temple bar

        CameraPosition camera_position = new CameraPosition.Builder().target(pub_coordinates)
                .zoom(17)
                .tilt(30)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera_position));

        Marker user_marker = mMap.addMarker(new MarkerOptions().position(user_coordinates)
                .title("You are here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        user_marker.showInfoWindow();

        Marker pub_marker = mMap.addMarker(new MarkerOptions().position(pub_coordinates)
                .title("The Temple Bar")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        pub_marker.showInfoWindow();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
