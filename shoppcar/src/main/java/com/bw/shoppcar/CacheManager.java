package com.bw.shoppcar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import com.bw.common.ShoppingConstant;
import com.bw.net.NetFunction;
import com.bw.net.RetroCreator;
import com.bw.net.ShopmallObserver;
import com.bw.net.bean.BaseBean;
import com.bw.net.bean.ShoppCarBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//存储购物车数据的类
public class CacheManager {

    //当用户登录成功后，CacheManger会调用服务端接口请求购物车数据。拿到购物车数据后，给shopcarBeanList赋值
    private List<ShoppCarBean> shopcarBeanList = new ArrayList<>();
    private List<ShoppCarBean> deleteShopcarBeanList = new ArrayList<>();

    private static CacheManager instance;

    //有多个页面监听数据的变化，所以维护一个监听listener的列表
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    public void init(Context context) {
        this.context = context;
        initReceiver();
    }

    //注册广播，监听当前用户的登录状态
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(ShoppingConstant.LOGIN_ACTION);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //当前用户登录成功
                if (intent.getAction().equals(ShoppingConstant.LOGIN_ACTION)) {
                    Log.i("WYF", "获取了");
                    getShopcarDataFromServer();//从服务端获取购物车的数据
                }
            }
        }, intentFilter);
    }

    private void getShopcarDataFromServer() {
        RetroCreator.getShoppService().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<ShoppCarBean>>, List<ShoppCarBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<List<ShoppCarBean>>() {
                    @Override
                    public void onNext(List<ShoppCarBean> shopcarBeans) {
                        Log.i("WYF", shopcarBeans.toString());
                        shopcarBeanList.addAll(shopcarBeans);//初始化购物车的公共数据
                        //通知页面去刷新UI
                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        Toast.makeText(context, "请求购物车数据错误" + errorCode + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void add(ShoppCarBean shopcarBean) {
        shopcarBeanList.add(shopcarBean);//提供接口，添加一个商品到购物车公共数据里
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }

    private void notifyShopcarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }

    public List<ShoppCarBean> getShopcarBeanList() {
        return shopcarBeanList;
    }

    //获取已经选择的商品列表
    public List<ShoppCarBean> getSelectedProductBeanList() {
        List<ShoppCarBean> selectedList = new ArrayList<>();
        for (ShoppCarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    //从缓存中删除已经支付的商品
    public void removeSelectedProducts() {
        shopcarBeanList.removeAll(getSelectedProductBeanList());

        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    public void setShopcarBeanList(List<ShoppCarBean> shopcarBeanList) {
        this.shopcarBeanList = shopcarBeanList;
    }

    //当页面想监听数据的改变，就注册一个listener
    public void setShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }

    //因为只有两个状态，所以改成相反的状态即可
    public void updateProductSelected(int position) {
        ShoppCarBean shopcarBean = shopcarBeanList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);

        //通知UI，数据发生了改变，需要更新UI
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()) {
                listener.onAllSelected(true);
            } else {
                listener.onAllSelected(false);
            }
        }
    }

    public String getMoneyValue() {
        float totalPrice = 0;
        for (ShoppCarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                float productPrice = Float.parseFloat(shopcarBean.getProductPrice());
                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice + productPrice * productNum;
            }
        }
        return String.valueOf(totalPrice);
    }

    //更新缓存中商品的数量
    public void updateProductNum(int position, String newNum) {
        ShoppCarBean shopcarBean = shopcarBeanList.get(position);
        shopcarBean.setProductNum(newNum);

        //通知UI，数据发生了改变，需要更新UI
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }

    public int getShopCount() {
        if (shopcarBeanList.size() == 0) {
            return 0;
        } else {
            return shopcarBeanList.size();
        }
    }


    //更新缓存中商品的数量
    public void updateProductNum(String productId, String newNum) {
        int i = 0;
        for (ShoppCarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.getProductId().equals(productId)) {
                //通知UI，数据发生了改变，需要更新UI
                shopcarBean.setProductNum(newNum);
                for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
                    listener.onOneDataChanged(i, shopcarBean);
                    listener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
    }

    //当页面销毁时，页面不需要再监听数据改变了，我们把它从列表中删除
    public void unSetShopcarDataChangerListener(IShopcarDataChangeListener listener) {
        if (iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.remove(listener);
        }
    }

    public ShoppCarBean getShopcarBan(String productId) {
        for (ShoppCarBean shopcarBean : shopcarBeanList) {
            if (productId.equals(shopcarBean.getProductId())) {
                return shopcarBean;
            }
        }
        return null;
    }

    public boolean isAllSelected() {
        for (ShoppCarBean shopcarBean : shopcarBeanList) {
            if (!shopcarBean.isProductSelected()) {
                return false;
            }
        }
        return true;
    }

    public void selectAllProduct(boolean isAllSelect) {
        for (ShoppCarBean shopcarBean : shopcarBeanList) {
            shopcarBean.setProductSelected(isAllSelect);
        }

        //通知UI更新页面
        //通知UI，数据发生了改变，需要更新UI
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelect);
        }

    }

    //把它加入到删除队列中
    public void addDeleteShopcarBean(ShoppCarBean shopcarBean, int position) {
        deleteShopcarBeanList.add(shopcarBean);
        boolean isAllSelect = deleteShopcarBeanList.size() == shopcarBeanList.size();//判断下当前删除队列数据数目和购物车商品数目是否一致，一致代表是全选删除
        //需要更新UI
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            if (isAllSelect) {
                listener.onAllSelected(isAllSelect);
            }
        }
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeanList.size();
    }

    //从删除队列中删除
    public void deleteOneShopcarBean(ShoppCarBean shopcarBean, int position) {
        deleteShopcarBeanList.remove(shopcarBean);
        //需要更新UI
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }

    public void selectAllProductInEditMode(boolean isAllSelect) {
        if (isAllSelect) {
            deleteShopcarBeanList.clear();
            deleteShopcarBeanList.addAll(shopcarBeanList);
        } else {
            deleteShopcarBeanList.clear();
        }

        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onAllSelected(isAllSelect);
            listener.onDataChanged(shopcarBeanList);
        }
    }

    public boolean checkIfDataInDeleteShopcarBeanList(ShoppCarBean shopcarBean) {
        return deleteShopcarBeanList.contains(shopcarBean);
    }

    public List<ShoppCarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }

    public void processDeleteProducts() {
        //首先将删除列表中的数据在购物车缓存张删除
        shopcarBeanList.removeAll(deleteShopcarBeanList);
        //把删除列表清空
        deleteShopcarBeanList.clear();

        //通知UI刷新页面
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }

    //定义接口，当购物车数据发生改变时，通过该接口通知页面刷新
    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShoppCarBean> shopcarBeanList);

        void onOneDataChanged(int position, ShoppCarBean shopcarBean);

        void onMoneyChanged(String moneyValue);

        void onAllSelected(boolean isAllSelect);
    }
}

