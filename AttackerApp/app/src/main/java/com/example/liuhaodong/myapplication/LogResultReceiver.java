package com.example.liuhaodong.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import javax.inject.Inject;

/**
 * Created by liuhaodong1 on 12/2/17.
 */

public class LogResultReceiver extends ResultReceiver {


    public LogResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);

    }

}
