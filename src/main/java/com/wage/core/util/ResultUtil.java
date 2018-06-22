package com.wage.core.util;

/**
 * @Author: zb
 * @Date: Created in 2018/5/23 20:17
 * @Description: 结果转换为封装后的对象
 */
public class ResultUtil {

    private final static String SUCCESS = "成功";
    private final static String FAIL = "语法格式有误";
    private final static String UNAUTHORIZED = "未授权，证书不匹配";
    private final static String NOT_FOUND = "请求内容不存在";
    private final static String INTERNAL_SERVER_ERROR = "服务器内部错误";

    public static <T> Result<T> SUCCESS() {
        return new Result<T>().setCode(ResultCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> Result<T> SUCCESS(String msg) {
        return new Result<T>().setCode(ResultCode.SUCCESS).setMsg(msg);
    }

    public static <T> Result<T> SUCCESS(T data) {
        return new Result<T>().setCode(ResultCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> Result<T> SUCCESS_DATA(T data) {
        return new Result<T>().setCode(ResultCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> Result<T> Error(int code, String msg) {
        return new Result<T>().setCode(code).setMsg(msg);
    }

    public static <T> Result<T> Error(ResultCode code, String msg) {
        return new Result<T>().setCode(code).setMsg(msg);
    }

    public static <T> Result<T> Error(int code, String msg, T data) {
        return new Result<T>().setCode(code).setMsg(msg).setData(data);
    }

    public static <T> Result<T> Error(ResultCode code, String msg, T data) {
        return new Result<T>().setCode(code).setMsg(msg).setData(data);
    }

    public static <T> Result<T> Error400(String msg) {
        return new Result<T>().setCode(ResultCode.FAIL).setMsg(msg);
    }

    public static <T> Result<T> Error400() {
        return new Result<T>().setCode(ResultCode.FAIL).setMsg(FAIL);
    }

    public static <T> Result<T> Error401() {
        return new Result<T>().setCode(ResultCode.FAIL).setMsg(UNAUTHORIZED);
    }

    public static <T> Result<T> Error404() {
        return new Result<T>().setCode(ResultCode.FAIL).setMsg(NOT_FOUND);
    }

    public static <T> Result<T> Error500() {
        return new Result<T>().setCode(ResultCode.INTERNAL_SERVER_ERROR).setMsg(INTERNAL_SERVER_ERROR);
    }
}
