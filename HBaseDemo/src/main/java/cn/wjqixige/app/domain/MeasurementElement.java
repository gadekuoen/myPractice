package cn.wjqixige.app.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class MeasurementElement {
    private String TAG;
    private String VAL;
    private String Q;
    private String DT;

    @JSONField(name="TAG")
    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    @JSONField(name="VAL")
    public String getVAL() {
        return VAL;
    }

    public void setVAL(String VAL) {
        this.VAL = VAL;
    }

    @JSONField(name="Q")
    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    @JSONField(name="DT")
    public String getDT() {
        return DT;
    }

    public void setDT(String DT) {
        this.DT = DT;
    }
}
