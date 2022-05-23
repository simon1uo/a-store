package com.store.exception;

import com.store.common.BaseResponseCommon;
import com.store.constant.ErrorCodeEnum;
import com.store.util.ResponseResultGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponseCommon businessExceptionHandler(BusinessException e) {
        log.error("businessException" + e.getMessage(), e);
        return ResponseResultGenerateUtil.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponseCommon runtimeExceptionHandler(BusinessException e) {
        log.error("runtimeException", e);
        return ResponseResultGenerateUtil.error(ErrorCodeEnum.SYSTEM_ERROR, "SYSTEM_ERROR");
    }
}
