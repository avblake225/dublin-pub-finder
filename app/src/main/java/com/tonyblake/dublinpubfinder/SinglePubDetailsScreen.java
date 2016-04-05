package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

public class SinglePubDetailsScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient client;

    private Context context;

    private String name, address, description, place_ID;
    private int rating_resource_ID;

    private LinearLayout single_pub_details_container;

    private PubLayout pub;

    private Button map_button;

    private Bitmap downloadedPhoto;
    private int downloadedPhoto_width, downloadedPhoto_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_pub_details_screen);

        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        client.connect();

        context = this;

        downloadedPhoto_width = 300;
        downloadedPhoto_height = 300;

        savedInstanceState = getIntent().getExtras();
        name = savedInstanceState.getString("name");
        address = savedInstanceState.getString("address");
        description = savedInstanceState.getString("description");
        place_ID = savedInstanceState.getString("place_ID");
        rating_resource_ID = savedInstanceState.getInt("rating_resource_ID");

        single_pub_details_container = (LinearLayout)findViewById(R.id.single_pub_details_container);

        pub = new PubLayout(context, single_pub_details_container);

        pub.setPubName(name);
        pub.setPubAddress(address);

        Drawable pub_rating = context.getResources().getDrawable(rating_resource_ID);
        pub.setPubRating(pub_rating);

        setPubImage(place_ID);

        pub.setPubDescription(description);

        pub.attachToParent();

        map_button = pub.getMapButton(name);
    }

    @Override
    public void onResume(){
        super.onResume();

        map_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                launchMapScreen(name, place_ID);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        client.disconnect();
    }

    private void launchMapScreen(String name, String place_ID){
        Intent intent = new Intent(this, MapScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("place_ID", place_ID);
        startActivity(intent);
    }

    private void setPubImage(String placeId) {

        // Create a new AsyncTask that displays the bitmap once loaded.
        new PhotoTask(downloadedPhoto_width, downloadedPhoto_height, client) {

            @Override
            protected void onPostExecute(AttributedPhoto attributedPhoto) {

                if (attributedPhoto != null) {

                    downloadedPhoto = attributedPhoto.bitmap;

                    pub.setPubImage(downloadedPhoto);
                }
            }
        }.execute(placeId);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
