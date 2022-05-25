package com.store.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName a_store_shopping_bag_item
 */
@TableName(value = "a_store_shopping_bag_item")
@Data
public class AStoreShoppingBagItem implements Serializable {
    /**
     * 购物项目id
     */
    @TableId(type = IdType.AUTO)
    private Long bagItemId;

    /**
     * 关联商品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer productAmount;

    /**
     * 用户id
     */
    private Long userId;

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
     * 最新修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}