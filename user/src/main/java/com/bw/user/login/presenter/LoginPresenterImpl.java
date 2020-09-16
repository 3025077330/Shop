package com.bw.user.login.presenter;

import android.util.Log;

import com.bw.net.RetroCreator;
import com.bw.net.bean.LoginBean;
import com.bw.user.login.contract.LoginContract;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter {
    @Override
    public void login(String name, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("password", password);
        Log.i("WYF", name + "用户名" + password + "密码");
        RetroCreator.getShoppService().login(hashMap)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //该方法是在网络请求发起前，在主线程中调用的方法
                        ihttpView.showloading();//显示加载的UI
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //该方法是在通过RxJava获取网络数据后在主线程中调用的一个方法
                        ihttpView.hideloading();//隐藏加载的UI
                    }
                }).subscribe(new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean loginBean) {
                ihttpView.onlogin(loginBean);
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
