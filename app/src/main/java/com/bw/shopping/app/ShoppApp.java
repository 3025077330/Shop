package com.bw.shopping.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.net.NetModule;
import com.bw.shoppcar.CacheManager;
import com.bw.user.manager.ShoppUserManager;


public class ShoppApp extends Application {
    public static ShoppApp instance;//作为项目一个全局的上下文

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NetModule.init(this);
        ShoppUserManager.getInstance().init(this);//用户管理类
        CacheManager.getInstance().init(this);//购物车数据管理类
        //初始化Arouter
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }
}
