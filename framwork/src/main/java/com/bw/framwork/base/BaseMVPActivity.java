package com.bw.framwork.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMVPActivity<T extends IPresenter, V extends IView> extends BaseActivity {
    protected T ihttpView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initpresenter();
        ihttpView.attachView((IView) this);
        initpredata();
    }

    protected abstract void initpresenter();

    protected abstract void initpredata();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ihttpView.deatchView();
    }
}
