package com.example.zhehui.receiveapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by liuhaodong1 on 11/24/17.
 */

public class AIDLService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IAIDLService.Stub mBinder = new IAIDLService.Stub() {

        public int getPid(){
            return Process.myPid();
        }
        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
        }

        @Override
        public String getMessage(String title, String data) throws RemoteException {
            Log.e("AIDLService","received message: "+title+" data: "+data);
            return title;
        }
    };
}
