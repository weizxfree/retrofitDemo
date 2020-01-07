package com.vipabc.im.mytestapplication.manager;

import android.util.Log;

import com.vipabc.im.mytestapplication.config.AppConfig;
import com.vipabc.im.mytestapplication.app.BaseApp;
import com.vipabc.im.mytestapplication.BuildConfig;
import com.vipabc.im.mytestapplication.utils.NetworkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public class RetrofitManager {

    private static RetrofitManager mRetrofitManager;
    private Retrofit mRetrofit;
    HttpLoggingInterceptor.Logger logger;

    public static synchronized RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    private RetrofitManager() {
        initRetrofit();
    }

    class LoggingInterceptor implements Interceptor {


        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtil.isNetworkAvailable(BaseApp.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            long t1 = System.nanoTime();
            Log.d("LoggingInterceptor", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d("LoggingInterceptor", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
    private void initRetrofit() {
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
        LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(new RspCheckInterceptor());
        builder.addNetworkInterceptor(new LoggingInterceptor());
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(LoginInterceptor); //添加retrofit日志打印
        }


        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();


    }


    public <T> T createReq(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }


    public class RspCheckInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            try {
                ResponseBody rspBody = response.body();
                JSONObject jsonObject = new JSONObject(rspBody.toString());
                int error = jsonObject.getInt("error");
                if (error != 0) {
                    throw new IOException(jsonObject.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                throw new IOException("parase data error");
            } catch (Exception e) {
                if (e instanceof IOException) {
                    throw (IOException) e;
                }
            }

            return response;
        }
    }


}
