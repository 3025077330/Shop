package com.bw.net;


import com.bw.net.bean.BaseBean;
import com.bw.net.bean.ClassifGoodBean;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.InventoryBean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.LoginOutBean;
import com.bw.net.bean.OrderInfoBean;
import com.bw.net.bean.RegisterBean;
import com.bw.net.bean.ShoppCarBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ShoppService {

    @GET("atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHomeData();

    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<String>> register(@FieldMap HashMap<String, String> params);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap HashMap<String, String> params);

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> autoLogin(@FieldMap HashMap<String, String> params);


    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<BaseBean<String>> checkOneProductInventory(@FieldMap HashMap<String, String> params);

    @POST("addOneProduct")
    Observable<BaseBean<String>> addOneProduct(@Body RequestBody requestBody);

    @GET("getShortcartProducts")
    Observable<BaseBean<List<ShoppCarBean>>> getShortcartProducts();

    @POST("logout")
    Observer<LoginOutBean> logioout();

    //小裙子
    @GET("atguigu/json/SKIRT_URL.json")
    Observable<ClassifGoodBean> getSkirtBean();

    //上衣
    @GET("atguigu/json/JACKET_URL.json")
    Observable<ClassifGoodBean> getJacketBean();

    //下装(裤子)
    @GET("atguigu/json/PANTS_URL.json")
    Observable<ClassifGoodBean> getPantsBean();

    //外套
    @GET("atguigu/json/OVERCOAT_URL.json")
    Observable<ClassifGoodBean> getOvercoatBean();

    //配件
    @GET("atguigu/json/ACCESSORY_URL.json")
    Observable<ClassifGoodBean> getAccessoryBean();

    //包包
    @GET("atguigu/json/BAG_URL.json")
    Observable<ClassifGoodBean> getBigBean();

    //装扮
    @GET("atguigu/json/DRESS_UP_URL.json")
    Observable<ClassifGoodBean> getDressUpBean();

    //居家宅品
    @GET("atguigu/json/HOME_PRODUCTS_URL.json")
    Observable<ClassifGoodBean> getHomeProductsBean();

    //办公文具
    @GET("atguigu/json/STATIONERY_URL.json")
    Observable<ClassifGoodBean> getStationeryBean();

    //数码周边
    @GET("atguigu/json/DIGIT_URL.json")
    Observable<ClassifGoodBean> getDigitBean();

    //    游戏专区
    @GET("atguigu/json/GAME_URL.json")
    Observable<ClassifGoodBean> getGameBean();


    @POST("updateProductNum")
    Observable<BaseBean<String>> updateProductNum(@Body RequestBody requestBody);

    @POST("updateProductSelected")
    Observable<BaseBean<String>> updateProductSelected(@Body RequestBody requestBody);

    @POST("selectAllProduct")
    Observable<BaseBean<String>> selectAllProduct(@Body RequestBody requestBody);

    @POST("removeManyProduct")
    Observable<BaseBean<String>> removeManyProduct(@Body RequestBody requestBody);

    @POST("checkInventory")
    Observable<BaseBean<List<InventoryBean>>> checkInventory(@Body RequestBody requestBody);

    @POST("getOrderInfo")
    Observable<BaseBean<OrderInfoBean>> getOrderInfo(@Body RequestBody requestBody);

}
