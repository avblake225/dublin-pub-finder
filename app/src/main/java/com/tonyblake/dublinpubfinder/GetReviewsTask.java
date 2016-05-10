package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetReviewsTask extends AsyncTask<String,Void,String> {

    private Context context;

    private String content = "";

    public GetReviewsTask(Context context){

        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String place_id = params[0];

        String url_name = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + place_id + "&key=" + context.getString(R.string.server_key);

        URL url = null;

        try {

            url = new URL(url_name);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection conn = null;

        try {

            conn = (HttpURLConnection) url.openConnection();

            conn.connect();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        InputStream is = null;

        try {

            is = conn.getInputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String data = "";

        try {

            while((data = reader.readLine()) != null){

                content += data + "\n";
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
