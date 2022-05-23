package com.store.common;

/**
 * 响应工具类
 */
public class ResponseResultUtil {
    /**
     * 成功响应
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 返回响应对象
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok", "");
    }

    /**
     * 失败响应
     *
     * @param errorCode 错误码
     * @return 返回响应对象
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse<>(code, null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), description);
    }
}
