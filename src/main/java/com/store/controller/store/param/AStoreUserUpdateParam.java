package com.store.controller.store.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class AStoreUserUpdateParam implements Serializable {

    private static final long serialVersionUID = -8937983263221201578L;

    private String userName;

    private String userPassword;

    private String userAvatarUrl;

    private String userEmail;

    private String userPhone;
}
