package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class SearchDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public CheckBox cb_traditional_irish_pub, cb_modern_pub;
    public Button btn_go;

    public SearchDialog(Activity a) {
        super(a);

        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.search_dialog);

        cb_traditional_irish_pub = (CheckBox) findViewById(R.id.cb_traditional_irish_pub);
        cb_modern_pub = (CheckBox) findViewById(R.id.cb_modern_pub);
        btn_go = (Button) findViewById(R.id.btn_go);

        btn_go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        dismiss();
    }
}