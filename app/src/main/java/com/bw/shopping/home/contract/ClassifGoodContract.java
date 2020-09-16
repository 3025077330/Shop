package com.bw.shopping.home.contract;

import com.bw.framwork.base.BasePresenter;
import com.bw.framwork.base.IView;
import com.bw.net.bean.ClassifGoodBean;

import java.util.List;

public class ClassifGoodContract {
  public   interface ClassifView extends IView {
        void onClassGood(ClassifGoodBean classifGoodBean);
    }

    public static abstract class ClassifPresenter extends BasePresenter<ClassifView> {
        //小裙子
        public abstract void getSkirt();

        //上衣
        public abstract void getJacket();

        //下装
        public abstract void getPants();

        //外套
        public abstract void getOvercoat();

        //配件
        public abstract void getAccessory();

        //包包
        public abstract void getBig();

        //扮装
        public abstract void getDressUp();

        //居家宅品
        public abstract void getHomeProducts();

        //办公文具
        public abstract void getStationery();

        //数码周边
        public abstract void getNumRound();

        //游戏专区
        public abstract void getGames();


    }

}
