package com.bw.shoppcar.contract;


import com.bw.framwork.base.BasePresenter;
import com.bw.framwork.base.IView;
import com.bw.net.bean.InventoryBean;
import com.bw.net.bean.OrderInfoBean;
import com.bw.net.bean.ShoppCarBean;

import java.util.List;

public class ShopcarContract {

    public interface IShopcarView extends IView {
        void onProductNumChange(String result, int position, String newNum);

        void onProductSelected(String result, int position);

        void onAllSelected(String result);

        void onDeleteProducts(String result);

        void onInventory(List<InventoryBean> inventoryBean);

        void onOrderInfo(OrderInfoBean orderInfoBean);
    }

    public static abstract class ShopcarPresenter extends BasePresenter<IShopcarView> {
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);

        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);

        public abstract void selectAllProduct(boolean isAllSelect);

        public abstract void deleteProducts(List<ShoppCarBean> products);

        public abstract void checkInventory(List<ShoppCarBean> products);

        public abstract void getOrderInfo(List<ShoppCarBean> products);
    }
}
