package com.xc.cms.model.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author : 吴后荣
 * @date : 2019/10/17 14:22
 * @description :
 */
@Data
public class CmsConfigModel {
    /**
     * 主键
     */
    private String key;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目url
     */
    private String url;

    /**
     * 简单值
     */
    private String value;

    /**
     * 复杂值
     */
    private Map mapValue;

}
