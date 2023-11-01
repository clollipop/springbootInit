package com.star.springbootinit.common.page;

import com.github.pagehelper.PageInfo;

/**
 * <p>
 *
 * </p>
 *
 * @author: Star
 * @Date: 2023-10-31
 * @Description: com.star.springbootinit.common
 * @Version: 1.0
 */
public class PageUtils {

    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        System.out.println("a::"+pageInfo);
        PageResult pageResult = new PageResult();
        // 当前页数
        pageResult.setPageIndex(pageInfo.getPageNum());
        // 每页记录数 每页数量
        pageResult.setPageSize(pageInfo.getPageSize());
        // 总数量
        pageResult.setTotalCount(pageInfo.getTotal());
        // 总页数
        pageResult.setTotalPage(pageInfo.getPages());
        // 数据
        pageResult.setList(pageInfo.getList());
        return pageResult;
    }
}