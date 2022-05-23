package com.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.store.controller.store.vo.AStoreUserInfoVO;
import com.store.domain.AStoreUser;

/**
 * @author Simon
 * @description 针对表【a_store_user】的数据库操作Service
 * @createDate 2022-05-23 14:34:49
 */
public interface AStoreUserService extends IService<AStoreUser> {


    /**
     * 用户登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @return 登录结果
     */
    String UserSignIn(String userAccount, String userPassword);

    /**
     * 用户注册
     *
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 用户确认密码
     * @return 注册结果
     */
    String UserSignUp(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户退出
     *
     * @param userId 用户id
     * @return 退出结果
     */
    int UserSignOut(long userId);

    /**
     * 用户信息脱敏
     *
     * @param sourceUser 源用户信息
     * @return 脱敏结果
     */
    AStoreUserInfoVO getSafetyUser(AStoreUser sourceUser);
}
