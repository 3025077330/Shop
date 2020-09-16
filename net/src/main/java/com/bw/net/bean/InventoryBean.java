package com.bw.net.bean;

public class InventoryBean {


    /**
     * productId : 1000
     * productName : null
     * productNum : 0
     * url : null
     * productPrice : null
     */

    private String productId;
    private Object productName;
    private String productNum;
    private Object url;
    private Object productPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Object getProductName() {
        return productName;
    }

    public void setProductName(Object productName) {
        this.productName = productName;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Object productPrice) {
        this.productPrice = productPrice;
    }
}
