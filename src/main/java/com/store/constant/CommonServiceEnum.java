package com.store.constant;

public enum CommonServiceEnum {
    SUCCESS("success"),
    ERROR("error"),
    DATA_NOT_EXIST("data not exist"),
    PARAM_ERROR("param error"),
    DATABASE_ERROR("database error"),

    OPERATE_ERROR("操作失败！"),
    REQUEST_FORBID_ERROR("禁止该操作！"),
    NO_PERMISSION_ERROR("无权限！"),
    ;

    private String result;

    CommonServiceEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
