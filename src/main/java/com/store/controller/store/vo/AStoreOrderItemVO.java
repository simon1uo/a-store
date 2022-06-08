package com.store.controller.store.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AStoreOrderItemVO implements Serializable {

    private static final long serialVersionUID = 8409639885468503013L;

    private Long productId;

    private String productName;

    private String productImageUrl;

    private Integer productSellingPrice;

    private Integer productAmount;
}
