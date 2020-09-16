package com.bw.shopping.product.presenter;


import android.util.Log;

import com.bw.net.NetFunction;
import com.bw.net.RetroCreator;
import com.bw.net.ShopmallObserver;
import com.bw.net.bean.BaseBean;
import com.bw.shopping.product.contract.ProductDetailContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProductDetailPresenterImpl extends ProductDetailContract.ProductDetailPresenter {
    @Override
    public void checkOneProductNum(String productId, String productNum) {
        HashMap<String, String> params = new HashMap<>();
        params.put("productId", productId);
        params.put("productNum", productNum);
        RetroCreator.getShoppService().checkOneProductInventory(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        ihttpView.showloading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        ihttpView.hideloading();
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        ihttpView.onCheckOneProduct(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        Log.i("WYF", errorCode+errorMessage);
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });

    }

    @Override
    public void addOneProduct(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
            jsonObject.put("productSelected", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetroCreator.getShoppService().addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        ihttpView.showloading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        ihttpView.hideloading();
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        ihttpView.onAddProduct(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });

    }

    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetroCreator.getShoppService().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        ihttpView.showloading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        ihttpView.hideloading();
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        ihttpView.onProductNumChange(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });


    }


}
