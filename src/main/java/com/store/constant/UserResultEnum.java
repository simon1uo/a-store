package com.store.constant;


public enum UserResultEnum {
    USER_NULL_ERROR("用户不存在"),
    SAME_USER_ACCOUNT_EXIST("用户已存在"),
    USER_ACCOUNT_AND_PASSWORD_NULL("用户账号或者密码不能为空"),
    USER_ACCOUNT_AND_PASSWORD_ERROR("用户账号或者密码不能为空"),
    USER_ACCOUNT_LENGTH_ERROR("账号长度有误"),
    USER_ACCOUNT_INPUT_ERROR("账号输入有误"),
    USER_PASSWORD_LENGTH_ERROR("密码长度有误"),
    USER_PASSWORD_SAME_ERROR("密码输入两次不相同"),
    SIGNIN_ERROR("登录失败！"),
    NOT_SIGNIN_ERROR("未登录！"),
    TOKEN_EXPIRE_ERROR("无效认证！请重新登录！"),
    SIGNIN_USER_LOCKED_ERROR("用户已被禁止登录！"),


    ;
    private String result;

    UserResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
