package com.vipabc.im.mytestapplication.api;


import com.vipabc.im.mytestapplication.model.BaseRequest;
import com.vipabc.im.mytestapplication.model.Device;
import com.vipabc.im.mytestapplication.model.Devices;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public interface GetDevicesApi {
    @POST("/cdz-service/device/getDeviceList")
    Observable<Devices> getDevices(@Body BaseRequest requestBody);
}
