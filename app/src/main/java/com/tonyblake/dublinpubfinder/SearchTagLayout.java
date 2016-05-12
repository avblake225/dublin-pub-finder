package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchTagLayout {

    private Context context;

    private LinearLayout search_tag_layout_container;

    private int max_width;

    private LayoutInflater inflater;

    private View view;

    private LinearLayout search_tag_layout;

    public SearchTagLayout(Context context, LinearLayout search_tag_layout_container, int max_width){

        this.context = context;

        this.search_tag_layout_container = search_tag_layout_container;

        this.max_width = max_width;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.search_tag_layout, null);

        search_tag_layout = (LinearLayout)view.findViewById(R.id.search_tag_layout);

        search_tag_layout_container.addView(view);
    }

    public void addSearchTag(TextView tv_search_tag){

        search_tag_layout.addView(tv_search_tag);
    }

    public boolean hasSpaceForMoreTags(){

        search_tag_layout.measure(0,0);

        int search_tag_layout_width = search_tag_layout.getMeasuredWidth();

        if(search_tag_layout_width < max_width){

            return true;
        }
        else{

            return false;
        }
    }
}
