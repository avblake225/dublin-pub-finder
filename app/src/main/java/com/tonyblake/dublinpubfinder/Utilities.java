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
        getPubImage.put(context.getString(R.string.bruxelles),context.getResources().getDrawable(R.drawable.bruxelles));
        getPubImage.put(context.getString(R.string.cassidys),context.getResources().getDrawable(R.drawable.cassidys));
        getPubImage.put(context.getString(R.string.doyles),context.getResources().getDrawable(R.drawable.doyles));
        getPubImage.put(context.getString(R.string.east_side_tavern),context.getResources().getDrawable(R.drawable.east_side_tavern));
        getPubImage.put(context.getString(R.string.fibber_magee), context.getResources().getDrawable(R.drawable.fibber_magee));
        getPubImage.put(context.getString(R.string.hartigans),context.getResources().getDrawable(R.drawable.hartigans));
        getPubImage.put(context.getString(R.string.hogans),context.getResources().getDrawable(R.drawable.hogans));
        getPubImage.put(context.getString(R.string.jj_smyths),context.getResources().getDrawable(R.drawable.jj_smyths));
        getPubImage.put(context.getString(R.string.john_kehoe),context.getResources().getDrawable(R.drawable.john_kehoe));
        getPubImage.put(context.getString(R.string.jw_sweetman), context.getResources().getDrawable(R.drawable.jw_sweetman));
        getPubImage.put(context.getString(R.string.the_temple_bar), context.getResources().getDrawable(R.drawable.the_temple_bar));
        getPubImage.put(context.getString(R.string.the_globe_bar), context.getResources().getDrawable(R.drawable.the_globe_bar));
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
