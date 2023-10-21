package com.star.springbootinit.tools.enums;

/**
 * <p>
 *     主要是用来给枚举继承和实现该接口，达到一个向下转型的一个效果
 *     {@link }
 * <p/>
 * @Auther: Star
 * @Description: com.star.utils.enums
 * @Version: 1.0
 */
public interface ValueEnum<T> {
    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    T getValue();
}
