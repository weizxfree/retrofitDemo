package com.vipabc.im.mytestapplication.model;

import android.text.TextUtils;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public class BaseResponse {

    public String code;
    public String message;

    public boolean isSuccess(){
        return TextUtils.equals("1000",code);
    }
}
