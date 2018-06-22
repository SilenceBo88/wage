package com.wage.core.util;

/**
 * @Author: zb
 * @Date: Created in 2018/5/23 19:58
 * @Description: 结果集实体
 */
public class Result<T>{

    //状态码
    private int code;

    //提示信息
    private String msg;

    //返回的数据
    private T data;


    public Result<T> setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
