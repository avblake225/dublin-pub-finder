package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    private Context context;
    public static DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show Status Bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);

        context = this;

        dbManager = new DBManager(this);

        int num_pubs_listed = dbManager.getNumPubsListed();
        int num_pubs_added_to_DB = dbManager.getNumPubsAddedToDB();
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {

            public void run() {

                try{
                    Thread.sleep(2000);
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
