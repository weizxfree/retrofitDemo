package com.vipabc.im.mytestapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vipabc.im.mytestapplication.R;
import com.vipabc.im.mytestapplication.api.ApiRequest;
import com.vipabc.im.mytestapplication.api.IApiCallBack;
import com.vipabc.im.mytestapplication.config.DeviceEnum;
import com.vipabc.im.mytestapplication.model.BaseResponse;
import com.vipabc.im.mytestapplication.model.Device;
import com.vipabc.im.mytestapplication.utils.ToastUtil;

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
        return new ViewHolder(inflater.inflate(R.layout.cell_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final Device device = mDatas.get(position);
        viewHolder.tvDeviceID.setText(String.format("设备: %s", device.getDeviceId()));
        viewHolder.tvRunStatus.setText(String.format("控制器: %s", device.getRunStatus()));
        viewHolder.tvBreakerStatus.setText(String.format("断路器: %s", DeviceEnum.getName(device.getBreakerStatus())));
        viewHolder.btOptOpenDevice.setTag(R.id.tag_device_id, device.getDeviceId());
        viewHolder.btOptOpenDevice.setOnClickListener(mOptOnClickListener);
        viewHolder.btOptCloseDevice.setTag(R.id.tag_device_id, device.getDeviceId());
        viewHolder.btOptCloseDevice.setOnClickListener(mOptOnClickListener);
        DeviceEnum deviceEnum = device.getDeviceStatus();
        if (deviceEnum == DeviceEnum.OPEN) {
            viewHolder.btOptOpenDevice.setEnabled(false);
            viewHolder.btOptCloseDevice.setEnabled(true);
        } else if(deviceEnum == DeviceEnum.CLOSE) {
            viewHolder.btOptOpenDevice.setEnabled(true);
            viewHolder.btOptCloseDevice.setEnabled(false);
        } else if(deviceEnum == DeviceEnum.NC){
            viewHolder.btOptOpenDevice.setEnabled(false);
            viewHolder.btOptCloseDevice.setEnabled(false);
        }
    }

    private View.OnClickListener mOptOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String optCode = null;
            String deviceId = (String) view.getTag(R.id.tag_device_id);
            switch (view.getId()) {
                case R.id.bt_opt_open:
                    optCode = DeviceEnum.OPEN.code;
                    break;
                case R.id.bt_opt_close:
                    optCode = DeviceEnum.CLOSE.code;
                    break;
            }

            new ApiRequest().optDevice(deviceId, optCode, new IApiCallBack<BaseResponse>() {
                @Override
                public void onResult(BaseResponse response) {
                    if (response == null) {
                        return;
                    }
                    if (response.isSuccess()) {
                        ToastUtil.show("操作成功");
                    } else {
                        ToastUtil.show(response.message);
                    }
                }
            });

        }
    };

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDeviceID;
        TextView tvRunStatus;
        TextView tvBreakerStatus;
        Button btOptOpenDevice;
        Button btOptCloseDevice;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDeviceID = itemView.findViewById(R.id.tv_device_id);
            tvRunStatus = itemView.findViewById(R.id.tv_run_status);
            tvBreakerStatus = itemView.findViewById(R.id.tv_breaker_status);
            btOptOpenDevice = itemView.findViewById(R.id.bt_opt_open);
            btOptCloseDevice = itemView.findViewById(R.id.bt_opt_close);
        }
    }


}
