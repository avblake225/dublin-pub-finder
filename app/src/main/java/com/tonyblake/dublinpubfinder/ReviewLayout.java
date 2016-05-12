package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class ReviewLayout {

    private Context context;

    private LinearLayout review_layout_container;

    private LayoutInflater inflater;

    private View view;

    private TextView tv_author_name;

    private RatingBar review_rating_bar;

    private TextView tv_text;

    public ReviewLayout(Context context, LinearLayout review_layout_container){

        this.context = context;

        this.review_layout_container = review_layout_container;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.review_layout, null);

        tv_author_name = (TextView)view.findViewById(R.id.tv_author_name);

        review_rating_bar = (RatingBar)view.findViewById(R.id.review_rating_bar);

        tv_text = (TextView)view.findViewById(R.id.tv_text);
    }

    public void setAuthorName(String name){

        tv_author_name.setText(name);

        tv_author_name.setTypeface(null, Typeface.BOLD);
    }

    public void setRating(String rating){

        review_rating_bar.setRating(Float.valueOf(rating));

        LayerDrawable stars = (LayerDrawable) review_rating_bar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }

    public void setText(String text){

        String text_with_quotes = "\"" + text + "\"";

        tv_text.setText(text_with_quotes);

        tv_text.setTypeface(null, Typeface.ITALIC);
    }

    public void finish(){

        review_layout_container.addView(view);
    }
}
