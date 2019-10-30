package com.xc.cms.service;

import com.xc.cms.model.entity.CmsTemplate;
import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/10/22 00:20
 * @description :
 */
public interface CmsTemplateService {
    List<CmsTemplate> findAll();
}
