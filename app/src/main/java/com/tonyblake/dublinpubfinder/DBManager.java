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

        pub_names = new String[]{context.getString(R.string.alfie_byrnes),
                                 context.getString(R.string.bailey_bar),
                                 context.getString(R.string.bruxelles),
                                 context.getString(R.string.capitol_bar),
                                 context.getString(R.string.cassidys),
                                 context.getString(R.string.doyles),
                                 context.getString(R.string.east_side_tavern),
                                 context.getString(R.string.fibber_magee),
                                 context.getString(R.string.hartigans),
                                 context.getString(R.string.hogans),
                                 context.getString(R.string.jj_smyths),
                                 context.getString(R.string.john_kehoe),
                                 context.getString(R.string.jw_sweetman),
                                 context.getString(R.string.nearys),
                                 context.getString(R.string.odonoghues),
                                 context.getString(R.string.porterhouse_central),
                                 context.getString(R.string.river_bar),
                                 context.getString(R.string.synnotts),
                                 context.getString(R.string.the_duke),
                                 context.getString(R.string.the_globe),
                                 context.getString(R.string.grafton_lounge),
                                 context.getString(R.string.the_long_hall),
                                 context.getString(R.string.the_stags_head),
                                 context.getString(R.string.the_temple_bar),
        };

        insertData(context.getString(R.string.alfie_byrnes),
                context.getString(R.string.alfie_byrnes_address),
                context.getString(R.string.alfie_byrnes_directions));

        insertData(context.getString(R.string.bailey_bar),
                context.getString(R.string.bailey_bar_address),
                context.getString(R.string.bailey_bar_directions));

        insertData(context.getString(R.string.bruxelles),
                context.getString(R.string.bruxelles_address),
                context.getString(R.string.bruxelles_directions));

        insertData(context.getString(R.string.capitol_bar),
                context.getString(R.string.capitol_bar_address),
                context.getString(R.string.capitol_bar_directions));

        insertData(context.getString(R.string.cassidys),
                context.getString(R.string.cassidys_address),
                context.getString(R.string.cassidys_directions));

        insertData(context.getString(R.string.doyles),
                context.getString(R.string.doyles_address),
                context.getString(R.string.doyles_directions));

        insertData(context.getString(R.string.east_side_tavern),
                context.getString(R.string.east_side_tavern_address),
                context.getString(R.string.east_side_tavern_directions));

        insertData(context.getString(R.string.fibber_magee),
                context.getString(R.string.fibber_magee_address),
                context.getString(R.string.fibber_magee_directions));

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

        insertData(context.getString(R.string.nearys),
                context.getString(R.string.nearys_address),
                context.getString(R.string.nearys_directions));

        insertData(context.getString(R.string.odonoghues),
                context.getString(R.string.odonoghues_address),
                context.getString(R.string.odonoghues_directions));

        insertData(context.getString(R.string.porterhouse_central),
                context.getString(R.string.porterhouse_central_address),
                context.getString(R.string.porterhouse_central_directions));

        insertData(context.getString(R.string.river_bar),
                context.getString(R.string.river_bar_address),
                context.getString(R.string.river_bar_directions));

        insertData(context.getString(R.string.synnotts),
                context.getString(R.string.synnotts_address),
                context.getString(R.string.synnotts_directions));

        insertData(context.getString(R.string.the_duke),
                context.getString(R.string.the_duke_address),
                context.getString(R.string.the_duke_directions));

        insertData(context.getString(R.string.the_globe),
                context.getString(R.string.the_globe_address),
                context.getString(R.string.the_globe_directions));

        insertData(context.getString(R.string.grafton_lounge),
                context.getString(R.string.grafton_lounge_address),
                context.getString(R.string.grafton_lounge_directions));

        insertData(context.getString(R.string.the_long_hall),
                context.getString(R.string.the_long_hall_address),
                context.getString(R.string.the_long_hall_directions));

        insertData(context.getString(R.string.the_stags_head),
                context.getString(R.string.the_stags_head_address),
                context.getString(R.string.the_stags_head_directions));

        insertData(context.getString(R.string.the_temple_bar),
                   context.getString(R.string.the_temple_bar_address),
                   context.getString(R.string.the_temple_bar_directions));
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
