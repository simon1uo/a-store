package com.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.store.controller.store.vo.AStoreProductCategoryCardVO;
import com.store.domain.AStoreProductCategoryCard;

import java.util.List;

/**
 * @author Simon
 * @description 针对表【a_store_product_category_card】的数据库操作Service
 * @createDate 2022-05-23 21:00:17
 */
public interface AStoreProductCategoryCardService extends IService<AStoreProductCategoryCard> {
    List<AStoreProductCategoryCardVO> getCategoryCardItems();
}
