package com.vipabc.im.mytestapplication.api;

import android.util.Log;

import com.google.gson.Gson;
import com.vipabc.im.mytestapplication.manager.RetrofitManager;
import com.vipabc.im.mytestapplication.model.BaseRequest;
import com.vipabc.im.mytestapplication.model.BaseResponse;
import com.vipabc.im.mytestapplication.model.Devices;
import com.vipabc.im.mytestapplication.model.OptDevice;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiRequest {


    private static final String TAG = "ApiRequest";


    public void getDevices(final IApiCallBack callBack) {
        RetrofitManager.getInstance().createReq(GetDevicesApi.class).getDevices(new BaseRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Devices>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Devices devices) {
                        Log.d(TAG, "onNext: " + new Gson().toJson(devices));
                        if (devices != null && devices.data != null && devices.data.deviceList != null) {
                            if (callBack != null) {
                                callBack.onResult(devices);
                            }
                        }
                    }
                });
    }

    public void optDevice(String deviceID, String optCode, final IApiCallBack callBack) {
        OptDevice optDevice = new OptDevice(deviceID, optCode);
        RetrofitManager.getInstance().createReq(OptDeviceApi.class).optDevice(new BaseRequest(optDevice))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BaseResponse response) {
                        if (callBack != null) {
                            callBack.onResult(response);
                        }
                    }
                });
    }
}
