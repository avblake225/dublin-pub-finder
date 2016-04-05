package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapScreen extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback{

    private String name, place_ID;
    private Context context;
    private GoogleMap mMap;
    double user_latitude, user_longitude;
    private LatLng pub_coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_location);

        savedInstanceState = getIntent().getExtras();
        name = savedInstanceState.getString("name");
        place_ID = savedInstanceState.getString("place_ID");

        context = this;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onStart() {
        PubListScreen.client.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        PubListScreen.client.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {

        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(PubListScreen.client);

            if (mLastLocation != null) {
                user_latitude = Double.valueOf(mLastLocation.getLatitude());
                user_longitude = Double.valueOf(mLastLocation.getLongitude());
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng user_coordinates = new LatLng(user_latitude, user_longitude);

        Places.GeoDataApi.getPlaceById(PubListScreen.client, place_ID).setResultCallback(new ResultCallback<PlaceBuffer>() {

            @Override
            public void onResult(PlaceBuffer places) {

                if (places.getStatus().isSuccess() && places.getCount() > 0) {

                    final Place myPlace = places.get(0);

                    pub_coordinates = myPlace.getLatLng();

                    CameraPosition camera_position = new CameraPosition.Builder().target(pub_coordinates)
                            .zoom(17)
                            .tilt(30)
                            .build();

                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera_position));

                    Marker pub_marker = mMap.addMarker(new MarkerOptions().position(pub_coordinates)
                            .title(name)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                    pub_marker.showInfoWindow();
                }

                places.release();
            }
        });

        Marker user_marker = mMap.addMarker(new MarkerOptions().position(user_coordinates)
                .title(context.getString(R.string.you_are_here))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        user_marker.showInfoWindow();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
