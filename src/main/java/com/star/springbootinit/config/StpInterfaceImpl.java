package com.star.springbootinit.config;

import cn.dev33.satoken.stp.StpInterface;
import com.star.springbootinit.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * saToken 权限分配类
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private UserMapper userMapper;

    /**
     * 返回一个用户所拥有的权限集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        Long userId = Long.parseLong(loginId.toString());
        List<String> list = userMapper.searchUserPermissions(userId);
        return list;
    }


    /**
     * 返回一个用户所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        return null;
    }

}