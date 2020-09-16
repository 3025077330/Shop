package com.bw.framwork.base;

public interface IPresenter<V extends IView> {
    void attachView(V ihttpView);

    void deatchView();
}
