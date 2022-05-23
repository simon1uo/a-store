package com.store.config.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.store.config.annotation.TokenToUser;
import com.store.constant.AStoreUserServiceResultEnum;
import com.store.constant.CommonConstant;
import com.store.constant.ErrorCodeEnum;
import com.store.domain.AStoreUser;
import com.store.domain.AStoreUserToken;
import com.store.exception.BusinessException;
import com.store.mapper.AStoreUserMapper;
import com.store.mapper.AStoreUserTokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    private AStoreUserMapper aStoreUserMapper;
    @Resource
    private AStoreUserTokenMapper aStoreUserTokenMapper;

    public TokenToUserMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToUser.class) != null) {
            AStoreUser aStoreUser;

            String token = webRequest.getHeader("token");
            if (token != null && !"".equals(token) && token.length() == CommonConstant.TOKEN_LENGTH) {
                QueryWrapper<AStoreUserToken> tokenQueryWrapper = new QueryWrapper<>();
                tokenQueryWrapper.eq("token", token);
                AStoreUserToken aStoreUserToken = aStoreUserTokenMapper.selectOne(tokenQueryWrapper);

                if (aStoreUserToken == null || aStoreUserToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    throw new BusinessException(ErrorCodeEnum.NOT_LOGIN, AStoreUserServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }

                aStoreUser = aStoreUserMapper.selectById(aStoreUserToken.getUserId());
                if (aStoreUser == null)
                    throw new BusinessException(ErrorCodeEnum.NOT_LOGIN, AStoreUserServiceResultEnum.USER_NULL_ERROR.getResult());
                if (aStoreUser.getLockedFlag() == 1)
                    throw new BusinessException(ErrorCodeEnum.NO_AUTH, AStoreUserServiceResultEnum.SIGNIN_USER_LOCKED_ERROR.getResult());

                return aStoreUser;
            } else {
                throw new BusinessException(ErrorCodeEnum.NOT_LOGIN);
            }
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
}
