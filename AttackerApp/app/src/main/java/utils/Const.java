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
    public static final String CASE_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath()+
            File.separator+"LightBringer"+File.separator;
    public static final int request_code = 10001;
}
