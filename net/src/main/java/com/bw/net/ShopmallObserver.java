package com.bw.net;

import android.util.Log;

import com.bw.common.ShoppingConstant;

import org.json.JSONException;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

//自定义一个observer,让presenter实现类更清晰，干净
public abstract class ShopmallObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t);


    @Override
    public void onError(Throwable e) {
        if (e instanceof JSONException) {//JSON解析异常
            onRequestError(ShoppingConstant.JSCON_ERROR_CODE, ShoppingConstant.JSON_ERROR_MESSAGE);
        } else if (e instanceof HttpException) {//网络错误
            onRequestError(ShoppingConstant.HTTP_ERROR_CODE, ShoppingConstant.HTTP_ERROR_MESSAGE);
        } else if (e instanceof SocketTimeoutException) {//连接超时错误
            onRequestError(ShoppingConstant.SOCKET_TIMEOUT_ERROR_CODE, ShoppingConstant.SOCKET_TIMEOUT_ERROR_MESSAGE);
        } else if (e instanceof NetBusinessException) {//针对关于业务错误及异常处理
            NetBusinessException netBusinessException = (NetBusinessException) e;
            onRequestError(netBusinessException.getErrorCode(), netBusinessException.getErrorMessage());
        } else if (e instanceof SecurityException) {

        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onRequestError(String errorCode, String errorMessage);
}
