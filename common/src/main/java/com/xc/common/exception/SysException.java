package com.xc.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 吴后荣
 * @date : 2019/10/15 10:47
 * @description :
 */
@Getter
@AllArgsConstructor
public class SysException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
