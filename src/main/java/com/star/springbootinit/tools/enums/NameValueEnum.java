package com.star.springbootinit.tools.enums;
/**
 * <p>
 *      名称和值 接口
 * </p>
 * @author: Star
 * @Description: com.star.utils.enums
 * @Version: 1.0
 */
public interface NameValueEnum<T> extends ValueEnum<T>{
    /**
     * 获取枚举名称
     *
     * @return 枚举名
     */
    String getName();
}
