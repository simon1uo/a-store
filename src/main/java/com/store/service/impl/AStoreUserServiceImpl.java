package com.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.domain.AStoreUser;
import com.store.service.AStoreUserService;
import com.store.mapper.AStoreUserMapper;
import org.springframework.stereotype.Service;

/**
* @author Simon
* @description 针对表【a_store_user】的数据库操作Service实现
* @createDate 2022-05-23 14:34:49
*/
@Service
public class AStoreUserServiceImpl extends ServiceImpl<AStoreUserMapper, AStoreUser>
    implements AStoreUserService{

}




