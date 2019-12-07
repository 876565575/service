package com.xc.cms.advice;

import com.xc.common.exception.ExceptionResult;
import com.xc.common.exception.SysException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author : 吴后荣
 * @date : 2019/10/15 10:55
 * @description : 全局自定义异常处理器
 */
@Log4j2
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(SysException.class)
    public ResponseEntity<ExceptionResult> handlerException(SysException e){
        log.error(e.getExceptionEnum().getMsg(), e);
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }

}
