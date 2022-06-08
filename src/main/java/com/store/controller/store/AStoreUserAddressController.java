package com.store.controller.store;

import com.store.common.BaseResponseCommon;
import com.store.config.annotation.TokenToUser;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.param.AStoreUserAddressAddParam;
import com.store.controller.store.param.AStoreUserAddressUpdateParam;
import com.store.controller.store.vo.AStoreUserAddressVO;
import com.store.domain.AStoreUser;
import com.store.domain.AStoreUserAddress;
import com.store.exception.BusinessException;
import com.store.service.AStoreUserAddressService;
import com.store.util.BeanUtil;
import com.store.util.ResponseResultGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/user")
@RestController
@Slf4j
public class AStoreUserAddressController {
    @Resource
    private AStoreUserAddressService aStoreUserAddressService;

    @GetMapping("/address")
    public BaseResponseCommon<List<AStoreUserAddressVO>> getUserAddressList(@TokenToUser AStoreUser loginAStoreUser) {
        return ResponseResultGenerateUtil.success(aStoreUserAddressService.getUserAddressList(loginAStoreUser.getUserId()));
    }

    @GetMapping("/address/{addressId}")
    public BaseResponseCommon<AStoreUserAddressVO> getUserAddress(@PathVariable("addressId") Long addressId, @TokenToUser AStoreUser loginAStoreUser) {
        AStoreUserAddress userAddressById = aStoreUserAddressService.getUserAddressById(addressId);
        AStoreUserAddressVO aStoreUserAddressVO = new AStoreUserAddressVO();
        BeanUtil.copyProperties(userAddressById, aStoreUserAddressVO);
        if (!loginAStoreUser.getUserId().equals(userAddressById.getUserId()))
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        return ResponseResultGenerateUtil.success(aStoreUserAddressVO);
    }

    @GetMapping("/address/default")
    public BaseResponseCommon getUserDefaultAddress(@TokenToUser AStoreUser loginAStoreUser) {
        return ResponseResultGenerateUtil.success(aStoreUserAddressService.getUserDefaultAddressByUserId(loginAStoreUser.getUserId()));
    }

    @PostMapping("/address")
    public BaseResponseCommon addUserAddress(@RequestBody AStoreUserAddressAddParam aStoreUserAddressAddParam, @TokenToUser AStoreUser loginAStoreUser) {
        AStoreUserAddress aStoreUserAddress = new AStoreUserAddress();
        BeanUtil.copyProperties(aStoreUserAddressAddParam, aStoreUserAddress);
        aStoreUserAddress.setUserId(loginAStoreUser.getUserId());
        aStoreUserAddress.setDefaultFlag(0);
        Boolean saveResult = aStoreUserAddressService.saveUserAddress(aStoreUserAddress);
        if (saveResult) return ResponseResultGenerateUtil.success("添加用户地址信息成功");
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @PutMapping("/address")
    public BaseResponseCommon updateAddress(@RequestBody AStoreUserAddressUpdateParam aStoreUserAddressUpdateParam, @TokenToUser AStoreUser loginAStoreUser) {
        AStoreUserAddress aStoreUserAddress = aStoreUserAddressService.getUserAddressById(aStoreUserAddressUpdateParam.getAddressId());
        if (!loginAStoreUser.getUserId().equals(aStoreUserAddress.getUserId())) {
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        }

        AStoreUserAddress userAddress = new AStoreUserAddress();
        BeanUtil.copyProperties(aStoreUserAddressUpdateParam, userAddress);
        userAddress.setUserId(loginAStoreUser.getUserId());
        Boolean updateResult = aStoreUserAddressService.updateUserAddress(userAddress);
        //修改成功
        if (updateResult) {
            return ResponseResultGenerateUtil.success("修改地址信息成功");
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @DeleteMapping("/address/{addressId}")
    public BaseResponseCommon deleteAddress(@PathVariable Long addressId, @TokenToUser AStoreUser loginAStoreUser) {
        AStoreUserAddress aStoreUserAddress = aStoreUserAddressService.getUserAddressById(addressId);
        if (!loginAStoreUser.getUserId().equals(aStoreUserAddress.getUserId())) {
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        }
        Boolean deleteResult = aStoreUserAddressService.deleteUserAddressById(addressId);
        //删除成功
        if (deleteResult) {
            return ResponseResultGenerateUtil.success("删除地址信息成功");
        }
        //删除失败
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @GetMapping("/address/default/{addressId}")
    public BaseResponseCommon setDefaultAddress(@PathVariable Long addressId, @TokenToUser AStoreUser loginAStoreUser) {
        AStoreUserAddress aStoreUserAddress = aStoreUserAddressService.getUserAddressById(addressId);
        if (!loginAStoreUser.getUserId().equals(aStoreUserAddress.getUserId())) {
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        }
        Boolean setResult = aStoreUserAddressService.setDefaultUserAddress(addressId, aStoreUserAddress.getUserId());
        if (setResult) {
            return ResponseResultGenerateUtil.success("设置默认地址成功");
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }
}