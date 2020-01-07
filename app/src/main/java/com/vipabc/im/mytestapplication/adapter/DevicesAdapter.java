package com.vipabc.im.mytestapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vipabc.im.mytestapplication.R;
import com.vipabc.im.mytestapplication.model.Device;

import java.util.List;

/**
 * Created by tom_zxzhang on 2016/10/21.
 */
public class DevicesAdapter extends RecyclerView.Adapter {


    private List<Device>  mDatas;
    private LayoutInflater inflater;
    private Context mContext;


    public DevicesAdapter(Context context, List<Device> results) {
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

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
