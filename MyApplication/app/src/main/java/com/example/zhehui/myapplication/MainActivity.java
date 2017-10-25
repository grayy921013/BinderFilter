package com.example.zhehui.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent sendIntent = new Intent(this, Main2Activity.class);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "4122519664.");
        startActivity(sendIntent);
    }
}
