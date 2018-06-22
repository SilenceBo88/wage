package com.wage.core.util;

/**
 * @Author: zb
 * @Date: Created in 2018/5/23 19:54
 * @Description: 响应码枚举
 */
public enum ResultCode {

    // 成功
    SUCCESS(200),

    // 失败（语法错误）
    FAIL(400),

    // 未认证（签名错误）
    UNAUTHORIZED(401),

    /** 未登录 */
    UNAUTHEN(4401),

    /** 未授权，拒绝访问 */
    UNAUTHZ(4403),

    // 接口不存在（找不到）
    NOT_FOUND(404),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
