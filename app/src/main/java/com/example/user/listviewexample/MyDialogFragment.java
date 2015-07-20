package com.example.user.listviewexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by katerinaskroumpelou on 19/7/2015.
 */

//this class creates the dialog

public class MyDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("App Info");

        theDialog.setMessage("App Version: 0.0 \nDeveloper: Katerina Skroumpelou \n\nPurpose: Android skills demo for Demokritos");

        //just added a button and a toast for checking/validation, does nothing more

        theDialog.setNeutralButton("Got It!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cool!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return theDialog.create();
    }
}
