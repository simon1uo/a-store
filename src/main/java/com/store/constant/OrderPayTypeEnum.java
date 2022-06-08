package com.store.constant;

public enum OrderPayTypeEnum {
    DEFAULT(-1, "ERROR"),
    NOT_PAY(0, "无"),
    ALI_PAY(1, "支付宝"),
    WECHAT_PAY(2, "微信支付");

    private int payType;

    private String name;

    OrderPayTypeEnum(int payType, String name) {
        this.payType = payType;
        this.name = name;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static OrderPayTypeEnum getOrderPayEnumByType(int payType) {
        for (OrderPayTypeEnum payTypeEnum : OrderPayTypeEnum.values()) {
            if (payTypeEnum.getPayType() == payType) return payTypeEnum;
        }
        return DEFAULT;
    }
}
