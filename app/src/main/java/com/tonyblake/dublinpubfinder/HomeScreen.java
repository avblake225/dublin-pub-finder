package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class HomeScreen extends AppCompatActivity implements SearchDialog.SearchDialogListener,
                                                 GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private Context context;

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

    private ArrayList<Pub> pubs_found;

    private int num_pubs_found;

    private PubItem pubItem;

    private SearchDialog searchDialog;

    private SearchByNameDialog searchByNameDialog;

    private ArrayList<Integer> options_selected;

    private Bitmap downloadedPhoto;
    private int downloadedPhoto_width;
    private int downloadedPhoto_height;

    private String num_pubs_returned_str;

    private ListView list;
    private PubAdapter adapter;

    public ArrayList<PubItem> pubItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        context = this;

        homeScreen = this;

        // Set up Action Bar
        actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        actionBar.setNavigationIcon(context.getResources().getDrawable(R.drawable.ic_menu_white_24dp));
        actionBar.setTitle(context.getString(R.string.app_name));
        actionBar.setTitleTextColor(context.getResources().getColor(R.color.white));

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        actionBar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dLayout.openDrawer(dList);
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

                        //dLayout.closeDrawer(dList);

                        //tv_home_screen.setText(context.getString(R.string.no_favourites));

                        //tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;

                        showToastMessage(context.getString(R.string.feature_unavailable));

                        break;

                    // About Author
                    case 3:

                        dLayout.closeDrawer(dList);

                        tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;

                        tv_home_screen.setText(context.getString(R.string.author));

                        single_pub_details_container.removeAllViews();

                        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        View about_author_layout = inflater.inflate(R.layout.about_author_layout, null);

                        single_pub_details_container.addView(about_author_layout);

                        break;

                    // Disclaimer
                    case 4:

                        dLayout.closeDrawer(dList);

                        single_pub_details_container.removeAllViews();

                        String disclaimer = "<i>" + context.getString(R.string.disclaimer) + "</i>";

                        tv_home_screen.setText((Html.fromHtml(disclaimer)));

                        tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;

                        break;
                }
            }
        });
    }

    private void launchMapScreen(String name, String place_ID){
        Intent intent = new Intent(this, MapScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("place_ID", place_ID);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();

        client.disconnect();
    }

    private void search(){

        pubs_found = new ArrayList<>();

        query = context.getString(R.string.select_all_rows_from) + MainActivity.dbManager.getTableName()
                            + context.getString(R.string.where) + getPubTypeSelection() + getSideOfCitySelection()
                            + getLiveMusicSelection() + getLiveSportsSelection() + getCocktailsSelection()
                            + getCraftBeerSelection() + getLatePubSelection() + context.getString(R.string.end_query);

        if(pubItems.size() != 0){

            pubItems.removeAll(pubItems);
            adapter.notifyDataSetChanged();
        }

        num_pubs_returned_str = "";

        try {
            Cursor res = MainActivity.dbManager.getPubs(query);

            res.moveToFirst();

            do {
                Pub pub = new Pub();
                pub.name = res.getString(1);
                pub.address = res.getString(2);
                pub.description = res.getString(3);
                pub.place_ID = res.getString(4);
                pub.rating = res.getString(5);
                pubs_found.add(pub);

            } while (res.moveToNext());

            num_pubs_found = pubs_found.size();

            if(num_pubs_found == 1){
                num_pubs_returned_str = num_pubs_found + " " + context.getString(R.string.pub_found);
            }
            else if(num_pubs_found > 1){
                num_pubs_returned_str = num_pubs_found + " " + context.getString(R.string.pubs_found);
            }

            displayPubs();
        }
        catch (Exception e) {
            showToastMessage(context.getString(R.string.no_pubs_match_your_search));
        }

        tv_home_screen.setText(num_pubs_returned_str);
    }

    private void displayPubs(){

        downloadedPhoto = null;
        downloadedPhoto_width = (int)context.getResources().getDimension(R.dimen.pub_item_image_width);
        downloadedPhoto_height = (int)context.getResources().getDimension(R.dimen.pub_item_image_height);

        for(int i=0;i<num_pubs_found;i++){

            pubItem = new PubItem();

            pubItem.setPubName(pubs_found.get(i).name);

            pubItem.setPubAddress(pubs_found.get(i).address);

            pubItem.setPubRating(pubs_found.get(i).rating);

            pubItem.setPubPlaceId(pubs_found.get(i).place_ID);

            getPubImage(pubItem, pubItem.getPubPlaceId());

            pubItem.setPubDescription(pubs_found.get(i).description);

            pubItems.add(pubItem);
        }

        Resources res = getResources();

        list= ( ListView )findViewById( R.id.pub_list );

        adapter = new PubAdapter( homeScreen, pubItems, res);

        list.setAdapter(adapter);
    }

    private void getPubImage(final PubItem pubItem, String placeId) {

        new PhotoTask(downloadedPhoto_width, downloadedPhoto_height, client) {

            @Override
            protected void onPostExecute(AttributedPhoto attributedPhoto) {

                if (attributedPhoto != null) {

                    downloadedPhoto = attributedPhoto.bitmap;
                }
                else{

                    downloadedPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.image_unavailable);
                }

                pubItem.setPubImage(downloadedPhoto);
            }
        }.execute(placeId);
    }

    private void getPubImage(final PubLayout featured_pub, String placeId) {

        new PhotoTask(downloadedPhoto_width, downloadedPhoto_height, client) {

            @Override
            protected void onPostExecute(AttributedPhoto attributedPhoto) {

                if (attributedPhoto != null) {

                    downloadedPhoto = attributedPhoto.bitmap;
                }
                else{

                    downloadedPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.image_unavailable);
                }

                featured_pub.setPubImage(downloadedPhoto);
            }
        }.execute(placeId);
    }

    public void onItemClick(int mPosition){

        PubItem pubItem = pubItems.get(mPosition);

        Intent intent = new Intent(this, SinglePubDetailsScreen.class);

        intent.putExtra("name",pubItem.getPubName());
        intent.putExtra("address",pubItem.getPubAddress());
        intent.putExtra("description",pubItem.getPubDescription());
        intent.putExtra("place_ID", pubItem.getPubPlaceId());
        intent.putExtra("rating",pubItem.getPubRating());

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

        clearAllSelections();

        single_pub_details_container.removeAllViews();
        single_pub_details_container_parent.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        single_pub_details_container_parent.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        tv_home_screen_parent.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;

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
