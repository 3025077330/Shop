package com.bw.user.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.framwork.base.BaseMVPFragment;
import com.bw.user.R;
import com.bw.user.RegiLoginActivity;
import com.bw.user.register.contract.RegisterContract;
import com.bw.user.register.presenter.RegisterPresenterImpl;

public class RegisterFragment extends BaseMVPFragment<RegisterPresenterImpl, RegisterContract.IRegister> implements RegisterContract.IRegister {
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private RadioButton radioXieyi;
    private Button buRegister;
    private TextView tvLogin;


    @Override
    protected void initpresenter() {
        ihttpView = new RegisterPresenterImpl();
    }

    @Override
    protected void initpredata() {

    }

    @Override
    protected int bandlayout() {
        return R.layout.regist_fragm;
    }

    @Override
    protected void initview() {
        etLoginPhone = findViewById(R.id.et_login_phone);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        radioXieyi = findViewById(R.id.radio_xieyi);
        buRegister = findViewById(R.id.bu_register);
        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegiLoginActivity regiLoginActivity = (RegiLoginActivity) getActivity();
                regiLoginActivity.pagechange(0);
            }
        });
        buRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioXieyi.isChecked()) {
                    //同意协议
                    ihttpView.register(etLoginPhone.getText().toString().trim(), etLoginPwd.getText().toString().trim());

                } else {
                    Toast.makeText(getContext(), "请勾选用户协议", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void initdata() {

    }

    @Override
    public void onReigster(String registerResult) {
        if (registerResult.equals("注册成功")) {
            //跳转到登录页面
            RegiLoginActivity regiLoginActivity = (RegiLoginActivity) getActivity();
            regiLoginActivity.pagechange(0);
        } else {
            Toast.makeText(getContext(), "用户已经注册，请直接登录", Toast.LENGTH_SHORT).show();
        }
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


}
