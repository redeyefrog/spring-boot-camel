package com.redeyefrog.enums;

public enum StatusCode {

    SUCCESS("0000", "交易成功"),

    UNKNOWN("9999", "發生未知錯誤，請聯絡管理人員");

    private String code;

    private String desc;

    StatusCode(String code, String desc) {
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
