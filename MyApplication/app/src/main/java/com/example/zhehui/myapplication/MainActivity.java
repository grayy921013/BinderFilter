package com.example.zhehui.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sendBtn;
    Button sendBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn = (Button)findViewById(R.id.send_btn);
        sendBtn2 = (Button)findViewById(R.id.send_btn2);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setClassName("com.example.zhehui.receiveapplication", "com.example.zhehui.receiveapplication.MainActivity");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "4122519664.");
                startActivity(sendIntent);
            }
        });
        sendBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setClassName("com.example.zhehui.receiveapplication2", "com.example.zhehui.receiveapplication2.MainActivity");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "4122519664.");
                startActivity(sendIntent);
            }
        });
    }
}
