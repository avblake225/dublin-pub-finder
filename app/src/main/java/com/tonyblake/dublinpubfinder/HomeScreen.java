package com.tonyblake.dublinpubfinder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements SearchDialog.SearchDialogListener, SearchByNameDialog.SearchByNameDialogListener,
                                                 GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private Context context;

    public static DBManager dbManager;

    public  HomeScreen homeScreen;

    private Toolbar actionBar;

    private DrawerLayout dLayout;
    private ListView dList;
    private ArrayAdapter<String> drawerAdapter;

    private TextView tv_home_screen;
    private RelativeLayout tv_home_screen_parent;

    private RelativeLayout single_pub_details_container_parent;
    private LinearLayout single_pub_details_container;

    private GoogleApiClient client;

    private boolean traditional_irish_pub, modern_pub;
    private boolean north_side_of_city, south_side_of_city;
    private boolean live_music, live_sports, cocktails, craft_beer, late_pub;

    private String query;

    private ArrayList<String> placeIDs;

    private int num_place_IDs_found;

    private SearchDialog searchDialog;

    private SearchByNameDialog searchByNameDialog;

    private ArrayList<Integer> options_selected;

    private int downloadedPhoto_width;
    private int downloadedPhoto_height;

    private String num_pubs_str;

    private ListView list;
    private PubAdapter adapter;

    private ArrayList<Pub> pubsToDisplay;

    private ProgressDialog progressDialog;

    private String placeID;
    private boolean refresh;
    private int refreshMode;
    private boolean findAPubRefresh;
    private boolean searchForPubNameRefresh;
    private boolean favouritesRefresh;

    private boolean getFavourites;

    private ImageView reloadButton;

    public static boolean updateFavourites;

    private LinearLayout search_options_layout_1;
    private LinearLayout search_options_layout_2;
    private LinearLayout search_options_layout_3;
    private LinearLayout search_options_layout_4;

    private TextView tv_search_option;

    private LinearLayout.LayoutParams option_params;

    private Display display;

    private int max_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        // Show Status Bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);

        context = this;

        dbManager = new DBManager(this);

        int num_pubs_listed = dbManager.getNumPubsListed();
        int num_pubs_added_to_DB = dbManager.getNumPubsAddedToDB();

        homeScreen = this;

        // Set up Action Bar
        actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        actionBar.setNavigationIcon(context.getResources().getDrawable(R.drawable.ic_menu_white_24dp));
        actionBar.setTitle(context.getString(R.string.app_name));
        actionBar.setTitleTextColor(context.getResources().getColor(R.color.white));
        actionBar.setOverflowIcon(context.getResources().getDrawable(R.drawable.ic_refresh_white_24dp));

        // Set up Navigation Drawer
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);
        drawerAdapter = new ArrayAdapter<String>(this,R.layout.drawer_item_layout,context.getResources().getStringArray(R.array.menu_items));
        dList.setAdapter(drawerAdapter);

        // Set up Home Screen TextView
        tv_home_screen = (TextView)findViewById(R.id.tv_home_screen);
        tv_home_screen.setText(context.getString(R.string.welcome));
        tv_home_screen_parent = (RelativeLayout) findViewById(R.id.tv_home_screen_parent);

        // Set up Google API Client
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        client.connect();

        downloadedPhoto_width = (int)context.getResources().getDimension(R.dimen.pub_image_width);
        downloadedPhoto_height = (int)context.getResources().getDimension(R.dimen.pub_image_height);

        // Set up Single Pub Details
        single_pub_details_container_parent = (RelativeLayout) findViewById(R.id.single_pub_details_container_parent);
        single_pub_details_container = (LinearLayout) findViewById(R.id.single_pub_details_container);

        setRefreshMode(0);

        getFavourites = false;

        reloadButton = (ImageView) findViewById(R.id.reload_btn);

        updateFavourites = false;

        search_options_layout_1 = (LinearLayout) findViewById(R.id.search_options_layout_1);
        search_options_layout_2 = (LinearLayout) findViewById(R.id.search_options_layout_2);
        search_options_layout_3 = (LinearLayout) findViewById(R.id.search_options_layout_3);
        search_options_layout_4 = (LinearLayout) findViewById(R.id.search_options_layout_4);

        option_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        option_params.setMargins((int)context.getResources().getDimension(R.dimen.option_margin_left),
                                 (int)context.getResources().getDimension(R.dimen.option_margin_top),
                                 (int)context.getResources().getDimension(R.dimen.option_margin_right),
                                 (int)context.getResources().getDimension(R.dimen.option_margin_bottom));

        display = getWindowManager().getDefaultDisplay();
        max_width = display.getWidth() - 200;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(getFavourites){

            if(updateFavourites){

                search(null);
            }
        }

        actionBar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dLayout.openDrawer(dList);
            }
        });

        reloadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                refresh = true;

                if (refreshMode == 1) {

                    search(null);

                } else if (refreshMode == 2) {

                    search(placeID);

                } else if (refreshMode == 3) {

                    search(placeID);

                } else {

                    showToastMessage(context.getString(R.string.nothing_to_reload));
                }
            }
        });

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fm = getSupportFragmentManager();

                switch (position) {

                    // Find A Pub
                    case 0:

                        dLayout.closeDrawer(dList);

                        searchDialog = new SearchDialog();
                        searchDialog.show(fm, "search_dialog_fragment");

                        break;

                    // Search For Pub Name
                    case 1:

                        dLayout.closeDrawer(dList);

                        searchByNameDialog = new SearchByNameDialog();
                        searchByNameDialog.show(fm, "search_by_name_dialog_fragment");

                        break;

                    // Favourites
                    case 2:

                        dLayout.closeDrawer(dList);

                        updateFavourites = false;

                        clearScreen();

                        setRefreshMode(3);

                        tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;

                        getFavourites = true;

                        search(null);

                        break;

                    // About Author
                    case 3:

                        dLayout.closeDrawer(dList);

                        clearScreen();

                        setRefreshMode(0);

                        single_pub_details_container_parent.getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;

                        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        View about_author_layout = inflater.inflate(R.layout.about_author_layout, null);

                        single_pub_details_container.addView(about_author_layout);

                        break;

                    // Disclaimer
                    case 4:

                        dLayout.closeDrawer(dList);

                        clearScreen();

                        setRefreshMode(0);

                        tv_home_screen.setText(context.getString(R.string.disclaimer));

                        tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;

                        break;
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        client.disconnect();
    }

    private void search(String placeID){

        placeIDs = new ArrayList<>();

        if(getFavourites){

            query = context.getString(R.string.select_all_rows_from) + dbManager.getTableName()
                    + " " + context.getString(R.string.where_favourite_equals_yes);
        }
        else{

            query = context.getString(R.string.select_all_rows_from) + dbManager.getTableName()
                    + context.getString(R.string.where) + getPubTypeSelection() + getSideOfCitySelection()
                    + getLiveMusicSelection() + getLiveSportsSelection() + getCocktailsSelection()
                    + getCraftBeerSelection() + getLatePubSelection() + context.getString(R.string.end_query);
        }

        num_pubs_str = "";

        try {
            Cursor res = dbManager.getPubs(query);

            res.moveToFirst();

            if(placeID == null){

                do {
                    placeID = res.getString(2);
                    placeIDs.add(placeID);

                } while (res.moveToNext());
            }
            else placeIDs.add(placeID);

            num_place_IDs_found = placeIDs.size();

            if(num_place_IDs_found == 1){

                if(getFavourites){

                    num_pubs_str = num_place_IDs_found + " " + context.getString(R.string.favourite);
                }
                else{

                    num_pubs_str = num_place_IDs_found + " " + context.getString(R.string.pub_found);
                }
            }
            else if(num_place_IDs_found > 1){

                if(getFavourites){

                    num_pubs_str = num_place_IDs_found + " " + context.getString(R.string.favourites);
                }
                else{

                    num_pubs_str = num_place_IDs_found + " " + context.getString(R.string.pubs_found);
                }
            }

            getPubItems();
        }
        catch (Exception e) {

            if(getFavourites){

                tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;
                tv_home_screen.setText(context.getString(R.string.no_favourites));
            }
            else{

                showToastMessage(context.getString(R.string.no_pubs_match_your_search));
            }
        }
    }

    private void getPubItems(){

        pubsToDisplay = new ArrayList<>();

        downloadedPhoto_width = (int)context.getResources().getDimension(R.dimen.pub_image_width);
        downloadedPhoto_height = (int)context.getResources().getDimension(R.dimen.pub_image_height);

        new GetPubsTask(client, downloadedPhoto_height, downloadedPhoto_width, context) {

            @Override
            protected void onPreExecute() {

                progressDialog = new ProgressDialog(context);

                if(refresh){

                    progressDialog.setMessage(context.getString(R.string.reloading));
                }
                else if(getFavourites){

                    if(updateFavourites){

                        progressDialog.setMessage(context.getString(R.string.updating_favourites));
                    }
                    else{

                        progressDialog.setMessage(context.getString(R.string.getting_favourites));
                    }
                }
                else{

                    progressDialog.setMessage(context.getString(R.string.searching));
                }

                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(ArrayList<Pub> pubs_returned) {

                if (pubs_returned != null) {

                    pubsToDisplay = pubs_returned;

                    progressDialog.dismiss();

                    updateFavourites = false;

                    displayPubs();

                }
            }
        }.execute(placeIDs);
    }

    private void setRefreshMode(int mode){

        refreshMode = mode;
        refresh = false;
        getFavourites = false;

        switch(mode){

            // Nothing to Refresh
            case 0:
                findAPubRefresh = false;
                searchForPubNameRefresh = false;
                favouritesRefresh = false;
                break;

            // Refresh "Find A Pub"
            case 1:
                findAPubRefresh = true;
                searchForPubNameRefresh = false;
                favouritesRefresh = false;
                break;

            // Refresh "Search For Pub Name"
            case 2:
                findAPubRefresh = false;
                searchForPubNameRefresh = true;
                favouritesRefresh = false;
                break;

            // Refresh "Favourites"
            case 3:
                findAPubRefresh = false;
                searchForPubNameRefresh = true;
                favouritesRefresh = true;
                break;
        }
    }

    private void displayPubs(){

        tv_home_screen.setText(num_pubs_str);

        Resources res = getResources();

        list= ( ListView )findViewById(R.id.pub_list);

        adapter = new PubAdapter( homeScreen, pubsToDisplay, res);

        list.setAdapter(adapter);
    }

    public void onItemClick(int mPosition){

        Pub pub = pubsToDisplay.get(mPosition);

        String query = context.getString(R.string.select_all_rows_from) + dbManager.getTableName()
                + context.getString(R.string.where_placeID_equals) + "'" + pub.placeID + "';";

        String favourite = "";

        try{
            Cursor res = dbManager.getPubs(query);

            res.moveToFirst();

            do {
                favourite = res.getString(4);
            }
            while (res.moveToNext());
        }
        catch(Exception e){

            showToastMessage(context.getString(R.string.error_determining_favourite));
        }

        Intent intent = new Intent(this, SinglePubDetailsScreen.class);

        intent.putExtra("place_ID", pub.placeID);
        intent.putExtra("name", pub.name);
        intent.putExtra("address", pub.address);
        intent.putExtra("rating", pub.rating);
        intent.putExtra("image", pub.image);
        intent.putExtra("description", pub.description);
        intent.putExtra("favourite", favourite);

        startActivity(intent);
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
    public void onDestroy(){
        super.onDestroy();
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onSearchDialogSearchClick(DialogFragment dialog) {

        refresh = false;
        setRefreshMode(1);

        clearAllSelections();

        clearScreen();

        options_selected = SearchDialog.search_options;

        for(int option : options_selected){

            switch(option){

                case 0:

                    traditional_irish_pub = true;

                    displaySearchOptions(context.getString(R.string.traditional_irish_pub));

                    break;

                case 1:

                    modern_pub = true;

                    displaySearchOptions(context.getString(R.string.modern_pub));

                    break;

                case 2:

                    north_side_of_city = true;

                    displaySearchOptions(context.getString(R.string._north_side_of_city));

                    break;

                case 3:

                    south_side_of_city = true;

                    displaySearchOptions(context.getString(R.string._south_side_of_city));

                    break;

                case 4:

                    live_music = true;

                    displaySearchOptions(context.getString(R.string.live_music));

                    break;

                case 5:

                    live_sports = true;

                    displaySearchOptions(context.getString(R.string.live_sports));

                    break;

                case 6:

                    cocktails = true;

                    displaySearchOptions(context.getString(R.string.cocktails));

                    break;

                case 7:

                    craft_beer = true;

                    displaySearchOptions(context.getString(R.string.craft_beer));

                    break;

                case 8:

                    late_pub = true;

                    displaySearchOptions(context.getString(R.string.late_pub));

                    break;
            }
        }

        search(null);
    }

    private void displaySearchOptions(String option){

        tv_search_option = new TextView(context);

        tv_search_option.setLayoutParams(option_params);

        tv_search_option.setPadding((int) context.getResources().getDimension(R.dimen.option_padding_left),
                (int) context.getResources().getDimension(R.dimen.option_padding_top),
                (int) context.getResources().getDimension(R.dimen.option_padding_right),
                (int) context.getResources().getDimension(R.dimen.option_padding_bottom));

        tv_search_option.setText(option);

        tv_search_option.setTextColor(context.getResources().getColor(R.color.white));

        tv_search_option.setBackground(context.getResources().getDrawable(R.drawable.clr_normal_gray));

        search_options_layout_1.measure(0, 0);

        int layout_1_width = search_options_layout_1.getMeasuredWidth();

        search_options_layout_2.measure(0, 0);

        int layout_2_width = search_options_layout_2.getMeasuredWidth();

        search_options_layout_3.measure(0, 0);

        int layout_3_width = search_options_layout_3.getMeasuredWidth();

        if(layout_1_width >= max_width){

            if(layout_2_width >= max_width){

                if(layout_3_width >= max_width){

                    search_options_layout_4.addView(tv_search_option);
                }
                else{

                    search_options_layout_3.addView(tv_search_option);
                }
            }
            else{

                search_options_layout_2.addView(tv_search_option);
            }
        }
        else{

            search_options_layout_1.addView(tv_search_option);
        }
    }

    @Override
    public void onSearchByNameDialogSearchClick(DialogFragment dialog, String placeID) {

        this.placeID = placeID;

        refresh = false;
        setRefreshMode(2);

        clearScreen();

        search(placeID);
    }

    private void clearScreen(){

        tv_home_screen.setText("");
        tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;

        single_pub_details_container.removeAllViews();
        single_pub_details_container_parent.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        single_pub_details_container_parent.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;

        if(list != null) list.setAdapter(null);

        search_options_layout_1.removeAllViews();
        search_options_layout_2.removeAllViews();
        search_options_layout_3.removeAllViews();
        search_options_layout_4.removeAllViews();
    }

    private void clearAllSelections(){

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

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
