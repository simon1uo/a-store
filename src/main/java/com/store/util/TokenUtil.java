package com.store.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class TokenUtil {
    
    /**
     * 生成 token 值
     * 根据时间和用户id以及随机数生成token
     *
     * @param src 来源处理值
     * @return 结果token值
     */
    public static String generateToken(String src) {
        if (null == src || "".equals(src)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            String result = new BigInteger(1, md.digest()).toString(16);
            if (result.length() == 31) {
                result = result + "-";
            }
            System.out.println(result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
