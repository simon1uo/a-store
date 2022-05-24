package com.store.controller.store.param;

import lombok.Data;

@Data
public class AStoreUserAddressAddParam {
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
