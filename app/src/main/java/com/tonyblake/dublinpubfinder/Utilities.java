package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.HashMap;

public class Utilities {

    public static HashMap<String, Drawable> getPubRating = new HashMap<String, Drawable>();

    public static HashMap<String, Drawable> getPubImage = new HashMap<String,Drawable>();

    public static HashMap<String, Integer> getSelectionCode = new HashMap<String,Integer>();

    public static HashMap<Integer, String> getDBQuery = new HashMap<Integer,String>();

    public static void populatePubRatingMap(Context context){

        getPubRating.put(context.getString(R.string.against_the_grain), context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
        getPubRating.put(context.getString(R.string.p_macs),context.getResources().getDrawable(R.drawable.four_and_a_half_star_rating));
    }

    public static void populatePubImageMap(Context context){

        getPubImage.put(context.getString(R.string.against_the_grain),context.getResources().getDrawable(R.drawable.against_the_grain));
        getPubImage.put(context.getString(R.string.alfie_byrnes),context.getResources().getDrawable(R.drawable.alfie_byrnes));
        getPubImage.put(context.getString(R.string.anseo),context.getResources().getDrawable(R.drawable.anseo));
        getPubImage.put(context.getString(R.string.bad_bobs),context.getResources().getDrawable(R.drawable.bad_bobs));
        getPubImage.put(context.getString(R.string.break_for_the_border),context.getResources().getDrawable(R.drawable.break_for_the_border));
        getPubImage.put(context.getString(R.string.bruxelles), context.getResources().getDrawable(R.drawable.bruxelles));
        getPubImage.put(context.getString(R.string.buskers), context.getResources().getDrawable(R.drawable.buskers));
        getPubImage.put(context.getString(R.string.café_en_seine), context.getResources().getDrawable(R.drawable.cafe_en_seine));
        getPubImage.put(context.getString(R.string.cassidys),context.getResources().getDrawable(R.drawable.cassidys));
        getPubImage.put(context.getString(R.string.devitts),context.getResources().getDrawable(R.drawable.devitts));
        getPubImage.put(context.getString(R.string.doyles),context.getResources().getDrawable(R.drawable.doyles));
        getPubImage.put(context.getString(R.string.fibber_magees), context.getResources().getDrawable(R.drawable.fibber_magees));
        getPubImage.put(context.getString(R.string.fitzsimons), context.getResources().getDrawable(R.drawable.fitzsimons));
        getPubImage.put(context.getString(R.string.flannerys), context.getResources().getDrawable(R.drawable.flannerys));
        getPubImage.put(context.getString(R.string.grogans), context.getResources().getDrawable(R.drawable.grogans));
        getPubImage.put(context.getString(R.string.hartigans),context.getResources().getDrawable(R.drawable.hartigans));
        getPubImage.put(context.getString(R.string.hogans),context.getResources().getDrawable(R.drawable.hogans));
        getPubImage.put(context.getString(R.string.jj_smyths),context.getResources().getDrawable(R.drawable.jj_smyths));
        getPubImage.put(context.getString(R.string.john_kehoes),context.getResources().getDrawable(R.drawable.john_kehoes));
        getPubImage.put(context.getString(R.string.jw_sweetmans), context.getResources().getDrawable(R.drawable.jw_sweetmans));
        getPubImage.put(context.getString(R.string.lanigans), context.getResources().getDrawable(R.drawable.lanigans));
        getPubImage.put(context.getString(R.string.lagoona), context.getResources().getDrawable(R.drawable.lagoona));
        getPubImage.put(context.getString(R.string.mulligans), context.getResources().getDrawable(R.drawable.mulligans));
        getPubImage.put(context.getString(R.string.nearys), context.getResources().getDrawable(R.drawable.nearys));
        getPubImage.put(context.getString(R.string.o_donoghues), context.getResources().getDrawable(R.drawable.o_donoghues));
        getPubImage.put(context.getString(R.string.o_reillys), context.getResources().getDrawable(R.drawable.o_reillys));
        getPubImage.put(context.getString(R.string.p_macs), context.getResources().getDrawable(R.drawable.p_macs));
        getPubImage.put(context.getString(R.string.peters_pub), context.getResources().getDrawable(R.drawable.peters_pub));
        getPubImage.put(context.getString(R.string.robert_reids), context.getResources().getDrawable(R.drawable.robert_reids));
        getPubImage.put(context.getString(R.string.ryans), context.getResources().getDrawable(R.drawable.ryans));
        getPubImage.put(context.getString(R.string.sin_é), context.getResources().getDrawable(R.drawable.sin_e));
        getPubImage.put(context.getString(R.string.synnotts), context.getResources().getDrawable(R.drawable.sinnotts));
        getPubImage.put(context.getString(R.string.the_auld_dubliner), context.getResources().getDrawable(R.drawable.the_auld_dubliner));
        getPubImage.put(context.getString(R.string.the_bankers), context.getResources().getDrawable(R.drawable.the_bankers));
        getPubImage.put(context.getString(R.string.the_bailey_bar),context.getResources().getDrawable(R.drawable.the_bailey_bar));
        getPubImage.put(context.getString(R.string.the_bison_bar),context.getResources().getDrawable(R.drawable.the_bison_bar));
        getPubImage.put(context.getString(R.string.the_brew_dock), context.getResources().getDrawable(R.drawable.the_brew_dock));
        getPubImage.put(context.getString(R.string.the_camden_exchange), context.getResources().getDrawable(R.drawable.the_camden_exchange));
        getPubImage.put(context.getString(R.string.the_capitol_bar),context.getResources().getDrawable(R.drawable.the_capitol_bar));
        getPubImage.put(context.getString(R.string.the_czech_inn),context.getResources().getDrawable(R.drawable.the_czech_inn));
        getPubImage.put(context.getString(R.string.the_dame_tavern),context.getResources().getDrawable(R.drawable.the_dame_tavern));
        getPubImage.put(context.getString(R.string.the_duke), context.getResources().getDrawable(R.drawable.the_duke));
        getPubImage.put(context.getString(R.string.the_east_side_tavern),context.getResources().getDrawable(R.drawable.the_east_side_tavern));
        getPubImage.put(context.getString(R.string.the_garage_bar), context.getResources().getDrawable(R.drawable.the_garage_bar));
        getPubImage.put(context.getString(R.string.the_george), context.getResources().getDrawable(R.drawable.the_george));
        getPubImage.put(context.getString(R.string.the_globe), context.getResources().getDrawable(R.drawable.the_globe));
        getPubImage.put(context.getString(R.string.the_grafton_lounge), context.getResources().getDrawable(R.drawable.the_grafton_lounge));
        getPubImage.put(context.getString(R.string.the_hairy_lemon),context.getResources().getDrawable(R.drawable.the_hairy_lemon));
        getPubImage.put(context.getString(R.string.the_harbourmaster), context.getResources().getDrawable(R.drawable.the_harbourmaster));
        getPubImage.put(context.getString(R.string.the_liquor_rooms), context.getResources().getDrawable(R.drawable.the_liquor_rooms));
        getPubImage.put(context.getString(R.string.the_long_hall), context.getResources().getDrawable(R.drawable.the_long_hall));
        getPubImage.put(context.getString(R.string.the_long_stone), context.getResources().getDrawable(R.drawable.the_long_stone));
        getPubImage.put(context.getString(R.string.the_market_bar), context.getResources().getDrawable(R.drawable.the_market_bar));
        getPubImage.put(context.getString(R.string.the_mercantile), context.getResources().getDrawable(R.drawable.the_mercantile));
        getPubImage.put(context.getString(R.string.the_morgan_bar), context.getResources().getDrawable(R.drawable.the_morgan_bar));
        getPubImage.put(context.getString(R.string.the_norseman), context.getResources().getDrawable(R.drawable.the_norseman));
        getPubImage.put(context.getString(R.string.the_oliver_st_john_gogarty), context.getResources().getDrawable(R.drawable.the_oliver_st_john_gogarty));
        getPubImage.put(context.getString(R.string.the_palace_bar), context.getResources().getDrawable(R.drawable.the_palace_bar));
        getPubImage.put(context.getString(R.string.the_porterhouse_central), context.getResources().getDrawable(R.drawable.the_porterhouse_central));
        getPubImage.put(context.getString(R.string.the_river_bar), context.getResources().getDrawable(R.drawable.the_river_bar));
        getPubImage.put(context.getString(R.string.the_south_william), context.getResources().getDrawable(R.drawable.the_south_william));
        getPubImage.put(context.getString(R.string.the_stags_head), context.getResources().getDrawable(R.drawable.the_stags_head));
        getPubImage.put(context.getString(R.string.the_sweeney_mongrel), context.getResources().getDrawable(R.drawable.the_sweeney_mongrel));
        getPubImage.put(context.getString(R.string.the_temple_bar), context.getResources().getDrawable(R.drawable.the_temple_bar));
        getPubImage.put(context.getString(R.string.the_workmans_club), context.getResources().getDrawable(R.drawable.the_workmans_club));
        getPubImage.put(context.getString(R.string.the_turks_head), context.getResources().getDrawable(R.drawable.the_turks_head));
        getPubImage.put(context.getString(R.string.whelans), context.getResources().getDrawable(R.drawable.whelans));
    }

    public static void populateSelectionCodeMap(){

        getSelectionCode.put("00000",0);
        getSelectionCode.put("00001",1);
        getSelectionCode.put("00010",2);
        getSelectionCode.put("00011",3);
        getSelectionCode.put("00100",4);
        getSelectionCode.put("00101",5);
        getSelectionCode.put("00110",6);
        getSelectionCode.put("00111",7);
        getSelectionCode.put("01000",8);
        getSelectionCode.put("01001",9);
        getSelectionCode.put("01010",10);
        getSelectionCode.put("01011",11);
        getSelectionCode.put("01100",12);
        getSelectionCode.put("01101",13);
        getSelectionCode.put("01110",14);
        getSelectionCode.put("01111",15);
        getSelectionCode.put("10000",16);
        getSelectionCode.put("10001",17);
        getSelectionCode.put("10010",18);
        getSelectionCode.put("10011",19);
        getSelectionCode.put("10100",20);
        getSelectionCode.put("10101",21);
        getSelectionCode.put("10110",22);
        getSelectionCode.put("10111",23);
        getSelectionCode.put("11000",24);
        getSelectionCode.put("11001",25);
        getSelectionCode.put("11010",26);
        getSelectionCode.put("11011",27);
        getSelectionCode.put("11100",28);
        getSelectionCode.put("11101",29);
        getSelectionCode.put("11110",30);
        getSelectionCode.put("11111",31);
    }

    public static void populateDBQueryMap(String table_name){

        getDBQuery.put(0,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No')");
        getDBQuery.put(1,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes')");
        getDBQuery.put(2,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No'");
        getDBQuery.put(3,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
        getDBQuery.put(4,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No'");
        getDBQuery.put(5,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes'");
        getDBQuery.put(6,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No'");
        getDBQuery.put(7,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
        getDBQuery.put(8,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No'");
        getDBQuery.put(9,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes'");
        getDBQuery.put(10,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No'");
        getDBQuery.put(11,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
        getDBQuery.put(12,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No'");
        getDBQuery.put(13,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes'");
        getDBQuery.put(14,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No'");
        getDBQuery.put(15,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Traditional' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
        getDBQuery.put(16,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No'");
        getDBQuery.put(17,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes'");
        getDBQuery.put(18,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No'");
        getDBQuery.put(19,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
        getDBQuery.put(20,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No'");
        getDBQuery.put(21,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes'");
        getDBQuery.put(22,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No'");
        getDBQuery.put(23,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Northside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
        getDBQuery.put(24,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No'");
        getDBQuery.put(25,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes'");
        getDBQuery.put(26,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No')");
        getDBQuery.put(27,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'No' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
        getDBQuery.put(28,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'No'");
        getDBQuery.put(29,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'No' AND LATE_PUB = 'Yes'");
        getDBQuery.put(30,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'No'");
        getDBQuery.put(31,"SELECT * FROM " + table_name + " WHERE (PUB_TYPE = 'Modern' AND SIDE_OF_CITY = 'Southside' AND LIVE_MUSIC = 'Yes' AND CRAFT_BEER = 'Yes' AND LATE_PUB = 'Yes'");
    }

    public static Drawable getPubRating(String name, Context context){

        Drawable rating = null;

        try{
            rating = getPubRating.get(name);
        }
        catch(Exception e){
            System.out.println(context.getString(R.string.errorretrievingpubrating));
        }

        return rating;
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

    public static int getSelectionCode(String bit_combination){

        return getSelectionCode.get(bit_combination);
    }

    public static String getDBQuery(int selection_code){

        return getDBQuery.get(selection_code);
    }
}
