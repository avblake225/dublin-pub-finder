package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Context context;
    DBManager dbManager;
    AutoCompleteTextView tv_qwhatpub;
    String[] pubs;
    Button btn_findpub;
    String name;
    String address;
    String directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        dbManager = new DBManager(this);

        tv_qwhatpub = (AutoCompleteTextView)findViewById(R.id.tv_qwhatpub);

        pubs = dbManager.getPubNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pubs);
        tv_qwhatpub.setThreshold(1);
        tv_qwhatpub.setAdapter(adapter);

        btn_findpub = (Button)findViewById(R.id.btn_findpub);
    }

    @Override
    protected void onResume(){
        super.onResume();

        System.out.println("Number of pubs: " + DBManager.pub_names.length);

        name = null;

        tv_qwhatpub.setText("");

        tv_qwhatpub.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {

                name = (String) parent.getItemAtPosition(position);
            }
        });

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Cursor res = dbManager.getPub(name);

                    while (res.moveToNext()) {
                        name = res.getString(1);
                        address = res.getString(2);
                        directions = res.getString(3);
                        break;
                    }

                    if (name != null) {
                        launchPubDetailsScreen();
                    } else {
                        showToastMessage(context.getString(R.string.pubnotfound));
                    }
                } catch (Exception e) {
                    showToastMessage(context.getString(R.string.errorfindingpub));
                }
            }
        });
    }

    private void launchPubDetailsScreen(){
        Intent intent = new Intent(this, PubDetailsScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("directions", directions);
        startActivity(intent);
    }

    private void showToastMessage(CharSequence text){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        name = null;
    }
}
