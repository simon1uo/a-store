package com.store.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName a_store_order_item
 */
@TableName(value = "a_store_order_item")
@Data
public class AStoreOrderItem implements Serializable {
    /**
     * 订单关联购物项主键id
     */
    @TableId(type = IdType.AUTO)
    private Long orderItemId;

    /**
     * 订单主键id
     */
    private Long orderId;

    /**
     * 关联商品id
     */
    private Long productId;

    /**
     * 下单时商品的名称(订单快照)
     */
    private String productName;

    /**
     * 下单时商品的主图(订单快照)
     */
    private String productImageUrl;

    /**
     * 下单时商品的价格(订单快照)
     */
    private Integer productSellingPrice;

    /**
     * 数量(订单快照)
     */
    private Integer productAmount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}