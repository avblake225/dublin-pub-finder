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

public class SearchByNameDialog extends AppCompatActivity {

    private Context context;
    private DBManager dbManager;
    private AutoCompleteTextView tv_pub_name;
    private Button btn_search;
    public static String pub_name = "";
    private Pub pub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_name_dialog);

        context = this;

        dbManager = MainActivity.dbManager;

        tv_pub_name = (AutoCompleteTextView) findViewById(R.id.tv_pub_name);

        btn_search = (Button) findViewById(R.id.btn_search);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dbManager.getPubNames());
        tv_pub_name.setAdapter(adapter);
        tv_pub_name.setThreshold(1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        tv_pub_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pub_name = (String) parent.getItemAtPosition(position);

            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String query = context.getString(R.string.select_all_rows_from) + MainActivity.dbManager.getTableName()
                        + context.getString(R.string.where) + context.getString(R.string.name_equals)
                        + pub_name + "'" + context.getString(R.string.end_query);

                try {
                    Cursor res = MainActivity.dbManager.getPubs(query);

                    res.moveToFirst();

                    do {
                        pub = new Pub();
                        pub.name = res.getString(1);
                        pub.address = res.getString(2);
                        pub.description = res.getString(3);
                        pub.place_ID = res.getString(4);
                        pub.rating_resource_ID = res.getInt(5);

                    } while (res.moveToNext());

                    launchSinglePubDetailsScreen();

                } catch (Exception e) {
                    showToastMessage(context.getString(R.string.no_pubs_match_your_search));
                }
            }
        });
    }

    private void launchSinglePubDetailsScreen(){

        Intent intent = new Intent(this, SinglePubDetailsScreen.class);

        intent.putExtra("name", pub.name);
        intent.putExtra("address", pub.address);
        intent.putExtra("description", pub.description);
        intent.putExtra("place_ID", pub.place_ID);
        intent.putExtra("rating_resource_ID", pub.rating_resource_ID);

        startActivity(intent);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
