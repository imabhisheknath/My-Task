package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;


public class ProgressDialog {


    Dialog dialog;


    android.app.ProgressDialog progressBar;


    public ProgressDialog(Context context) {


        progressBar = new android.app.ProgressDialog(context);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading.. Please Wait...");
        //progressBar.setProgressStyle(android.app.ProgressDialog.STYLE_HORIZONTAL);



     /*   dialog = new Dialog(context, R.style.NewDialog);
        dialog.setContentView(R.layout.action_progress_dialog);
        dialog.setCancelable(false);*/

        // dualProgressView=new DualProgressView()


    }


    public void show() {
        //dialog.show();
        progressBar.show();

    }


    public void dissmiss() {
        //dialog.dismiss();
        progressBar.dismiss();
    }


    public void setMessage(String msg) {
        progressBar.setMessage(msg);
    }

}
