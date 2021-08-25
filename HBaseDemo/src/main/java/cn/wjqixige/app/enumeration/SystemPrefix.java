package cn.wjqixige.app.enumeration;

public enum SystemPrefix {
    GONGBIAN("0001", "智能公变系统(专变、低压)"),
    LOUBAO("0002", "漏保系统"),
    ZAIXIANJIANCE("0003", "在线检测系统"),
    YONGCAI("0004", "用采系统"),
    YONGDIANCAIJI("0005", "用电系统采集系统"),
    YINGXIAOFADIAN("0006", "营销发电用户"),
    PEIWANGZIDONGHUA("0007", "配网自动化系统"),
    DIAODUEMS("0008", "调度EMS"),
    SHUBIANDIANZAIXIANJIANCE("0009", "输变电在线检测"),
    FUKONG("0010", "辅控"),
    ZHUKONG("0011", "主控"),
    DIAOKONGYUN("0012", "调控云");

    private String code;
    private String desc;

    private SystemPrefix(String code, String desc) {
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
