package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    private Context context;
    private DBManager dbManager;
    private RadioButton traditional_pub, modern_pub;
    private RadioButton north_side, south_side;
    private RadioButton no_to_live_music, yes_to_live_music;
    private RadioButton no_to_late_pub, yes_to_late_pub;
    private RadioButton no_to_craft_beer, yes_to_craft_beer;
    private Button btn_findpub;

    private String name, address, description, side_of_city, latitude, longitude, pub_type, live_music, craft_beer, late_pub;

    private ArrayList<Pub> pubs_found;

    private int num_pubs_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        context = this;

        dbManager = new DBManager(this);

        Utilities.populatePubImageMap(context);
        Utilities.populateSelectionCodeMap();
        Utilities.populateDBQueryMap(dbManager.getTableName());

        traditional_pub =(RadioButton)findViewById(R.id.radiobutton_traditional);
        modern_pub =(RadioButton)findViewById(R.id.radiobutton_modern);

        north_side =(RadioButton)findViewById(R.id.radiobutton_northside);
        south_side =(RadioButton)findViewById(R.id.radiobutton_southside);

        no_to_live_music =(RadioButton)findViewById(R.id.radiobutton_notolivemusic);
        yes_to_live_music =(RadioButton)findViewById(R.id.radiobutton_yestolivemusic);

        no_to_late_pub =(RadioButton)findViewById(R.id.radiobutton_notolatepub);
        yes_to_late_pub =(RadioButton)findViewById(R.id.radiobutton_yestolatepub);

        no_to_craft_beer =(RadioButton)findViewById(R.id.radiobutton_notocraftbeer);
        yes_to_craft_beer =(RadioButton)findViewById(R.id.radiobutton_yestocraftbeer);

        btn_findpub = (Button) findViewById(R.id.btn_findpub);
    }

    @Override
    protected void onResume() {
        super.onResume();

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

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                checkIfAllSelectionsMade();

                int selection_code = getSelectionCode();

                String query = Utilities.getDBQuery(selection_code);

                try {
                    Cursor res = dbManager.getPubs(query);

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
        });
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
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void checkIfAllSelectionsMade(){

        if(!traditional_pub.isChecked() && !modern_pub.isChecked()){
            showToastMessage(context.getString(R.string.please_select_pub_type));
        }
        else if(!north_side.isChecked() && !south_side.isChecked()){
            showToastMessage(context.getString(R.string.please_select_your_location));
        }
        else if(!yes_to_live_music.isChecked() && !no_to_live_music.isChecked()){
            showToastMessage(context.getString(R.string.please_select_live_music));
        }
        else if(!yes_to_craft_beer.isChecked() && !no_to_craft_beer.isChecked()){
            showToastMessage(context.getString(R.string.please_select_craft_beer));
        }
        else if(!no_to_late_pub.isChecked() && !yes_to_late_pub.isChecked()){
            showToastMessage(context.getString(R.string.please_select_late_pub));
        }
    }

    private int getSelectionCode(){

        String bit_combination = String.valueOf(modern_pub.isChecked() ? 1 : 0)
                + String.valueOf(south_side.isChecked() ? 1 : 0)
                + String.valueOf(yes_to_live_music.isChecked() ? 1 : 0)
                + String.valueOf(yes_to_craft_beer.isChecked() ? 1 : 0)
                + String.valueOf(yes_to_late_pub.isChecked() ? 1 : 0);

        int selection_code = Utilities.getSelectionCode(bit_combination);

        return selection_code;
    }

    private class Pub{

        private String name;
        private String address;
        private String description;
        private String latitude;
        private String longitude;
    }
}
