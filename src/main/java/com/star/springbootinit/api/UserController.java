package com.star.springbootinit.api;

import cn.dev33.satoken.stp.StpUtil;
import com.star.springbootinit.common.page.PageResult;
import com.star.springbootinit.common.page.PageRequest;
import com.star.springbootinit.common.resp.ResponseResult;
import com.star.springbootinit.model.dto.UserLoginDTO;
import com.star.springbootinit.model.entity.User;
import com.star.springbootinit.model.vo.UserVO;
import com.star.springbootinit.service.UserService;
import com.star.springbootinit.common.resp.R;
import com.star.springbootinit.tools.NetUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author: Star
 * @Description: com.star.springbootinit.api
 * @Version: 1.0
 */
@Api(tags="用户模块")
@RestController
@RequestMapping("/user")
@ResponseResult
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/get/{id}")
    public R<User> getUser(@PathVariable Long id) {
        return R.success(null);
    }
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public UserVO login(@Valid @RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        // 获取IP
        String ip = NetUtils.getIpAddress(request);
        return userService.login(userLoginDTO, ip);
    }
    @ApiOperation(value = "退出登录")
    @GetMapping("/outLogin")
    public String outLogin(Long id){
        StpUtil.logout(id);
        return "注销成功";
    }
    @ApiOperation(value = "获取用户信息(分页)")
    @PostMapping("/info")
    public PageResult getUserInfo(@RequestBody PageRequest pageQuery){
        PageResult userInfoPage = userService.getUserInfoPage(pageQuery);
        return userInfoPage;
    }
}
