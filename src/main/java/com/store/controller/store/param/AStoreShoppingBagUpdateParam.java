package com.store.controller.store.param;

import lombok.Data;

@Data
public class AStoreShoppingBagUpdateParam {
    private Long bagItemId;
    private Integer productAmount;
}
