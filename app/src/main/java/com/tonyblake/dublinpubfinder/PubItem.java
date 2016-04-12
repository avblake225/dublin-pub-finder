package com.tonyblake.dublinpubfinder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class PubItem {

    private Bitmap pub_image = null;
    private String pub_name = "";
    private String pub_address = "";
    private Drawable pub_rating = null;

    // Setter methods
    public void setPubImage(Bitmap pub_image){

        this.pub_image = pub_image;
    }

    public void setPubName(String pub_name){

        this.pub_name = pub_name;
    }

    public void setPubAddress(String pub_address){

        this.pub_address = pub_address;
    }

    public void setPubRating(Drawable pub_rating){

        this.pub_rating = pub_rating;
    }

    // Getter methods
    public Bitmap getPubImage(){

        return pub_image;
    }

    public String getPubName(){

        return pub_name;
    }

    public String getPubAddress(){

        return pub_address;
    }

    public Drawable getPubRating(){

        return pub_rating;
    }
}
