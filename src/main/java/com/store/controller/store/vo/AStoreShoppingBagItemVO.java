package com.store.controller.store.vo;

import lombok.Data;

@Data
public class AStoreShoppingBagItemVO {
    private Long bagItemId;

    private Long productId;

    private Integer productAmount;

    private String productName;

    private String productImageUrl;

    private Integer productSellingPrice;
}
