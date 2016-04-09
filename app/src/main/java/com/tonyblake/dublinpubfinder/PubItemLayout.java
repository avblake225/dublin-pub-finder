package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PubItemLayout {

    private Context context;
    private LinearLayout pub_item_container;
    private LinearLayout pub_item_layout;
    private ImageView pub_image;
    private LinearLayout pub_details;
    private TextView pub_name;
    private TextView pub_address;
    private ImageView pub_rating;

    public PubItemLayout(Context context, LinearLayout pub_item_container){

        this.context = context;

        this.pub_item_container = pub_item_container;

        pub_item_layout = new LinearLayout(context);

        pub_item_layout.setOrientation(LinearLayout.HORIZONTAL);

        // Pub image
        LinearLayout.LayoutParams pub_image_params = new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT);
        pub_image_params.gravity = Gravity.LEFT;
        pub_image_params.setMargins((int) context.getResources().getDimension(R.dimen.pub_item_image_margin_left),
                ((int) context.getResources().getDimension(R.dimen.pub_item_image_margin_top)),
                ((int) context.getResources().getDimension(R.dimen.no_margin_right)),
                ((int) context.getResources().getDimension(R.dimen.no_margin_bottom)));
        pub_image = new ImageView(context);
        pub_image.setLayoutParams(pub_image_params);
        pub_item_layout.addView(pub_image);

        // Linear layout for pub details
        pub_details = new LinearLayout(context);
        pub_details.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams pub_details_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pub_details_params.setMargins((int) context.getResources().getDimension(R.dimen.tv_pub_item_margin_left),
                ((int) context.getResources().getDimension(R.dimen.tv_pub_item_margin_top)),
                ((int) context.getResources().getDimension(R.dimen.no_margin_right)),
                ((int) context.getResources().getDimension(R.dimen.tv_pub_item_margin_bottom)));

        // Pub name
        LinearLayout.LayoutParams pub_name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pub_name_params.setMargins((int) context.getResources().getDimension(R.dimen.pub_item_name_margin_left),
                ((int) context.getResources().getDimension(R.dimen.pub_item_name_margin_top)),
                ((int) context.getResources().getDimension(R.dimen.no_margin_right)),
                ((int) context.getResources().getDimension(R.dimen.pub_item_name_margin_bottom)));
        pub_name = new TextView(context);
        pub_name.setLayoutParams(pub_details_params);
        pub_details.addView(pub_name);

        // Pub address
        pub_address = new TextView(context);
        pub_address.setLayoutParams(pub_details_params);
        pub_details.addView(pub_address);

        // Pub rating
        pub_rating = new ImageView(context);
        pub_rating.setLayoutParams(pub_details_params);
        pub_details.addView(pub_rating);

        pub_item_layout.addView(pub_details);
    }

    public void setPubName(String name){

        pub_name.setText(name);
        pub_name.setTextAppearance(context, R.style.pub_item_name_style);

    }

    public void setPubAddress(String address){

        pub_address.setText(address);
        pub_address.setTextAppearance(context, R.style.pub_item_address_style);
    }

    public void setPubRating(Drawable rating){

        pub_rating.setImageDrawable(rating);
    }

    public void setPubImage(Bitmap image){

        pub_image.setImageBitmap(image);
    }

    public void attachToParent(){

        pub_item_container.addView(pub_item_layout);

    }
}
