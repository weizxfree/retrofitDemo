package com.vipabc.im.mytestapplication.utils;

import android.content.Context;
import android.widget.Toast;

import com.vipabc.im.mytestapplication.app.BaseApp;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
    public class ToastUtil {


        private ToastUtil() {

        }

        private static Toast mToast;
        private static final Context mContext = BaseApp.getInstance();

        public static void show(int resId) {
            show(mContext.getString(resId));
        }

        public static void show(String msg) {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
        }
    }
