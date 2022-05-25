package com.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.store.controller.store.param.AStoreShoppingBagAddParam;
import com.store.controller.store.param.AStoreShoppingBagUpdateParam;
import com.store.controller.store.vo.AStoreShoppingBagItemVO;
import com.store.domain.AStoreShoppingBagItem;

import java.util.List;

/**
 * @author Simon
 * @description 针对表【a_store_shopping_bag_item】的数据库操作Service
 * @createDate 2022-05-24 16:48:07
 */
public interface AStoreShoppingBagItemService extends IService<AStoreShoppingBagItem> {

    /**
     * 根据用户id获取购物袋列表
     *
     * @param userId 用户id
     * @return 购物袋列表
     */
    List<AStoreShoppingBagItemVO> getShoppingBagItemListByUserId(Long userId);

    /**
     * 根据购物项id获取详细信息
     *
     * @param bagItemId id 购物袋项id
     * @return 购物袋项详细信息
     */
    AStoreShoppingBagItem getShoppingBagItemDetailById(Long bagItemId);

    /**
     * 根据用户id和购物项ids获取购物袋信息
     *
     * @param bagItemIds 购物项id列表
     * @param userId     用户id
     * @return 购物袋列表
     */
    List<AStoreShoppingBagItemVO> getShoppingBagItemsForSettle(List<Long> bagItemIds, Long userId);

    /**
     * 新增购物袋购物项
     *
     * @param aStoreUserAddressAddParam 购物项
     * @param userId                    用户id
     * @return 新增操作结果
     */
    String addShoppingBagItem(AStoreShoppingBagAddParam aStoreUserAddressAddParam, Long userId);

    /**
     * 修改购物袋购物项
     *
     * @param aStoreShoppingBagUpdateParam 购物项
     * @return 修改操作结果
     */
    String updateShoppingBagItem(AStoreShoppingBagUpdateParam aStoreShoppingBagUpdateParam, Long userId);

    /**
     * 根据用户id和购物项id 删除购物袋项
     *
     * @param bagItemId 购物项id
     * @param userId    用户id
     * @return 删除操作结果
     */
    Boolean deleteShoppingBagItemById(Long bagItemId, Long userId);

}
