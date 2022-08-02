package com.wqddg.common.vo;

/**
 * @Author: wqddg
 * @ClassName ResStatus
 * @DateTime: 2021/11/14 21:36
 * @remarks : #
 */
public class ResStatus {
    public static final int OK=10000;
    public static final int NO=10001;
    public static final int LOGIN_SUCCESS=2000;//认证成功
    public static final int LOGIN_FAIL_NOT=2001;//用户未登录
    public static final int LOGIN_FAIL_TIMEOUT=2002;//用户超时
}
