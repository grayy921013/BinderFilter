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

//        if(title != null && !title.equals("")) {
//            Intent sendIntent = new Intent();
//            sendIntent.setClassName("com.example.liuhaodong.myapplication",
//                    "com.example.liuhaodong.myapplication.MainActivity");
//            sendIntent.putExtra(Intent.EXTRA_TITLE, title);
//            Log.e("title",title);
//            startActivity(sendIntent);
//        }
    }
}
