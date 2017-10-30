package com.example.zhehui.receiveapplication2;

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
    }
}
