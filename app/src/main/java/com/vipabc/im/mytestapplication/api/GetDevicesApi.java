package com.vipabc.im.mytestapplication.api;


import com.vipabc.im.mytestapplication.model.Device;

import java.util.List;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public interface GetDevicesApi {
    @POST("/cdz-service/device/getDeviceList")
    Observable<List<Device>> getDevices();
}
