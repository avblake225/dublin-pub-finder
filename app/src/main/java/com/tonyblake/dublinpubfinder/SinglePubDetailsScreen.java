package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SinglePubDetailsScreen extends AppCompatActivity {

    private Context context;

    private String name, address, description, latitude, longitude;

    private LinearLayout single_pub_details_container;

    private PubLayout pub;

    private Button map_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_pub_details_screen);

        context = this;

        savedInstanceState = getIntent().getExtras();
        name = savedInstanceState.getString("name");
        address = savedInstanceState.getString("address");
        description = savedInstanceState.getString("description");
        latitude = savedInstanceState.getString("latitude");
        longitude = savedInstanceState.getString("longitude");

        single_pub_details_container = (LinearLayout)findViewById(R.id.single_pub_details_container);

        pub = new PubLayout(context, single_pub_details_container);

        pub.setPubName(name);
        pub.setPubAddress(address);

        Drawable pub_rating = Utilities.getPubRating(name, context);
        pub.setPubRating(pub_rating);

        //pub.setPubImage(pub_pic);

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

                launchMapScreen(name, latitude, longitude);
            }
        });
    }

    private void launchMapScreen(String name, String latitude, String longitude){
        Intent intent = new Intent(this, MapScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }
}
