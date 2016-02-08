package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class Utilities {

    public static HashMap<String,Drawable> getPubImage = new HashMap<String,Drawable>();

    public static void populatePubImageMap(Context context){

        getPubImage.put(context.getString(R.string.alfie_byrnes),context.getResources().getDrawable(R.drawable.alfie_byrnes));
        getPubImage.put(context.getString(R.string.bailey_bar),context.getResources().getDrawable(R.drawable.bailey_bar));
        getPubImage.put(context.getString(R.string.bruxelles),context.getResources().getDrawable(R.drawable.bruxelles));
        getPubImage.put(context.getString(R.string.capitol_bar),context.getResources().getDrawable(R.drawable.capitol_bar));
        getPubImage.put(context.getString(R.string.cassidys),context.getResources().getDrawable(R.drawable.cassidys));
        getPubImage.put(context.getString(R.string.doyles),context.getResources().getDrawable(R.drawable.doyles));
        getPubImage.put(context.getString(R.string.east_side_tavern),context.getResources().getDrawable(R.drawable.east_side_tavern));
        getPubImage.put(context.getString(R.string.fibber_magee), context.getResources().getDrawable(R.drawable.fibber_magee));
        getPubImage.put(context.getString(R.string.hartigans),context.getResources().getDrawable(R.drawable.hartigans));
        getPubImage.put(context.getString(R.string.hogans),context.getResources().getDrawable(R.drawable.hogans));
        getPubImage.put(context.getString(R.string.jj_smyths),context.getResources().getDrawable(R.drawable.jj_smyths));
        getPubImage.put(context.getString(R.string.john_kehoe),context.getResources().getDrawable(R.drawable.john_kehoe));
        getPubImage.put(context.getString(R.string.jw_sweetman), context.getResources().getDrawable(R.drawable.jw_sweetman));
        getPubImage.put(context.getString(R.string.nearys), context.getResources().getDrawable(R.drawable.nearys));
        getPubImage.put(context.getString(R.string.odonoghues), context.getResources().getDrawable(R.drawable.o_donoghues));
        getPubImage.put(context.getString(R.string.porterhouse_central), context.getResources().getDrawable(R.drawable.porterhouse_central));
        getPubImage.put(context.getString(R.string.river_bar), context.getResources().getDrawable(R.drawable.river_bar));
        getPubImage.put(context.getString(R.string.synnotts), context.getResources().getDrawable(R.drawable.sinnotts));
        getPubImage.put(context.getString(R.string.the_duke), context.getResources().getDrawable(R.drawable.the_duke));
        getPubImage.put(context.getString(R.string.the_globe), context.getResources().getDrawable(R.drawable.the_globe));
        getPubImage.put(context.getString(R.string.grafton_lounge), context.getResources().getDrawable(R.drawable.grafton_lounge));
        getPubImage.put(context.getString(R.string.the_temple_bar), context.getResources().getDrawable(R.drawable.the_temple_bar));
    }

    public static Drawable getPubImage(String name, Context context){

        Drawable image = null;

        try{
            image = getPubImage.get(name);
        }
        catch(Exception e){
            System.out.println(context.getString(R.string.errorretrievingpubimage));
        }

        return image;
    }
}
