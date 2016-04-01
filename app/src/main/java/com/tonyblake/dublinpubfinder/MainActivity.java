package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private Context context;
    public static DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        dbManager = new DBManager(this);

        int num_pubs_listed = dbManager.getNumPubsListed();
        int num_pubs_added_to_DB = dbManager.getNumPubsAddedToDB();

        Utilities.populatePubRatingMap(context);
        Utilities.populatePubImageMap(context);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {

            public void run() {

                try{
                    Thread.sleep(3000);
                    launchHomeScreen();
                }
                catch(InterruptedException e){
                    System.out.println("Thread exception");
                }
            }
        }).start();
    }

    private void launchHomeScreen(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
