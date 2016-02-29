package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PubLayout {

    private Context context;
    private LinearLayout pub_details_container;
    private LinearLayout pub_layout;
    private TextView tv_name;
    private ImageView iv_rating;
    private ImageView iv_pub;
    private TextView tv_address;
    private Button btn_getMapLocation;

    public PubLayout(Context context, LinearLayout pub_details_container){

        this.context = context;
        this.pub_details_container = pub_details_container;

        pub_layout = new LinearLayout(context);
        pub_layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams v_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        v_params.gravity = Gravity.CENTER_HORIZONTAL;
        v_params.setMargins((int) context.getResources().getDimension(R.dimen.no_margin_left),
                ((int) context.getResources().getDimension(R.dimen.tv_margin_top)),
                ((int) context.getResources().getDimension(R.dimen.no_margin_right)),
                ((int) context.getResources().getDimension(R.dimen.tv_margin_bottom)));

        tv_name = new TextView(context);
        tv_name.setLayoutParams(v_params);
        pub_layout.addView(tv_name);

        tv_address = new TextView(context);
        tv_address.setLayoutParams(v_params);
        pub_layout.addView(tv_address);

        iv_rating = new ImageView(context);
        pub_layout.addView(iv_rating);

        iv_pub = new ImageView(context);
        iv_pub.setLayoutParams(v_params);
        pub_layout.addView(iv_pub);

        LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn_params.gravity = Gravity.CENTER_HORIZONTAL;
        btn_params.setMargins((int) context.getResources().getDimension(R.dimen.no_margin_left),
                ((int) context.getResources().getDimension(R.dimen.btn_margin_top)),
                ((int) context.getResources().getDimension(R.dimen.no_margin_right)),
                ((int) context.getResources().getDimension(R.dimen.btn_margin_bottom)));

        btn_getMapLocation = new Button(context);
        btn_getMapLocation.setLayoutParams(btn_params);
        pub_layout.addView(btn_getMapLocation);
    }

    public void setPubName(String name){

        tv_name.setText(name);
        tv_name.setTextAppearance(context, R.style.pub_name_style);

    }

    public void setPubAddress(String address){

        tv_address.setText(address);
        tv_address.setTextAppearance(context, R.style.pub_address_style);
    }

    public void setPubRating(Bitmap rating){

        Bitmap rating_resized = Bitmap.createScaledBitmap(rating, 50, 50, true);
        iv_rating.setImageBitmap(rating);
    }

    public void setPubImage(Drawable image){

        iv_pub.setImageDrawable(image);
    }

    public void attachToParent(){

        pub_details_container.addView(pub_layout);
    }

    public Button getMapButton(String name){

        String find_pub_on_map = context.getString(R.string.find) + " " + name + " " + context.getString(R.string.on_map);
        btn_getMapLocation.setText(find_pub_on_map);
        btn_getMapLocation.setTextAppearance(context, R.style.map_button_text_style);

        return btn_getMapLocation;
    }
}
