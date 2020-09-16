package com.bw.shopping.home.presenter;

import com.bw.net.RetroCreator;
import com.bw.net.bean.ClassifGoodBean;
import com.bw.shopping.home.contract.ClassifGoodContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassifPresenterImpl extends ClassifGoodContract.ClassifPresenter {

    @Override
    public void getSkirt() {
        RetroCreator.getShoppService().getSkirtBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getJacket() {
        RetroCreator.getShoppService().getJacketBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getPants() {
        RetroCreator.getShoppService().getPantsBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getOvercoat() {
        RetroCreator.getShoppService().getOvercoatBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getAccessory() {
        RetroCreator.getShoppService().getAccessoryBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getBig() {
        RetroCreator.getShoppService().getBigBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getDressUp() {
        RetroCreator.getShoppService().getDressUpBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHomeProducts() {
        RetroCreator.getShoppService().getHomeProductsBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getStationery() {
        RetroCreator.getShoppService().getStationeryBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getNumRound() {
        RetroCreator.getShoppService().getDigitBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getGames() {
        RetroCreator.getShoppService().getGameBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifGoodBean classifGoodBean) {
                        if (classifGoodBean.getCode() == 200)
                            ihttpView.onClassGood(classifGoodBean);
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
