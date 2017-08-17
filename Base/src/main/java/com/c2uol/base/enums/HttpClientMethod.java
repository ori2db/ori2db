package com.c2uol.base.enums;

/**
 * 
 * @描述: HTTP网络连接工具访问方法
 * @作者: Lyon
 * @版本: v1.0
 * @时间: 2017年8月17日 下午10:39:13
 */
public enum HttpClientMethod {

    POST("POST"), GET("GET"), PUSH("PUSH"), DELETE("DELETE");

    private String operational;

    HttpClientMethod(String val) {
        this.operational = val;
    }

    @Override
    public String toString() {
        return operational;
    }

}
