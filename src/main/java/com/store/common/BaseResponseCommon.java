package com.store.common;

import com.store.constant.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用响应信息类
 *
 * @param <T>
 */
@Data
public class BaseResponseCommon<T> implements Serializable {
    private int code;

    private T data;

    private String message;

    private String description;

    public BaseResponseCommon(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponseCommon(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponseCommon(int code, T data) {
        this(code, data, "", "");
    }

    public BaseResponseCommon(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getCode(), null, errorCodeEnum.getMessage(), errorCodeEnum.getDescription());
    }
}
