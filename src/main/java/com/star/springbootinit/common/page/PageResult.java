package com.star.springbootinit.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int pageIndex;
    /**
     * 列表数据
     */
    private List<?> list;

//    public PageResult(List<?> list, long totalCount, int pageIndex, int pageSize) {
//        this.list = list;
//        this.totalCount = totalCount;
//        this.pageSize = pageSize;
//        this.pageIndex = pageIndex;
//        this.totalPage =(int) Math.ceil((double) totalCount / pageSize) ;
//    }

}