package com.tonyblake.dublinpubfinder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SearchByNameDialog extends DialogFragment {

    private Context context;
    private DBManager dbManager;
    private View view;
    private AutoCompleteTextView tv_pub_name;
    public static String pub_name = "";
    private Pub pub;
    private WindowManager.LayoutParams lp;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        context = getActivity();

        dbManager = MainActivity.dbManager;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.search_by_name_dialog, null);

        builder.setTitle(R.string.enter_pub_name)
                .setView(view)
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String query = context.getString(R.string.select_all_rows_from) + MainActivity.dbManager.getTableName()
                                + context.getString(R.string.where) + context.getString(R.string.name_equals)
                                + pub_name + "'" + context.getString(R.string.end_query);

                        try {
                            Cursor res = MainActivity.dbManager.getPubs(query);

                            res.moveToFirst();

                            do {
                                String place_ID = res.getString(4);

                            } while (res.moveToNext());

                            launchSinglePubDetailsScreen();

                        } catch (Exception e) {
                            showToastMessage(context.getString(R.string.no_pubs_match_your_search));
                        }

                    }
                })

                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        dismiss();
                    }
                });

        final AlertDialog dialog = builder.create();

        lp = new WindowManager.LayoutParams();

        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = Integer.valueOf(context.getString(R.string.search_by_name_dialog_height));
        dialog.getWindow().setAttributes(lp);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button cancelButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                Button searchButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);

                cancelButton.setTextColor(Color.BLACK);
                searchButton.setTextColor(Color.BLACK);

                final Drawable cancelButtonBackground = getResources().getDrawable(R.drawable.background_color);
                cancelButton.setBackground(cancelButtonBackground);

                final Drawable searchButtonBackground = getResources().getDrawable(R.drawable.background_color);
                searchButton.setBackground(searchButtonBackground);
            }
        });

        return dialog;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        tv_pub_name = (AutoCompleteTextView) view.findViewById(R.id.tv_pub_name);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1, dbManager.getPubNames());
        tv_pub_name.setAdapter(adapter);
        tv_pub_name.setThreshold(1);
    }

    @Override
    public void onResume(){
        super.onResume();

        tv_pub_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pub_name = (String) parent.getItemAtPosition(position);

            }
        });
    }

    private void launchSinglePubDetailsScreen(){

        Intent intent = new Intent(context, SinglePubDetailsScreen.class);

        intent.putExtra("name", pub.name);
        intent.putExtra("address", pub.address);
        intent.putExtra("description", pub.description);
        //intent.putExtra("place_ID", pub.place_ID);
        intent.putExtra("rating", pub.rating);

        startActivity(intent);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
