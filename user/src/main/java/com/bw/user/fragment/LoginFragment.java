package com.bw.user.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.common.view.BottomBar;
import com.bw.framwork.base.BaseMVPFragment;
import com.bw.net.bean.LoginBean;
import com.bw.user.R;
import com.bw.user.RegiLoginActivity;
import com.bw.user.login.contract.LoginContract;
import com.bw.user.login.presenter.LoginPresenterImpl;
import com.bw.user.manager.ShoppUserManager;

public class LoginFragment extends BaseMVPFragment<LoginPresenterImpl, LoginContract.ILogin> implements LoginContract.ILogin {
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private RadioButton radioXieyi;


    @Override
    protected void initpresenter() {
        ihttpView = new LoginPresenterImpl();
    }

    @Override
    protected void initpredata() {

    }

    @Override
    protected int bandlayout() {
        return R.layout.login_fragm;
    }

    @Override
    protected void initview() {
        etLoginPhone = findViewById(R.id.et_login_phone);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        tvLoginRegister = findViewById(R.id.tv_login_register);
        radioXieyi = findViewById(R.id.radio_xieyi);


        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegiLoginActivity regiLoginActivity = (RegiLoginActivity) getActivity();
                regiLoginActivity.pagechange(1);
            }
        });
        etLoginPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btnLogin.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etLoginPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btnLogin.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioXieyi.isChecked()) {
                    Toast.makeText(getContext(), "请勾选用户协议", Toast.LENGTH_SHORT).show();
                    return;
                }

                ihttpView.login(etLoginPhone.getText().toString().trim(), etLoginPwd.getText().toString().trim());
            }
        });


    }

    @Override
    protected void initdata() {

    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void onlogin(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")) {
            //实现跳转到MainActivity，显示HomeFragment,Activity的启动模式问题.
            //把用户存入缓存
            ShoppUserManager.getInstance().setLoginBean(loginBean);
            Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
            getActivity().finish();//是不是一定能回到MainActivity，这个不一定，因为，MainActivity有可能被系统回收.
        } else {
            etLoginPhone.setText("");
            etLoginPwd.setText("");
            Toast.makeText(getContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }

    }


}
