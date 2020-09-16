package com.bw.framwork.base;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V ihttpView;

    @Override
    public void attachView(V ihttpView) {
        this.ihttpView = ihttpView;
    }

    @Override
    public void deatchView() {
        this.ihttpView = null;
    }
}
