package com.star.springbootinit.model.vo;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author: Star
 * @Description: com.star.springbootinit.model.vo
 * @Version: 1.0
 */
@Data
public class UserVO {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     */
    private String userAccount;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别(0未知 1男 2女)
     */
    private Integer gender;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;
    /**
     * token
     */
    private String token;
}
