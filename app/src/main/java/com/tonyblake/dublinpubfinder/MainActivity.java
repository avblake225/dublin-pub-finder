package com.tonyblake.dublinpubfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
