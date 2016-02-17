package com.tonyblake.dublinpubfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "dublinpubdatabase.db";
    public static final String TABLE_NAME = "pubs_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "DIRECTIONS";

    public SQLiteDatabase db;

    public static String[] pub_names = null;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();

        pub_names = new String[]{context.getString(R.string.against_the_grain),
                                 context.getString(R.string.alfie_byrnes),
                                 context.getString(R.string.anseo),
                                 context.getString(R.string.bad_bobs),
                                 context.getString(R.string.bailey_bar),
                                 context.getString(R.string.bison_bar),
                                 context.getString(R.string.break_for_the_border),
                                 context.getString(R.string.bruxelles),
                                 context.getString(R.string.buskers),
                                 context.getString(R.string.café_en_seine),
                                 context.getString(R.string.camden_exchange),
                                 context.getString(R.string.capitol_bar),
                                 context.getString(R.string.cassidys),
                                 context.getString(R.string.czech_inn),
                                 context.getString(R.string.dame_tavern),
                                 context.getString(R.string.doyles),
                                 context.getString(R.string.east_side_tavern),
                                 context.getString(R.string.fibber_magee),
                                 context.getString(R.string.fitzsimons),
                                 context.getString(R.string.four_dame_lane),
                                 context.getString(R.string.garage_bar),
                                 context.getString(R.string.grafton_lounge),
                                 context.getString(R.string.grogans),
                                 context.getString(R.string.harbourmaster),
                                 context.getString(R.string.hairy_lemon),
                                 context.getString(R.string.hartigans),
                                 context.getString(R.string.hogans),
                                 context.getString(R.string.jj_smyths),
                                 context.getString(R.string.john_kehoe),
                                 context.getString(R.string.jw_sweetman),
                                 context.getString(R.string.lanigans),
                                 context.getString(R.string.lagoona),
                                 context.getString(R.string.market_bar),
                                 context.getString(R.string.mercantile),
                                 context.getString(R.string.morgan_bar),
                                 context.getString(R.string.mulligans),
                                 context.getString(R.string.nearys),
                                 context.getString(R.string.odonoghues),
                                 context.getString(R.string.oliver_st_john_gogarty),
                                 context.getString(R.string.palace_bar),
                                 context.getString(R.string.peters_pub),
                                 context.getString(R.string.porterhouse_central),
                                 context.getString(R.string.p_macs),
                                 context.getString(R.string.robert_reid),
                                 context.getString(R.string.river_bar),
                                 context.getString(R.string.sin_é),
                                 context.getString(R.string.south_william),
                                 context.getString(R.string.synnotts),
                                 context.getString(R.string.the_brew_dock),
                                 context.getString(R.string.the_duke),
                                 context.getString(R.string.the_globe),
                                 context.getString(R.string.the_lombard),
                                 context.getString(R.string.the_long_hall),
                                 context.getString(R.string.the_long_stone),
                                 context.getString(R.string.the_norseman),
                                 context.getString(R.string.the_stags_head),
                                 context.getString(R.string.the_sweeney_mongrel),
                                 context.getString(R.string.the_temple_bar),
                                 context.getString(R.string.the_workmans_club),
                                 context.getString(R.string.turks_head),
        };

        insertData(context.getString(R.string.against_the_grain),
                context.getString(R.string.against_the_grain_address),
                context.getString(R.string.against_the_grain_directions));

        insertData(context.getString(R.string.alfie_byrnes),
                context.getString(R.string.alfie_byrnes_address),
                context.getString(R.string.alfie_byrnes_directions));

        insertData(context.getString(R.string.anseo),
                context.getString(R.string.anseo_address),
                context.getString(R.string.anseo_directions));

        insertData(context.getString(R.string.bad_bobs),
                context.getString(R.string.bad_bobs_address),
                context.getString(R.string.bad_bobs_directions));

        insertData(context.getString(R.string.bailey_bar),
                context.getString(R.string.bailey_bar_address),
                context.getString(R.string.bailey_bar_directions));

        insertData(context.getString(R.string.bison_bar),
                context.getString(R.string.bison_bar_address),
                context.getString(R.string.bison_bar_directions));

        insertData(context.getString(R.string.break_for_the_border),
                context.getString(R.string.break_for_the_border_address),
                context.getString(R.string.break_for_the_border_directions));

        insertData(context.getString(R.string.bruxelles),
                context.getString(R.string.bruxelles_address),
                context.getString(R.string.bruxelles_directions));

        insertData(context.getString(R.string.buskers),
                context.getString(R.string.buskers_address),
                context.getString(R.string.buskers_directions));

        insertData(context.getString(R.string.café_en_seine),
                context.getString(R.string.café_en_seine_address),
                context.getString(R.string.café_en_seine_directions));

        insertData(context.getString(R.string.camden_exchange),
                context.getString(R.string.camden_exchange_address),
                context.getString(R.string.camden_exchange_directions));

        insertData(context.getString(R.string.capitol_bar),
                context.getString(R.string.capitol_bar_address),
                context.getString(R.string.capitol_bar_directions));

        insertData(context.getString(R.string.cassidys),
                context.getString(R.string.cassidys_address),
                context.getString(R.string.cassidys_directions));

        insertData(context.getString(R.string.czech_inn),
                context.getString(R.string.czech_inn_address),
                context.getString(R.string.czech_inn_directions));

        insertData(context.getString(R.string.dame_tavern),
                context.getString(R.string.dame_tavern_address),
                context.getString(R.string.dame_tavern_directions));

        insertData(context.getString(R.string.doyles),
                context.getString(R.string.doyles_address),
                context.getString(R.string.doyles_directions));

        insertData(context.getString(R.string.east_side_tavern),
                context.getString(R.string.east_side_tavern_address),
                context.getString(R.string.east_side_tavern_directions));

        insertData(context.getString(R.string.fibber_magee),
                context.getString(R.string.fibber_magee_address),
                context.getString(R.string.fibber_magee_directions));

        insertData(context.getString(R.string.fitzsimons),
                context.getString(R.string.fitzsimons_address),
                context.getString(R.string.fitzsimons_directions));

        insertData(context.getString(R.string.four_dame_lane),
                context.getString(R.string.four_dame_lane_address),
                context.getString(R.string.four_dame_lane_directions));

        insertData(context.getString(R.string.garage_bar),
                context.getString(R.string.garage_bar_address),
                context.getString(R.string.garage_bar_directions));

        insertData(context.getString(R.string.grafton_lounge),
                context.getString(R.string.grafton_lounge_address),
                context.getString(R.string.grafton_lounge_directions));

        insertData(context.getString(R.string.grogans),
                context.getString(R.string.grogans_address),
                context.getString(R.string.grogans_directions));

        insertData(context.getString(R.string.harbourmaster),
                context.getString(R.string.harbourmaster_address),
                context.getString(R.string.harbourmaster_directions));

        insertData(context.getString(R.string.hairy_lemon),
                context.getString(R.string.hairy_lemon_address),
                context.getString(R.string.hairy_lemon_directions));

        insertData(context.getString(R.string.hartigans),
                context.getString(R.string.hartigans_address),
                context.getString(R.string.hartigans_directions));

        insertData(context.getString(R.string.hogans),
                context.getString(R.string.hogans_address),
                context.getString(R.string.hogans_directions));

        insertData(context.getString(R.string.jj_smyths),
                context.getString(R.string.jj_smyths_address),
                context.getString(R.string.jj_smyths_directions));

        insertData(context.getString(R.string.john_kehoe),
                context.getString(R.string.john_kehoe_address),
                context.getString(R.string.john_kehoe_directions));

        insertData(context.getString(R.string.jw_sweetman),
                context.getString(R.string.jw_sweetman_address),
                context.getString(R.string.jw_sweetman_directions));

        insertData(context.getString(R.string.lanigans),
                context.getString(R.string.lanigans_address),
                context.getString(R.string.lanigans_directions));

        insertData(context.getString(R.string.lagoona),
                context.getString(R.string.lagoona_address),
                context.getString(R.string.lagoona_directions));

        insertData(context.getString(R.string.market_bar),
                context.getString(R.string.market_bar_address),
                context.getString(R.string.market_bar_directions));

        insertData(context.getString(R.string.mercantile),
                context.getString(R.string.mercantile_address),
                context.getString(R.string.mercantile_directions));

        insertData(context.getString(R.string.morgan_bar),
                context.getString(R.string.morgan_bar_address),
                context.getString(R.string.morgan_bar_directions));

        insertData(context.getString(R.string.mulligans),
                context.getString(R.string.mulligans_address),
                context.getString(R.string.mulligans_directions));

        insertData(context.getString(R.string.nearys),
                context.getString(R.string.nearys_address),
                context.getString(R.string.nearys_directions));

        insertData(context.getString(R.string.odonoghues),
                context.getString(R.string.odonoghues_address),
                context.getString(R.string.odonoghues_directions));

        insertData(context.getString(R.string.oliver_st_john_gogarty),
                context.getString(R.string.oliver_st_john_gogarty_address),
                context.getString(R.string.oliver_st_john_gogarty_directions));

        insertData(context.getString(R.string.palace_bar),
                context.getString(R.string.palace_bar_address),
                context.getString(R.string.palace_bar_directions));

        insertData(context.getString(R.string.peters_pub),
                context.getString(R.string.peters_pub_address),
                context.getString(R.string.peters_pub_directions));

        insertData(context.getString(R.string.porterhouse_central),
                context.getString(R.string.porterhouse_central_address),
                context.getString(R.string.porterhouse_central_directions));

        insertData(context.getString(R.string.p_macs),
                context.getString(R.string.p_macs_address),
                context.getString(R.string.p_macs_directions));

        insertData(context.getString(R.string.robert_reid),
                context.getString(R.string.robert_reid_address),
                context.getString(R.string.robert_reid_directions));

        insertData(context.getString(R.string.river_bar),
                context.getString(R.string.river_bar_address),
                context.getString(R.string.river_bar_directions));

        insertData(context.getString(R.string.sin_é),
                context.getString(R.string.sin_é_address),
                context.getString(R.string.sin_é_directions));

        insertData(context.getString(R.string.south_william),
                context.getString(R.string.south_william_address),
                context.getString(R.string.south_william_directions));

        insertData(context.getString(R.string.synnotts),
                context.getString(R.string.synnotts_address),
                context.getString(R.string.synnotts_directions));

        insertData(context.getString(R.string.the_brew_dock),
                context.getString(R.string.the_brew_dock_address),
                context.getString(R.string.the_brew_dock_directions));

        insertData(context.getString(R.string.the_duke),
                context.getString(R.string.the_duke_address),
                context.getString(R.string.the_duke_directions));

        insertData(context.getString(R.string.the_globe),
                context.getString(R.string.the_globe_address),
                context.getString(R.string.the_globe_directions));

        insertData(context.getString(R.string.the_lombard),
                context.getString(R.string.the_lombard_address),
                context.getString(R.string.the_lombard_directions));

        insertData(context.getString(R.string.the_long_hall),
                context.getString(R.string.the_long_hall_address),
                context.getString(R.string.the_long_hall_directions));

        insertData(context.getString(R.string.the_long_stone),
                context.getString(R.string.the_long_stone_address),
                context.getString(R.string.the_long_stone_directions));

        insertData(context.getString(R.string.the_norseman),
                context.getString(R.string.the_norseman_address),
                context.getString(R.string.the_norseman_directions));

        insertData(context.getString(R.string.the_stags_head),
                context.getString(R.string.the_stags_head_address),
                context.getString(R.string.the_stags_head_directions));

        insertData(context.getString(R.string.the_sweeney_mongrel),
                context.getString(R.string.the_sweeney_mongrel_address),
                context.getString(R.string.the_sweeney_mongrel_directions));

        insertData(context.getString(R.string.the_temple_bar),
                   context.getString(R.string.the_temple_bar_address),
                   context.getString(R.string.the_temple_bar_directions));

        insertData(context.getString(R.string.the_workmans_club),
                context.getString(R.string.the_workmans_club_address),
                context.getString(R.string.the_workmans_club_directions));

        insertData(context.getString(R.string.turks_head),
                context.getString(R.string.turks_head_address),
                context.getString(R.string.turks_head_directions));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PUBS_TABLE = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ADDRESS TEXT, DIRECTIONS INTEGER)";
        db.execSQL(CREATE_PUBS_TABLE);
    }

    public String[] getPubNames(){
        return pub_names;
    }

    public boolean insertData(String name, String address, String directions){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, directions);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public Cursor getPub(String selected_pub){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE NAME = '" + selected_pub + "'" ,null);
        return res;
    }
}
