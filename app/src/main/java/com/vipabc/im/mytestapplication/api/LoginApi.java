package com.vipabc.im.mytestapplication.api;

import com.vipabc.im.mytestapplication.model.Weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public interface LoginApi {
    @GET("weather")
    Observable<Weather> queryWeather(@Query("location") String location, @Query("output") String output, @Query("ak") String ak);
}
