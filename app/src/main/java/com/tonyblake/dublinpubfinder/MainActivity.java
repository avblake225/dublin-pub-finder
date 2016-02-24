package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Context context;
    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        btn_enter = (Button) findViewById(R.id.btn_enter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                launchHomeScreen();

            }
        });
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
