package com.xc.common.exception;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : 吴后荣
 * @date : 2019/10/15 10:59
 * @description :
 */
@Data
public class ExceptionResult {
    /**
     * 状态码
     */
    private int status;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 时间戳
     */
    private LocalDateTime timeStamp;

    public ExceptionResult(ExceptionEnum exceptionEnum){
        this.status = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
        this.timeStamp = LocalDateTime.now();
    }
}
