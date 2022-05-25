package com.store.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResultUtil<T> implements Serializable {
    /**
     * 总记录
     */
    private int totalCount;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 列表数据
     */
    private List<T> dataList;


    public PageResultUtil(int totalCount, int pageSize, int currentPage, List<T> dataList) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.dataList = dataList;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}
