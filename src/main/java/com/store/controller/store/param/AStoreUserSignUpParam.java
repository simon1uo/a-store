package com.store.controller.store.param;

import lombok.Data;

@Data
public class AStoreUserSignUpParam {
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
