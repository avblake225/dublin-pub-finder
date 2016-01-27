package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class PubDetailsScreen extends Activity{

    private String name;
    private String address;
    private String directions;

    TextView tv_name;
    TextView tv_address;
    TextView tv_directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_details_screen);

        savedInstanceState = getIntent().getExtras();
        name = savedInstanceState.getString("name");
        address = savedInstanceState.getString("address");
        directions = savedInstanceState.getString("directions");

        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_directions = (TextView)findViewById(R.id.tv_directions);
    }

    @Override
    public void onResume(){
        super.onResume();

        tv_name.setText(name);
        tv_address.setText(address);
        tv_directions.setText(directions);
    }
}
