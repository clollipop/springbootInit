package com.star.springbootinit.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * <p>
 *      分页请求
 * </p>
 *
 * @author: Star
 * @Description: com.star.springbootinit.common
 * @Version: 1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    /**
     * 当前页码
     */
    @Min(0)
    private int pageNum;
    /**
     * 每页数量
     */
    @Max(25)
    private int pageSize;

}
