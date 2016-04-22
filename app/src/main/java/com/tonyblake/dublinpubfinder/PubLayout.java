package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class PubLayout{

    private LinearLayout pub_layout_container;
    private View pub_layout;
    public TextView tv_pub_name;
    public TextView tv_pub_address;
    public RatingBar rating_bar;
    public ImageView iv_pub;
    public TextView tv_pub_description;
    public Button btn_find_on_map;
    public Button btn_add_to_favourites;

    public PubLayout(Context context, LinearLayout pub_layout_container){

        this.pub_layout_container = pub_layout_container;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        pub_layout = inflater.inflate(R.layout.pub_layout, null);

        tv_pub_name = (TextView) pub_layout.findViewById(R.id.tv_pub_name);

        tv_pub_address = (TextView) pub_layout.findViewById(R.id.tv_pub_address);

        rating_bar = (RatingBar) pub_layout.findViewById(R.id.rating_bar);

        iv_pub = (ImageView) pub_layout.findViewById(R.id.iv_pub);

        tv_pub_description = (TextView) pub_layout.findViewById(R.id.tv_pub_description);

        btn_find_on_map = (Button) pub_layout.findViewById(R.id.btn_find_on_map);

        btn_add_to_favourites = (Button) pub_layout.findViewById(R.id.btn_add_to_favourites);
    }

    public void setPubName(String name){

        tv_pub_name.setText(name);
    }

    public void setPubAddress(String name){

        tv_pub_address.setText(name);
    }

    public void setPubRating(float rating, int numStars){

        rating_bar.setNumStars(numStars);
        rating_bar.setRating(rating);
    }

    public void setPubImage(Bitmap image){

        iv_pub.setImageBitmap(image);
    }

    public void setPubDescription(String description){

        tv_pub_description.setText(description);
    }

    public void attachToParent(){

        pub_layout_container.addView(pub_layout);
    }

    public Button getFindOnMapButton(){

        return btn_find_on_map;
    }

    public Button getAddToFavouritesButton(){

        return btn_add_to_favourites;
    }
}
