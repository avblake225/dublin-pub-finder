package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePubDetailsFragment extends Fragment {

    private Context context;

    private LinearLayout single_pub_details_container;

    private PubLayout pubLayout;

    private TextView findOnMapButton;
    private TextView addToFavouritesButton;

    private boolean addToFavourites;
    private String add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        context = this.getActivity();

        View view = inflater.inflate(R.layout.single_pub_details_fragment, container, false);

        single_pub_details_container = (LinearLayout) view.findViewById(R.id.single_pub_details_container);

        pubLayout = new PubLayout(context,single_pub_details_container);

        pubLayout.setPubName(SinglePubActivity.name);
        pubLayout.setPubAddress(SinglePubActivity.address);

        pubLayout.setPubRating(SinglePubActivity.rating, Integer.valueOf(context.getString(R.string.num_stars)),
                Float.valueOf(context.getString(R.string.rating_step)));

        pubLayout.setPubImage(SinglePubActivity.image, context);


        pubLayout.setPubDescription(SinglePubActivity.description);

        pubLayout.attachToParent();

        findOnMapButton = pubLayout.getFindOnMapButton();
        addToFavouritesButton = pubLayout.getAddToFavouritesButton();

        if(context.getString(R.string.no).equals(SinglePubActivity.favourite)){

            addToFavouritesButton.setText(context.getString(R.string.add_to_favourites));

            addToFavourites = true;

            add = context.getString(R.string.yes);
        }
        else{

            addToFavouritesButton.setText(context.getString(R.string.remove_from_favourites));

            addToFavourites = false;

            add = context.getString(R.string.no);
        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        findOnMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                launchMapScreen(SinglePubActivity.name, SinglePubActivity.place_ID);
            }
        });

        addToFavouritesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String query = context.getString(R.string.update) + " " + SinglePubActivity.dbManager.getTableName()
                        + " " + context.getString(R.string.set_favourite_qual_to) + "'" + add
                        + "'" + context.getString(R.string.where_placeID_equals) + "'" + SinglePubActivity.place_ID + "';";

                try{
                    SinglePubActivity.dbManager.execQuery(query);

                    if(addToFavourites){

                        addToFavouritesButton.setText(context.getString(R.string.remove_from_favourites));

                        showToastMessage(SinglePubActivity.name + " " + context.getString(R.string.has_been_added_to_favourites));

                        addToFavourites = false;
                    }
                    else{

                        addToFavouritesButton.setText(context.getString(R.string.add_to_favourites));

                        showToastMessage(SinglePubActivity.name + " " + context.getString(R.string.has_been_removed_from_favourites));

                        addToFavourites = true;
                    }

                    HomeScreen.updateFavourites = true;
                }
                catch(Exception e){

                    showToastMessage(context.getString(R.string.error_adding_to_favourites));
                }
            }
        });
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



    private void launchMapScreen(String name, String place_ID){
        Intent intent = new Intent(context, MapScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("place_ID", place_ID);
        startActivity(intent);
    }
}
