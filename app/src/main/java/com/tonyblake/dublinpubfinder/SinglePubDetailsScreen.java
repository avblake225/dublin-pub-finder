package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

public class SinglePubDetailsScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient client;

    private Context context;

    private Toolbar actionBar;

    private String name, address, description, place_ID;
    private float rating;
    private Bitmap image;

    private LinearLayout single_pub_details_container;

    private PubLayout pubLayout;

    private TextView findOnMapButton;
    private TextView addToFavouritesButton;

    private String favourite;
    private boolean addToFavourites;
    private String add;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_pub_details_screen);

        dbManager = HomeScreen.dbManager;

        context = this;

        // Set up Action Bar
        actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationIcon(context.getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        actionBar.setTitleTextColor(context.getResources().getColor(R.color.white));

        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        client.connect();

        savedInstanceState = getIntent().getExtras();
        place_ID = savedInstanceState.getString("place_ID");
        name = savedInstanceState.getString("name");
        address = savedInstanceState.getString("address");
        rating = savedInstanceState.getFloat("rating");
        image = savedInstanceState.getParcelable("image");
        description = savedInstanceState.getString("description");
        favourite = savedInstanceState.getString("favourite");

        single_pub_details_container = (LinearLayout)findViewById(R.id.single_pub_details_container);

        pubLayout = new PubLayout(context,single_pub_details_container);

        pubLayout.setPubName(name);
        pubLayout.setPubAddress(address);

        pubLayout.setPubRating(rating, Integer.valueOf(context.getString(R.string.num_stars)),
                Float.valueOf(context.getString(R.string.rating_step)));

        pubLayout.setPubImage(image, context);


        pubLayout.setPubDescription("Some description");

        pubLayout.attachToParent();

        findOnMapButton = pubLayout.getFindOnMapButton();
        addToFavouritesButton = pubLayout.getAddToFavouritesButton();

        if(context.getString(R.string.no).equals(favourite)){

            addToFavouritesButton.setText(context.getString(R.string.add_to_favourites));

            addToFavourites = true;

            add = context.getString(R.string.yes);
        }
        else{

            addToFavouritesButton.setText(context.getString(R.string.remove_from_favourites));

            addToFavourites = false;

            add = context.getString(R.string.no);
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        actionBar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        findOnMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                launchMapScreen(name, place_ID);
            }
        });

        addToFavouritesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String query = context.getString(R.string.update) + " " + dbManager.getTableName()
                            + " " + context.getString(R.string.set_favourite_qual_to) + "'" + add
                            + "'" + context.getString(R.string.where_placeID_equals) + "'" + place_ID + "';";

                try{
                    dbManager.execQuery(query);

                    if(addToFavourites){

                        addToFavouritesButton.setText(context.getString(R.string.remove_from_favourites));

                        showToastMessage(name + " " + context.getString(R.string.has_been_added_to_favourites));

                        addToFavourites = false;
                    }
                    else{

                        addToFavouritesButton.setText(context.getString(R.string.add_to_favourites));

                        showToastMessage(name + " " + context.getString(R.string.has_been_removed_from_favourites));

                        addToFavourites = true;
                    }
                }
                catch(Exception e){

                    showToastMessage(context.getString(R.string.error_adding_to_favourites));
                }
            }
        });
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
