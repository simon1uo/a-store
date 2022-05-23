package com.store.util;

import com.store.common.BaseResponseCommon;
import com.store.constant.ErrorCodeEnum;

public class ResponseResultGenerateUtil {
    /**
     * 成功响应
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 返回响应对象
     */
    public static <T> BaseResponseCommon<T> success(T data) {
        return new BaseResponseCommon<>(0, data, "ok", "");
    }

    /**
     * 失败响应
     *
     * @param errorCodeEnum 错误码
     * @return 返回响应对象
     */
    public static BaseResponseCommon error(ErrorCodeEnum errorCodeEnum) {
        return new BaseResponseCommon<>(errorCodeEnum);
    }

    public static BaseResponseCommon error(int code, String message, String description) {
        return new BaseResponseCommon<>(code, null, message, description);
    }

    public static BaseResponseCommon error(ErrorCodeEnum errorCodeEnum, String description) {
        return new BaseResponseCommon<>(errorCodeEnum.getCode(), errorCodeEnum.getMessage(), description);
    }
}
