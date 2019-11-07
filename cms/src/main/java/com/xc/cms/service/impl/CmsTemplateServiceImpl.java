package com.xc.cms.service.impl;

import com.xc.cms.dao.CmsTemplateRepository;
import com.xc.cms.model.entity.CmsTemplate;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.model.vo.TemplateQueryRequest;
import com.xc.cms.service.CmsTemplateService;
import com.xc.common.exception.ExceptionEnum;
import com.xc.common.exception.SysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author : 吴后荣
 * @date : 2019/10/22 00:21
 * @description :
 */
@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Override
    public List<CmsTemplate> findAll() {
        return cmsTemplateRepository.findAll();
    }

    @Override
    public Page<CmsTemplate> list(int page, int size, TemplateQueryRequest templateQueryRequest) {
        if (templateQueryRequest == null){
            templateQueryRequest = new TemplateQueryRequest();
        }
        // 定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());
        // 设置条件
        CmsTemplate cmsTemplate = new CmsTemplate();
        if (!StringUtils.isEmpty(templateQueryRequest.getSiteId())){
            cmsTemplate.setSiteId(templateQueryRequest.getSiteId());
        }
        if (!StringUtils.isEmpty(templateQueryRequest.getTemplateName())){
            cmsTemplate.setTemplateName(templateQueryRequest.getTemplateName());
        }
        // 定义条件对象
        Example<CmsTemplate> example = Example.of(cmsTemplate, exampleMatcher);
        if (page <= 0){
            page = 1;
        }
        if (size <= 0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return cmsTemplateRepository.findAll(example, pageable);
    }

    @Override
    public CmsTemplate save(CmsTemplate cmsTemplate) {
        return cmsTemplateRepository.save(cmsTemplate);
    }

    @Override
    public String saveFile(InputStream template, String fileName) {
        return gridFsTemplate.store(template, fileName).toString();
    }

    @Override
    public CmsTemplate update(CmsTemplate cmsTemplate, String newTemplateFileId) {
        String templateFileId = cmsTemplate.getTemplateFileId();
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(templateFileId)));
        cmsTemplate.setTemplateFileId(newTemplateFileId);
        return cmsTemplateRepository.save(cmsTemplate);
    }

    @Override
    public void delete(String cmsTemplateId) {
        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(cmsTemplateId);
        if (!optional.isPresent()){
            throw new SysException(ExceptionEnum.NOT_FIND);
        }
        CmsTemplate cmsTemplate = optional.get();
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(cmsTemplate.getTemplateFileId())));
        cmsTemplateRepository.deleteById(cmsTemplate.getId());
    }

    @Override
    public void deleteFile(String fileId) {
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId)));
    }
}
