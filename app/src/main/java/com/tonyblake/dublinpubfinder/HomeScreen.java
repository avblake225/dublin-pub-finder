package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends FragmentActivity implements SearchDialog.SearchDialogListener{

    private Context context;

    private boolean traditional_irish_pub, modern_pub;
    private boolean north_side_of_city, south_side_of_city;
    private boolean live_music, live_sports, cocktails, craft_beer, late_pub;

    private Button btn_findpubbyname;

    private String query;

    private ArrayList<Pub> pubs_found;

    private int num_pubs_found;

    private SearchDialog searchDialog;

    private ArrayList<Integer> options_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        context = this;

        btn_findpubbyname = (Button) findViewById(R.id.btn_findpubbyname);

        clearSelections();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                searchDialog = new SearchDialog();
                searchDialog.show(fm, "search_dialog_fragment");


            }
        });
    }

    private void search(){

        if (noSelectionMade()) {

            showToastMessage(context.getString(R.string.please_make_a_selection));
        }

        query = context.getString(R.string.select_all_rows_from) + MainActivity.dbManager.getTableName()
                            + context.getString(R.string.where) + getPubTypeSelection() + getSideOfCitySelection()
                            + getLiveMusicSelection() + getLiveSportsSelection() + getCocktailsSelection()
                            + getCraftBeerSelection() + getLatePubSelection() + context.getString(R.string.end_query);

        try {
            Cursor res = MainActivity.dbManager.getPubs(query);

            pubs_found = new ArrayList<Pub>();

            res.moveToFirst();

            do {
                Pub pub = new Pub();
                pub.name = res.getString(1);
                pub.address = res.getString(2);
                pub.description = res.getString(3);
                pub.place_ID = res.getString(4);
                pub.rating_resource_ID = res.getInt(5);
                pubs_found.add(pub);

            } while (res.moveToNext());

            num_pubs_found = pubs_found.size();

            launchPubDetailsScreen();

        } catch (Exception e) {
            showToastMessage(context.getString(R.string.no_pubs_match_your_search));
        }

        btn_findpubbyname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                launchFindPubByNameScreen();

            }
        });
    }

    private String getPubTypeSelection(){

        if(traditional_irish_pub) return context.getString(R.string.pub_type_traditional_irish);

        else if(modern_pub) return context.getString(R.string.pub_type_modern);

        else return context.getString(R.string.pub_type_traditional_irish_or_modern);
    }

    private String getSideOfCitySelection(){

        if(north_side_of_city) return context.getString(R.string.north_side_of_city);

        else if(south_side_of_city) return context.getString(R.string.south_side_of_city);

        else return context.getString(R.string.north_or_south_side_of_city);
    }

    private String getLiveMusicSelection(){

        if(live_music) return context.getString(R.string.live_music_yes);

        else return context.getString(R.string.live_music_yes_or_no);
    }

    private String getLiveSportsSelection(){

        if(live_sports) return context.getString(R.string.live_sports_yes);

        else return context.getString(R.string.live_sports_yes_or_no);
    }

    private String getCocktailsSelection(){

        if(cocktails) return context.getString(R.string.cocktails_yes);

        else return context.getString(R.string.cocktails_yes_or_no);
    }

    private String getCraftBeerSelection(){

        if(craft_beer) return context.getString(R.string.craft_beer_yes);

        else return context.getString(R.string.craft_beer_yes_or_no);
    }

    private String getLatePubSelection(){

        if(late_pub) return context.getString(R.string.late_pub_yes);

        else return context.getString(R.string.late_pub_yes_or_no);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private void launchPubDetailsScreen() {

        Intent intent = new Intent(this, PubListScreen.class);

        String[] name = new String[num_pubs_found];
        String[] address = new String[num_pubs_found];
        String[] description = new String[num_pubs_found];
        String[] place_ID = new String[num_pubs_found];
        int[] rating_resource_ID = new int[num_pubs_found];

        for(int i=0;i<num_pubs_found;i++){
            name[i] = pubs_found.get(i).name;
            address[i] = pubs_found.get(i).address;
            description[i] = pubs_found.get(i).description;
            place_ID[i] = pubs_found.get(i).place_ID;
            rating_resource_ID[i] = pubs_found.get(i).rating_resource_ID;
        }
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("description", description);
        intent.putExtra("place_ID", place_ID);
        intent.putExtra("rating_resource_ID", rating_resource_ID);

        startActivity(intent);
    }

    private void launchFindPubByNameScreen() {

        Intent intent = new Intent(this, FindPubByNameScreen.class);
        startActivity(intent);
    }

    private boolean noSelectionMade(){

        boolean no_selection_made = false;

        if(!traditional_irish_pub && !modern_pub
                && !north_side_of_city && !south_side_of_city
                && !live_music && !live_sports
                && !cocktails && !craft_beer
                && !late_pub){

            no_selection_made = true;
        }

        return no_selection_made;
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onDialogSearchButtonClick(DialogFragment dialog) {

        options_selected = SearchDialog.search_options;

        for(int option : options_selected){

            switch(option){

                case 0:
                    traditional_irish_pub = true;
                    break;

                case 1:
                    modern_pub = true;
                    break;

                case 2:
                    north_side_of_city = true;
                    break;

                case 3:
                    south_side_of_city = true;
                    break;

                case 4:
                    live_music = true;
                    break;

                case 5:
                    live_sports = true;
                    break;

                case 6:
                    cocktails = true;
                    break;

                case 7:
                    craft_beer = true;
                    break;

                case 8:
                    late_pub = true;
                    break;
            }
        }

        search();
    }

    private class Pub{

        private String name;
        private String address;
        private String description;
        private String place_ID;
        private int rating_resource_ID;
    }

    private void clearSelections(){

        traditional_irish_pub = false;
        modern_pub = false;
        north_side_of_city = false;
        south_side_of_city = false;
        live_music = false;
        live_sports = false;
        cocktails = false;
        craft_beer = false;
        late_pub = false;
    }
}
