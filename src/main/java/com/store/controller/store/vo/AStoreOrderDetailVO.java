package com.store.controller.store.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class AStoreOrderDetailVO implements Serializable {

    private static final long serialVersionUID = -2655707696898746285L;

    private String orderNo;

    private Integer totalPrice;

    private Integer payStatus;

    private Integer payType;

    private String payTypeString;

    private Date payTime;

    private Integer orderStatus;

    private String orderStatusString;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private List<AStoreOrderItemVO> aStoreOrderItemVOList;

}
