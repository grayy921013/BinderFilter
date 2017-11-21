package com.example.zhehui.receiveapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.label);
        textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        String title = intent.getStringExtra(Intent.EXTRA_TITLE);
        Intent ret = new Intent();
        ret.putExtra(Intent.EXTRA_TITLE,title);
        setResult(2,ret);
        finish();
    }
}
