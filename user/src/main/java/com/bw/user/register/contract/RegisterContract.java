package com.bw.user.register.contract;

import com.bw.net.bean.RegisterBean;
import com.bw.framwork.base.BasePresenter;
import com.bw.framwork.base.IView;

public class RegisterContract {
    public interface IRegister extends IView {
        void onReigster(String registerResult);
    }

    public static abstract class RegisterPresenter extends BasePresenter<IRegister> {
        public abstract void register(String name, String password);
    }

}
