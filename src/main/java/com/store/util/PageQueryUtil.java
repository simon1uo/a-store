package com.store.util;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class PageQueryUtil extends LinkedHashMap<String, Object> {

    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 每页条数
     */
    private int limitCount;


    public PageQueryUtil(Map<String, Object> params) {
        // 分页参数
        this.currentPage = Integer.parseInt(params.get("currentPage").toString());
        this.limitCount = Integer.parseInt(params.get("limitCount").toString());

        this.put("start", (currentPage - 1) * limitCount);
        this.put("currentPage", currentPage);
        this.put("limitCount", limitCount);
    }
}
