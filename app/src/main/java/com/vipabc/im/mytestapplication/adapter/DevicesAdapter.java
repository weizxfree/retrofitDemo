package com.vipabc.im.mytestapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vipabc.im.mytestapplication.R;
import com.vipabc.im.mytestapplication.model.Device;

import java.util.List;

/**
 * Created by tom_zxzhang on 2016/10/21.
 */
public class DevicesAdapter extends RecyclerView.Adapter {


    private List<Device> mDatas;
    private LayoutInflater inflater;
    private Context mContext;

    public DevicesAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public DevicesAdapter(Context context, List<Device> results) {
        this(context);
        this.mDatas = results;
    }

    public void setData(List<Device> results) {
        this.mDatas = results;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.cell_recyclerview, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final Device device = mDatas.get(position);
        viewHolder.tvDeviceID.setText(String.format("设备id: %s",device.getDeviceId()));
        viewHolder.tvRunStatus.setText(String.format("控制器运行状态: %s",device.getRunStatus()));
        viewHolder.tvBreakerStatus.setText(String.format("断路器状态: %s", device.getBreakerStatus()));
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDeviceID;
        TextView tvRunStatus;
        TextView tvBreakerStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            tvDeviceID = itemView.findViewById(R.id.tv_device_id);
            tvRunStatus  = itemView.findViewById(R.id.tv_run_status);
            tvBreakerStatus = itemView.findViewById(R.id.tv_breaker_status);
        }
    }

}
