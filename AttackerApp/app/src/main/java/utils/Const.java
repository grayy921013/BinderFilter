package utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by liuhaodong1 on 11/19/17.
 */

public class Const {
    public static final String PACKAGE_NAME = "com.example.zhehui.receiveapplication";
    public static final String CLASS_NAME = "com.example.zhehui.receiveapplication.MainActivity";
    public static final String SERVICE_NAME = "com.example.zhehui.receiveapplication.MainService";
    public static final String AIDL_SERVICE_NAME = "com.example.zhehui.receiveapplication.AIDLService";
    public static final String CASE_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath()+
            File.separator+"LightBringer"+File.separator;
    public static final String WHITE_LIST_FOLDER = CASE_FOLDER + "whitelists" + File.separator;
    public static final String BLACK_LIST_FOLDER = CASE_FOLDER + "blacklists" + File.separator;
    public static final String LOG_FOLDER = CASE_FOLDER + "logs" + File.separator;
    public static final int request_code = 10001;

    public static final String dialog_part_1 = "A app called ";
    public static final String dialog_part_2 = " is sending suspected data: \n";
    public static final String dialog_part_3 = "\n do you think this is a malicious app?";
}
