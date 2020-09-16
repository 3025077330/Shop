package com.bw.framwork.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bw.common.view.ToolBar;
import com.bw.framwork.R;


public abstract class BaseActivity extends AppCompatActivity  {
    private String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandlayout());
        TAG = "WYF" + getClass().getSimpleName();
        initview();
        initdata();

    }

    protected abstract int bandlayout();

    protected abstract void initview();

    protected abstract void initdata();

    protected void printLog(String message) {
        Log.i(TAG, message);
    }

    protected void showMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void launActivity(Class launActivity, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.setClass(this, launActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
