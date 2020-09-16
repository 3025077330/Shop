package com.bw.common;

//该类提供共有的网络接口路径
public class ShoppingConstant {
    public static String BASE_URL = "http://49.233.93.155:8080/";
    public static String BASE_RESOURCE_URL = BASE_URL + "atguigu";
    public static String BASE_RESOURCE_IMAGE_URL = BASE_URL + "atguigu/img";

    public static final String JSCON_ERROR_CODE = "10000";
    public static final String JSON_ERROR_MESSAGE = "服务端范湖数据解析错误";

    public static final String HTTP_ERROR_CODE = "20000";
    public static final String HTTP_ERROR_MESSAGE = "网络错误";

    public static final String SECURITY_ERROR_CODE = "30000";
    public static final String SECURITY_ERROR_MESSAGE = "权限错误";

    public static final String USER_NOT_REGISTER_ERROR = "1001";
    public static final String USER_NOT_REGISTER_MESSAGE = "用户没有注册";
    public static final String SOCKET_TIMEOUT_ERROR_CODE = "40000";
    public static final String SOCKET_TIMEOUT_ERROR_MESSAGE = "连接超时错误";
    public static final String LOGIN_ACTION = "com.bawei.shoppcar.LOGIN_ACTION";
    public static final String spName = "ksSp";
    public static final String tokenName = "tokenSp";


}
