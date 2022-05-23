package com.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.store.domain.AStoreUserAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Simon
 * @description 针对表【a_store_user_address(用户收货地址表)】的数据库操作Mapper
 * @createDate 2022-05-23 14:39:10
 * @Entity AStoreUserAddress
 */
@Mapper
public interface AStoreUserAddressMapper extends BaseMapper<AStoreUserAddress> {

}




