package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;

public class HomeScreen extends FragmentActivity implements SearchDialog.SearchDialogListener,
                                                 GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private Context context;

    private boolean traditional_irish_pub, modern_pub;
    private boolean north_side_of_city, south_side_of_city;
    private boolean live_music, live_sports, cocktails, craft_beer, late_pub;

    private String query;

    private ArrayList<Pub> pubs_found;

    private int num_pubs_found;

    private SearchDialog searchDialog;

    private ArrayList<Integer> options_selected;

    private String pub_name_entered;

    private GoogleApiClient client;

    private Bitmap downloadedPhoto;
    private int downloadedPhoto_width;
    private int downloadedPhoto_height;
    private ArrayList<PubLayout> pubs;
    private ArrayList<Button> buttons;

    private TextView tv_num_pubs_found;
    private LinearLayout pub_details_container;
    private PubLayout pub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        context = this;

        clearAllSelections();

        tv_num_pubs_found = (TextView)findViewById(R.id.tv_num_pubs_found);

        pub_details_container = (LinearLayout) findViewById(R.id.pub_details_container);

        pubs_found = new ArrayList<>();

        pubs = new ArrayList<>();

        buttons = new ArrayList<>();

        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        client.connect();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                searchDialog = new SearchDialog();
                searchDialog.show(fm, "search_dialog_fragment");


            }
        });

        findViewById(R.id.btn_search_by_name).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SearchByNameDialog.class);
                startActivity(intent);
            }
        });

        for(int i=0;i<buttons.size();i++){

            final int j = i;

            buttons.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    launchMapScreen(pubs_found.get(j).name, pubs_found.get(j).place_ID);
                }
            });
        }
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

    private void search(){

        if (noSelectionMade()) {

            showToastMessage(context.getString(R.string.please_make_a_selection));
        }

        query = context.getString(R.string.select_all_rows_from) + MainActivity.dbManager.getTableName()
                            + context.getString(R.string.where) + getPubTypeSelection() + getSideOfCitySelection()
                            + getLiveMusicSelection() + getLiveSportsSelection() + getCocktailsSelection()
                            + getCraftBeerSelection() + getLatePubSelection() + context.getString(R.string.end_query);

        if(pubs_found.size() != 0){

            pubs_found.removeAll(pubs_found);
            pub_details_container.removeAllViews();
        }

        try {
            Cursor res = MainActivity.dbManager.getPubs(query);

            res.moveToFirst();

            do {
                Pub pub = new Pub();
                pub.name = res.getString(1);
                pub.address = res.getString(2);
                pub.description = res.getString(3);
                pub.place_ID = res.getString(4);
                pub.rating_resource_ID = res.getInt(5);
                pubs_found.add(pub);

            } while (res.moveToNext());

            num_pubs_found = pubs_found.size();

            displayPubs();

        } catch (Exception e) {
            showToastMessage(context.getString(R.string.no_pubs_match_your_search));
        }
    }

    private void displayPubs(){

        downloadedPhoto_width = 300;
        downloadedPhoto_height = 300;

        for(int i=0;i<num_pubs_found;i++){

            pub = new PubLayout(context, pub_details_container);

            pub.setPubName(pubs_found.get(i).name);
            pub.setPubAddress(pubs_found.get(i).address);

            Drawable pub_rating = context.getResources().getDrawable(pubs_found.get(i).rating_resource_ID);
            pub.setPubRating(pub_rating);

            setPubImage(i,pubs_found.get(i).place_ID);

            pub.setPubDescription(pubs_found.get(i).description);

            pub.attachToParent();

            pubs.add(pub);
            buttons.add(pub.getMapButton(pubs_found.get(i).name));
        }

        String num_pubs_returned_str;

        if(num_pubs_found == 1){
            num_pubs_returned_str = num_pubs_found + " " + context.getString(R.string.pub_found);
        }
        else{
            num_pubs_returned_str = num_pubs_found + " " + context.getString(R.string.pubs_found);
        }

        tv_num_pubs_found.setText(num_pubs_returned_str);
    }

    private void setPubImage(final int pubIndex, String placeId) {

        // Create a new AsyncTask that displays the bitmap once loaded.
        new PhotoTask(downloadedPhoto_width, downloadedPhoto_height, client) {

            @Override
            protected void onPostExecute(AttributedPhoto attributedPhoto) {

                if (attributedPhoto != null) {

                    downloadedPhoto = attributedPhoto.bitmap;

                    pubs.get(pubIndex).setPubImage(downloadedPhoto);
                }
            }
        }.execute(placeId);
    }

    private String getPubTypeSelection(){

        if(traditional_irish_pub) return context.getString(R.string.pub_type_traditional_irish);

        else if(modern_pub) return context.getString(R.string.pub_type_modern);

        else return context.getString(R.string.pub_type_traditional_irish_or_modern);
    }

    private String getSideOfCitySelection(){

        if(north_side_of_city) return context.getString(R.string.north_side_of_city);

        else if(south_side_of_city) return context.getString(R.string.south_side_of_city);

        else return context.getString(R.string.north_or_south_side_of_city);
    }

    private String getLiveMusicSelection(){

        if(live_music) return context.getString(R.string.live_music_yes);

        else return context.getString(R.string.live_music_yes_or_no);
    }

    private String getLiveSportsSelection(){

        if(live_sports) return context.getString(R.string.live_sports_yes);

        else return context.getString(R.string.live_sports_yes_or_no);
    }

    private String getCocktailsSelection(){

        if(cocktails) return context.getString(R.string.cocktails_yes);

        else return context.getString(R.string.cocktails_yes_or_no);
    }

    private String getCraftBeerSelection(){

        if(craft_beer) return context.getString(R.string.craft_beer_yes);

        else return context.getString(R.string.craft_beer_yes_or_no);
    }

    private String getLatePubSelection(){

        if(late_pub) return context.getString(R.string.late_pub_yes);

        else return context.getString(R.string.late_pub_yes_or_no);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private boolean noSelectionMade(){

        boolean no_selection_made = false;

        if(!traditional_irish_pub && !modern_pub
                && !north_side_of_city && !south_side_of_city
                && !live_music && !live_sports
                && !cocktails && !craft_beer
                && !late_pub){

            no_selection_made = true;
        }

        return no_selection_made;
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onSearchDialogSearchClick(DialogFragment dialog) {

        options_selected = SearchDialog.search_options;

        for(int option : options_selected){

            switch(option){

                case 0:
                    traditional_irish_pub = true;
                    break;

                case 1:
                    modern_pub = true;
                    break;

                case 2:
                    north_side_of_city = true;
                    break;

                case 3:
                    south_side_of_city = true;
                    break;

                case 4:
                    live_music = true;
                    break;

                case 5:
                    live_sports = true;
                    break;

                case 6:
                    cocktails = true;
                    break;

                case 7:
                    craft_beer = true;
                    break;

                case 8:
                    late_pub = true;
                    break;
            }
        }

        search();
    }

    private void clearAllSelections(){

        traditional_irish_pub = false;
        modern_pub = false;
        north_side_of_city = false;
        south_side_of_city = false;
        live_music = false;
        live_sports = false;
        cocktails = false;
        craft_beer = false;
        late_pub = false;
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
