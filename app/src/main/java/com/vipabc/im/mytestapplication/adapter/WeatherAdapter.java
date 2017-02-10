package com.vipabc.im.mytestapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vipabc.im.mytestapplication.R;
import com.vipabc.im.mytestapplication.model.Weather;

import java.util.List;

/**
 * Created by tom_zxzhang on 2016/10/21.
 */
public class WeatherAdapter extends RecyclerView.Adapter {


    private List<Weather.Result> mDatas;
    private LayoutInflater inflater;
    private Context mContext;


    public WeatherAdapter(Context context,List<Weather.Result> results) {
        this.mDatas = results;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.cell_recyclerview,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_title.setText(mDatas.get(0).index.get(position).title);
        viewHolder.tv_zs.setText(mDatas.get(0).index.get(position).zs);
        viewHolder.tv_tipt.setText(mDatas.get(0).index.get(position).tipt);
        viewHolder.tv_des.setText(mDatas.get(0).index.get(position).des);
        Glide.with(mContext).load(mDatas.get(0).weather_data.get(position).dayPictureUrl).into(viewHolder.dayPictureUrl);
        Glide.with(mContext).load(mDatas.get(0).weather_data.get(position).nightPictureUrl).into(viewHolder.nightPictureUrl);

    }

    @Override
    public int getItemCount() {
        return 4;
    }


    private class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_title;
        private TextView tv_zs;
        private TextView tv_tipt;
        private TextView tv_des;
        private TextView tv_date;
        private ImageView dayPictureUrl;
        private ImageView nightPictureUrl;
        private TextView tv_weather;
        private TextView tv_wind;
        private TextView tv_temperature;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_zs = (TextView) itemView.findViewById(R.id.tv_zs);
            tv_tipt = (TextView) itemView.findViewById(R.id.tv_tipt);
            tv_des = (TextView)itemView.findViewById(R.id.tv_des);
            tv_date  = (TextView)itemView.findViewById(R.id.tv_date);
            dayPictureUrl = (ImageView)itemView.findViewById(R.id.dayPictureUrl);
            nightPictureUrl = (ImageView)itemView.findViewById(R.id.nightPictureUrl);
            tv_weather  = (TextView)itemView.findViewById(R.id.tv_weather);
            tv_wind  = (TextView)itemView.findViewById(R.id.tv_wind);
            tv_temperature  = (TextView)itemView.findViewById(R.id.tv_temperature);
        }
    }

}
