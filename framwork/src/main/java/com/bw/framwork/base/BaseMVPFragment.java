package com.bw.framwork.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseMVPFragment<T extends IPresenter, V extends IView> extends BaseFragment {
    protected T ihttpView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initpresenter();
        ihttpView.attachView((IView) this);
        initpredata();
    }

    protected abstract void initpresenter();

    protected abstract void initpredata();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ihttpView.deatchView();
    }
}
