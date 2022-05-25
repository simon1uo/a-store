package com.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.store.domain.AStoreProductInfo;
import com.store.util.PageQueryUtil;
import com.store.util.PageResultUtil;

/**
 * @author Simon
 * @description 针对表【a_store_product_info】的数据库操作Service
 * @createDate 2022-05-24 17:21:05
 */
public interface AStoreProductInfoService extends IService<AStoreProductInfo> {

    /**
     * 根据产品id获取
     *
     * @param productId
     * @return
     */
    AStoreProductInfo getProductInfoById(Long productId);

    PageResultUtil searchProduct(PageQueryUtil pageQuery);
}
