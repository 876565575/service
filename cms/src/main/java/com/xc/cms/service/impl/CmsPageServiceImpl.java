package com.xc.cms.service.impl;

import com.xc.cms.dao.CmsPageRepository;
import com.xc.cms.model.entity.CmsPage;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Page<CmsPage> findList(Integer pageNum, Integer pageSize, PageQueryRequest pageQueryRequest) {
        if (pageQueryRequest == null){
            pageQueryRequest = new PageQueryRequest();
        }

        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
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
}
