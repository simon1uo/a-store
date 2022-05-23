DROP TABLE IF EXISTS `a_store_user`;

CREATE TABLE `a_store_user`
(
    `user_id`       bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
    `user_account`  varchar(20) NOT NULL COMMENT '用户账号',
    `user_name`     varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
    `user_password` varchar(32) NOT NULL DEFAULT '' COMMENT '加密后的用户名密码',
    `is_deleted`    tinyint(4)  NOT NULL DEFAULT '0' COMMENT '注销标识字段(0-正常 1-已注销)',
    `locked_flag`   tinyint(4)  NOT NULL DEFAULT '0' COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
    `create_time`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;


# Dump of table a_store_user_address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `a_store_user_address`;

CREATE TABLE `a_store_user_address`
(
    `address_id`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '地址id',
    `user_id`        bigint(20)  NOT NULL DEFAULT '0' COMMENT '用户主键id',
    `user_name`      varchar(30) NOT NULL DEFAULT '' COMMENT '收货人姓名',
    `user_phone`     varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
    `default_flag`   tinyint(4)  NOT NULL DEFAULT '0' COMMENT '是否为默认 0-非默认 1-是默认',
    `province_name`  varchar(32) NOT NULL DEFAULT '' COMMENT '省',
    `city_name`      varchar(32) NOT NULL DEFAULT '' COMMENT '城',
    `region_name`    varchar(32) NOT NULL DEFAULT '' COMMENT '区',
    `detail_address` varchar(64) NOT NULL DEFAULT '' COMMENT '收件详细地址(街道/楼宇/单元)',
    `is_deleted`     tinyint(4)  NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
    `create_time`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `update_time`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`address_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户收货地址表';

# Dump of table a_store_user_token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `a_store_user_token`;

CREATE TABLE `a_store_user_token`
(
    `user_id`     bigint(20)  NOT NULL COMMENT '用户主键id',
    `token`       varchar(32) NOT NULL COMMENT 'token值',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `expire_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'token过期时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uq_token` (`token`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
