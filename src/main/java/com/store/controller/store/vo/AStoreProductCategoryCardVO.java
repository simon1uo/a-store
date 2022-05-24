package com.store.controller.store.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AStoreProductCategoryCardVO implements Serializable {

    private static final long serialVersionUID = -2457885528247600367L;

    private Integer itemId;

    private String itemImageUrl;

    private String itemInfo;

    private String itemUrl;

}
