package com.xc.cms.model.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/22 14:56 <br>
 */

@Data
public class PageQueryRequest {

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
     * 别名
     */
    private String pageAliase;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 页面类型
     * 0：静态
     * 1：动态
     */
    private String pageType;
}
