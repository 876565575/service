package com.xc.cms.service.impl;

import cn.hutool.core.io.IoUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xc.cms.dao.CmsPageRepository;
import com.xc.cms.dao.CmsTemplateRepository;
import com.xc.cms.model.entity.CmsConfig;
import com.xc.cms.model.entity.CmsPage;
import com.xc.cms.model.entity.CmsTemplate;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.service.CmsPageService;
import com.xc.common.exception.ExceptionEnum;
import com.xc.common.exception.SysException;
import kotlin.text.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/22 23:49 <br>
 * @see com.xc.cms.service.impl <br>
 */
@Service
class CmsPageServiceImpl implements CmsPageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    @Override
    public Page<CmsPage> findList(Integer pageNum, Integer pageSize,@NotNull PageQueryRequest pageQueryRequest) {
        if (pageQueryRequest == null){
            pageQueryRequest = new PageQueryRequest();
        }

        // 定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        exampleMatcher = exampleMatcher.withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());
        // 设置条件值
        CmsPage cmsPage = new CmsPage();
        if (!StringUtils.isEmpty(pageQueryRequest.getPageAliase())){
            cmsPage.setPageAliase(pageQueryRequest.getPageAliase());
        }
        if (!StringUtils.isEmpty(pageQueryRequest.getPageId())) {
            cmsPage.setPageId(pageQueryRequest.getPageId());
        }
        if (!StringUtils.isEmpty(pageQueryRequest.getSiteId())) {
            cmsPage.setSiteId(pageQueryRequest.getSiteId());
        }
        if (!StringUtils.isEmpty(pageQueryRequest.getTemplateId())) {
            cmsPage.setTemplateId(pageQueryRequest.getTemplateId());
        }
        if (!StringUtils.isEmpty(pageQueryRequest.getPageName())) {
            cmsPage.setPageName(pageQueryRequest.getPageName());
        }
        if (!StringUtils.isEmpty(pageQueryRequest.getPageType())) {
            cmsPage.setPageType(pageQueryRequest.getPageType());
        }
        // 定义条件对象
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        if (pageNum <= 0){
            pageNum = 1;
        }
        if (pageSize <= 0){
            pageSize = 0;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return cmsPageRepository.findAll(example, pageable);
    }

    @Override
    public CmsPage get(String pageId) {
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public void remove(String pageId) {
        cmsPageRepository.deleteById(pageId);
    }

    @Override
    public void edit(CmsPage cmsPage) {
        Optional<CmsPage> optional = cmsPageRepository.findById(cmsPage.getPageId());
        if (optional.isPresent()) {
            CmsPage oldCmsPage = optional.get();
        }
        cmsPageRepository.save(cmsPage);
    }

    @Override
    public CmsPage add(CmsPage cmsPage) {
        return cmsPageRepository.insert(cmsPage);
    }

    @Override
    public String getPageHtml(String pageId) {
        // 获取页面信息
        CmsPage cmsPage = this.get(pageId);
        // 获得页面数据
        CmsConfig cmsConfig = this.getModelByPageId(pageId);
        // 获取模板信息
        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(cmsPage.getTemplateId());
        if (!optional.isPresent()) {
            throw new SysException(ExceptionEnum.NOT_FIND);
        }
        CmsTemplate cmsTemplate = optional.get();
        // 获取模板文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(cmsTemplate.getTemplateFileId())));
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, downloadStream);
        try {
            InputStream inputStream = gridFsResource.getInputStream();
            String html = IoUtil.read(inputStream, Charsets.UTF_8);
            inputStream.close();
            return html;
        } catch (IOException e) {
            throw new SysException(ExceptionEnum.NOT_FIND);
        }
    }

    /**
     * 根据pageId获取页面的配置信息
     * @param pageId
     * @return
     */
    private CmsConfig getModelByPageId(String pageId){
        CmsPage cmsPage = this.get(pageId);
        String dataUrl = cmsPage.getDataUrl();
        ResponseEntity<CmsConfig> forEntity = restTemplate.getForEntity(dataUrl, CmsConfig.class);
        return forEntity.getBody();
    }
}
