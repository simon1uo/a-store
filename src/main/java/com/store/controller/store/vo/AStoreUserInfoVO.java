package com.store.controller.store.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AStoreUserInfoVO implements Serializable {

    private static final long serialVersionUID = -7381622125813158664L;

    private String userAccount;

    private String userPassword;

    private String userName;

    private String userAvatarUrl;

    private String userEmail;

    private String userPhone;
}
