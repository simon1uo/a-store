package com.store.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName a_store_user
 */
@TableName(value = "a_store_user")
@Data
public class AStoreUser implements Serializable {
    /**
     * 用户主键id
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 加密后的用户名密码
     */
    private String userPassword;

    /**
     *
     */
    private String userAvatarUrl;

    /**
     *
     */
    private String userEmail;

    /**
     *
     */
    private String userPhone;

    /**
     * 注销标识字段(0-正常 1-已注销)
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     */
    private Integer lockedFlag;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}