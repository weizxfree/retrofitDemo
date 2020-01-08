package com.vipabc.im.mytestapplication.model;

import android.text.TextUtils;

import com.vipabc.im.mytestapplication.config.DeviceEnum;

public class Device {

    private String breakerSn;
    private String breakerStatus;
    private String deviceId;
    private String firstReportDate;
    private String installDate;
    private String lastReportDate;
    private String latitude;
    private String longitude;
    private String position;
    private String produceDate;
    private int repairNum;
    private String runStatus;

    public void setBreakerSn(String breakerSn) {
        this.breakerSn = breakerSn;
    }

    public String getBreakerSn() {
        return breakerSn;
    }

    public void setBreakerStatus(String breakerStatus) {
        this.breakerStatus = breakerStatus;
    }

    public String getBreakerStatus() {
        return breakerStatus;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setFirstReportDate(String firstReportDate) {
        this.firstReportDate = firstReportDate;
    }

    public String getFirstReportDate() {
        return firstReportDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setLastReportDate(String lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    public String getLastReportDate() {
        return lastReportDate;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setRepairNum(int repairNum) {
        this.repairNum = repairNum;
    }

    public int getRepairNum() {
        return repairNum;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getRunStatus() {
        return runStatus;
    }


    public DeviceEnum getDeviceStatus() {
        if(TextUtils.equals(breakerStatus,DeviceEnum.OPEN.code)) {
            return DeviceEnum.OPEN;
        }else if(TextUtils.equals(breakerStatus,DeviceEnum.CLOSE.code)){
            return DeviceEnum.CLOSE;
        }
        return DeviceEnum.NC;
    }

}
