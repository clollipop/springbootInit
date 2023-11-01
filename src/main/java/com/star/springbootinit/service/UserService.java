package com.star.springbootinit.service;

import com.star.springbootinit.common.page.PageRequest;
import com.star.springbootinit.common.page.PageResult;
import com.star.springbootinit.model.dto.UserLoginDTO;
import com.star.springbootinit.model.vo.UserVO;

/**
 * <p>
 *
 * </p>
 *
 * @author: Star
 * @Date: 2023-10-29
 * @Description: com.star.springbootinit.service
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 登录
     * @param userLoginDTO 前端传给后端登录数据
     * @param ip 客户端ip地址
     * @return 用户信息
     */
    UserVO login(UserLoginDTO  userLoginDTO, String ip);

    /**
     *
     * @param pageRequest 用户请求分页数据
     * @return 分页数据结果
     */
    PageResult getUserInfoPage(PageRequest pageRequest);

}
