package com.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.constant.CommonServiceEnum;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.vo.AStoreUserAddressVO;
import com.store.domain.AStoreUserAddress;
import com.store.exception.BusinessException;
import com.store.mapper.AStoreUserAddressMapper;
import com.store.service.AStoreUserAddressService;
import com.store.util.BeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Simon
 * @description 针对表【a_store_user_address(用户收货地址表)】的数据库操作Service实现
 * @createDate 2022-05-23 14:39:10
 */
@Service
public class AStoreUserAddressServiceImpl extends ServiceImpl<AStoreUserAddressMapper, AStoreUserAddress>
        implements AStoreUserAddressService {

    @Resource
    private AStoreUserAddressMapper aStoreUserAddressMapper;

    @Override
    public List<AStoreUserAddressVO> getUserAddressList(Long userId) {
        QueryWrapper<AStoreUserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<AStoreUserAddress> UserAddresses = aStoreUserAddressMapper.selectList(queryWrapper);
        return BeanUtil.copyList(UserAddresses, AStoreUserAddressVO.class);
    }

    @Override
    public AStoreUserAddress getUserAddressById(Long addressId) {
        QueryWrapper<AStoreUserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("address_id", addressId);
        return aStoreUserAddressMapper.selectOne(queryWrapper);
    }

    @Override
    public AStoreUserAddress getUserDefaultAddressByUserId(Long userId) {
        return getDefaultAddress(userId);
    }

    @Override
    @Transactional
    public Boolean saveUserAddress(AStoreUserAddress aStoreUserAddress) {
        Date now = new Date();
        if (aStoreUserAddress.getDefaultFlag() == 1) {
            AStoreUserAddress defaultAddress = getDefaultAddress(aStoreUserAddress.getUserId());
            if (defaultAddress != null) {
                defaultAddress.setDefaultFlag(0);
                defaultAddress.setUpdateTime(now);
                int updateResult = aStoreUserAddressMapper.updateById(defaultAddress);
                if (updateResult < 1)
                    throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR, CommonServiceEnum.DATABASE_ERROR.getResult());

            }
        }
        return aStoreUserAddressMapper.insert(aStoreUserAddress) > 0;
    }

    @Override
    public Boolean updateUserAddress(AStoreUserAddress aStoreUserAddress) {
        AStoreUserAddress tempUserAddress = getUserAddressById(aStoreUserAddress.getAddressId());

        Date now = new Date();
        if (tempUserAddress.getDefaultFlag() == 1) {
            AStoreUserAddress defaultAddress = getDefaultAddress(aStoreUserAddress.getUserId());
            if (defaultAddress != null && !defaultAddress.getAddressId().equals(tempUserAddress.getAddressId())) {
                defaultAddress.setDefaultFlag(0);
                defaultAddress.setUpdateTime(now);
                int updateResult = aStoreUserAddressMapper.updateById(defaultAddress);
                if (updateResult < -1)
                    throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR, CommonServiceEnum.DATABASE_ERROR.getResult());
            }
        }

        aStoreUserAddress.setUpdateTime(now);
        return aStoreUserAddressMapper.updateById(aStoreUserAddress) > 0;
    }

    @Override
    public Boolean deleteUserAddressById(Long addressId) {
        AStoreUserAddress aStoreUserAddress = new AStoreUserAddress();
        aStoreUserAddress.setAddressId(addressId);
        aStoreUserAddress.setIsDeleted(1);
        return aStoreUserAddressMapper.updateById(aStoreUserAddress) > 0;
    }

    /**
     * 根据用户id获取默认地址
     *
     * @param userId 用户id
     * @return 地址结果
     */
    public AStoreUserAddress getDefaultAddress(Long userId) {
        QueryWrapper<AStoreUserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("default_flag", 1);
        AStoreUserAddress aStoreUserAddress = aStoreUserAddressMapper.selectOne(queryWrapper);
        if (aStoreUserAddress == null)
            throw new BusinessException(ErrorCodeEnum.NULL_ERROR, CommonServiceEnum.DATABASE_ERROR.getResult());
        return aStoreUserAddress;
    }
}




