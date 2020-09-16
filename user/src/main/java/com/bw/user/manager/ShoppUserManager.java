package com.bw.user.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.bw.common.ShoppingConstant;
import com.bw.net.bean.LoginBean;
import com.bw.user.service.ShoppUserService;

import java.util.LinkedList;
import java.util.List;

//使用单例来存储当前用户的状态
public class ShoppUserManager {
    private static ShoppUserManager instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String shoppSp = "ksSp";
    private String tokenName = "tokenSp";
    private LoginBean loginBean;
    private List<ILoginStatusChangeListener> loginStatusChangeListeners = new LinkedList<>();
    private Context context;

    private ShoppUserManager() {
    }

    public static ShoppUserManager getInstance() {
        if (instance == null) {
            synchronized (ShoppUserManager.class) {
                if (instance == null) {
                    instance = new ShoppUserManager();
                }
            }

        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(shoppSp, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //去实现自动登录
        Intent intent = new Intent();
        intent.setClass(context, ShoppUserService.class);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ShoppUserService.ShopUserBinder shopUserBinder = (ShoppUserService.ShopUserBinder) service;
                if (!TextUtils.isEmpty(getToken())) {

                    shopUserBinder.getService().autoLogin(getToken());
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);


    }


    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        //去通知当前用户成功登录
        if (loginStatusChangeListeners.size() > 0) {
            for (ILoginStatusChangeListener listener : loginStatusChangeListeners) {
                listener.onLoginSuccess(loginBean);
            }
        }
        //存储token
        editor.putString(tokenName, loginBean.getResult().getToken());
        editor.commit();//注意提交
        //发送广播，通知当前应用用户已经登录成功
        Intent intent = new Intent();
        intent.setAction(ShoppingConstant.LOGIN_ACTION);
        context.sendBroadcast(intent);
    }

    public String getToken() {
        Log.i("WYF", sharedPreferences.getString(tokenName, ""));
        return sharedPreferences.getString(tokenName, "");
    }

    public Object getUserpoint() {
        return this.loginBean.getResult().getPoint();
    }

    public String getName() {
        if (loginBean != null) {
            return loginBean.getResult().getName();
        } else {
            return null;
        }

    }

    public boolean isUserLogin() {
        return loginBean != null;//只要loginBean不为空，就认为用户已经登录
    }

    //用户退出登录时调用，该函数处理缓存,会将用户登录后存储的缓存清空
    public void processLogout() {
        this.loginBean = null;//内存登录状态变为未登录
        editor.putString(tokenName, "");
        editor.commit();
        //去通知各个页面当前用户已退出登录
        if (loginStatusChangeListeners.size() > 0) {
            for (ILoginStatusChangeListener listener : loginStatusChangeListeners) {
                listener.onLogoutSuccess();
            }
        }
    }

    //添加一个通知接口，当用户登录或者退出登录，通知监听状态的页面
    public interface ILoginStatusChangeListener {
        void onLoginSuccess(LoginBean loginBean);

        void onLogoutSuccess();//退出登录成功
    }
}
