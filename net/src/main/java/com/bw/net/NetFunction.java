package com.bw.net;

import com.bw.net.bean.BaseBean;

import io.reactivex.functions.Function;

//类型转换含少数，将类型R转换成类型T
public class NetFunction<R extends BaseBean<T>, T> implements Function<R, T> {
    @Override
    public T apply(R r) throws Exception {
        if (r.getCode().equals("200")) {
            return r.getResult();//1，如果服务端正确处理服务请求，会将服务端返回的数据类型由BaseBean转换成ResultBean
        } else {
            //2,当服务端，没有处理该请求，返回业务错误时,抛出业务类型到的异常,该异常错误将会进入到onError方法中处理
            throw new NetBusinessException(r.getCode(), r.getMessage());//在此处抛出的异常会进入到RxJava的onError方法中
        }
    }
}