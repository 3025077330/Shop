package com.bw.framwork.base;

public interface IView {
    void showError(String code, String message);

    void showloading();

    void hideloading();
}
