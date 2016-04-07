package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class SearchByNameDialog extends AppCompatActivity {

    private Context context;
    private DBManager dbManager;
    private AutoCompleteTextView tv_pub_name;
    private Button btn_search_by_name;
    private String pub_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_name_dialog);

        context = this;

        dbManager = MainActivity.dbManager;

        tv_pub_name = (AutoCompleteTextView) findViewById(R.id.tv_pub_name);

        btn_search_by_name = (Button) findViewById(R.id.btn_search_by_name);

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
    }
}
