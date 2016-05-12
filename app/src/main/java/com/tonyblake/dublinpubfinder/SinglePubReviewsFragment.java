package com.tonyblake.dublinpubfinder;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SinglePubReviewsFragment extends Fragment {

    private Context context;

    private LinearLayout review_layout_container;

    private ArrayList<Review> userReviews;

    private ConnectivityManager check;

    private NetworkInfo[] info;

    private boolean connectedToNetwork;

    private boolean download_reviews;

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view =  inflater.inflate(R.layout.single_pub_reviews_fragment, container, false);

        context = this.getActivity();

        review_layout_container = (LinearLayout)view.findViewById(R.id.review_layout_container);

        check = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        info = check.getAllNetworkInfo();

        for (int i = 0; i<info.length; i++){

            if (info[i].getState() == NetworkInfo.State.CONNECTED){

                connectedToNetwork = true;
            }
        }

        download_reviews = true;

        userReviews = new ArrayList<>();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        if(connectedToNetwork){

            if(download_reviews){

                new GetReviewsTask(context){

                    @Override
                    protected void onPreExecute(){

                        progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage(context.getString(R.string.getting_reviews));
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setIndeterminate(true);
                        progressDialog.show();
                    }

                    @Override
                    protected void onPostExecute(String result){
                        super.onPostExecute(result);

                        progressDialog.dismiss();

                        download_reviews = false;

                        if(result != null){

                            parseJsonData(result);
                        }
                        else{

                            showToastMessage(context.getString(R.string.error_downloading_reviews));
                        }
                    }
                }.execute(SinglePubActivity.place_ID);
            }
        }
    }

    private void parseJsonData(String data){

        try {

            JSONObject reader = new JSONObject(data);

            JSONObject result = reader.optJSONObject("result");

            JSONArray reviews  = result.optJSONArray("reviews");

            if(reviews != null) {

                for (int i = 0; i < reviews.length(); i++) {

                    JSONObject jsonObject = reviews.getJSONObject(i);

                    Review review = new Review();

                    review.author_name = jsonObject.optString("author_name");

                    review.rating = jsonObject.optString("rating");

                    review.text = jsonObject.optString("text");

                    userReviews.add(review);
                }

                for (Review review : userReviews) {

                    ReviewLayout reviewLayout = new ReviewLayout(context, review_layout_container);

                    reviewLayout.setAuthorName(review.author_name);

                    reviewLayout.setRating(review.rating);

                    reviewLayout.setText(review.text);

                    reviewLayout.finish();
                }
            }
            else{

                showToastMessage(context.getString(R.string.no_reviews_found));
            }
        }
        catch (JSONException e) {

            showToastMessage(context.getString(R.string.error_downloading_reviews));
        }
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private class Review{

        String author_name;
        String rating;
        String text;
    }
}
