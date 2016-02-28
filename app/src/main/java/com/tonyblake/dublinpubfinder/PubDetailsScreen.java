package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class PubDetailsScreen extends Activity{

    private Context context;
    private String[] name, address, latitude, longitude;
    private LinearLayout pub_details_container;
    private PubLayout pub;
    private ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_details_screen);

        context = this;

        savedInstanceState = getIntent().getExtras();
        name = savedInstanceState.getStringArray("name");
        address = savedInstanceState.getStringArray("address");
        latitude = savedInstanceState.getStringArray("latitude");
        longitude = savedInstanceState.getStringArray("longitude");

        Utilities.populatePubImageMap(context);

        pub_details_container = (LinearLayout)findViewById(R.id.pub_details_container);

        buttons = new ArrayList<Button>();

        for(int i=0;i<name.length;i++){

            pub = new PubLayout(context, pub_details_container);

            pub.setPubName(name[i]);
            pub.setPubAddress(address[i]);

            Drawable pub_pic = Utilities.getPubImage(name[i],context);
            pub.setPubImage(pub_pic);

            pub.attachToParent();

            buttons.add(pub.getMapButton());
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        for(int i=0;i<buttons.size();i++){

            buttons.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //launchMapScreen();
                    showToastMessage("testing");
                }
            });
        }
    }

    private void launchMapScreen(){
        Intent intent = new Intent(this, MapScreen.class);
        startActivity(intent);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
