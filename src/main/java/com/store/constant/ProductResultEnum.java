package com.store.constant;

public enum ProductResultEnum {
    PRODUCT_CATEGORY_ERROR("商品分类数据异常！"),

    SAME_PRODUCT_EXIST("已存在相同的商品信息！"),

    PRODUCT_NOT_EXIST("商品不存在！"),

    PRODUCT_PUT_DOWN("商品已下架！");

    private String result;

    ProductResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
