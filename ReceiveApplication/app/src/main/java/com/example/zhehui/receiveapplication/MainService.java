package com.example.zhehui.receiveapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by liuhaodong1 on 11/24/17.
 */

public class MainService extends Service {


    class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Const.request_code:
                    Messenger toClient = msg.replyTo;
                    Bundle data = msg.getData();
                    if(toClient != null && data != null)
                        try {
                            String title = data.getString(Intent.EXTRA_TITLE);
                            if(title != null && !title.equals("")){
                                Bundle bundle = new Bundle();
                                bundle.putString(Intent.EXTRA_TITLE, title);
                                Message message = Message.obtain(null,
                                        Const.request_code, 0, 0);
                                message.setData(bundle);
                                toClient.send(message);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
