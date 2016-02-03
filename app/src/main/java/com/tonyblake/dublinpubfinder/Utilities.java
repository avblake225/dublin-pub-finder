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

        getPubImage.put(context.getString(R.string.the_temple_bar), context.getResources().getDrawable(R.drawable.the_temple_bar));
        getPubImage.put(context.getString(R.string.the_globe_bar), context.getResources().getDrawable(R.drawable.the_globe_bar));
        getPubImage.put(context.getString(R.string.fibber_magee), context.getResources().getDrawable(R.drawable.fibber_magee));
        getPubImage.put(context.getString(R.string.jw_sweetman), context.getResources().getDrawable(R.drawable.jw_sweetman));
    }

    public static Drawable getPubImage(String name){

        return getPubImage.get(name);
    }
}
