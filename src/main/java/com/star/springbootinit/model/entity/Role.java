package com.star.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 *
 * @TableName role
 */
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色备注（描述）
     */
    private String remark;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 状态(0正常 1禁用)
     */
    private Integer status;

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