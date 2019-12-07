package com.xc.common.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : 吴后荣
 * @date : 2019/10/22 00:17
 * @description : 页面模板
 */
@Document("cms_template")
@Data
public class CmsTemplate {

    /**
     * _id : 5a925be7b00ffc4b3c1578b5
     * _class : com.xuecheng.framework.domain.cms.CmsTemplate
     * siteId : 5a751fab6abb5044e0d19ea1
     * templateName : 课程详情页面
     * templateParameter : courseid
     * templateFileId : 5ad8a51f68db5240b42e5fea
     */
    @Id
    private String id;

    /**
     * 站点id
     */
    private String siteId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板参数
     */
    private String templateParameter;

    /**
     * 模板文件id
     */
    private String templateFileId;
}
