package com.bw.user.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bw.net.RetroCreator;
import com.bw.net.bean.LoginBean;
import com.bw.user.manager.ShoppUserManager;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppUserService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ShopUserBinder();
    }

    public class ShopUserBinder extends Binder {
        public ShoppUserService getService() {
            return ShoppUserService.this;
        }
    }

    public void autoLogin(String tokenValue) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", tokenValue);
        RetroCreator.getShoppService().autoLogin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode().equals("200")) {
                            ShoppUserManager.getInstance().setLoginBean(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
