package com.store.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName a_store_user_token
 */
@TableName(value ="a_store_user_token")
@Data
public class AStoreUserToken implements Serializable {
    /**
     * 用户主键id
     */
    @TableId
    private Long userId;

    /**
     * token值
     */
    private String token;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * token过期时间
     */
    private Date expireTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}