-- we don't know how to generate root <with-no-name> (class Root) :(
grant select, system_user on *.* to 'mysql.infoschema'@localhost;

grant backup_admin, clone_admin, connection_admin, persist_ro_variables_admin, session_variables_admin, shutdown, super, system_user, system_variables_admin on *.* to 'mysql.session'@localhost;

grant system_user on *.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, group_replication_admin, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, trigger, update, xa_recover_admin, grant option on *.* to root@localhost;

create table a_store_product_carousel_card
(
    item_id          int auto_increment
        primary key,
    product_id       bigint unsigned                        null comment '商品id',
    item_image_url   varchar(100) default ''                not null comment '图片链接',
    item_title       varchar(100) default ''                not null comment '标题',
    item_subtitle    varchar(100) default ''                not null comment '副标题',
    item_description varchar(100) default ''                not null comment '描述',
    item_text_color  varchar(10)  default ''                not null comment '文字颜色',
    item_background  varchar(10)  default ''                not null comment '背景颜色',
    item_url         varchar(100) default ''                not null comment '链接',
    is_deleted       tinyint      default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user      int          default 0                 not null comment '创建者id',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    update_user      int          default 0                 not null comment '修改者id'
);

create table a_store_product_category
(
    category_id    bigint auto_increment comment '分类id'
        primary key,
    category_level tinyint     default 1                 not null comment '分类级别',
    parent_id      bigint      default 0                 not null comment '父分类id',
    category_name  varchar(50) default ''                not null comment '分类名称',
    category_rank  int         default 0                 not null comment '排序值(字段越大越靠前)',
    is_deleted     tinyint     default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time    datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    create_admin   int         default 0                 not null comment '创建者id',
    update_time    datetime    default CURRENT_TIMESTAMP not null comment '修改时间',
    update_admin   int         default 0                 null comment '修改者id'
)
    comment '产品分类';

create table a_store_product_category_card
(
    item_id        int auto_increment
        primary key,
    item_image_url varchar(200) default ''                not null comment '图片链接',
    item_info      varchar(200) default ''                not null comment '描述',
    item_url       varchar(100) default ''                not null comment '链接',
    is_deleted     tinyint      default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user    int          default 0                 not null comment '创建者id',
    update_time    datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    update_user    int          default 0                 not null comment '修改者id'
);

create table a_store_product_info
(
    product_id                 bigint unsigned auto_increment comment '商品表主键id'
        primary key,
    product_name               varchar(200) default ''                not null comment '商品名',
    product_intro              varchar(200) default ''                not null comment '商品简介',
    product_category_id        bigint       default 0                 not null comment '关联分类id',
    product_cover_image_url    varchar(200) default ''                not null comment '商品主图',
    product_carousel_image_url text                                   not null comment '轮播图链接',
    product_detail_content     text                                   not null comment '商品详情',
    original_price             int          default 1                 not null comment '商品价格',
    selling_price              int          default 1                 not null comment '商品实际售价',
    stock_num                  int unsigned default '0'               not null comment '商品库存数量',
    tag                        varchar(20)  default ''                not null comment '商品标签',
    product_status             tinyint      default 0                 not null comment '商品上架状态 1-下架 0-上架',
    is_deleted                 tinyint      default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_user                int          default 0                 not null comment '添加者主键id',
    create_time                datetime     default CURRENT_TIMESTAMP not null comment '商品添加时间',
    update_user                int          default 0                 not null comment '修改者主键id',
    update_time                datetime     default CURRENT_TIMESTAMP not null comment '商品修改时间'
);

create table a_store_shopping_bag_item
(
    bag_item_id    bigint auto_increment comment '购物项目id'
        primary key,
    product_id     bigint   default 0                 not null comment '关联商品id',
    product_amount int      default 1                 not null comment '数量',
    user_id        bigint                             not null comment '用户id',
    is_deleted     tinyint  default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime default CURRENT_TIMESTAMP not null comment '最新修改时间'
);

create table a_store_user
(
    user_id         bigint auto_increment comment '用户主键id'
        primary key,
    user_account    varchar(20)                           not null comment '用户账号',
    user_name       varchar(50) default ''                not null comment '用户昵称',
    user_password   varchar(32) default ''                not null comment '加密后的用户名密码',
    user_avatar_url varchar(50) default ''                not null,
    user_email      varchar(20) default ''                not null,
    user_phone      varchar(20) default ''                not null,
    is_deleted      tinyint     default 0                 not null comment '注销标识字段(0-正常 1-已注销)',
    locked_flag     tinyint     default 0                 not null comment '锁定标识字段(0-未锁定 1-已锁定)',
    create_time     datetime    default CURRENT_TIMESTAMP not null comment '注册时间'
);

create table a_store_user_address
(
    address_id     bigint auto_increment comment '地址id'
        primary key,
    user_id        bigint      default 0                 not null comment '用户主键id',
    user_name      varchar(30) default ''                not null comment '收货人姓名',
    user_phone     varchar(11) default ''                not null comment '收货人手机号',
    default_flag   tinyint     default 0                 not null comment '是否为默认 0-非默认 1-是默认',
    province_name  varchar(32) default ''                not null comment '省',
    city_name      varchar(32) default ''                not null comment '城',
    region_name    varchar(32) default ''                not null comment '区',
    detail_address varchar(64) default ''                not null comment '收件详细地址(街道/楼宇/单元)',
    is_deleted     tinyint     default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time    datetime    default CURRENT_TIMESTAMP not null comment '添加时间',
    update_time    datetime    default CURRENT_TIMESTAMP not null comment '修改时间'
)
    comment '用户收货地址表';

create table a_store_user_token
(
    user_id     bigint                             not null comment '用户主键id'
        primary key,
    token       varchar(32)                        not null comment 'token值',
    update_time datetime default CURRENT_TIMESTAMP not null comment '修改时间',
    expire_time datetime default CURRENT_TIMESTAMP not null comment 'token过期时间',
    constraint uq_token
        unique (token)
);

