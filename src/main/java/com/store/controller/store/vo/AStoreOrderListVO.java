package com.store.controller.store.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class AStoreOrderListVO implements Serializable {
    private static final long serialVersionUID = -4215627668658916163L;

    private Long orderId;

    private String orderNo;

    private Integer totalPrice;

    private Integer payType;

    private Integer orderStatus;

    private String orderStatusString;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private List<AStoreOrderItemVO> orderItemList;
}
