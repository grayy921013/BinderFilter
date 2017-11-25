package com.example.liuhaodong.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zhehui.receiveapplication.IAIDLService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Case;
import module.DaggerMainComponent;
import module.MainModule;
import utils.Const;
import utils.IPC_TYPE;

public class MainActivity extends AppCompatActivity implements MainTarget{

    @BindView(R.id.case_list_view)
    RecyclerView recyclerView;

    @BindView(R.id.case_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.info_send)
    Button sendBtn;

    @BindView(R.id.info_reset)
    Button resetBtn;

    @BindView(R.id.info_type)
    Button typeBtn;

    @Inject MainPresenter mainPresenter;

    ServiceConnection serviceConnection = null;

    ServiceConnection aidlConnection = null;

    boolean isServiceConnected = false;

    boolean isAIDLServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);

        DaggerMainComponent.builder().
                mainModule(new MainModule(this)).
                build().
                inject(this);

        mainPresenter.setTarget(this);
        mainPresenter.loadCases();

        sendBtn.setOnClickListener(v -> mainPresenter.sendSelectedCases());
        resetBtn.setOnClickListener(v -> mainPresenter.resetReceivedState());
        typeBtn.setOnClickListener(v -> mainPresenter.updateIPCType());
    }

    @Override
    protected void onDestroy() {
        try{
            if(isServiceConnected)
                unbindService(serviceConnection);
            if(isAIDLServiceConnected)
                unbindService(aidlConnection);
        }catch (Exception e){
        }
        super.onDestroy();
    }

    @Override
    public void loadingCases(int percentage) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(percentage);
    }

    @Override
    public void loadingCasesFinished(CaseListviewAdapter adapter) {
        progressBar.setVisibility(View.GONE);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        final DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSend(IPC_TYPE type, List<Case> sendList) {
        switch (type) {
            case Intent:
                for(Case c : sendList) {
                    Intent sendIntent = createIntentFromCase(c);
                    startActivityForResult(sendIntent, Const.request_code);
                }
                break;
            case Messenger:
                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        isServiceConnected = true;
                        Messenger mService = new Messenger(service);
                        try {
                            for(Case c : sendList) {
                                Bundle bundle = new Bundle();
                                bundle.putString(Intent.EXTRA_TITLE, c.name());
                                bundle.putString(Intent.EXTRA_TEXT, c.data());
                                Message msg = Message.obtain(null, Const.request_code, 0, 0);
                                msg.setData(bundle);
                                msg.replyTo = mMessenger;
                                mService.send(msg);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            onError(e.getMessage());
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        onError("Main Service Disconnected");
                        isServiceConnected = false;
                    }
                };
                Intent sendIntent = new Intent();
                sendIntent.setClassName(Const.PACKAGE_NAME, Const.SERVICE_NAME);
                bindService(sendIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case AIDL:
                aidlConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {

                        isAIDLServiceConnected = true;
                        IAIDLService iaidlService = IAIDLService.Stub.asInterface(service);
                        for(Case c : sendList){
                            try {
                                String title = iaidlService.getMessage(c.name(), c.data());
                                if(title != null && !title.equals(""))
                                    mainPresenter.updateReceivedState(title);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                onError(e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        isAIDLServiceConnected = false;
                        onError("AIDL Service Disconnected");
                    }
                };
                Intent aidlIntent = new Intent();
                aidlIntent.setClassName(Const.PACKAGE_NAME, Const.AIDL_SERVICE_NAME);
                bindService(aidlIntent, aidlConnection, Context.BIND_AUTO_CREATE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Const.request_code && data != null){
            String title = data.getStringExtra(Intent.EXTRA_TITLE);
            if(title != null && !title.equals("")){
                mainPresenter.updateReceivedState(title);
            }
        }
    }

    @Override
    public void onError(String errMsg) {
        Toast.makeText(this,errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateType(IPC_TYPE type) {
        typeBtn.setText(type.name());
    }

    private Intent createIntentFromCase(Case c){
        Intent sendIntent = new Intent();
        sendIntent.setClassName(Const.PACKAGE_NAME, Const.CLASS_NAME);
        sendIntent.putExtra(Intent.EXTRA_TITLE, c.name());
        sendIntent.putExtra(Intent.EXTRA_TEXT, c.data());
        return sendIntent;
    }

    Messenger mMessenger = new Messenger(new Handler()
    {
        @Override
        public void handleMessage(Message msgFromServer){
            switch (msgFromServer.what){
                case Const.request_code:
                    Bundle bundle = msgFromServer.getData();
                    if(bundle != null){
                        String title = bundle.getString(Intent.EXTRA_TITLE);
                        if(title != null && !title.equals(""))
                            mainPresenter.updateReceivedState(title);
                    }
                    break;
            }
            super.handleMessage(msgFromServer);
        }
    });
}
