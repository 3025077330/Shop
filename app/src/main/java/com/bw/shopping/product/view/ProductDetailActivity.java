package com.bw.shopping.product.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseMVPActivity;
import com.bw.net.bean.ShoppCarBean;
import com.bw.shoppcar.CacheManager;

import com.bw.shoppcar.ShopcarActivity;
import com.bw.shopping.R;
import com.bw.shopping.product.contract.ProductDetailContract;
import com.bw.shopping.product.presenter.ProductDetailPresenterImpl;
import com.bw.user.RegiLoginActivity;
import com.bw.user.manager.ShoppUserManager;

import java.util.List;

public class ProductDetailActivity extends BaseMVPActivity<ProductDetailPresenterImpl, ProductDetailContract.IProductDetailView> implements ProductDetailContract.IProductDetailView, View.OnClickListener {
    private WebView picWebView;
    private TextView priceTv;
    private String productId;
    private String productName;
    private String prodctPrice;
    private String url;
    private int newNum;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addProduct:
                //第一步先判断用户是否登录，没有登录的话，跳转到登录页面
                if (!ShoppUserManager.getInstance().isUserLogin()) {
                    launActivity(RegiLoginActivity.class, null);
                    return;
                }
                //第二步判断仓库是否有足够的产品
                checkHasProduct();
                break;
            case R.id.productDetailShopcar:
                if (!ShoppUserManager.getInstance().isUserLogin()) {
                    launActivity(RegiLoginActivity.class, null);
                    return;
                }
                launActivity(ShopcarActivity.class, null);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initpresenter() {
        ihttpView = new ProductDetailPresenterImpl();
    }

    @Override
    protected void initpredata() {

    }

    @Override
    protected int bandlayout() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initview() {
        picWebView = findViewById(R.id.productWebView);
        priceTv = findViewById(R.id.productDetailPrice);
        findViewById(R.id.addProduct).setOnClickListener(this);

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        productName = intent.getStringExtra("productName");
        prodctPrice = intent.getStringExtra("productPrice");
        url = intent.getStringExtra("url");

        priceTv.setText(prodctPrice);
        picWebView.setWebViewClient(new WebViewClient());
        picWebView.setWebChromeClient(new WebChromeClient());
        picWebView.loadUrl(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + url);

        findViewById(R.id.productDetailShopcar).setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    public void onCheckOneProduct(String productNum) {

        printLog(productNum);
        //服务端将仓库的产品数量返回
        if (Integer.valueOf(productNum) >= 1) { //当前仓库有该商品,把该商品添加到购物车
            //添加个判断，判断当前这个商品在购物车是不是已经有了，如果有了，只是把这个数量增加一个，如果购物车
            //上没有这个商品，再把商品添加到购物车上，防止一个商品出现两条数据
            if (checkIfShopcarListHasProduct()) {
                ShoppCarBean shopcarBean = CacheManager.getInstance().getShopcarBan(productId);
                int oldNum = Integer.parseInt(shopcarBean.getProductNum());
                newNum = oldNum + 1;
                ihttpView.updateProductNum(productId, String.valueOf(newNum), productName, url, prodctPrice);
            } else {
                ihttpView.addOneProduct(productId, "1", productName, url, prodctPrice);
            }
        }
    }

    private boolean checkIfShopcarListHasProduct() {
        //第一步获取当前购物车上的商品列表
        List<ShoppCarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for (ShoppCarBean shopcarBean : shopcarBeanList) {
            if (productId.equals(shopcarBean.getProductId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAddProduct(String addResult) {
        //把该商品也添加到在单例中存储的购物车公共数据里
        ShoppCarBean shopcarBean = new ShoppCarBean();
        shopcarBean.setProductId(productId);
        shopcarBean.setProductName(productName);
        shopcarBean.setProductPrice(prodctPrice);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductSelected(true);
        shopcarBean.setUrl(url);
        CacheManager.getInstance().add(shopcarBean);
    }

    @Override
    public void onProductNumChange(String result) {
//已经成功的把购物车商品数据增加了一个

        //更新本地缓存中的商品数量，始终让两个数据保持一致
        CacheManager.getInstance().updateProductNum(productId, String.valueOf(newNum));
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

    private void checkHasProduct() {
        ihttpView.checkOneProductNum(productId, "1");//检查仓库是不是还有一件该商品
    }
}
