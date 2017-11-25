package com.example.liuhaodong.myapplication;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import models.Case;
import usecases.CaseFromFiles;
import utils.IPC_TYPE;

/**
 * Created by liuhaodong1 on 11/18/17.
 */

public class MainPresenter {

    MainTarget mainTarget;
    CaseFromFiles caseFromFiles;
    CaseListviewAdapter caseListviewAdapter = null;
    IPC_TYPE ipc_type = IPC_TYPE.Intent;

    @Inject
    public MainPresenter(CaseFromFiles caseFromFiles){
        this.caseFromFiles = caseFromFiles;
    }

    public void loadCases(){
        mainTarget.loadingCases(0);
        caseFromFiles.getCasesTask().
                 subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    new SingleObserver<List<Case>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onSuccess(List<Case> value) {
                            caseListviewAdapter = new CaseListviewAdapter(value);
                            mainTarget.loadingCasesFinished(caseListviewAdapter);
                        }
                        @Override
                        public void onError(Throwable e) {
                            mainTarget.onError("loading cases failed");
                        }
                    }
                );
    }

    public void sendSelectedCases(){
        if(caseListviewAdapter == null) return;
        List<Case> selectedList = caseListviewAdapter.getSelectedCases();
        mainTarget.onSend(ipc_type, selectedList);
    }

    public void sendAllCases(){
        if(caseListviewAdapter == null) return;
        List<Case> allList = caseListviewAdapter.getAllCases();
        mainTarget.onSend(ipc_type, allList);
    }

    public void updateReceivedState(String title){
        if(caseListviewAdapter == null) return;
        caseListviewAdapter.updateReceivedState(title);
    }

    public void resetReceivedState(){
        if(caseListviewAdapter == null) return;
        caseListviewAdapter.resetReceivedState();
    }

    public void setTarget(MainTarget mainTarget){
        this.mainTarget = mainTarget;
    }

    public void updateIPCType(){
        switch (this.ipc_type){
            case Intent:
                this.ipc_type = IPC_TYPE.Messenger;
                break;
            case Messenger:
                this.ipc_type = IPC_TYPE.AIDL;
                break;
            case AIDL:
                this.ipc_type = IPC_TYPE.Intent;
                break;
            default:
                break;
        }
        mainTarget.onUpdateType(this.ipc_type);
    }
}
