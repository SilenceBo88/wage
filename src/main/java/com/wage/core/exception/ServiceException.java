package com.wage.core.exception;

import com.wage.core.util.ResultCode;

/**
 * @Author: zb
 * @Date: Created in 2018/5/27 16:20
 * @Description: 自定义异常类
 */
public class ServiceException extends RuntimeException{
    private ResultCode code = ResultCode.FAIL;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ResultCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }
}
