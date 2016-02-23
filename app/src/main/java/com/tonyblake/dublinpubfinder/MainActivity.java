package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;
    DBManager dbManager;
    AutoCompleteTextView tv_qwhatpub;
    String[] pubs;
    Button btn_findpub;
    String name_entered, name_chosen, name_retrieved;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        dbManager = new DBManager(this);

        tv_qwhatpub = (AutoCompleteTextView) findViewById(R.id.tv_qwhatpub);

        pubs = dbManager.getPubNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pubs);
        tv_qwhatpub.setThreshold(1);
        tv_qwhatpub.setAdapter(adapter);

        btn_findpub = (Button) findViewById(R.id.btn_findpub);
    }

    @Override
    protected void onResume() {
        super.onResume();

        name_entered = null;
        name_chosen = null;
        name_retrieved = null;

        System.out.println("Number of pubs: " + DBManager.pub_names.length);

        tv_qwhatpub.setText("");

        tv_qwhatpub.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {

                name_chosen = (String) parent.getItemAtPosition(position);
            }
        });

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                name_entered = tv_qwhatpub.getText().toString();

                if ("".equals(name_entered)) {
                    showToastMessage(context.getString(R.string.enter_a_pub));
                } else {
                    try {
                        Cursor res = dbManager.getPub(name_chosen);

                        while (res.moveToNext()) {
                            name_retrieved = res.getString(1);
                            address = res.getString(2);
                            break;
                        }

                        if (name_retrieved != null) {

                            launchPubDetailsScreen();

                        } else {
                            showToastMessage(context.getString(R.string.pubnotfound));
                        }
                    } catch (Exception e) {
                        showToastMessage(context.getString(R.string.errorfindingpub));
                    }
                }
            }
        });
    }

    private void launchPubDetailsScreen() {
        Intent intent = new Intent(this, PubDetailsScreen.class);
        intent.putExtra("name", name_retrieved);
        intent.putExtra("address", address);
        startActivity(intent);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
