package com.store.constant;

public enum ShoppingBagResultEnum {

    SHOPPING_BAG_ITEM_LIMIT_NUMBER_ERROR("超出单个商品的最大购买数量！"),

    SHOPPING_BAG_ITEM_NUMBER_ERROR("商品数量有误！"),

    SHOPPING_BAG_ITEM_TOTAL_NUMBER_ERROR("超出购物车最大容量！"),

    SHOPPING_BAG_ITEM_EXIST_ERROR("已存在！无需重复添加！");

    private String result;

    ShoppingBagResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
