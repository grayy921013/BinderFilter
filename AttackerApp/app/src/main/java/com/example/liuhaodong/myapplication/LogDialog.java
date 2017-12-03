package com.example.liuhaodong.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liuhaodong1 on 12/2/17.
 */

public class LogDialog implements LogTarget{

    private static AlertDialog instance = null;


    public static AlertDialog getAlertDialogInstance(Context context, DialogOnClick onClick){
        if(instance == null) {
            instance = new LogDialog().build(context, onClick);
        }
        return instance;
    }

    private AlertDialog build(Context context, DialogOnClick dialogOnClick){
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setMessage("A malicious app is sending suspected data, could you recognize it?");
        ab.setPositiveButton("YES",(dialog,which) -> dialogOnClick.onYes());
        ab.setNegativeButton("NO",(dialog,which) -> dialogOnClick.onNo());
        return ab.create();
    }

    public interface DialogOnClick{
        void onYes();
        void onNo();
    }

}
