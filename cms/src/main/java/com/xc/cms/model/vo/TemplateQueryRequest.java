package com.xc.cms.model.vo;

import lombok.Data;

/**
 * @author : 吴后荣
 * @date : 2019/11/1 22:53
 * @description :
 */
@Data
public class TemplateQueryRequest {

    /**
     * 站点id
     */
    private String siteId;

    /**
     * 模板名称
     */
    private String templateName;
}
