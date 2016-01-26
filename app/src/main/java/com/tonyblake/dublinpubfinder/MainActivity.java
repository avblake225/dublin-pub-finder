package com.tonyblake.dublinpubfinder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView tv_qwhatpub;
    Button btn_findpub;

    String selected_pub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_qwhatpub = (AutoCompleteTextView)findViewById(R.id.tv_qwhatpub);

        String[] pubs = new String[] {"The Temple Bar", "J.W.Sweetmans", "Fibber Magees", "The Globe"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pubs);
        tv_qwhatpub.setThreshold(1);
        tv_qwhatpub.setAdapter(adapter);

        btn_findpub = (Button)findViewById(R.id.btn_findpub);
    }

    @Override
    protected void onResume(){
        super.onResume();

        tv_qwhatpub.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {

                selected_pub = (String) parent.getItemAtPosition(position);
            }
        });

        btn_findpub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Context context = getApplicationContext();
                CharSequence text = "Needs implementation";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

    }
}
