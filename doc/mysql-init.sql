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
    gender       tinyint	  default 0 				comment '性别(0未知 1男 2女)',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    status       tinyint      default 0                 comment '用户状态：0正常 1停用',
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
    status       tinyint  default 0               	        not null comment '状态(0正常 1禁用)',
    createTime   datetime     default CURRENT_TIMESTAMP     not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP comment '更新时间',
)comment '角色表' collate = utf8mb4_unicode_ci;


-- 权限表
drop table if exists `func`;
create table if not exists `func` (
    funcId bigint auto_increment comment '权限id' primary key,
    parentId   bigint                           null  comment '父菜单ID',
    funcName    varchar(50)                      not null comment '权限类型',
    funcUrl     varchar(50)                      not null comment '路径 /userManager',
    funcCode    varchar(50)                     null comment '权限代码 sys:dept:add',
    funcType    tinyint     default 1            not null comment '功能类型1：目录、2：菜单、3：按钮',
    status      tinyint     default 0            null comment '用户状态：0正常 1停用',
    createBy   varchar(64) default ''           null comment '创建者',
    updateBy   varchar(64) default ''           null comment '更新者',
    sortNum     int(4)      default 0            null comment '排序',
    createTime   datetime     default CURRENT_TIMESTAMP     not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP comment '更新时间',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='权限表';


-- ----------------------------
-- 用户和角色关联表  用戶N-1角色
-- ----------------------------
drop table if exists userRole;
create table  userRole
(
    userId           bigint(20)    comment '用户id',
    roleId           bigint(20)    comment '角色id',
    primary key(userId, roleId)
) comment '用户和角色关联表' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- ----------------------------
-- 角色和菜单关联表  角色1-N权限
-- ----------------------------
drop table if exists roleFunc;
create table  roleFunc
(
    roleId              bigint(20)  comment '角色id',
    funcId              bigint(20)  comment '权限id',
    primary key(roleId, funcId)
) comment '角色和菜单关联表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

use star_init;
-- 添加管理员用户
insert into user(userAccount,userPassword,email,phone,gender,userName,userProfile,status,usertype)
values ('star_1','123456','1289232@qq.com',18188290381,1,'帅管理员','这个是帅管理员',0,'00');

-- 添加角色
insert into role(roleName,remark,createBy)
values ('系统超级管理员','系统超级管理员角色','star');

insert into role(roleName,remark,createBy)
values ('平台管理员','平台管理员','star');

insert into role(roleName,remark,createBy)
values ('超级会员用户','开通超级会员用户','star');

insert into role(roleName,remark,createBy)
values ('会员用户','开通会员用户','star');

insert into role(roleName,remark,createBy)
values ('普通用户','注册默认角色','star');

-- 添加权限
insert into func(funcName,funcUrl,createBy,sortNum)
values ('系统管理','','star',9000);
insert into func(funcName,funcUrl,createBy,sortNum)
values ('用户管理','/userManager','star',8000);
insert into func(funcName,funcUrl,createBy,sortNum)
values ('角色管理','/roleManager','star',7000);
insert into func(funcName,funcUrl,createBy,sortNum)
values ('权限管理','/funcManager','star',6000);

-- 绑定 用户角色
insert into userRole
values (1,1);
insert into userRole
values (1,2);

-- 绑定 角色权限
insert into roleFunc(roleId,funcId) values (1,1);
insert into roleFunc(roleId,funcId) values (1,2);
insert into roleFunc(roleId,funcId) values (1,3);
insert into roleFunc(roleId,funcId) values (1,4);
