package com.store.controller.store;

import com.store.common.BaseResponseCommon;
import com.store.config.annotation.TokenToUser;
import com.store.constant.CommonConstant;
import com.store.constant.CommonServiceEnum;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.param.AStoreUserSignInParam;
import com.store.controller.store.param.AStoreUserSignUpParam;
import com.store.controller.store.param.AStoreUserUpdateParam;
import com.store.controller.store.vo.AStoreUserInfoVO;
import com.store.controller.store.vo.AStoreUserSignInVO;
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
        String signInResult = aStoreUserService.userSignIn(aStoreUserSignInParam.getUserAccount(), aStoreUserSignInParam.getUserPassword());

        log.info("SignIn api,userAccount={},signInResult={}", aStoreUserSignInParam.getUserAccount(), signInResult);

        AStoreUserSignInVO aStoreUserSignInVO = new AStoreUserSignInVO();
        aStoreUserSignInVO.setToken(signInResult);
        //登录成功
        if (!StringUtils.isEmpty(signInResult) && signInResult.length() == CommonConstant.TOKEN_LENGTH) {
            aStoreUserSignInVO.setMessage("登录成功");
            return ResponseResultGenerateUtil.success(aStoreUserSignInVO);
        }
        //登录失败
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @GetMapping("/signout")
    public BaseResponseCommon UserSignOut(@TokenToUser AStoreUser aStoreUser) {
        int signOutResult = aStoreUserService.userSignOut(aStoreUser.getUserId());

        log.info("SignOut api,userInfo={},signOutResult={}", aStoreUser, signOutResult);
        if (signOutResult == 1) {
            return ResponseResultGenerateUtil.success("退出登录成功");
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @PostMapping("/signup")
    public BaseResponseCommon<String> UserSignUp(@RequestBody AStoreUserSignUpParam aStoreUserSignUpParam) {
        String signUpResult = aStoreUserService.userSignUp(aStoreUserSignUpParam.getUserAccount(), aStoreUserSignUpParam.getUserPassword(), aStoreUserSignUpParam.getCheckPassword());
        if (CommonServiceEnum.SUCCESS.getResult().equals(signUpResult)) {
            return ResponseResultGenerateUtil.success("注册用户成功");
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @GetMapping("/status")
    public BaseResponseCommon getUserSignInStatus(@TokenToUser AStoreUser aStoreUser) {
        if (aStoreUser != null) return ResponseResultGenerateUtil.success(true);
        return ResponseResultGenerateUtil.success(false);
    }

    @GetMapping("/info")
    public BaseResponseCommon<AStoreUserInfoVO> getUserInfo(@TokenToUser AStoreUser aStoreUser) {
        AStoreUserInfoVO safetyUserInfo = aStoreUserService.getSafetyUser(aStoreUser);
        if (safetyUserInfo == null) throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
        return ResponseResultGenerateUtil.success(safetyUserInfo);
    }

    @PostMapping("/info")
    public BaseResponseCommon updateUserInfo(@RequestBody AStoreUserUpdateParam aStoreUserUpdateParam, @TokenToUser AStoreUser aStoreUser) {
        if (aStoreUserUpdateParam == null) throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        Boolean result = aStoreUserService.updateUserInfo(aStoreUserUpdateParam, aStoreUser.getUserId());
        return ResponseResultGenerateUtil.success("修改用户信息成功");
    }
}
