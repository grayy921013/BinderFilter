package com.example.liuhaodong.myapplication;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by liuhaodong1 on 11/18/17.
 */

public class MainPresenter {

    MainTarget mainTarget;
    CaseProvider caseProvider;
    CaseListviewAdapter caseListviewAdapter;

    @Inject
    public MainPresenter(CaseProvider caseProvider){
        this.caseProvider = caseProvider;
        caseListviewAdapter = new CaseListviewAdapter(caseProvider.caseList);
    }

    public void loadCases(){
        mainTarget.loadingCases(0);
        mainTarget.loadingCasesFinished(caseProvider.caseList);
    }

    public CaseListviewAdapter getListAdapter(){
        return caseListviewAdapter;
    }

    public void sendSelectedCases(){
        List<Case> selectedList = caseListviewAdapter.getSelectedCases();
        mainTarget.onSend(selectedList);
    }

    public void sendAllCases(){
        mainTarget.onSend(caseProvider.caseList);
    }

    public void updateReceivedState(String title){
        caseListviewAdapter.updateReceivedState(title);
        caseListviewAdapter.notifyDataSetChanged();
    }

    public void setTarget(MainTarget mainTarget){
        this.mainTarget = mainTarget;
    }

    public MainTarget getTarget(){
        return mainTarget;
    }
}
