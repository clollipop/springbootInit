-- 创建库
create database if not exists star_init;

-- 选择库
use star_init;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 用户表
drop table if exists `user`;
create table if not exists`user`(
    userId       bigint auto_increment comment '用户id'  primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    email        varchar(50)                            not null comment '邮箱',
    phone        varchar(50)                            not null comment '手机号',
    gender       tinyint	  default 2 				null comment '性别(0未知 1男 2女)',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    status       tinyint      default 0                 null comment '用户状态：0正常 1停用',
    usertype     varchar(2)   default '00' 				null comment '用户类型（00系统用户 01注册用户）',
    loginIp      varchar(128) default ''			    null comment '最后登录IP',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
) comment '用户表' collate = utf8mb4_unicode_ci;

-- 角色表
drop table if exists `role`;
create table if not exists`role`(
    roleId  bigint auto_increment comment '角色id' primary key,
    roleName      varchar(50)                               not null comment '角色名称',
    remark        varchar(512)                              not null comment '角色备注（描述）',
    createBy     varchar(64) default ''                 	not null comment '创建者',
    createTime   datetime     default CURRENT_TIMESTAMP     not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP comment '更新时间',
)comment '角色表' collate = utf8mb4_unicode_ci;


-- 权限表
drop table if exists `func`;
create table if not exists `func` (
    funcId bigint auto_increment comment '权限id' primary key,
    parentId   bigint                           null  comment '父菜单ID',
    funcName    varchar(50)                      not null comment '权限类型',
    funcUrl     varchar(50)                      not null comment '功能代码 user:create',
    funcType    tinyint     default 0            not null comment '功能类型1：目录、2：菜单、3：按钮',
    status      tinyint     default 0            null comment '用户状态：0正常 1停用',
    createBy   varchar(64) default ''           null comment '创建者',
    updateBy   varchar(64) default ''           null comment '更新者',
    sortNum     int(4)      default 0            null comment '排序',
    createTime   datetime     default CURRENT_TIMESTAMP     not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP comment '更新时间',
) ENGINE=InnoDB AUTO_INCREMENT=3054 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='权限表';


-- ----------------------------
-- 用户和角色关联表  用戶N-1角色
-- ----------------------------
drop table if exists user_role;
create table  userRole
(
    userId           bigint(20)    comment '用户id',
    roleId           bigint(20)    comment '角色id',
    primary key(userId, roleId)
) comment '用户和角色关联表' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- ----------------------------
-- 角色和菜单关联表  角色1-N权限
-- ----------------------------
drop table if exists role_menu;
create table  roleFunc
(
    roleId              bigint(20)  comment '角色id',
    funcId              bigint(20)  comment '权限id',
    primary key(roleId, funcId)
) comment '角色和菜单关联表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
