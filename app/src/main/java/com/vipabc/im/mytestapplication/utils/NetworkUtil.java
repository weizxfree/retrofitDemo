package com.vipabc.im.mytestapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.vipabc.im.mytestapplication.app.BaseApp;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public class NetworkUtil {

    public static boolean isNetworkAvailable(Context mContext) {
        boolean resp = false;
        ConnectivityManager connMgr = (ConnectivityManager) BaseApp.getInstance().getSystemService(BaseApp.getInstance().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connMgr.getActiveNetworkInfo();
        if(activeNetInfo != null && activeNetInfo.isAvailable()) {
            resp = true;
        }

        return resp;
    }
}
