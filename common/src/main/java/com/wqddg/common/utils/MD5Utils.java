package com.wqddg.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: wqddg
 * @ClassName MD5Utils
 * @DateTime: 2021/11/13 23:08
 * @remarks : #md5加密
 */
public class MD5Utils {


    public static String md5(String password){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());

            return new BigInteger(1,md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
