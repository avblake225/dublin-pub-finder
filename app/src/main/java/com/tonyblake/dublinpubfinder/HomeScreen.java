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

    private Button btn_findpub, btn_findpubbyname;

    private String query;

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

        btn_findpubbyname = (Button) findViewById(R.id.btn_findpubbyname);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (noSelectionMade()) {

                    showToastMessage(context.getString(R.string.please_make_a_selection));
                }
                else if (cb_traditional_irish_pub.isChecked() && cb_modern_pub.isChecked()) {

                    showToastMessage(context.getString(R.string.invalid_selection_traditional_modern_pub));
                }
                else if (cb_pub_on_north_side_of_city.isChecked() && cb_pub_on_south_side_of_city.isChecked()) {

                    showToastMessage(context.getString(R.string.invalid_selection_pub_on_north_side_south_side));
                }
                else {

                    query = context.getString(R.string.select_all_rows_from) + MainActivity.dbManager.getTableName()
                            + context.getString(R.string.where) + getPubTypeSelection() + getSideOfCitySelection()
                            + getLiveMusicSelection() + getLiveSportsSelection() + getCocktailsSelection()
                            + getCraftBeerSelection() + getLatePubSelection() + context.getString(R.string.end_query);

                    try {
                        Cursor res = MainActivity.dbManager.getPubs(query);

                        pubs_found = new ArrayList<Pub>();

                        res.moveToFirst();

                        do {
                            Pub pub = new Pub();
                            pub.name = res.getString(1);
                            pub.address = res.getString(2);
                            pub.description = res.getString(3);
                            pub.latitude = res.getString(4);
                            pub.longitude = res.getString(5);
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

        btn_findpubbyname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                launchFindPubByNameScreen();

            }
        });
    }

    private String getPubTypeSelection(){

        if(cb_traditional_irish_pub.isChecked()) return context.getString(R.string.pub_type_traditional_irish);

        else if(cb_modern_pub.isChecked()) return context.getString(R.string.pub_type_modern);

        else return context.getString(R.string.pub_type_traditional_irish_or_modern);
    }

    private String getSideOfCitySelection(){

        if(cb_pub_on_north_side_of_city.isChecked()) return context.getString(R.string.north_side_of_city);

        else if(cb_pub_on_south_side_of_city.isChecked()) return context.getString(R.string.south_side_of_city);

        else return context.getString(R.string.north_or_south_side_of_city);
    }

    private String getLiveMusicSelection(){

        if(cb_pub_with_live_music.isChecked()) return context.getString(R.string.live_music_yes);

        else return context.getString(R.string.live_music_yes_or_no);
    }

    private String getLiveSportsSelection(){

        if(cb_pub_with_live_sports.isChecked()) return context.getString(R.string.live_sports_yes);

        else return context.getString(R.string.live_sports_yes_or_no);
    }

    private String getCocktailsSelection(){

        if(cb_pub_that_serves_cocktails.isChecked()) return context.getString(R.string.cocktails_yes);

        else return context.getString(R.string.cocktails_yes_or_no);
    }

    private String getCraftBeerSelection(){

        if(cb_pub_that_serves_craft_beer.isChecked()) return context.getString(R.string.craft_beer_yes);

        else return context.getString(R.string.craft_beer_yes_or_no);
    }

    private String getLatePubSelection(){

        if(cb_pub_that_stays_open_late.isChecked()) return context.getString(R.string.late_pub_yes);

        else return context.getString(R.string.late_pub_yes_or_no);
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

    private void launchFindPubByNameScreen() {

        Intent intent = new Intent(this, FindPubByNameScreen.class);
        startActivity(intent);
    }

    private boolean noSelectionMade(){

        boolean no_selection_made = false;

        if(!cb_traditional_irish_pub.isChecked() && !cb_modern_pub.isChecked()
                && !cb_pub_on_north_side_of_city.isChecked() && !cb_pub_on_south_side_of_city.isChecked()
                && !cb_pub_with_live_music.isChecked() && !cb_pub_with_live_sports.isChecked()
                && !cb_pub_that_serves_cocktails.isChecked() && !cb_pub_that_serves_craft_beer.isChecked()
                && !cb_pub_that_stays_open_late.isChecked()){

            no_selection_made = true;
        }

        return no_selection_made;
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private class Pub{

        private String name;
        private String address;
        private String description;
        private String latitude;
        private String longitude;
    }
}
