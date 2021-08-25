package cn.wjqixige.app.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MpYCDataMsg {
    private String SSID;
    private String MPID;
    private String FDT;
    private String RKSJ;
    private List<MeasurementElement> MDATA;

    @JSONField(name="SSID")
    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    @JSONField(name="MPID")
    public String getMPID() {
        return MPID;
    }

    public void setMPID(String MPID) {
        this.MPID = MPID;
    }

    @JSONField(name="FDT")
    public String getFDT() {
        return FDT;
    }

    public void setFDT(String FDT) {
        this.FDT = FDT;
    }

    @JSONField(name="RKSJ")
    public String getRKSJ() {
        return RKSJ;
    }

    public void setRKSJ(String RKSJ) {
        this.RKSJ = RKSJ;
    }

    @JSONField(name="MDATA")
    public List<MeasurementElement> getMDATA() {
        return MDATA;
    }

    public void setMDATA(List<MeasurementElement> MDATA) {
        this.MDATA = MDATA;
    }
}
