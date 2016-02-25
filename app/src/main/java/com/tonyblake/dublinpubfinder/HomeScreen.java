package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    private Context context;
    private DBManager dbManager;
    private RadioButton traditional_pub, modern_pub;
    private RadioButton north_side, south_side;
    private RadioButton no_to_live_music, yes_to_live_music;
    private RadioButton no_to_late_pub, yes_to_late_pub;
    private RadioButton no_to_craft_beer, yes_to_craft_beer;
    private Button btn_findpub;

    private String name, address;

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

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                checkIfAllSelectionsMade();

                int selection_code = getSelectionCode();

                String query = Utilities.getDBQuery(selection_code);

                String dummy = "dummy string";

                /*try {
                    Cursor res = dbManager.getPub(name_chosen);

                    while (res.moveToNext()) {
                        name = res.getString(1);
                        address = res.getString(2);
                        break;
                    }

                    if (name != null) {

                        launchPubDetailsScreen();

                    } else {
                        showToastMessage(context.getString(R.string.pubnotfound));
                    }
                } catch (Exception e) {
                    showToastMessage(context.getString(R.string.errorfindingpub));
                }*/
            }
        });
    }

    private void launchPubDetailsScreen() {
        Intent intent = new Intent(this, PubDetailsScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
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
}
