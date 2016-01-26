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

    public static String[] pub_names = null;

    // this creates the database
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);

        pub_names = new String[]{context.getString(R.string.the_temple_bar),
                                 context.getString(R.string.jw_sweetman),
                                 context.getString(R.string.fibber_magees),
                                 context.getString(R.string.the_globe_bar)
        };

        insertData(context.getString(R.string.the_temple_bar),
                   context.getString(R.string.the_temple_bar_address),
                   context.getString(R.string.the_temple_bar_directions));

        insertData(context.getString(R.string.jw_sweetman),
                context.getString(R.string.jw_sweetman_address),
                context.getString(R.string.jw_sweetman_directions));

        insertData(context.getString(R.string.fibber_magees),
                context.getString(R.string.fibber_magees_address),
                context.getString(R.string.fibber_magees_directions));

        insertData(context.getString(R.string.the_globe_bar),
                context.getString(R.string.the_globe_bar_address),
                context.getString(R.string.the_globe_bar_directions));
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
        SQLiteDatabase db = this.getWritableDatabase();
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

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return res;
    }
}
