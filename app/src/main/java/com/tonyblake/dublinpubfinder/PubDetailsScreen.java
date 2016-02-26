package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PubDetailsScreen extends Activity{

    private Context context;
    private String[] name, address, latitude, longitude;

    private TextView tv_name;
    private ImageView iv_pub;
    private TextView tv_address;
    private Button btn_getMapLocation;

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

        tv_name = (TextView)findViewById(R.id.tv_name);
        iv_pub = (ImageView)findViewById(R.id.iv_pub);

        Utilities.populatePubImageMap(context);

        tv_address = (TextView)findViewById(R.id.tv_address);

        btn_getMapLocation = (Button)findViewById(R.id.btn_getMapLocation);
    }

    @Override
    public void onResume(){
        super.onResume();

        tv_name.setText(name[0]);

        Drawable pub_pic = Utilities.getPubImage(name[0],context);

        iv_pub.setImageDrawable(pub_pic);
        tv_address.setText(address[0]);

        btn_getMapLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //launchMapScreen();
            }
        });
    }

    private void launchMapScreen(){
        Intent intent = new Intent(this, MapScreen.class);
        startActivity(intent);
    }
}
