package com.vipabc.im.mytestapplication.model;

/**
 * Created by Administrator on 2020/1/7.
 */

public class BaseRequest<T> {

    public Base base;
    public T param;

    public BaseRequest() {
        this(null);
    }

    public BaseRequest(T param) {
        this.base = new Base();
        this.param = param;
    }

    public static class Base {
        String appId = "123";
        String clientVer = "1.0.0";
        String osid = "android";
    }

}



