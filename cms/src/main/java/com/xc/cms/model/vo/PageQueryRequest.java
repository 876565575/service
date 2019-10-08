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
 * @see com.xc.common.model.cms <br>
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

}
