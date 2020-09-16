package com.bw.net.bean;

public class OrderInfoBean {

    /**
     * orderInfo : app_id=2016092200570833&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%22500%22%2C%22subject%22%3A%22buy%22%2C%22body%22%3A%22%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22112809550921653%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2019-11-28+09%3A55%3A09&version=1.0&sign=azQvfykaVhDxFqtgqpfKcom8PKRMWkpPclW8xFT3IVAt1fYj2rw04FoXykcKPzNKdvZ3csE20SAnn1dOYiaEwrZzbE8V5%2FYOJRzmt%2FZxTrVy5Hmeo%2Fmv9q%2B0l3bJtUePX91RzTmsmJ3rku388pc7UaXRmzodae0ROLpvGHRxxZigU9EM8K%2FaqEqrSEMdK0wbnOw63CMAtz4c9ZeNSmz%2BHAf3GXI0qX4eqCdKf1VWPB3DCnwD73hQuGV9MJ1C%2FizG8T8pXq5eobkO8wwH0%2FPRezSCRV%2F0badfSQ%2BTHX6kPCi4sUalZG3m7p6tGf3mAUn4KA%2FwncCLLhAEMYRnOpyLHw%3D%3D
     * outTradeNo : 112809550921653
     */

    private String orderInfo;
    private String outTradeNo;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
