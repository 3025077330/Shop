package com.bw.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.TextView;

//欢迎页面，作用，就是实现应用启动广告
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200, 200);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(50);
        setContentView(textView);
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();//欢迎页面跳转到主界面后，要关掉自己
            }
        }.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
