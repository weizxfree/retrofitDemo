package com.vipabc.im.mytestapplication.config;

import android.text.TextUtils;

public enum DeviceEnum {

    OPEN("open", "分闸"),
    CLOSE("close", "合闸"),
    NC("nc", "未连接");
    public String code;
    public String name;

    DeviceEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (DeviceEnum deviceEnum : DeviceEnum.values()) {
            if (TextUtils.equals(code, deviceEnum.code)) {
                return deviceEnum.name;
            }
        }
        return null;
    }






}
