package com.bw.user.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.common.view.BottomBar;
import com.bw.framwork.base.BaseFragment;

import com.bw.net.RetroCreator;
import com.bw.net.ShopmallObserver;
import com.bw.net.ShoppService;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.LoginOutBean;
import com.bw.user.R;
import com.bw.user.RegiLoginActivity;
import com.bw.user.manager.ShoppUserManager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MyFragment extends BaseFragment implements View.OnClickListener, ShoppUserManager.ILoginStatusChangeListener {
    private SlidingMenu slidingMenu;
    private TextView tvUsername;
    private ImageButton ibUserSetting;
    private ImageView imageViewMenuView;
    private TextView textName;
    private TextView tvOutuser;


    @Override
    protected int bandlayout() {
        return R.layout.my_fragme;
    }

    @Override
    protected void initview() {
        ibUserSetting = findViewById(R.id.ib_user_setting);
        tvUsername = findViewById(R.id.tv_username);
        ibUserSetting.setOnClickListener(this);
        if (ShoppUserManager.getInstance().isUserLogin()) {
            //用户已经登录
            initslidemenu();
            tvUsername.setClickable(false);
            tvUsername.setText(ShoppUserManager.getInstance().getName());
            ibUserSetting.setClickable(true);
            ibUserSetting.setOnClickListener(this);
        } else {
            ibUserSetting.setClickable(false);
            tvUsername.setClickable(true);
        }
        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegiLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initdata() {

    }

    private void initslidemenu() {
        slidingMenu = new SlidingMenu(getContext());
        View slidemenuview = LayoutInflater.from(getContext()).inflate(R.layout.user_layout, null);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffset(300);
        imageViewMenuView = slidemenuview.findViewById(R.id.imageView_menu_view);
        //   Glide.with(this).load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + ShoppUserManager.getInstance().getUserpoint()).into(imageViewMenuView);
        textName = slidemenuview.findViewById(R.id.text_name);
        tvOutuser = slidemenuview.findViewById(R.id.tv_outuser);
        tvOutuser.setOnClickListener(this);
        textName.setText(ShoppUserManager.getInstance().getName());
        slidingMenu.setMenu(slidemenuview);
        slidingMenu.setBehindOffset(150);
        slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_user_setting) {
            slidingMenu.showMenu();
        } else if (v.getId() == R.id.tv_outuser) {
            loginout();
        }
    }

    private void loginout() {

    }


    @Override
    public void onLoginSuccess(LoginBean loginBean) {

    }

    @Override
    public void onLogoutSuccess() {
        showMsg("成功退出登录");
        //缓存的处理
        //1,改变当前应用程序的在内存中的登录状态，登录状态，由已登录状态，变为未登录状态   2,把token清除，禁止下次应用程序冷启动时实现自动登录
        ShoppUserManager.getInstance().processLogout();
        tvUsername.setClickable(true);
        ibUserSetting.setClickable(false);

    }
}
