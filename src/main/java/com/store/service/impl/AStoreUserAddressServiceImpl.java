package com.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.domain.AStoreUserAddress;
import com.store.mapper.AStoreUserAddressMapper;
import com.store.service.AStoreUserAddressService;
import org.springframework.stereotype.Service;

/**
* @author Simon
* @description 针对表【a_store_user_address(用户收货地址表)】的数据库操作Service实现
* @createDate 2022-05-23 14:39:10
*/
@Service
public class AStoreUserAddressServiceImpl extends ServiceImpl<AStoreUserAddressMapper, AStoreUserAddress>
    implements AStoreUserAddressService{

}




