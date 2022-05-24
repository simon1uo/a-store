package com.store.controller.store.vo;

import lombok.Data;

@Data
public class AStoreUserAddressVO {
    private Long addressId;

    private Long userId;

    private String userName;

    private String userPhone;

    private Byte defaultFlag;

    private String provinceName;

    private String cityName;

    private String regionName;

    private String detailAddress;
}
