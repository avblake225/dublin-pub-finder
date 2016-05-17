package com.tonyblake.dublinpubfinder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchDialog extends DialogFragment{

    private Context context;

    public static ArrayList<Integer> search_options;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        context = getActivity();

        search_options = new ArrayList<Integer>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

         builder.setTitle(R.string.make_selections)
                .setMultiChoiceItems(R.array.search_options, null,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                if (isChecked) {

                                    search_options.add(which);

                                } else if (search_options.contains(which)) {

                                    search_options.remove(Integer.valueOf(which));
                                }
                            }
                        })

                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if (search_options.size() == 0) {

                            showToastMessage(context.getString(R.string.no_selections_made));
                        }
                        else {

                            mListener.onSearchDialogSearchClick(SearchDialog.this);
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

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button cancelButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                Button searchButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);

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

    public interface SearchDialogListener {

        void onSearchDialogSearchClick(DialogFragment dialog);
    }

    SearchDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (SearchDialogListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}