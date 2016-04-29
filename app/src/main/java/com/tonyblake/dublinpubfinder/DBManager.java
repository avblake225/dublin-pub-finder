package com.tonyblake.dublinpubfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class DBManager extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "dublinpubdatabase.db";
    public static final String TABLE_NAME = "pubs_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PLACE_ID";
    public static final String COL_4 = "DESCRIPTION";
    public static final String COL_5 = "FAVOURITE";
    public static final String COL_6 = "PUB_TYPE";
    public static final String COL_7 = "SIDE_OF_CITY";
    public static final String COL_8 = "LIVE_MUSIC";
    public static final String COL_9 = "LIVE_SPORTS";
    public static final String COL_10 = "COCKTAILS";
    public static final String COL_11 = "CRAFT_BEER";
    public static final String COL_12 = "LATE_PUB";

    private File db_file;

    public SQLiteDatabase db;

    public String[] pub_names;

    public int i, j;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);

        db_file = context.getDatabasePath(DATABASE_NAME);

        if(db_file.exists()){

           db = SQLiteDatabase.openDatabase(db_file.toString(),null,SQLiteDatabase.OPEN_READWRITE);
        }
        else {

            db = this.getWritableDatabase();

            i = 0;
            j = 0;

            boolean against_the_grain = insertData(context.getString(R.string.against_the_grain),
                    context.getString(R.string.against_the_grain_place_ID),
                    context.getString(R.string.against_the_grain_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no));

            i++;
            if (against_the_grain) {
                j++;
            }

            boolean alfie_byrnes = insertData(context.getString(R.string.alfie_byrnes),
                    context.getString(R.string.alfie_byrnes_place_ID),
                    context.getString(R.string.alfie_byrnes_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no));

            i++;
            if (alfie_byrnes) {
                j++;
            }

            boolean alfies = insertData(context.getString(R.string.alfies),
                    context.getString(R.string.alfies_place_ID),
                    context.getString(R.string.alfies_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (alfies) {
                j++;
            }

            boolean anseo = insertData(context.getString(R.string.anseo),
                    context.getString(R.string.anseo_place_ID),
                    context.getString(R.string.anseo_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no));

            i++;
            if (anseo) {
                j++;
            }

            boolean bad_bobs = insertData(context.getString(R.string.bad_bobs),
                    context.getString(R.string.bad_bobs_place_ID),
                    context.getString(R.string.bad_bobs_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (bad_bobs) {
                j++;
            }

            boolean break_for_the_border = insertData(context.getString(R.string.break_for_the_border),
                    context.getString(R.string.break_for_the_border_place_ID),
                    context.getString(R.string.break_for_the_border_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (break_for_the_border) {
                j++;
            }

            boolean bruxelles = insertData(context.getString(R.string.bruxelles),
                    context.getString(R.string.bruxelles_place_ID),
                    context.getString(R.string.bruxelles_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (bruxelles) {
                j++;
            }

            boolean buskers = insertData(context.getString(R.string.buskers),
                    context.getString(R.string.buskers_place_ID),
                    context.getString(R.string.buskers_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (buskers) {
                j++;
            }

            boolean cafe_en_seine = insertData(context.getString(R.string.café_en_seine),
                    context.getString(R.string.café_en_seine_place_ID),
                    context.getString(R.string.café_en_seine_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (cafe_en_seine) {
                j++;
            }

            boolean cassidys = insertData(context.getString(R.string.cassidys),
                    context.getString(R.string.cassidys_place_ID),
                    context.getString(R.string.cassidys_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no));

            i++;
            if (cassidys) {
                j++;
            }

            boolean devitts = insertData(context.getString(R.string.devitts),
                    context.getString(R.string.devitts_place_ID),
                    context.getString(R.string.devitts_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (devitts) {
                j++;
            }

            boolean doheny_and_nesbitts = insertData(context.getString(R.string.doheny_and_nesbitts),
                    context.getString(R.string.doheny_and_nesbitts_place_ID),
                    context.getString(R.string.doheny_and_nesbitts_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (doheny_and_nesbitts) {
                j++;
            }

            boolean doyles = insertData(context.getString(R.string.doyles),
                    context.getString(R.string.doyles_place_ID),
                    context.getString(R.string.doyles_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes));

            i++;
            if (doyles) {
                j++;
            }

            boolean fibber_magees = insertData(context.getString(R.string.fibber_magees),
                    context.getString(R.string.fibber_magees_place_ID),
                    context.getString(R.string.fibber_magees_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.northside),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (fibber_magees) {
                j++;
            }

            boolean fitzgeralds = insertData(context.getString(R.string.fitzgeralds),
                    context.getString(R.string.fitzgeralds_place_ID),
                    context.getString(R.string.fitzgeralds_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (fitzgeralds) {
                j++;
            }

            boolean fitzsimons = insertData(context.getString(R.string.fitzsimons),
                    context.getString(R.string.fitzsimons_place_ID),
                    context.getString(R.string.fitzsimons_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (fitzsimons) {
                j++;
            }

            boolean flannerys = insertData(context.getString(R.string.flannerys),
                    context.getString(R.string.flannerys_place_ID),
                    context.getString(R.string.flannerys_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes));

            i++;
            if (flannerys) {
                j++;
            }

            boolean four_dame_lane = insertData(context.getString(R.string.four_dame_lane),
                    context.getString(R.string.four_dame_lane_place_ID),
                    context.getString(R.string.four_dame_lane_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (four_dame_lane) {
                j++;
            }

            boolean grogans = insertData(context.getString(R.string.grogans),
                    context.getString(R.string.grogans_place_ID),
                    context.getString(R.string.grogans_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (grogans) {
                j++;
            }

            boolean hartigans = insertData(context.getString(R.string.hartigans),
                    context.getString(R.string.hartigans_place_ID),
                    context.getString(R.string.hartigans_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (hartigans) {
                j++;
            }

            boolean hogans = insertData(context.getString(R.string.hogans),
                    context.getString(R.string.hogans_place_ID),
                    context.getString(R.string.hogans_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.yes));

            i++;
            if (hogans) {
                j++;
            }

            boolean james_toners = insertData(context.getString(R.string.james_toners),
                    context.getString(R.string.james_toners_place_ID),
                    context.getString(R.string.james_toners_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (james_toners) {
                j++;
            }

            boolean jj_smyths = insertData(context.getString(R.string.jj_smyths),
                    context.getString(R.string.jj_smyths_place_ID),
                    context.getString(R.string.jj_smyths_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (jj_smyths) {
                j++;
            }

            boolean john_kehoes = insertData(context.getString(R.string.john_kehoes),
                    context.getString(R.string.john_kehoes_place_ID),
                    context.getString(R.string.john_kehoes_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.southside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (john_kehoes) {
                j++;
            }

            boolean jw_sweetmans = insertData(context.getString(R.string.jw_sweetmans),
                    context.getString(R.string.jw_sweetmans_place_ID),
                    context.getString(R.string.jw_sweetmans_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.southside),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes));

            i++;
            if (jw_sweetmans) {
                j++;
            }

            boolean lanigans = insertData(context.getString(R.string.lanigans),
                    context.getString(R.string.lanigans_place_ID),
                    context.getString(R.string.lanigans_description),
                    context.getString(R.string.no),
                    context.getString(R.string.traditional_irish),
                    context.getString(R.string.northside),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.no));

            i++;
            if (lanigans) {
                j++;
            }

            boolean lagoona = insertData(context.getString(R.string.lagoona),
                    context.getString(R.string.lagoona_place_ID),
                    context.getString(R.string.lagoona_description),
                    context.getString(R.string.no),
                    context.getString(R.string.modern),
                    context.getString(R.string.northside),
                    context.getString(R.string.no),
                    context.getString(R.string.yes),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    context.getString(R.string.no));

            i++;
            if (lagoona) {
                j++;
            }

//            boolean mulligans = insertData(context.getString(R.string.mulligans),
//                    context.getString(R.string.mulligans_address),
//                    context.getString(R.string.mulligans_description),
//                    context.getString(R.string.mulligans_latitude),
//                    context.getString(R.string.mulligans_longitude),
//                    context.getString(R.string.traditional_irish),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no));
//
//            i++;
//            if (mulligans) {
//                j++;
//            }
//
//            boolean nearys = insertData(context.getString(R.string.nearys),
//                    context.getString(R.string.nearys_address),
//                    context.getString(R.string.nearys_description),
//                    context.getString(R.string.nearys_latitude),
//                    context.getString(R.string.nearys_longitude),
//                    context.getString(R.string.traditional_irish),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no));
//
//            i++;
//            if (nearys) {
//                j++;
//            }
//
//            boolean o_donoghues = insertData(context.getString(R.string.o_donoghues),
//                    context.getString(R.string.o_donoghues_address),
//                    context.getString(R.string.o_donoghues_description),
//                    context.getString(R.string.o_donoghues_latitude),
//                    context.getString(R.string.o_donoghues_longitude),
//                    context.getString(R.string.traditional_irish),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no));
//
//            i++;
//            if (o_donoghues) {
//                j++;
//            }
//
//            boolean o_reillys = insertData(context.getString(R.string.o_reillys),
//                    context.getString(R.string.o_reillys_address),
//                    context.getString(R.string.o_reillys_description),
//                    context.getString(R.string.o_reillys_latitude),
//                    context.getString(R.string.o_reillys_longitude),
//                    context.getString(R.string.modern),
//                    context.getString(R.string.northside),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes));
//
//            i++;
//            if (o_reillys) {
//                j++;
//            }
//
//            boolean peters_pub = insertData(context.getString(R.string.peters_pub),
//                    context.getString(R.string.peters_pub_address),
//                    context.getString(R.string.peters_pub_description),
//                    context.getString(R.string.peters_pub_latitude),
//                    context.getString(R.string.peters_pub_longitude),
//                    context.getString(R.string.traditional_irish),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no));
//
//            i++;
//            if (peters_pub) {
//                j++;
//            }
//
//            boolean p_macs = insertData(context.getString(R.string.p_macs),
//                    context.getString(R.string.p_macs_address),
//                    context.getString(R.string.p_macs_description),
//                    context.getString(R.string.p_macs_latitude),
//                    context.getString(R.string.p_macs_longitude),
//                    context.getString(R.string.modern),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no));
//
//            i++;
//            if (p_macs) {
//                j++;
//            }
//
//            boolean reillys = insertData(context.getString(R.string.reillys),
//                    context.getString(R.string.reillys_address),
//                    context.getString(R.string.reillys_description),
//                    context.getString(R.string.reillys_latitude),
//                    context.getString(R.string.reillys_longitude),
//                    context.getString(R.string.traditional_irish),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no));
//
//            i++;
//            if (reillys) {
//                j++;
//            }
//
//            boolean robert_reades = insertData(context.getString(R.string.robert_reades),
//                    context.getString(R.string.robert_reades_address),
//                    context.getString(R.string.robert_reades_description),
//                    context.getString(R.string.robert_reades_latitude),
//                    context.getString(R.string.robert_reades_longitude),
//                    context.getString(R.string.traditional_irish),
//                    context.getString(R.string.northside),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no));
//
//            i++;
//            if (robert_reades) {
//                j++;
//            }
//
//            boolean ryans = insertData(context.getString(R.string.ryans),
//                    context.getString(R.string.ryans_address),
//                    context.getString(R.string.ryans_description),
//                    context.getString(R.string.ryans_latitude),
//                    context.getString(R.string.ryans_longitude),
//                    context.getString(R.string.traditional_irish),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no));
//
//            i++;
//            if (ryans) {
//                j++;
//            }
//
//            boolean sams_bar = insertData(context.getString(R.string.sams_bar),
//                    context.getString(R.string.sams_bar_address),
//                    context.getString(R.string.sams_bar_description),
//                    context.getString(R.string.sams_bar_latitude),
//                    context.getString(R.string.sams_bar_longitude),
//                    context.getString(R.string.modern),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes));
//
//            i++;
//            if (sams_bar) {
//                j++;
//            }

        /*insertData(context.getString(R.string.searsons),
                context.getString(R.string.searsons_address),
                context.getString(R.string.searsons_directions));

        insertData(context.getString(R.string.sheehans),
                context.getString(R.string.sheehans_address),
                context.getString(R.string.sheehans_directions));

        insertData(context.getString(R.string.sin_é),
                context.getString(R.string.sin_é_address),
                context.getString(R.string.sin_é_directions));

        insertData(context.getString(R.string.synnotts),
                context.getString(R.string.synnotts_address),
                context.getString(R.string.synnotts_directions));

        insertData(context.getString(R.string.the_auld_dubliner),
                context.getString(R.string.the_auld_dubliner_address),
                context.getString(R.string.the_auld_dubliner_directions));

        insertData(context.getString(R.string.the_bailey_bar),
                context.getString(R.string.the_bailey_bar_address),
                context.getString(R.string.the_bailey_bar_directions));

        insertData(context.getString(R.string.the_bank),
                context.getString(R.string.the_bankers_address),
                context.getString(R.string.the_bankers_directions));

        insertData(context.getString(R.string.the_bankers),
                context.getString(R.string.the_bankers_address),
                context.getString(R.string.the_bankers_directions));*/

//            boolean the_bar_with_no_name = insertData(context.getString(R.string.the_bar_with_no_name),
//                    context.getString(R.string.the_bar_with_no_name_address),
//                    context.getString(R.string.the_bar_with_no_name_description),
//                    context.getString(R.string.the_bar_with_no_name_latitude),
//                    context.getString(R.string.the_bar_with_no_name_longitude),
//                    context.getString(R.string.modern),
//                    context.getString(R.string.southside),
//                    context.getString(R.string.no),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes),
//                    context.getString(R.string.no),
//                    context.getString(R.string.yes));
//
//            i++;
//            if (the_bar_with_no_name) {
//                j++;
//            }

        /*insertData(context.getString(R.string.the_bison_bar),
                context.getString(R.string.the_bison_bar_address),
                context.getString(R.string.the_bison_bar_directions));

        insertData(context.getString(R.string.the_brew_dock),
                context.getString(R.string.the_brew_dock_address),
                context.getString(R.string.the_brew_dock_directions));

        insertData(context.getString(R.string.the_bull_and_castle),
                context.getString(R.string.the_bull_and_castle_address),
                context.getString(R.string.the_bull_and_castle_directions));

        insertData(context.getString(R.string.the_camden_exchange),
                context.getString(R.string.the_camden_exchange_address),
                context.getString(R.string.the_camden_exchange_directions));

        insertData(context.getString(R.string.the_capitol_bar),
                context.getString(R.string.the_capitol_bar_address),
                context.getString(R.string.the_capitol_bar_directions));

        insertData(context.getString(R.string.the_celt),
                context.getString(R.string.the_celt_address),
                context.getString(R.string.the_celt_directions));

        insertData(context.getString(R.string.the_church),
                context.getString(R.string.the_church_address),
                context.getString(R.string.the_church_directions));

        insertData(context.getString(R.string.the_czech_inn),
                context.getString(R.string.the_czech_inn_address),
                context.getString(R.string.the_czech_inn_directions));

        insertData(context.getString(R.string.the_dame_tavern),
                context.getString(R.string.the_dame_tavern_address),
                context.getString(R.string.the_dame_tavern_directions));

        insertData(context.getString(R.string.the_dawson_lounge),
                context.getString(R.string.the_dawson_lounge_address),
                context.getString(R.string.the_dawson_lounge_directions));

        insertData(context.getString(R.string.the_drury_buildings),
                context.getString(R.string.the_drury_buildings_address),
                context.getString(R.string.the_drury_buildings_directions));

        insertData(context.getString(R.string.the_duke),
                context.getString(R.string.the_duke_address),
                context.getString(R.string.the_duke_directions));

        insertData(context.getString(R.string.the_east_side_tavern),
                context.getString(R.string.the_east_side_tavern_address),
                context.getString(R.string.the_east_side_tavern_directions));

        insertData(context.getString(R.string.the_exchequer),
                context.getString(R.string.the_exchequer_address),
                context.getString(R.string.the_exchequer_directions));

        insertData(context.getString(R.string.the_garage_bar),
                context.getString(R.string.the_garage_bar_address),
                context.getString(R.string.the_garage_bar_directions));

        insertData(context.getString(R.string.the_george),
                context.getString(R.string.the_george_address),
                context.getString(R.string.the_george_directions));

        insertData(context.getString(R.string.the_gingerman),
                context.getString(R.string.the_gingerman_address),
                context.getString(R.string.the_gingerman_directions));

        insertData(context.getString(R.string.the_globe),
                context.getString(R.string.the_globe_address),
                context.getString(R.string.the_globe_directions));

        insertData(context.getString(R.string.the_grafton_lounge),
                context.getString(R.string.the_grafton_lounge_address),
                context.getString(R.string.the_grafton_lounge_directions));

        insertData(context.getString(R.string.the_grand_social),
                context.getString(R.string.the_grand_social_address),
                context.getString(R.string.the_grand_social_directions));

        insertData(context.getString(R.string.the_gypsy_rose),
                context.getString(R.string.the_gypsy_rose_address),
                context.getString(R.string.the_gypsy_rose_directions));

        insertData(context.getString(R.string.the_hairy_lemon),
                context.getString(R.string.the_hairy_lemon_address),
                context.getString(R.string.the_hairy_lemon_directions));

        insertData(context.getString(R.string.the_harbourmaster),
                context.getString(R.string.the_harbourmaster_address),
                context.getString(R.string.the_harbourmaster_directions));

        insertData(context.getString(R.string.the_liquor_rooms),
                context.getString(R.string.the_liquor_rooms_address),
                context.getString(R.string.the_liquor_rooms_directions));

        insertData(context.getString(R.string.the_living_room),
                context.getString(R.string.the_living_room_address),
                context.getString(R.string.the_living_room_directions));

        insertData(context.getString(R.string.the_lombard),
                context.getString(R.string.the_lombard_address),
                context.getString(R.string.the_lombard_directions));

        insertData(context.getString(R.string.the_long_hall),
                context.getString(R.string.the_long_hall_address),
                context.getString(R.string.the_long_hall_directions));

        insertData(context.getString(R.string.the_long_stone),
                context.getString(R.string.the_long_stone_address),
                context.getString(R.string.the_long_stone_directions));

        insertData(context.getString(R.string.the_market_bar),
                context.getString(R.string.the_market_bar_address),
                context.getString(R.string.the_market_bar_directions));

        insertData(context.getString(R.string.the_mercantile),
                context.getString(R.string.the_mercantile_address),
                context.getString(R.string.the_mercantile_directions));

        insertData(context.getString(R.string.the_morgan_bar),
                context.getString(R.string.the_morgan_bar_address),
                context.getString(R.string.the_morgan_bar_directions));

        insertData(context.getString(R.string.the_norseman),
                context.getString(R.string.the_norseman_address),
                context.getString(R.string.the_norseman_directions));

        insertData(context.getString(R.string.the_octagon_bar),
                context.getString(R.string.the_octagon_bar_address),
                context.getString(R.string.the_octagon_bar_directions));

        insertData(context.getString(R.string.the_odeon),
                context.getString(R.string.the_odeon_address),
                context.getString(R.string.the_odeon_directions));

        insertData(context.getString(R.string.the_old_stand),
                context.getString(R.string.the_old_stand_address),
                context.getString(R.string.the_old_stand_directions));

        insertData(context.getString(R.string.the_oliver_st_john_gogarty),
                context.getString(R.string.the_oliver_st_john_gogarty_address),
                context.getString(R.string.the_oliver_st_john_gogarty_directions));

        insertData(context.getString(R.string.the_palace_bar),
                context.getString(R.string.the_palace_bar_address),
                context.getString(R.string.the_palace_bar_directions));

        insertData(context.getString(R.string.the_porterhouse_central),
                context.getString(R.string.the_porterhouse_central_address),
                context.getString(R.string.the_porterhouse_central_directions));

        insertData(context.getString(R.string.the_river_bar),
                context.getString(R.string.the_river_bar_address),
                context.getString(R.string.the_river_bar_directions));

        insertData(context.getString(R.string.the_south_william),
                context.getString(R.string.the_south_william_address),
                context.getString(R.string.the_south_william_directions));

        insertData(context.getString(R.string.the_stags_head),
                context.getString(R.string.the_stags_head_address),
                context.getString(R.string.the_stags_head_directions));

        insertData(context.getString(R.string.the_sweeney_mongrel),
                context.getString(R.string.the_sweeney_mongrel_address),
                context.getString(R.string.the_sweeney_mongrel_directions));

        insertData(context.getString(R.string.the_temple_bar),
                   context.getString(R.string.the_temple_bar_address),
                   context.getString(R.string.the_temple_bar_directions));

        insertData(context.getString(R.string.the_turks_head),
                context.getString(R.string.the_turks_head_address),
                context.getString(R.string.the_turks_head_directions));

        insertData(context.getString(R.string.whelans),
                context.getString(R.string.whelans_address),
                context.getString(R.string.whelans_directions));

        insertData(context.getString(R.string.the_woolshed),
                context.getString(R.string.the_woolshed_address),
                context.getString(R.string.the_woolshed_directions));

        insertData(context.getString(R.string.the_workmans_club),
                context.getString(R.string.the_workmans_club_address),
                context.getString(R.string.the_workmans_club_directions));*/
        }

        pub_names = new String[]{

                context.getString(R.string.against_the_grain),
                context.getString(R.string.alfie_byrnes),
                context.getString(R.string.alfies),
                context.getString(R.string.anseo),
                context.getString(R.string.bad_bobs),
                context.getString(R.string.break_for_the_border),
                context.getString(R.string.bruxelles),
                context.getString(R.string.buskers),
                context.getString(R.string.café_en_seine),
                context.getString(R.string.cassidys),
                context.getString(R.string.devitts),
                context.getString(R.string.doheny_and_nesbitts),
                context.getString(R.string.doyles),
                context.getString(R.string.fibber_magees),
                context.getString(R.string.fitzgeralds),
                context.getString(R.string.fitzsimons),
                context.getString(R.string.flannerys),
                context.getString(R.string.four_dame_lane),
                context.getString(R.string.grogans),
                context.getString(R.string.hartigans),
                context.getString(R.string.hogans),
                context.getString(R.string.james_toners),
                context.getString(R.string.jj_smyths),
                context.getString(R.string.john_kehoes),
                context.getString(R.string.jw_sweetmans),
                context.getString(R.string.lanigans),
                context.getString(R.string.lagoona),
                context.getString(R.string.mulligans),
                context.getString(R.string.nearys),
                context.getString(R.string.o_donoghues),
                context.getString(R.string.o_reillys),
                context.getString(R.string.peters_pub),
                context.getString(R.string.p_macs),
                context.getString(R.string.reillys),
                context.getString(R.string.robert_reades),
                context.getString(R.string.ryans),
                context.getString(R.string.sams_bar),
                context.getString(R.string.the_bar_with_no_name)
        };

        int num_pubs_in_autocomplete = pub_names.length;
    }

    public int getNumPubsListed(){
        return i;
    }

    public int getNumPubsAddedToDB(){
        return j;
    }

    public String[] getPubNames() { return pub_names; }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PUBS_TABLE = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PLACE_ID TEXT, DESCRIPTION TEXT, FAVOURITE TEXT, PUB_TYPE TEXT, SIDE_OF_CITY TEXT, LIVE_MUSIC TEXT, LIVE_SPORTS TEXT, COCKTAILS TEXT, CRAFT_BEER TEXT, LATE_PUB TEXT)";
        db.execSQL(CREATE_PUBS_TABLE);
    }

    public void execQuery(String query){
        db.execSQL(query);
    }

    public boolean insertData(String name, String place_ID, String description, String favourite, String pub_type, String side_of_city, String live_music, String live_sports, String cocktails, String craft_beer, String late_pub){

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, place_ID);
        contentValues.put(COL_4, description);
        contentValues.put(COL_5, favourite);
        contentValues.put(COL_6, pub_type);
        contentValues.put(COL_7, side_of_city);
        contentValues.put(COL_8, live_music);
        contentValues.put(COL_9, live_sports);
        contentValues.put(COL_10, cocktails);
        contentValues.put(COL_11, craft_beer);
        contentValues.put(COL_12, late_pub);

        long result = db.insert(TABLE_NAME, null, contentValues);

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

    public Cursor getPubs(String query){
        Cursor res = db.rawQuery(query,null);
        return res;
    }

    public String getTableName(){

        return TABLE_NAME;
    }
}
