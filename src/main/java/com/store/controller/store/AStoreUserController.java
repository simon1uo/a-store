package com.store.controller.store;

import com.store.common.BaseResponseCommon;
import com.store.config.annotation.TokenToUser;
import com.store.constant.CommonConstant;
import com.store.constant.CommonServiceEnum;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.param.AStoreUserSignInParam;
import com.store.controller.store.param.AStoreUserSignUpParam;
import com.store.controller.store.vo.AStoreUserInfoVO;
import com.store.domain.AStoreUser;
import com.store.exception.BusinessException;
import com.store.service.AStoreUserService;
import com.store.util.ResponseResultGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class AStoreUserController {

    @Resource
    private AStoreUserService aStoreUserService;

    @PostMapping("/signin")
    public BaseResponseCommon UserSignIn(@RequestBody AStoreUserSignInParam aStoreUserSignInParam) {
        String signInResult = aStoreUserService.UserSignIn(aStoreUserSignInParam.getUserAccount(), aStoreUserSignInParam.getUserPassword());

        log.info("SignIn api,userAccount={},signInResult={}", aStoreUserSignInParam.getUserAccount(), signInResult);

        //登录成功
        if (!StringUtils.isEmpty(signInResult) && signInResult.length() == CommonConstant.TOKEN_LENGTH) {
            return ResponseResultGenerateUtil.success(signInResult);
        }
        //登录失败
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @GetMapping("/signout")
    public BaseResponseCommon UserSignOut(@TokenToUser AStoreUser aStoreUser) {
        int signOutResult = aStoreUserService.UserSignOut(aStoreUser.getUserId());

        log.info("SignOut api,userInfo={},signOutResult={}", aStoreUser, signOutResult);
        if (signOutResult == 1) {
            return ResponseResultGenerateUtil.success(signOutResult);
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @PostMapping("/signup")
    public BaseResponseCommon<String> UserSignUp(@RequestBody AStoreUserSignUpParam aStoreUserSignUpParam) {
        String signUpResult = aStoreUserService.UserSignUp(aStoreUserSignUpParam.getUserAccount(), aStoreUserSignUpParam.getUserPassword(), aStoreUserSignUpParam.getCheckPassword());
        if (CommonServiceEnum.SUCCESS.getResult().equals(signUpResult)) {
            return ResponseResultGenerateUtil.success(signUpResult);
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @GetMapping("/info")
    public BaseResponseCommon<AStoreUserInfoVO> getUserInfo(@TokenToUser AStoreUser aStoreUser) {
        AStoreUserInfoVO safetyUserInfo = aStoreUserService.getSafetyUser(aStoreUser);
        if (safetyUserInfo == null) throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
        return ResponseResultGenerateUtil.success(safetyUserInfo);
    }
}
