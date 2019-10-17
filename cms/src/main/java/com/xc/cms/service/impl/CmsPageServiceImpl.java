package com.xc.cms.service.impl;

import com.xc.cms.dao.CmsPageRepository;
import com.xc.cms.model.entity.CmsPage;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
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

    @Override
    public Page<CmsPage> findList(Integer pageNum, Integer pageSize,@NotNull PageQueryRequest pageQueryRequest) {
        if (pageQueryRequest == null){
            pageQueryRequest = new PageQueryRequest();
        }

        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        exampleMatcher = exampleMatcher.withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());
        //设置条件值
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
        //定义条件对象
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
    public CmsPage query(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public void remove(String id) {
        cmsPageRepository.deleteById(id);
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
}
