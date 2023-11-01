package com.star.springbootinit.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.springbootinit.common.page.PageRequest;
import com.star.springbootinit.common.page.PageResult;
import com.star.springbootinit.common.page.PageUtils;
import com.star.springbootinit.common.resp.ResultCode;
import com.star.springbootinit.exception.BizException;
import com.star.springbootinit.exception.ParamException;
import com.star.springbootinit.mapper.UserMapper;
import com.star.springbootinit.model.dto.UserLoginDTO;
import com.star.springbootinit.model.entity.User;
import com.star.springbootinit.model.vo.UserVO;
import com.star.springbootinit.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Star
 * @Description: com.star.springbootinit.service
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param userLoginDTO 登录用户信息
     * @return
     */
    @Override
    public UserVO login(UserLoginDTO userLoginDTO, String ip) {
        User user = CglibUtil.copy(userLoginDTO, User.class);
        // 判断是不是邮箱
        boolean emailIs = ReUtil.isMatch(
                "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
                user.getEmail());
        // 如果是邮箱不需要进行替换 不是邮箱需要替换成手机号
        if (!emailIs){
           // 加强校验 判断是不是手机
            boolean phoneIs = ReUtil.isMatch(
                    "^[1-9]{1}[0-9]{5,8}$",
                    user.getEmail());
            if (!phoneIs){
                throw new ParamException(ResultCode.USER_EMAIL_PHONE_NULL);
            }
            user.setPhone(user.getEmail());
            user.setEmail(null);
        }
        User userLogin = userMapper.login(user);
        if (userLogin==null){
            throw new BizException(ResultCode.USER_EMAIL_PHONE_NULL);
        }
        // 更新登录IP
        userMapper.updateIp(user.getUserId(), ip);
        UserVO userVO = CglibUtil.copy(userLogin, UserVO.class);
        StpUtil.login(userVO.getUserId());
        // 第2步，获取 Token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        userVO.setToken(tokenInfo.tokenValue);
        return userVO;
    }

    /**
     *
     * @param pageRequest 用户请求分页数据
     * @return 分页结果
     */
    @Override
    public PageResult getUserInfoPage(PageRequest pageRequest) {
        PageInfo<User> pageInfo = getPageInfo(pageRequest);
        System.out.println("pageInfo:");
        System.out.println(pageInfo);
        PageResult pageResult = PageUtils.getPageResult(pageRequest, pageInfo);
        List<UserVO> userVOList = CglibUtil.copyList(pageResult.getList(), UserVO::new);
        pageResult.setList(userVOList);
        return pageResult;
    }

    /**
     * 调用分页插件完成分页
     * @return
     */
    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> userInfoList = userMapper.userInfoPage();
        return new PageInfo<User>(userInfoList);
    }

}
