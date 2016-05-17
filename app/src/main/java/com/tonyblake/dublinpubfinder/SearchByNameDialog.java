package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
    public static String pub_name;
    private WindowManager.LayoutParams lp;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        context = getActivity();

        dbManager = HomeScreen.dbManager;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.search_by_name_dialog, null);

        pub_name = null;

        builder.setTitle(R.string.enter_pub_name)
                .setView(view)
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if(pub_name == null) {

                            pub_name = tv_pub_name.getText().toString();
                        }

                        if("".equals(pub_name)){

                            showToastMessage(context.getString(R.string.no_pub_name_entered));
                        }
                        else {

                            String query = context.getString(R.string.select_all_rows_from) + dbManager.getTableName()
                                    + context.getString(R.string.where) + context.getString(R.string.name_like)
                                    + pub_name + "'" + context.getString(R.string.end_query);

                            try {
                                Cursor res = dbManager.rawQuery(query);

                                res.moveToFirst();

                                String placeID;

                                do {
                                    placeID = res.getString(2);
                                }
                                while (res.moveToNext());

                                ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE))
                                        .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

                                mListener.onSearchByNameDialogSearchClick(SearchByNameDialog.this, placeID);

                            } catch (Exception e) {

                                showToastMessage(context.getString(R.string.no_pubs_match_your_search));
                            }
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
        //lp.height = Integer.valueOf(context.getString(R.string.search_by_name_dialog_height));
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

    public interface SearchByNameDialogListener {

        void onSearchByNameDialogSearchClick(DialogFragment dialog, String placeID);
    }

    SearchByNameDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (SearchByNameDialogListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
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
    public void onResume() {
        super.onResume();

        tv_pub_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pub_name = (String) parent.getItemAtPosition(position);

            }
        });
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
