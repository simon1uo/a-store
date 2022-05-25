package com.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.constant.CommonServiceEnum;
import com.store.constant.ErrorCodeEnum;
import com.store.constant.UserResultEnum;
import com.store.controller.store.vo.AStoreUserInfoVO;
import com.store.domain.AStoreUser;
import com.store.domain.AStoreUserToken;
import com.store.exception.BusinessException;
import com.store.mapper.AStoreUserMapper;
import com.store.mapper.AStoreUserTokenMapper;
import com.store.service.AStoreUserService;
import com.store.util.MD5Util;
import com.store.util.NumberUtil;
import com.store.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Simon
 * @description 针对表【a_store_user】的数据库操作Service实现
 * @createDate 2022-05-23 14:34:49
 */
@Service()
@Slf4j
public class AStoreUserServiceImpl extends ServiceImpl<AStoreUserMapper, AStoreUser>
        implements AStoreUserService {

    @Resource
    private AStoreUserMapper aStoreUserMapper;

    @Resource
    private AStoreUserTokenMapper aStoreUserTokenMapper;

    @Override
    public String UserSignIn(String userAccount, String userPassword) {
        // 输入不能为空
        if (StringUtils.isAnyBlank(userAccount, userPassword))
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_ACCOUNT_AND_PASSWORD_NULL.getResult());
        // 规范长度
        if (userAccount.length() < 4)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_PASSWORD_LENGTH_ERROR.getResult());
        if (userPassword.length() < 8)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_PASSWORD_LENGTH_ERROR.getResult());
        // 检验账户是否存在
        if (!isUserExist(userAccount))
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.SAME_USER_ACCOUNT_EXIST.getResult());

        // 验证账户与密码
        String encryptPassword = MD5Util.MD5Encode(userPassword, "UTF-8");
        AStoreUser loginUser = this.isUserCorrect(userAccount, encryptPassword);
        if (loginUser == null)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_ACCOUNT_AND_PASSWORD_ERROR.getResult());

        // 账户是否被锁定
        if (loginUser.getLockedFlag() == 1)
            throw new BusinessException(ErrorCodeEnum.NO_AUTH, UserResultEnum.SIGNIN_USER_LOCKED_ERROR.getResult());


        // 生成新的token
        String token = getNewToken(System.currentTimeMillis() + "", loginUser.getUserId());

        // 赋予新的token
        AStoreUserToken userToken = aStoreUserTokenMapper.selectById(loginUser.getUserId());

        Date now = new Date();
        Date expireTime = new Date(now.getTime() + 24 * 3600 * 1000);
        if (userToken == null) {
            userToken = new AStoreUserToken();
            userToken.setUserId(loginUser.getUserId());
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            if (aStoreUserTokenMapper.insert(userToken) > 0) {
                return token;
            }
        } else {
            // 更新token
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            if (aStoreUserTokenMapper.updateById(userToken) > 0) {
                return token;
            }
        }

        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public String UserSignUp(String userAccount, String userPassword, String checkPassword) {
        // 不能为空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword))
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_ACCOUNT_AND_PASSWORD_NULL.getResult());

        // 规范长度
        if (userAccount.length() < 4)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_ACCOUNT_LENGTH_ERROR.getResult());
        if (userPassword.length() < 8 || checkPassword.length() < 8)
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_PASSWORD_LENGTH_ERROR.getResult());

        // 检验账户名不含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find())
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_ACCOUNT_INPUT_ERROR.getResult());

        // 两个密码是否相同
        if (!userPassword.equals(checkPassword))
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.USER_PASSWORD_SAME_ERROR.getResult());

        // 检验账户是否存在
        if (isUserExist(userAccount))
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, UserResultEnum.SAME_USER_ACCOUNT_EXIST.getResult());

        // 加密
        //        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        String encryptPassword = MD5Util.MD5Encode(userPassword, "UTF-8");

        // 插入数据到数据库
        AStoreUser signupUser = new AStoreUser();
        signupUser.setUserAccount(userAccount);
        signupUser.setUserPassword(encryptPassword);
        boolean result = this.save(signupUser);
        if (!result)
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR, CommonServiceEnum.DATABASE_ERROR.getResult());
        else
            return CommonServiceEnum.SUCCESS.getResult();
    }

    @Override
    public int UserSignOut(long userId) {
        return aStoreUserTokenMapper.deleteById(userId);
    }

    public AStoreUserInfoVO getSafetyUser(AStoreUser sourceUser) {
        log.info(sourceUser.toString());
        AStoreUserInfoVO safetyUser = new AStoreUserInfoVO();

        safetyUser.setUserAccount(sourceUser.getUserAccount());
        safetyUser.setUserName(sourceUser.getUserName());

        return safetyUser;
    }

    /**
     * 验证用户是否存在
     *
     * @param userAccount
     * @return
     */
    public Boolean isUserExist(String userAccount) {
        QueryWrapper<AStoreUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        Long count = aStoreUserMapper.selectCount(queryWrapper);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 验证账户密码
     *
     * @param userAccount
     * @param encryptPassword
     * @return
     */
    public AStoreUser isUserCorrect(String userAccount, String encryptPassword) {
        QueryWrapper<AStoreUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encryptPassword);
        AStoreUser user = aStoreUserMapper.selectOne(queryWrapper);
        if (user == null) {
            return null;
        }
        return user;
    }


    /**
     * 获取生成的token值
     *
     * @param timeStr 时间戳
     * @param userId  用户id
     * @return 结果token值
     */
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return TokenUtil.generateToken(src);
    }
}




