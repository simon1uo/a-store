package com.store.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单收货地址关联表
 *
 * @TableName a_store_order_address
 */
@TableName(value = "a_store_order_address")
@Data
public class AStoreOrderAddress implements Serializable {
    /**
     *
     */
    @TableId
    private Long orderId;

    /**
     * 收货人姓名
     */
    private String userName;

    /**
     * 收货人手机号
     */
    private String userPhone;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 城
     */
    private String cityName;

    /**
     * 区
     */
    private String regionName;

    /**
     * 收件详细地址(街道/楼宇/单元)
     */
    private String detailAddress;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}