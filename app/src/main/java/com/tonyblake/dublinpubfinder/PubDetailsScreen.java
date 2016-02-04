package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PubDetailsScreen extends Activity{

    private Context context;
    private String name;
    private String address;
    private String directions;

    private TextView tv_name;
    private ImageView iv_pub;
    private TextView tv_address;
    private TextView tv_directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_details_screen);

        context = this;

        savedInstanceState = getIntent().getExtras();
        name = savedInstanceState.getString("name");
        address = savedInstanceState.getString("address");
        directions = savedInstanceState.getString("directions");

        tv_name = (TextView)findViewById(R.id.tv_name);
        iv_pub = (ImageView)findViewById(R.id.iv_pub);

        Utilities.populatePubImageMap(context);

        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_directions = (TextView)findViewById(R.id.tv_directions);
    }

    @Override
    public void onResume(){
        super.onResume();

        tv_name.setText(name);

        Drawable pub_pic = Utilities.getPubImage(name,context);

        iv_pub.setImageDrawable(pub_pic);
        tv_address.setText(address);
        tv_directions.setText(directions);
    }
}
