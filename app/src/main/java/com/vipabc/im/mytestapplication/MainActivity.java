package com.vipabc.im.mytestapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.vipabc.im.mytestapplication.adapter.DevicesAdapter;
import com.vipabc.im.mytestapplication.api.ApiRequest;
import com.vipabc.im.mytestapplication.api.IApiCallBack;
import com.vipabc.im.mytestapplication.model.Devices;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private DevicesAdapter mAdapter;
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("充电桩列表");
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);//获取 SwipeRefreshLayout 实例
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);//设置刷新进度条颜色
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//设置刷新监听器
                                                     @Override
                                                     public void onRefresh() {
                                                         refreshListView();
                                                     }
                                                 }

        );
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DevicesAdapter(MainActivity.this);
        mAdapter.setOptOnChangeListener(new DevicesAdapter.OnOptChangeListener() {
            @Override
            public void onUpdate() {
                refreshListView();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        refreshListView();
    }

    private void refreshListView() {
        new ApiRequest().getDevices(new IApiCallBack<Devices>() {
            @Override
            public void onResult(Devices devices) {
                if (devices != null) {
                    mAdapter.setData(devices.data.deviceList);
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}
