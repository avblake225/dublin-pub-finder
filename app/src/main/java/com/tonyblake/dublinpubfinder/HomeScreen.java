package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    private Context context;

    private CheckBox cb_traditional_irish_pub;
    private CheckBox cb_modern_pub;
    private CheckBox cb_pub_on_north_side_of_city;
    private CheckBox cb_pub_on_south_side_of_city;
    private CheckBox cb_pub_with_live_music;
    private CheckBox cb_pub_with_live_sports;
    private CheckBox cb_pub_that_serves_cocktails;
    private CheckBox cb_pub_that_serves_craft_beer;
    private CheckBox cb_pub_that_stays_open_late;

    private Button btn_findpub;

    private String query;

    private String name, address, description, side_of_city, latitude, longitude, pub_type, live_music, craft_beer, late_pub;

    private ArrayList<Pub> pubs_found;

    private int num_pubs_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        context = this;

        cb_traditional_irish_pub = (CheckBox)findViewById(R.id.cb_traditional_irish_pub);
        cb_modern_pub = (CheckBox)findViewById(R.id.cb_modern_pub);
        cb_pub_on_north_side_of_city = (CheckBox)findViewById(R.id.cb_pub_on_north_side_of_city);
        cb_pub_on_south_side_of_city = (CheckBox)findViewById(R.id.cb_pub_on_south_side_of_city);
        cb_pub_with_live_music = (CheckBox)findViewById(R.id.cb_pub_with_live_music);
        cb_pub_with_live_sports = (CheckBox)findViewById(R.id.cb_pub_with_live_sports);
        cb_pub_that_serves_cocktails = (CheckBox)findViewById(R.id.cb_pub_that_serves_cocktails);
        cb_pub_that_serves_craft_beer = (CheckBox)findViewById(R.id.cb_pub_that_serves_craft_beer);
        cb_pub_that_stays_open_late = (CheckBox)findViewById(R.id.cb_pub_that_stays_open_late);

        btn_findpub = (Button) findViewById(R.id.btn_findpub);
    }

    @Override
    public void onStart(){
        super.onStart();

        name = null;
        address = null;
        description = null;
        side_of_city = null;
        latitude = null;
        longitude = null;
        pub_type = null;
        live_music = null;
        craft_beer = null;
        late_pub = null;

    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cb_traditional_irish_pub.isChecked() && cb_modern_pub.isChecked()) {

                    showToastMessage(context.getString(R.string.invalid_selection_traditional_modern_pub));
                } else if (cb_pub_on_north_side_of_city.isChecked() && cb_pub_on_south_side_of_city.isChecked()) {

                    showToastMessage(context.getString(R.string.invalid_selection_pub_on_north_side_south_side));
                } else {

                    String pub_type_selection = "";
                    String side_of_city_selection = "";
                    String live_music_and_sports_selection = "";
                    String cocktails_and_craft_beer_selection = "";
                    String late_pub_selection = ")";

                    if (cb_traditional_irish_pub.isChecked() | cb_modern_pub.isChecked()) {
                        pub_type_selection = getPubTypeSelection(context);
                    }

                    //side_of_city_selection = getSideOfCitySelection();
                    //live_music_and_sports_selection = getLiveMusicAndSportsSelection();
                    //cocktails_and_craft_beer_selection = getCocktailsAndCraftBeerSelection();
                    //late_pub_selection = getLatePubSelection();

                    query = context.getString(R.string.select_all_rows_from) + " " + MainActivity.dbManager.getTableName() + " "
                            + pub_type_selection
                            + side_of_city_selection + live_music_and_sports_selection
                            + cocktails_and_craft_beer_selection + late_pub_selection;

                    try {
                        Cursor res = MainActivity.dbManager.getPubs(query);

                        pubs_found = new ArrayList<Pub>();

                        res.moveToFirst();

                        do {
                            Pub pub = new Pub();
                            pub.name = res.getString(1);
                            pub.address = res.getString(2);
                            pub.description = res.getString(3);
                            pub.latitude = res.getString(5);
                            pub.longitude = res.getString(6);
                            pubs_found.add(pub);

                        } while (res.moveToNext());

                        num_pubs_found = pubs_found.size();

                        launchPubDetailsScreen();

                    } catch (Exception e) {
                        showToastMessage(context.getString(R.string.no_pubs_match_your_search));
                    }
                }
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private void launchPubDetailsScreen() {

        Intent intent = new Intent(this, PubDetailsScreen.class);

        String[] name = new String[num_pubs_found];
        String[] address = new String[num_pubs_found];
        String[] description = new String[num_pubs_found];
        String[] latitude = new String[num_pubs_found];
        String[] longitude = new String[num_pubs_found];

        for(int i=0;i<num_pubs_found;i++){
            name[i] = pubs_found.get(i).name;
            address[i] = pubs_found.get(i).address;
            description[i] = pubs_found.get(i).description;
            latitude[i] = pubs_found.get(i).latitude;
            longitude[i] = pubs_found.get(i).longitude;
        }
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("description", description);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);

        startActivity(intent);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private String getPubTypeSelection(Context context){

        String pub_type_selection = context.getString(R.string.where_pub_type_equals);

        if(cb_traditional_irish_pub.isChecked()){
            pub_type_selection += context.getString(R.string.traditional_irish_pub);
        }
        else{
            pub_type_selection += context.getString(R.string.modern_pub);
        }

        return pub_type_selection;
    }

    private class Pub{

        private String name;
        private String address;
        private String description;
        private String latitude;
        private String longitude;
    }
}
