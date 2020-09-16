package com.bw.shoppcar.presenter;

import com.bw.net.NetFunction;
import com.bw.net.RetroCreator;
import com.bw.net.ShopmallObserver;
import com.bw.net.bean.BaseBean;
import com.bw.net.bean.InventoryBean;
import com.bw.net.bean.OrderInfoBean;
import com.bw.net.bean.ShoppCarBean;
import com.bw.shoppcar.CacheManager;
import com.bw.shoppcar.contract.ShopcarContract;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopcarPresenterImpl extends ShopcarContract.ShopcarPresenter {
    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice,
                                 final int position, final String newNum) {
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
                        ihttpView.onProductNumChange(s, position, newNum);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });


    }

    @Override
    public void updateProductSelected(final String productId, boolean productSelected, String productName, String url, String productPrice, final int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productSelected", productSelected);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetroCreator.getShoppService().updateProductSelected(requestBody)
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
                        ihttpView.onProductSelected(s, position);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });
    }

    @Override
    public void selectAllProduct(final boolean isAllSelect) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isAllSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetroCreator.getShoppService().selectAllProduct(requestBody)
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
                        ihttpView.onAllSelected(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });
    }

    @Override
    public void deleteProducts(List<ShoppCarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShoppCarBean shopcarBean : products) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("url", shopcarBean.getUrl());
                jsonObject.put("productNum", shopcarBean.getProductNum());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());

        RetroCreator.getShoppService().removeManyProduct(requestBody)
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
                        ihttpView.onDeleteProducts(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });
    }

    @Override
    public void checkInventory(List<ShoppCarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShoppCarBean shopcarBean : products) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonObject.put("productNum", shopcarBean.getProductNum());
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("url", shopcarBean.getUrl());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());

        RetroCreator.getShoppService().checkInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<InventoryBean>>, List<InventoryBean>>())
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
                .subscribe(new ShopmallObserver<List<InventoryBean>>() {
                    @Override
                    public void onNext(List<InventoryBean> inventoryBeans) {
                        ihttpView.onInventory(inventoryBeans);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });


    }

    @Override
    public void getOrderInfo(List<ShoppCarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShoppCarBean shopcarBean : products) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject object = new JSONObject();
        try {
            object.put("subject", "buy");
            object.put("totalPrice", CacheManager.getInstance().getMoneyValue());
            object.put("body", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());

        RetroCreator.getShoppService().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<OrderInfoBean>, OrderInfoBean>())
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
                .subscribe(new ShopmallObserver<OrderInfoBean>() {
                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        ihttpView.onOrderInfo(orderInfoBean);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        ihttpView.showError(errorCode, errorMessage);
                    }
                });
    }
}
