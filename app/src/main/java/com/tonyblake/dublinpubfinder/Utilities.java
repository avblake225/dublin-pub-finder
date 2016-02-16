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
        getPubImage.put(context.getString(R.string.bad_bobs),context.getResources().getDrawable(R.drawable.bad_bobs));
        getPubImage.put(context.getString(R.string.bailey_bar),context.getResources().getDrawable(R.drawable.bailey_bar));
        getPubImage.put(context.getString(R.string.break_for_the_border),context.getResources().getDrawable(R.drawable.break_for_the_border));
        getPubImage.put(context.getString(R.string.bruxelles), context.getResources().getDrawable(R.drawable.bruxelles));
        getPubImage.put(context.getString(R.string.buskers), context.getResources().getDrawable(R.drawable.buskers));
        getPubImage.put(context.getString(R.string.capitol_bar),context.getResources().getDrawable(R.drawable.capitol_bar));
        getPubImage.put(context.getString(R.string.cassidys),context.getResources().getDrawable(R.drawable.cassidys));
        getPubImage.put(context.getString(R.string.czech_inn),context.getResources().getDrawable(R.drawable.czech_inn));
        getPubImage.put(context.getString(R.string.doyles),context.getResources().getDrawable(R.drawable.doyles));
        getPubImage.put(context.getString(R.string.east_side_tavern),context.getResources().getDrawable(R.drawable.east_side_tavern));
        getPubImage.put(context.getString(R.string.fibber_magee), context.getResources().getDrawable(R.drawable.fibber_magee));
        getPubImage.put(context.getString(R.string.fitzsimons), context.getResources().getDrawable(R.drawable.fitzsimons));
        getPubImage.put(context.getString(R.string.garage_bar), context.getResources().getDrawable(R.drawable.garage_bar));
        getPubImage.put(context.getString(R.string.grogans), context.getResources().getDrawable(R.drawable.grogans));
        getPubImage.put(context.getString(R.string.harbourmaster), context.getResources().getDrawable(R.drawable.harbourmaster));
        getPubImage.put(context.getString(R.string.hairy_lemon),context.getResources().getDrawable(R.drawable.hairy_lemon));
        getPubImage.put(context.getString(R.string.hartigans),context.getResources().getDrawable(R.drawable.hartigans));
        getPubImage.put(context.getString(R.string.hogans),context.getResources().getDrawable(R.drawable.hogans));
        getPubImage.put(context.getString(R.string.jj_smyths),context.getResources().getDrawable(R.drawable.jj_smyths));
        getPubImage.put(context.getString(R.string.john_kehoe),context.getResources().getDrawable(R.drawable.john_kehoe));
        getPubImage.put(context.getString(R.string.jw_sweetman), context.getResources().getDrawable(R.drawable.jw_sweetman));
        getPubImage.put(context.getString(R.string.lanigans), context.getResources().getDrawable(R.drawable.lanigans));
        getPubImage.put(context.getString(R.string.lagoona), context.getResources().getDrawable(R.drawable.lagoona));
        getPubImage.put(context.getString(R.string.mercantile), context.getResources().getDrawable(R.drawable.mercantile));
        getPubImage.put(context.getString(R.string.nearys), context.getResources().getDrawable(R.drawable.nearys));
        getPubImage.put(context.getString(R.string.market_bar), context.getResources().getDrawable(R.drawable.market_bar));
        getPubImage.put(context.getString(R.string.odonoghues), context.getResources().getDrawable(R.drawable.o_donoghues));
        getPubImage.put(context.getString(R.string.oliver_st_john_gogarty), context.getResources().getDrawable(R.drawable.oliver_st_john_gogarty));
        getPubImage.put(context.getString(R.string.palace_bar), context.getResources().getDrawable(R.drawable.palace_bar));
        getPubImage.put(context.getString(R.string.porterhouse_central), context.getResources().getDrawable(R.drawable.porterhouse_central));
        getPubImage.put(context.getString(R.string.p_macs), context.getResources().getDrawable(R.drawable.p_macs));
        getPubImage.put(context.getString(R.string.peters_pub), context.getResources().getDrawable(R.drawable.peters_pub));
        getPubImage.put(context.getString(R.string.robert_reid), context.getResources().getDrawable(R.drawable.robert_reid));
        getPubImage.put(context.getString(R.string.river_bar), context.getResources().getDrawable(R.drawable.river_bar));
        getPubImage.put(context.getString(R.string.south_william), context.getResources().getDrawable(R.drawable.south_william));
        getPubImage.put(context.getString(R.string.synnotts), context.getResources().getDrawable(R.drawable.sinnotts));
        getPubImage.put(context.getString(R.string.the_brew_dock), context.getResources().getDrawable(R.drawable.the_brew_dock));
        getPubImage.put(context.getString(R.string.the_duke), context.getResources().getDrawable(R.drawable.the_duke));
        getPubImage.put(context.getString(R.string.the_globe), context.getResources().getDrawable(R.drawable.the_globe));
        getPubImage.put(context.getString(R.string.grafton_lounge), context.getResources().getDrawable(R.drawable.grafton_lounge));
        getPubImage.put(context.getString(R.string.the_long_hall), context.getResources().getDrawable(R.drawable.the_long_hall));
        getPubImage.put(context.getString(R.string.the_norseman), context.getResources().getDrawable(R.drawable.the_norseman));
        getPubImage.put(context.getString(R.string.the_stags_head), context.getResources().getDrawable(R.drawable.the_stags_head));
        getPubImage.put(context.getString(R.string.the_temple_bar), context.getResources().getDrawable(R.drawable.the_temple_bar));
        getPubImage.put(context.getString(R.string.turks_head), context.getResources().getDrawable(R.drawable.turks_head));
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
