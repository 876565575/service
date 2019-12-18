package com.xc.model.cms.request;

import com.xc.model.cms.CmsTemplate;
import lombok.Data;

/**
 * @author : 吴后荣
 * @date : 2019/11/8 09:18
 * @description :
 */
@Data
public class CmsTemplateRequest {
    private CmsTemplate cmsTemplate;
    private String newTemplateFileId;
}
