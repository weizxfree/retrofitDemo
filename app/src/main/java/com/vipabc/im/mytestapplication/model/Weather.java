package com.vipabc.im.mytestapplication.model;

import java.util.List;

/**
 * Created by tom_zxzhang on 2016/10/20.
 */
public class Weather extends BaseResponse{

    public List<Result> results;

    public static class Result {
        public String currentCity;
        public String  pm25;
        public List<Index> index;
        public List<WeatherData> weather_data;

        @Override
        public String toString() {
            return "Result{" +
                    "currentCity='" + currentCity + '\'' +
                    ", pm25='" + pm25 + '\'' +
                    ", index=" + index +
                    ", weather_data=" + weather_data +
                    '}';
        }
    }


    public static class Index {

        public String title;

        public String zs;

        public String tipt;

        public String des;

        @Override
        public String toString() {
            return "Index{" +
                    "title='" + title + '\'' +
                    ", zs='" + zs + '\'' +
                    ", tipt='" + tipt + '\'' +
                    ", des='" + des + '\'' +
                    '}';
        }
    }

    public static class WeatherData{

        public String date;

        public String dayPictureUrl;

        public String nightPictureUrl;

        public String weather;

        public String wind;

        public String temperature;

        @Override
        public String toString() {
            return "WeatherData{" +
                    "date='" + date + '\'' +
                    ", dayPictureUrl='" + dayPictureUrl + '\'' +
                    ", nightPictureUrl='" + nightPictureUrl + '\'' +
                    ", weather='" + weather + '\'' +
                    ", wind='" + wind + '\'' +
                    ", temperature='" + temperature + '\'' +
                    '}';
        }
    }

}
