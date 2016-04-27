package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PubAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Pub pub = null;
    int i=0;

    public PubAdapter(Activity a, ArrayList d,Resources resLocal) {

        activity = a;
        data=d;
        res = resLocal;

        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {

        if(data.size()<=0) return 1;

        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder{

        public TextView pub_name;
        public TextView pub_address;
        public RatingBar pub_rating;
        public ImageView pub_image;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if(convertView==null){

            v = inflater.inflate(R.layout.pub_item_layout, null);

            holder = new ViewHolder();
            holder.pub_name = (TextView) v.findViewById(R.id.pub_name);
            holder.pub_address=(TextView)v.findViewById(R.id.pub_address);
            holder.pub_rating=(RatingBar)v.findViewById(R.id.rating_bar);
            holder.pub_image = (ImageView) v.findViewById(R.id.pub_image);

            v.setTag( holder );
        }
        else
            holder=(ViewHolder)v.getTag();

        if(data.size()<=0)
        {
            holder.pub_name.setText("No Data");
            holder.pub_address.setText("No Data");
        }
        else
        {
            pub = ( Pub ) data.get( position );

            holder.pub_name.setText(pub.name);
            holder.pub_address.setText(pub.address);

            LayerDrawable stars = (LayerDrawable) holder.pub_rating.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

            holder.pub_rating.setRating(pub.rating);
            holder.pub_image.setImageBitmap(pub.image);

            if(pub.image == null){

                holder.pub_image.setImageBitmap(BitmapFactory.decodeResource(res, R.drawable.image_unavailable));
            }

            v.setOnClickListener(new OnItemClickListener( position ));
        }
        return v;
    }

    private class OnItemClickListener implements View.OnClickListener {

        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

            HomeScreen homeScreen = (HomeScreen)activity;

            homeScreen.onItemClick(mPosition);
        }
    }
}