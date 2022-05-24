package com.store.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName a_store_product_category_card
 */
@TableName(value = "a_store_product_category_card")
@Data
public class AStoreProductCategoryCard implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer itemId;

    /**
     * 图片链接
     */
    private String itemImageUrl;

    /**
     * 描述
     */
    private String itemInfo;

    /**
     * 链接
     */
    private String itemUrl;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者id
     */
    private Integer createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改者id
     */
    private Integer updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}