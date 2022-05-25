package com.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.constant.ErrorCodeEnum;
import com.store.domain.AStoreProductInfo;
import com.store.exception.BusinessException;
import com.store.mapper.AStoreProductCategoryMapper;
import com.store.mapper.AStoreProductInfoMapper;
import com.store.service.AStoreProductInfoService;
import com.store.util.PageQueryUtil;
import com.store.util.PageResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Simon
 * @description 针对表【a_store_product_info】的数据库操作Service实现
 * @createDate 2022-05-24 17:21:05
 */
@Service
public class AStoreProductInfoServiceImpl extends ServiceImpl<AStoreProductInfoMapper, AStoreProductInfo>
        implements AStoreProductInfoService {
    @Resource
    private AStoreProductInfoMapper aStoreProductInfoMapper;

    @Resource
    private AStoreProductCategoryMapper aStoreProductCategoryMapper;


    @Override
    public AStoreProductInfo getProductInfoById(Long productId) {
        AStoreProductInfo aStoreProductInfo = aStoreProductInfoMapper.selectById(productId);
        if (aStoreProductInfo == null)
            throw new BusinessException(ErrorCodeEnum.NULL_ERROR);
        return aStoreProductInfo;
    }

    @Override
    public PageResultUtil searchProduct(PageQueryUtil pageQuery) {
        // todo
        return null;
    }
}




