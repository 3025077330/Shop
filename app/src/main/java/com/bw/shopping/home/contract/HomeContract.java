package com.bw.shopping.home.contract;

import com.bw.net.bean.HomeBean;
import com.bw.framwork.base.BasePresenter;
import com.bw.framwork.base.IView;

public class HomeContract {
    public interface IHomeView extends IView {
        void onHomeData(HomeBean homeBean);
    }

    public static abstract class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void getHomeData();
    }
}
