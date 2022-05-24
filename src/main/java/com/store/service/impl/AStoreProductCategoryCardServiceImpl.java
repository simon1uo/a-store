package com.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.constant.CommonServiceEnum;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.vo.AStoreProductCategoryCardVO;
import com.store.domain.AStoreProductCategoryCard;
import com.store.exception.BusinessException;
import com.store.mapper.AStoreProductCategoryCardMapper;
import com.store.service.AStoreProductCategoryCardService;
import com.store.util.BeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Simon
 * @description 针对表【a_store_product_category_card】的数据库操作Service实现
 * @createDate 2022-05-23 21:00:17
 */
@Service
public class AStoreProductCategoryCardServiceImpl extends ServiceImpl<AStoreProductCategoryCardMapper, AStoreProductCategoryCard>
        implements AStoreProductCategoryCardService {

    @Resource
    private AStoreProductCategoryCardMapper aStoreProductCategoryCardMapper;

    @Override
    public List<AStoreProductCategoryCardVO> getCategoryCardItems() {
        List<AStoreProductCategoryCardVO> aStoreProductCategoryCardVOS = null;
        QueryWrapper<AStoreProductCategoryCard> queryWrapper = new QueryWrapper<>();
        List<AStoreProductCategoryCard> aStoreProductCategoryCards = aStoreProductCategoryCardMapper.selectList(queryWrapper);

        if (!CollectionUtils.isEmpty(aStoreProductCategoryCards)) {
            aStoreProductCategoryCardVOS = BeanUtil.copyList(aStoreProductCategoryCards, AStoreProductCategoryCardVO.class);
        } else {
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR, CommonServiceEnum.DATABASE_ERROR.getResult());
        }
        return aStoreProductCategoryCardVOS;
    }
}




