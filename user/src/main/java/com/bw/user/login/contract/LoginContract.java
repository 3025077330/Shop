package com.bw.user.login.contract;

import com.bw.framwork.base.BasePresenter;
import com.bw.framwork.base.IView;
import com.bw.net.bean.LoginBean;


public class LoginContract {
    public interface ILogin extends IView {
        void onlogin(LoginBean loginBean);
    }

    public static abstract class LoginPresenter extends BasePresenter<LoginContract.ILogin> {
        public abstract void login(String name, String password);
    }

}
