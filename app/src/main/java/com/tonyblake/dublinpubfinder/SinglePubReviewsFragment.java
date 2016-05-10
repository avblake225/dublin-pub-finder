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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SinglePubReviewsFragment extends Fragment {

    private Context context;

    private TextView tv_author_name;

    private TextView tv_rating;

    private TextView tv_text;

    private ConnectivityManager check;

    private NetworkInfo[] info;

    private boolean connectedToNetwork;

    private String[] author_name, rating, text;

    private boolean download_reviews;

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view =  inflater.inflate(R.layout.single_pub_reviews_fragment, container, false);

        context = this.getActivity();

        tv_author_name = (TextView) view.findViewById(R.id.tv_author_name);

        tv_rating = (TextView) view.findViewById(R.id.tv_rating);

        tv_text = (TextView) view.findViewById(R.id.tv_text);

        check = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        info = check.getAllNetworkInfo();

        for (int i = 0; i<info.length; i++){

            if (info[i].getState() == NetworkInfo.State.CONNECTED){

                connectedToNetwork = true;
            }
        }

        download_reviews = true;

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

                        parseJsonData(result);
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

            author_name = new String[reviews.length()];
            rating = new String[reviews.length()];
            text = new String[reviews.length()];

            for(int i=0; i < reviews.length(); i++){

                JSONObject jsonObject = reviews.getJSONObject(i);

                author_name[i]  = jsonObject.optString("author_name");

                rating[i] = jsonObject.optString("rating");

                text[i] = jsonObject.optString("text");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        tv_author_name.setText(author_name[0]);
        tv_rating.setText(rating[0]);
        tv_text.setText(text[0]);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
