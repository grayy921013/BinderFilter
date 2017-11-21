package com.example.liuhaodong.myapplication;

import java.util.List;

import models.Case;

/**
 * Created by liuhaodong1 on 11/18/17.
 */

public interface MainTarget {

    void loadingCases(int percentage);

    void loadingCasesFinished(CaseListviewAdapter adapter);

    void onSend(List<Case> list);

    void onError(String errMsg);
}
