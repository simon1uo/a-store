package com.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.constant.*;
import com.store.controller.store.param.AStoreShoppingBagAddParam;
import com.store.controller.store.param.AStoreShoppingBagUpdateParam;
import com.store.controller.store.vo.AStoreShoppingBagItemVO;
import com.store.domain.AStoreProductInfo;
import com.store.domain.AStoreShoppingBagItem;
import com.store.exception.BusinessException;
import com.store.mapper.AStoreProductInfoMapper;
import com.store.mapper.AStoreShoppingBagItemMapper;
import com.store.service.AStoreShoppingBagItemService;
import com.store.util.BeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Simon
 * @description 针对表【a_store_shopping_bag_item】的数据库操作Service实现
 * @createDate 2022-05-24 16:48:07
 */
@Service
public class AStoreShoppingBagItemServiceImpl extends ServiceImpl<AStoreShoppingBagItemMapper, AStoreShoppingBagItem>
        implements AStoreShoppingBagItemService {

    @Resource
    private AStoreProductInfoMapper aStoreProductInfoMapper;

    @Resource
    private AStoreShoppingBagItemMapper aStoreShoppingBagItemMapper;

    @Override
    public List<AStoreShoppingBagItemVO> getShoppingBagItemListByUserId(Long userId) {
        QueryWrapper<AStoreShoppingBagItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<AStoreShoppingBagItem> aStoreShoppingBagItems = aStoreShoppingBagItemMapper.selectList(queryWrapper);
        List<AStoreShoppingBagItemVO> aStoreShoppingBagItemVOS = new ArrayList<>();
        return this.getShoppingCartItemVOS(aStoreShoppingBagItemVOS, aStoreShoppingBagItems);
    }

    @Override
    public AStoreShoppingBagItem getShoppingBagItemDetailById(Long bagItemId) {
        AStoreShoppingBagItem aStoreShoppingBagItem = aStoreShoppingBagItemMapper.selectById(bagItemId);
        if (aStoreShoppingBagItem == null) throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
        return aStoreShoppingBagItem;
    }

    @Override
    public List<AStoreShoppingBagItemVO> getShoppingBagItemsForSettle(List<Long> bagItemIds, Long userId) {
        List<AStoreShoppingBagItemVO> aStoreShoppingBagItemVOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(bagItemIds)) throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "购物项不能为空");
        List<AStoreShoppingBagItem> aStoreShoppingBagItems = aStoreShoppingBagItemMapper.selectBatchIds(bagItemIds);
        if (CollectionUtils.isEmpty(aStoreShoppingBagItems))
            throw new BusinessException(ErrorCodeEnum.NULL_ERROR, "购物项不能为空");
        if (aStoreShoppingBagItems.size() != bagItemIds.size())
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);

        return getShoppingCartItemVOS(aStoreShoppingBagItemVOS, aStoreShoppingBagItems);
    }

    @Override
    public String addShoppingBagItem(AStoreShoppingBagAddParam aStoreShoppingBagAddParam, Long userId) {
        QueryWrapper<AStoreShoppingBagItem> tempQueryWrapper = new QueryWrapper<>();
        tempQueryWrapper.eq("user_id", userId);
        tempQueryWrapper.eq("product_id", aStoreShoppingBagAddParam.getProductId());
        AStoreShoppingBagItem temp = aStoreShoppingBagItemMapper.selectOne(tempQueryWrapper);
        // 已存在
        if (temp != null)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, ShoppingBagResultEnum.SHOPPING_BAG_ITEM_EXIST_ERROR.getResult());

        // 商品不存在
        AStoreProductInfo product = aStoreProductInfoMapper.selectById(aStoreShoppingBagAddParam.getProductId());
        if (product == null)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, ProductResultEnum.PRODUCT_NOT_EXIST.getResult());

        // 数量有误
        if (aStoreShoppingBagAddParam.getProductAmount() < 1 || aStoreShoppingBagAddParam.getProductAmount() > CommonConstant.SHOPPING_BAG_ITEM_LIMIT_NUMBER)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, ShoppingBagResultEnum.SHOPPING_BAG_ITEM_NUMBER_ERROR.getResult());

        QueryWrapper<AStoreShoppingBagItem> totalCountQueryWrapper = new QueryWrapper<>();
        totalCountQueryWrapper.eq("user_id", userId);
        Long totalCount = aStoreShoppingBagItemMapper.selectCount(totalCountQueryWrapper);
        if (totalCount > CommonConstant.SHOPPING_BAG_ITEM_TOTAL_LIMIT_NUMBER)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, ShoppingBagResultEnum.SHOPPING_BAG_ITEM_LIMIT_NUMBER_ERROR.getResult());

        AStoreShoppingBagItem newShoppingBagItem = new AStoreShoppingBagItem();
        BeanUtil.copyProperties(aStoreShoppingBagAddParam, newShoppingBagItem);
        newShoppingBagItem.setUserId(userId);
        int insertResult = aStoreShoppingBagItemMapper.insert(newShoppingBagItem);
        if (insertResult > 0) return CommonServiceEnum.SUCCESS.getResult();

        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public String updateShoppingBagItem(AStoreShoppingBagUpdateParam aStoreShoppingBagUpdateParam, Long userId) {
        AStoreShoppingBagItem aStoreShoppingBagItemTemp = aStoreShoppingBagItemMapper.selectById(aStoreShoppingBagUpdateParam.getBagItemId());
        if (aStoreShoppingBagItemTemp == null)
            throw new BusinessException(ErrorCodeEnum.NULL_ERROR);
        if (!aStoreShoppingBagItemTemp.getUserId().equals(userId))
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        if (aStoreShoppingBagUpdateParam.getProductAmount() > CommonConstant.SHOPPING_BAG_ITEM_LIMIT_NUMBER)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        if (aStoreShoppingBagUpdateParam.getProductAmount().equals(aStoreShoppingBagItemTemp.getProductAmount()))
            return CommonServiceEnum.SUCCESS.getResult();

        aStoreShoppingBagItemTemp.setProductAmount(aStoreShoppingBagUpdateParam.getProductAmount());
        aStoreShoppingBagItemTemp.setUpdateTime(new Date());

        int updateResult = aStoreShoppingBagItemMapper.updateById(aStoreShoppingBagItemTemp);
        if (updateResult > 0) return CommonServiceEnum.SUCCESS.getResult();

        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public Boolean deleteShoppingBagItemById(Long bagItemId, Long userId) {
        AStoreShoppingBagItem aStoreShoppingBagItem = aStoreShoppingBagItemMapper.selectById(bagItemId);
        if (aStoreShoppingBagItem == null) throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        if (!aStoreShoppingBagItem.getUserId().equals(userId)) throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);

        return aStoreShoppingBagItemMapper.deleteById(bagItemId) > 0;
    }


    /**
     * 数据转换
     *
     * @param aStoreShoppingBagItemVOS
     * @param aStoreShoppingBagItems
     * @return
     */
    private List<AStoreShoppingBagItemVO> getShoppingCartItemVOS(List<AStoreShoppingBagItemVO> aStoreShoppingBagItemVOS, List<AStoreShoppingBagItem> aStoreShoppingBagItems) {
        if (!CollectionUtils.isEmpty(aStoreShoppingBagItems)) {
            //查询商品信息并做数据转换
            List<Long> newBeeMallGoodsIds = aStoreShoppingBagItems.stream().map(AStoreShoppingBagItem::getProductId).collect(Collectors.toList());
            List<AStoreProductInfo> aStoreProductInfos = aStoreProductInfoMapper.selectBatchIds(newBeeMallGoodsIds);
            Map<Long, AStoreProductInfo> aStoreProductInfosMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(aStoreProductInfos)) {
                aStoreProductInfosMap = aStoreProductInfos.stream().collect(Collectors.toMap(AStoreProductInfo::getProductId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (AStoreShoppingBagItem aStoreShoppingBagItem : aStoreShoppingBagItems) {
                AStoreShoppingBagItemVO aStoreShoppingBagItemVO = new AStoreShoppingBagItemVO();
                BeanUtil.copyProperties(aStoreShoppingBagItem, aStoreShoppingBagItemVO);
                if (aStoreProductInfosMap.containsKey(aStoreShoppingBagItem.getProductId())) {
                    AStoreProductInfo aStoreProductInfoTemp = aStoreProductInfosMap.get(aStoreShoppingBagItem.getProductId());
                    aStoreShoppingBagItemVO.setProductImageUrl(aStoreProductInfoTemp.getProductCoverImageUrl());
                    String productName = aStoreProductInfoTemp.getProductName();
                    // 字符串过长导致文字超出的问题
                    if (productName.length() > 28) {
                        productName = productName.substring(0, 28) + "...";
                    }
                    aStoreShoppingBagItemVO.setProductName(productName);
                    aStoreShoppingBagItemVO.setProductSellingPrice(aStoreProductInfoTemp.getSellingPrice());
                    aStoreShoppingBagItemVOS.add(aStoreShoppingBagItemVO);
                }
            }
        }
        return aStoreShoppingBagItemVOS;
    }
}




