package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PubDetailsScreen extends Activity{

    private String name;
    private String address;
    private String directions;

    private Context context;

    TextView tv_name;
    ImageView iv_pub;
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

        context = this;

        tv_name = (TextView)findViewById(R.id.tv_name);
        iv_pub = (ImageView)findViewById(R.id.iv_pub);
        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_directions = (TextView)findViewById(R.id.tv_directions);
    }

    @Override
    public void onResume(){
        super.onResume();

        tv_name.setText(name);

        Drawable pub_pic = null;

        if(context.getString(R.string.the_temple_bar).equals(tv_name.getText())){
            pub_pic = context.getResources().getDrawable(R.drawable.temple_bar);
        }

        iv_pub.setImageDrawable(pub_pic);
        tv_address.setText(address);
        tv_directions.setText(directions);
    }
}
