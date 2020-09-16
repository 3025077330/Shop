package com.bw.net.bean;

public class BaseBean<T> {
    private String code;
    private String message;

    private T result;//因为每个接口返回result可能不同

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
