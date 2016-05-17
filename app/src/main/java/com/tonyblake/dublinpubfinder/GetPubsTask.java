package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;

public class GetPubsTask extends AsyncTask<ArrayList<String>, Void, ArrayList<Pub>>{

    private Context context;

    private int image_height;
    private int image_width;
    private GoogleApiClient client;

    private ArrayList<String> placeIDs;
    private ArrayList<Pub> pubs;

    private Pub pub;

    private int multiplier;

    private PlacePhotoMetadataBuffer photoMetadataBuffer;

    private DBManager dbManager;

    public GetPubsTask(GoogleApiClient client, int image_height, int image_width, Context context, DBManager dbManager) {

        this.context = context;
        this.image_height = image_height;
        this.image_width = image_width;
        this.client = client;
        this.dbManager = dbManager;

        multiplier = Integer.valueOf(context.getString(R.string.rating_multiplier));

        pubs = new ArrayList<Pub>();
    }

    @Override
    protected ArrayList<Pub> doInBackground(ArrayList<String>... params) {

        placeIDs = params[0];

        for(String placeID: placeIDs){

            pub = new Pub();

            pub.placeID = placeID;

            String descriptionQuery = context.getString(R.string.select_all_rows_from) + dbManager.getTableName()
                    + context.getString(R.string.where_placeID_equals) + "'" + pub.placeID + "'";

            try{

                Cursor res = dbManager.rawQuery(descriptionQuery);

                res.moveToFirst();

                do {
                    pub.description = res.getString(3);
                }
                while (res.moveToNext());
            }
            catch(Exception e){

                showToastMessage(context.getString(R.string.error_retrieving_pub_description));
            }

            Places.GeoDataApi.getPlaceById(client, placeID).setResultCallback(new ResultCallback<PlaceBuffer>() {

                @Override
                public void onResult(PlaceBuffer places) {

                    if (places.getStatus().isSuccess() && places.getCount() > 0) {

                        final Place place = places.get(0);

                        pub.name = String.valueOf(place.getName());

                        pub.address = String.valueOf(place.getAddress());

                        pub.rating = place.getRating();
                    }

                    places.release();
                }
            });

            PlacePhotoMetadataResult result = Places.GeoDataApi.getPlacePhotos(client, pub.placeID).await();

            if (result.getStatus().isSuccess()) {

                photoMetadataBuffer = result.getPhotoMetadata();

                if (photoMetadataBuffer.getCount() > 0 && !isCancelled()) {

                    PlacePhotoMetadata photo = photoMetadataBuffer.get(0);

                    Bitmap image = photo.getScaledPhoto(client, image_width, image_height).await().getBitmap();

                    pub.image = image;
                }
                else{

                    pub.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_unavailable);
                }

                photoMetadataBuffer.release();
            }

            pubs.add(pub);
        }

        return pubs;
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
