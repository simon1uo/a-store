package com.store.controller.store.param;

import lombok.Data;

@Data
public class AStoreOrderCreateParam {
    private Long addressId;

    private Long[] bagItemIds;
}
