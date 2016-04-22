package com.tonyblake.dublinpubfinder;

import android.graphics.Bitmap;

public class PubItem {

    private Bitmap pub_image = null;
    private String pub_name = "";
    private String pub_address = "";
    private String pub_description = "";
    private String pub_placeId = "";
    private float pub_rating;

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

    public void setPubDescription(String pub_description){

        this.pub_description = pub_description;
    }

    public void setPubPlaceId(String pub_placeId){

        this.pub_placeId = pub_placeId;
    }

    public void setPubRating(float pub_rating){

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

    public String getPubDescription(){

        return pub_description;
    }

    public String getPubPlaceId(){

        return pub_placeId;
    }

    public float getPubRating(){

        return pub_rating;
    }
}
