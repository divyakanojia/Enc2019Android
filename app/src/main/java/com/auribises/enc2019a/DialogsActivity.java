package com.auribises.enc2019a;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DialogsActivity extends AppCompatActivity {


    Dialog dialog;
    ProgressDialog progressDialog; // Refer old sessions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        if(!isInternetConnected()){
            showDialogForInternetConnectivity();
        }else{
            // Send Request to WebService
        }

    }

    void showDialogForInternetConnectivity(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Connectivity Issue");
        builder.setMessage("Please Connect to Internet and Try Again !!");
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // or any action of your choice
            }
        });
        builder.setNegativeButton("Cancel", null);

        //builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This is Title");
        builder.setMessage("This is Message");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Intent
                // or any action of your choice
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        //builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showAlertDialogWithOptions(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"View", "Update", "Delete", "Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:

                        break;

                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:
                        finish();
                        break;
                }
            }
        });

        //builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    void showCustomDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.mydialog);
        //dialog.setCancelable(false);
        dialog.show();

    }

    boolean isInternetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }


    public void clickHandler(View view){
        //showAlertDialog();
        //showAlertDialogWithOptions();
        showCustomDialog();
    }
}
