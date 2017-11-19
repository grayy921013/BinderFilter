package com.example.liuhaodong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import module.DaggerMainComponent;
import module.MainModule;

public class MainActivity extends AppCompatActivity implements MainTarget{

    @BindView(R.id.case_list_view)
    RecyclerView recyclerView;

    @BindView(R.id.case_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.info_send)
    Button sendBtn;

    @BindView(R.id.info_send_all)
    Button sendAllBtn;

    @Inject MainPresenter mainPresenter;

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
        sendAllBtn.setOnClickListener(v -> mainPresenter.sendAllCases());
    }


    @Override
    public void loadingCases(int percentage) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(percentage);
    }

    @Override
    public void loadingCasesFinished(List<Case> list) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainPresenter.getListAdapter());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        final DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onSend(List<Case> sendList) {
        for(Case c : sendList) {
            Intent sendIntent = new Intent();
            sendIntent.setClassName(Const.PACKAGE_NAME,
                    Const.CLASS_NAME);
            sendIntent.putExtra(Intent.EXTRA_TITLE,c.name());
            sendIntent.putExtra(Intent.EXTRA_TEXT, c.data());
            startActivity(sendIntent);
        }
    }

    @Override
    public void onReceive() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(Intent.EXTRA_TITLE);
        if(title != null && !title.equals("")){
            mainPresenter.updateReceivedState(title);
        }
    }
}
