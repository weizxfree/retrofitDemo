package com.vipabc.im.mytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.vipabc.im.mytestapplication.adapter.DevicesAdapter;
import com.vipabc.im.mytestapplication.api.GetDevicesApi;
import com.vipabc.im.mytestapplication.manager.RetrofitManager;
import com.vipabc.im.mytestapplication.model.BaseRequest;
import com.vipabc.im.mytestapplication.model.Device;
import com.vipabc.im.mytestapplication.model.Devices;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private DevicesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DevicesAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
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
                            mAdapter.setData(devices.data.deviceList);
                        }
                    }

                });


    }


    public interface testInterface {
        void show();
    }
}
