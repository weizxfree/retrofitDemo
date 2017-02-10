package com.vipabc.im.mytestapplication.app;

import android.app.Application;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public class BaseApp extends Application {

    private static BaseApp mInstance;
    public static BaseApp getInstance() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
