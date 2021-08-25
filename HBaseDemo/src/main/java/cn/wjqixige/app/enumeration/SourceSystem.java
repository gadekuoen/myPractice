package cn.wjqixige.app.enumeration;

public enum SourceSystem {

    DAS("DAS", "配电自动化"),
    EMS("EMS", "调度自动化"),
    EDAS("EDAS", "用采系统"),
    DCC("DCC", "调控云"),
    SMP("SMP", "辅控"),
    TWS("TWS", "输变电在线监测");

    private String code;
    private String desc;

    SourceSystem(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
