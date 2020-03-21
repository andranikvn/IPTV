package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ErrorAlert {
    private Context context;




    public void Alert(String title, String message)
    {



        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });
        alertDialog.show();


    }

    public ErrorAlert(Context context) {
        this.context = context;
    }
}
