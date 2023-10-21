package com.star.springbootinit.tools.resp;

import lombok.Getter;

/**
 * @Auther: Star
 * @Date: 2023-06-14
 * @Description: 异常返回枚举
 * @Version: 1.0
 */
public enum ResultCode {
    /**
     * 操作失败
     **/
    RC999(999, "操作失败"),
    /**
     * 服务限流
     **/
    RC200(200, "操作成功"),
    /**
     * 服务降级
     **/
    RC201(201, "服务开启降级保护,请稍后再试!"),
    /**
     * 热点参数限流
     **/
    RC202(202, "热点参数限流,请稍后再试!"),
    /**
     * 系统规则不满足
     **/
    RC203(203, "系统规则不满足要求,请稍后再试!"),
    /**
     * 授权规则不通过
     **/
    RC204(204, "授权规则不通过,请稍后再试!"),
    /**
     * access_denied
     **/
    RC403(403, "无访问权限,请联系管理员授予权限"),
    /**
     * access_denied
     **/
    RC401(401, "匿名用户访问无权限资源时的异常"),
    /**
     * 服务异常
     **/
    RC500(500, "系统异常，请稍后重试"),
    /**
     * 请求参数校验异常
     */
    PARAMETER_EXCEPTION(501, "请求参数校验异常"),
    /**
     * 访问令牌不合法
     */
    INVALID_TOKEN(2001, "访问令牌不合法"),
    /**
     * 没有权限访问该资源
     */
    ACCESS_DENIED(2003, "没有权限访问该资源"),
    /**
     * 客户端认证失败
     */
    CLIENT_AUTHENTICATION_FAILED(1001, "客户端认证失败"),
    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    /**
     * 不支持的认证模式
     */
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),
    /**
     * 枚举参数不匹配
     */
    ENUM_PARAMS_NOT_MATCH(1004,"枚举参数不匹配"),

    // ----------------50000-----------member--------------------
    /**
     * 手机号已注册
     */
    MEMBER_MOBIL_EXIST(50001,"手机号已注册"),
    /**
     * 请输入邮箱或手机号
     */
    MEMBER_EMAIL_MOBIL_NULL(50002,"请输入邮箱或手机号"),
     /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERROR(50003,"验证码错误"),
    /**
     * 密码错误或账户不存在
     */
    MEMBER_EMAIL_NULL(50004,"密码错误或账户不存在"),
    /**
     * 登陆类型错误
     */
    MEMBER_LOGIN_TYPE(50005,"登陆类型有误"),


    ;

    /**
     * 自定义状态码
     **/
    @Getter
    private final int code;

    /**
     * 携 带 消 息
     */
    @Getter
    private final String message;

    /**
     * 构 造 方 法
     */
    ResultCode(int code, String message) {

        this.code = code;

        this.message = message;
    }

    /**
     * 根据 code 去找到 Message
     * 根据 Message 去找code
     * @param code
     * @return
     */
    public static <T> ResultCode findEnumCode(int code) {
        for (ResultCode statusEnum : ResultCode.values()) {
            if (statusEnum.getCode()==code) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("code is not support");
    }
    /**
     * 根据 Message 去找code对应的枚举
     * @param msg
     * @return
     */
    public static <T> ResultCode findEnumMsg(String msg) {
        for (ResultCode statusEnum : ResultCode.values()) {
            if (statusEnum.getMessage().equals(msg)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("msg is not support");
    }

}
