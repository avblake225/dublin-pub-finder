package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class FindPubByNameScreen extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView tv_pub_name;
    private Button btn_findpub;
    private String pub_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_pub_by_name_screen);

        context = this;

        tv_pub_name = (AutoCompleteTextView) findViewById(R.id.tv_pub_name);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,DBManager.pub_names);
        tv_pub_name.setAdapter(adapter);
        tv_pub_name.setThreshold(1);

        btn_findpub = (Button) findViewById(R.id.btn_findpub);
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

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if("".equals(pub_name)){

                    showToastMessage(context.getString(R.string.pub_not_found));
                }
            }
        });
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
