package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

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

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Context context;
    DBManager dbManager;
    AutoCompleteTextView tv_qwhatpub;
    String[] pubs;
    Button btn_findpub;
    String name_entered, name_chosen, name_retrieved;
    String address;
    String directions;

    private GoogleApiClient client;
    private GoogleMap mMap;
    double user_latitude, user_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_location);

        context = this;

        dbManager = new DBManager(this);

        //tv_qwhatpub = (AutoCompleteTextView) findViewById(R.id.tv_qwhatpub);

        pubs = dbManager.getPubNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pubs);
        //tv_qwhatpub.setThreshold(1);
        //tv_qwhatpub.setAdapter(adapter);

        //btn_findpub = (Button) findViewById(R.id.btn_findpub);

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
    protected void onResume() {
        super.onResume();

        name_entered = null;
        name_chosen = null;
        name_retrieved = null;

        System.out.println("Number of pubs: " + DBManager.pub_names.length);

        //tv_qwhatpub.setText("");

        /*tv_qwhatpub.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {

                name_chosen = (String) parent.getItemAtPosition(position);
            }
        });*/

        /*btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                name_entered = tv_qwhatpub.getText().toString();

                if ("".equals(name_entered)) {
                    showToastMessage(context.getString(R.string.enter_a_pub));
                } else {
                    try {
                        Cursor res = dbManager.getPub(name_chosen);

                        while (res.moveToNext()) {
                            name_retrieved = res.getString(1);
                            address = res.getString(2);
                            directions = res.getString(3);
                            break;
                        }

                        if (name_retrieved != null) {

                            launchPubDetailsScreen();

                        } else {
                            showToastMessage(context.getString(R.string.pubnotfound));
                        }
                    } catch (Exception e) {
                        showToastMessage(context.getString(R.string.errorfindingpub));
                    }
                }
            }
        });*/
    }

    private void launchPubDetailsScreen() {
        Intent intent = new Intent(this, PubDetailsScreen.class);
        intent.putExtra("name", name_retrieved);
        intent.putExtra("address", address);
        intent.putExtra("directions", directions);
        startActivity(intent);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

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
                .zoom(10)
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
