package com.store.controller.store.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AStoreProductDetailInfoVO implements Serializable {
    private Long productId;

    private String productName;

    private String productIntro;

    private Long productCategoryId;

    private String productCoverImageUrl;

    private String productCarouselImageUrl;

    private String productDetailContent;

    private Integer sellingPrice;

    private Integer originalPrice;

    private String tag;

}