package com.xc.cms.service.impl;

import com.xc.cms.dao.CmsTemplateRepository;
import com.xc.cms.model.entity.CmsTemplate;
import com.xc.cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/10/22 00:21
 * @description :
 */
@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    @Override
    public List<CmsTemplate> findAll() {
        return cmsTemplateRepository.findAll();
    }
}
