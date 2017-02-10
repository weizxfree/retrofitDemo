package com.vipabc.im.mytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vipabc.im.mytestapplication.adapter.WeatherAdapter;
import com.vipabc.im.mytestapplication.api.LoginApi;
import com.vipabc.im.mytestapplication.manager.RetrofitManager;
import com.vipabc.im.mytestapplication.model.Weather;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RetrofitManager.getInstance().createReq(LoginApi.class).queryWeather("绥德","json","11ffd27d38deda622f51c9d314d46b17")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Weather weather) {
                        Test test = new Test();
                        test.show();
                        mRecyclerView.setAdapter(new WeatherAdapter(MainActivity.this, weather.results));
                    }
                });




    }


    public interface testInterface{
        void show();
    }
}
