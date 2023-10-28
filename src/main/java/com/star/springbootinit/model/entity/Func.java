package com.star.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表
 * @TableName func
 */
@Data
public class Func implements Serializable {
    /**
     * 权限id
     */
    private Long funcId;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 权限类型
     */
    private String funcName;

    /**
     * 路径 /userManager
     */
    private String funcUrl;

    /**
     * 权限代码 sys:dept:add
     */
    private String funcCode;

    /**
     * 功能类型1：目录、2：菜单、3：按钮
     */
    private Integer funcType;

    /**
     * 用户状态：0正常 1停用
     */
    private Integer status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}