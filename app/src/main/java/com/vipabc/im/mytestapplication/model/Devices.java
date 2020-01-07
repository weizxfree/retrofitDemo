package com.vipabc.im.mytestapplication.model;

import java.util.List;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public class Devices extends BaseResponse {

    public DeviceList data;

    public static class DeviceList {
        public List<Device> deviceList;
    }

}
