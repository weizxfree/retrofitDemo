package com.vipabc.im.mytestapplication.api;

import com.vipabc.im.mytestapplication.model.BaseRequest;
import com.vipabc.im.mytestapplication.model.Devices;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface OptDeviceApi {
    @POST("/cdz-service/device/optDevice")
    Observable<Devices> optDevice(@Body BaseRequest<Object> requestBody);
}
