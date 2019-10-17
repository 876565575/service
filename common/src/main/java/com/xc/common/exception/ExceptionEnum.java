package com.xc.common.exception;

import lombok.*;

/**
 * @author : 吴后荣
 * @date : 2019/10/15 10:46
 * @description :
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    /**
     *
     */
    FAILED(500, "错误"),
    NOT_FIND(404, "未找到");

    private int code;
    private String msg;

}
