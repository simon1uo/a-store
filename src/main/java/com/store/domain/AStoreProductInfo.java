package com.store.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName a_store_product_info
 */
@TableName(value = "a_store_product_info")
@Data
public class AStoreProductInfo implements Serializable {
    /**
     * 商品表主键id
     */
    @TableId(type = IdType.AUTO)
    private Long productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品简介
     */
    private String productIntro;

    /**
     * 关联分类id
     */
    private Long productCategoryId;

    /**
     * 商品主图
     */
    private String productCoverImageUrl;

    /**
     * 轮播图链接
     */
    private String productCarouselImageUrl;

    /**
     * 商品详情
     */
    private String productDetailContent;

    /**
     * 商品价格
     */
    private Integer originalPrice;

    /**
     * 商品实际售价
     */
    private Integer sellingPrice;

    /**
     * 商品库存数量
     */
    private Integer stockNum;

    /**
     * 商品标签
     */
    private String tag;

    /**
     * 商品上架状态 1-下架 0-上架
     */
    private Integer productStatus;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 添加者主键id
     */
    private Integer createUser;

    /**
     * 商品添加时间
     */
    private Date createTime;

    /**
     * 修改者主键id
     */
    private Integer updateUser;

    /**
     * 商品修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}