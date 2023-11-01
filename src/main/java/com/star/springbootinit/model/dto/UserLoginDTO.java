package com.star.springbootinit.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
public class UserLoginDTO {

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String email;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String userPassword;

}
