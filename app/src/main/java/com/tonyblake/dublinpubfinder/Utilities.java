package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.HashMap;

public class Utilities {

    public static HashMap<String, Drawable> getPubRating = new HashMap<String, Drawable>();

    public static void populatePubRatingMap(Context context){

        getPubRating.put(context.getString(R.string.against_the_grain), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.alfie_byrnes), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.alfies), context.getResources().getDrawable(R.drawable.three_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.anseo), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.bad_bobs), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.break_for_the_border), context.getResources().getDrawable(R.drawable.three_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.bruxelles), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.buskers), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.café_en_seine), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.cassidys), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.devitts), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.doheny_and_nesbitts), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.doyles), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.fibber_magees), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.fitzgeralds), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.fitzsimons), context.getResources().getDrawable(R.drawable.three_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.flannerys), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.four_dame_lane), context.getResources().getDrawable(R.drawable.three_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.grogans), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.hartigans), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.hogans), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.james_toners), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.jj_smyths), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.john_kehoes), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.jw_sweetmans), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.lanigans), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.lagoona), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.mulligans), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.nearys), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.o_donoghues), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.o_reillys), context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.p_macs),context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.peters_pub),context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.reillys),context.getResources().getDrawable(R.drawable.three_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.robert_reades),context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.ryans),context.getResources().getDrawable(R.drawable.four_star_rating));
        getPubRating.put(context.getString(R.string.sams_bar),context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.the_bar_with_no_name),context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
    }

    public static Drawable getPubRating(String name, Context context){

        Drawable rating = null;

        try{
            rating = getPubRating.get(name);
        }
        catch(Exception e){
            System.out.println(context.getString(R.string.error_retrieving_pub_rating));
        }

        return rating;
    }
}
