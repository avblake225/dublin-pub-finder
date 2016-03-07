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
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(noSelectionMade()){

                    showToastMessage(context.getString(R.string.please_make_a_selection));

                }
                else if (cb_traditional_irish_pub.isChecked() && cb_modern_pub.isChecked()) {

                    showToastMessage(context.getString(R.string.invalid_selection_traditional_modern_pub));
                }
                else if (cb_pub_on_north_side_of_city.isChecked() && cb_pub_on_south_side_of_city.isChecked()) {

                    showToastMessage(context.getString(R.string.invalid_selection_pub_on_north_side_south_side));
                }
                else {

                    String pubTypeAndSideOfCity_logic_key = getLogicKey(context.getString(R.string.pubTypeAndSideOfCity));

                    String pubTypeAndSideOfCity = Utilities.getPubTypeAndSideOfCity(pubTypeAndSideOfCity_logic_key, context);

                    String extras_logic_key = getLogicKey(context.getString(R.string.extras));

                    String extras = Utilities.getExtras(extras_logic_key, context);

                    if(pubTypeAndSideOfCity_logic_key.equals(context.getString(R.string._0000))
                            && !extras_logic_key.equals(context.getString(R.string._00000))){

                        query = context.getString(R.string.select_all_rows_from) + " " + MainActivity.dbManager.getTableName()
                                + " " + context.getString(R.string.where) + " " + pubTypeAndSideOfCity + extras
                                + context.getString(R.string.end_query);
                    }
                    else if(extras_logic_key.equals(context.getString(R.string._00000))){

                        query = context.getString(R.string.select_all_rows_from) + " " + MainActivity.dbManager.getTableName()
                                + " " + context.getString(R.string.where) + " " + pubTypeAndSideOfCity + extras
                                + context.getString(R.string.end_query);
                    }
                    else{

                        query = context.getString(R.string.select_all_rows_from) + " " + MainActivity.dbManager.getTableName()
                                + " " + context.getString(R.string.where) + " " + pubTypeAndSideOfCity
                                + " " + context.getString(R.string.and) + " " + extras
                                + context.getString(R.string.end_query);
                    }

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

    private String getLogicKey(String selection){

        String logic_key = "";

        if(selection.equals(context.getString(R.string.pubTypeAndSideOfCity))){

            logic_key = String.valueOf(cb_traditional_irish_pub.isChecked() ? 1:0)
                    + String.valueOf(cb_modern_pub.isChecked() ? 1:0)
                    + String.valueOf(cb_pub_on_north_side_of_city.isChecked() ? 1:0)
                    + String.valueOf(cb_pub_on_south_side_of_city.isChecked() ? 1:0);
        }
        else if(selection.equals(context.getString(R.string.extras))){

            logic_key = String.valueOf(cb_pub_with_live_music.isChecked() ? 1:0)
                    + String.valueOf(cb_pub_with_live_sports.isChecked() ? 1:0)
                    + String.valueOf(cb_pub_that_serves_cocktails.isChecked() ? 1:0)
                    + String.valueOf(cb_pub_that_serves_craft_beer.isChecked() ? 1:0)
                    + String.valueOf(cb_pub_that_stays_open_late.isChecked() ? 1:0);
        }

        return logic_key;
    }

    private class Pub{

        private String name;
        private String address;
        private String description;
        private String latitude;
        private String longitude;
    }
}
