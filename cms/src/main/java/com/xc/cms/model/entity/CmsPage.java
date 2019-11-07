package com.xc.cms.model.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/22 14:58 <br>
 */

@Data
@ToString
@Document("cms_page")
public class CmsPage {

    /**
     * _id : 5a754adf6abb500ad05688d9
     * _class : com.xuecheng.framework.domain.cms.CmsPage
     * siteId : 5a751fab6abb5044e0d19ea1
     * pageName : index.html
     * pageAliase : 首页
     * pageWebPath : /index.html
     * pagePhysicalPath : F:\develop\xc_portal_static\
     * pageType : 0
     * pageCreateTime : 2018-02-03T05:37:53.256+0000
     * templateId : 5a962b52b00ffc514038faf7
     * htmlFileId : 5a7c1c54d019f14d90a1fb23
     */

    /**
     * 页面id
     */
    @Id
    private String pageId;

    /**
     * 站点id
     */
    private String siteId;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 别名
     */
    private String pageAliase;

    /**
     * 访问地址
     */
    private String pageWebPath;

    /**
     * 物理路径
     */
    private String pagePhysicalPath;

    /**
     * 类型（动态/静态）
     */
    private String pageType;

    /**
     * 创建时间
     */
    private String pageCreateTime;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * html静态文件id
     */
    private String htmlFileId;

    /**
     * 数据接口url
     */
    private String dataUrl;


}
