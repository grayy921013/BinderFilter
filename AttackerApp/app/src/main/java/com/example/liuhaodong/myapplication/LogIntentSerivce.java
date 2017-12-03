package com.example.liuhaodong.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;


import usecases.GetNewLog;

/**
 * Created by liuhaodong1 on 12/2/17.
 */

public class LogIntentSerivce extends IntentService{

    public static final String ACTION_LOG_FIND = "ACTION_LOG_FIND";

    public static final String ACTION_LOG_NOT_FIND = "ACTION_LOG_NOT_FIND";

    public static final String ACTION_LOG_START= "ACTION_LOG_START";

    public static final String EXTRAL_LOG_NAME_LIST = "EXTRAL_LOG_NAME_LIST";

    GetNewLog getNewLog;

    public LogIntentSerivce(){
        super("LogIntentSerivce");
        getNewLog = new GetNewLog();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            Log.e("onHandleIntent",action);
            if (ACTION_LOG_FIND.equals(action)){
                ArrayList<String> logsName = intent.getStringArrayListExtra(EXTRAL_LOG_NAME_LIST);
                if(logsName != null){
                    sendBroadcast(intent);
                }

            }else if(ACTION_LOG_START.equals(action) || ACTION_LOG_NOT_FIND.equals(action)){
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkLogAppear();
            }
        }
    }

    private void checkLogAppear(){
        //Trying to find a new log appearing
        ArrayList<String> overlapping = getNewLog.getNewLogList();
        Intent intent;
        if(overlapping.size() != 0){
            intent = new Intent(LogIntentSerivce.ACTION_LOG_FIND);
            intent.putStringArrayListExtra(EXTRAL_LOG_NAME_LIST, overlapping);
            onHandleIntent(intent);
        }else{
            intent = new Intent(LogIntentSerivce.ACTION_LOG_NOT_FIND);
            onHandleIntent(intent);
        }
    }
}
