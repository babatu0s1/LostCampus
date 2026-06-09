package com.example.finalexam.exception;

/**
 * 自定义业务异常。
 * 用于表达“数据不合法”“记录不存在”“违规发布”等业务问题。
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
