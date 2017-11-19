package com.example.liuhaodong.myapplication;

import java.util.List;

/**
 * Created by liuhaodong1 on 11/18/17.
 */

public interface MainTarget {

    void loadingCases(int percentage);

    void loadingCasesFinished(List<Case> list);

    void onSend(List<Case> list);

    void onReceive();
}
