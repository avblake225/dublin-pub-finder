package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PubLayout {

    private Context context;
    private LinearLayout parent_layout;
    private LinearLayout pub_layout;
    private TextView tv_name;
    private ImageView iv_pub;
    private TextView tv_address;
    private Button btn_getMapLocation;

    public PubLayout(Context context, LinearLayout parent_layout){

        this.context = context;
        this.parent_layout = parent_layout;

        pub_layout = new LinearLayout(context);

        LinearLayout.LayoutParams view_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view_params.gravity = Gravity.CENTER_HORIZONTAL;

        tv_name = new TextView(context);
        tv_name.setLayoutParams(view_params);
        pub_layout.addView(tv_name);

        iv_pub = new ImageView(context);
        iv_pub.setLayoutParams(view_params);
        pub_layout.addView(iv_pub);

        tv_address = new TextView(context);
        tv_address.setLayoutParams(view_params);
        pub_layout.addView(tv_address);

        btn_getMapLocation = new Button(context);
        btn_getMapLocation.setLayoutParams(view_params);
        pub_layout.addView(btn_getMapLocation);
    }

    public void setPubName(String name){

        tv_name.setText(name);
        tv_name.setTextAppearance(context, R.style.pub_name_style);

    }

    public void setPubAddress(String address){

        tv_address.setText(address);
        tv_name.setTextAppearance(context, R.style.pub_address_style);
    }

    public void setPubImage(Drawable image){

        iv_pub.setImageDrawable(image);
    }

    public void attachToParent(){

        parent_layout.addView(pub_layout);
    }

    public Button getButton(){

        return btn_getMapLocation;
    }
}
