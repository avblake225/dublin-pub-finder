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
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;

public class PubListScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    public static GoogleApiClient client;

    private Context context;
    private String[] name, address, description, place_ID;
    private int[] rating_resource_ID;
    private int num_pubs_returned;
    private TextView tv_num_pubs_returned;
    private LinearLayout pub_details_container;
    private PubLayout pub;
    public static String placeId;
    private Bitmap downloadedPhoto;
    private int downloadedPhoto_width;
    private int downloadedPhoto_height;
    private ArrayList<PubLayout> pubs;
    private ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_details_screen);

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

        savedInstanceState = getIntent().getExtras();
        name = savedInstanceState.getStringArray("name");
        address = savedInstanceState.getStringArray("address");
        description = savedInstanceState.getStringArray("description");
        place_ID = savedInstanceState.getStringArray("place_ID");
        rating_resource_ID = savedInstanceState.getIntArray("rating_resource_ID");

        num_pubs_returned = name.length;

        tv_num_pubs_returned = (TextView)findViewById(R.id.tv_num_pubs_found);

        String num_pubs_returned_str;

        if(num_pubs_returned == 1){
            num_pubs_returned_str = num_pubs_returned + " " + context.getString(R.string.pub_found);
        }
        else{
            num_pubs_returned_str = num_pubs_returned + " " + context.getString(R.string.pubs_found);
        }

        tv_num_pubs_returned.setText(num_pubs_returned_str);

        pub_details_container = (LinearLayout)findViewById(R.id.pub_details_container);

        pubs = new ArrayList<PubLayout>();
        buttons = new ArrayList<Button>();

        downloadedPhoto_width = 300;
        downloadedPhoto_height = 300;

        for(int i=0;i<num_pubs_returned;i++){

            pub = new PubLayout(context, pub_details_container);

            pub.setPubName(name[i]);
            pub.setPubAddress(address[i]);

            int pub_rating_ID = rating_resource_ID[i];
            Drawable pub_rating = context.getResources().getDrawable(pub_rating_ID);
            pub.setPubRating(pub_rating);

            setPubImage(i,place_ID[i]);

            pub.setPubDescription(description[i]);

            pub.attachToParent();

            pubs.add(pub);
            buttons.add(pub.getMapButton(name[i]));
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        client.disconnect();
    }

    @Override
    public void onResume(){
        super.onResume();

        for(int i=0;i<buttons.size();i++){

            final int j = i;

            buttons.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    launchMapScreen(name[j], place_ID[j]);
                }
            });
        }
    }

    private void launchMapScreen(String name, String place_ID){
        Intent intent = new Intent(this, MapScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("place_IF", place_ID);
        startActivity(intent);
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

    private void setPubImage(final int pubIndex, String placeId) {

        // Create a new AsyncTask that displays the bitmap once loaded.
        new PhotoTask(downloadedPhoto_width, downloadedPhoto_height) {

            @Override
            protected void onPostExecute(AttributedPhoto attributedPhoto) {

                if (attributedPhoto != null) {

                    downloadedPhoto = attributedPhoto.bitmap;

                    pubs.get(pubIndex).setPubImage(downloadedPhoto);
                }
            }
        }.execute(placeId);
    }
}
