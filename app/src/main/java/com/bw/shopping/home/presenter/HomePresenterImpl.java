package com.bw.shopping.home.presenter;

import com.bw.net.bean.HomeBean;
import com.bw.net.RetroCreator;
import com.bw.shopping.home.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {
    @Override
    public void getHomeData() {
        RetroCreator.getShoppService().getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //该方法是在网络请求发起前，在主线程中调用的方法
                        ihttpView.showloading();//显示加载的UI
                    }
                }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                //该方法是在通过RxJava获取网络数据后在主线程中调用的一个方法
                ihttpView.hideloading();//隐藏加载的UI
            }
        }).subscribe(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBean homeBean) {
                if (homeBean.getCode() == 200) {
                    ihttpView.onHomeData(homeBean);
                } else {
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
