package com.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.store.controller.store.vo.AStoreUserAddressVO;
import com.store.domain.AStoreUserAddress;

import java.util.List;

/**
 * @author Simon
 * @description 针对表【a_store_user_address(用户收货地址表)】的数据库操作Service
 * @createDate 2022-05-23 14:39:10
 */
public interface AStoreUserAddressService extends IService<AStoreUserAddress> {

    /**
     * 获取收货信息列表
     *
     * @return 结果
     */
    List<AStoreUserAddressVO> getUserAddressList(Long userId);

    /**
     * 根据id获取
     *
     * @param addressId
     * @return
     */
    AStoreUserAddress getUserAddressById(Long addressId);

    /**
     * 根据用户id获取默认地址
     *
     * @param userId 用户id
     * @return
     */
    AStoreUserAddress getUserDefaultAddressByUserId(Long userId);

    /**
     * 新增地址
     *
     * @param aStoreUserAddress 添加地址实体
     * @return 添加结果
     */
    Boolean saveUserAddress(AStoreUserAddress aStoreUserAddress);

    /**
     * 修改地址
     *
     * @param aStoreUserAddress 修改地址实体
     * @return 修改结果
     */
    Boolean updateUserAddress(AStoreUserAddress aStoreUserAddress);

    /**
     * 删除地址
     *
     * @param addressId 地址id
     * @return
     */
    Boolean deleteUserAddressById(Long addressId);

    /**
     * 设置地址为默认地址
     *
     * @param addressId 地址id
     * @return
     */
    Boolean setDefaultUserAddress(Long addressId, Long userId);
}
