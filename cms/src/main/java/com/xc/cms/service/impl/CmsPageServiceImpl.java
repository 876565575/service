package com.xc.cms.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.xc.cms.config.AmqpConfig;
import com.xc.cms.dao.CmsPageRepository;
import com.xc.cms.dao.CmsTemplateRepository;
import com.xc.model.cms.CmsConfig;
import com.xc.model.cms.CmsPage;
import com.xc.model.cms.CmsTemplate;
import com.xc.model.cms.request.PageQueryRequest;
import com.xc.cms.service.CmsPageService;
import com.xc.cms.service.CmsTemplateService;
import com.xc.common.exception.ExceptionEnum;
import com.xc.common.exception.SysException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
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
@Log4j2
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

    @Autowired
    CmsTemplateService cmsTemplateService;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public Page<CmsPage> findList(Integer pageNum, Integer pageSize, @NotNull PageQueryRequest pageQueryRequest) {
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
        //删除html文件
        CmsPage cmsPage = this.get(pageId);
        if (!StringUtils.isEmpty(cmsPage.getHtmlFileId())) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(cmsPage.getHtmlFileId())));
        }
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
    public String generateHtml(String pageId) {
        // 获取页面信息
        CmsPage cmsPage = this.get(pageId);
        // 获得页面数据
        Map model = this.getModelByPageId(pageId);
        // 获取模板信息
        CmsTemplate cmsTemplate = cmsTemplateService.get(cmsPage.getTemplateId());
        String template = cmsTemplateService.getFile(cmsTemplate.getTemplateFileId());
        String html = this.staticize(template, model);
        //保存html至文件服务器
        ObjectId htmlFileId = gridFsTemplate.store(new ByteArrayInputStream(html.getBytes()), cmsPage.getPageName());
        //修改页面信息
        cmsPage.setHtmlFileId(htmlFileId.toString());
        cmsPageRepository.save(cmsPage);
        return html;

    }


    @Override
    public String generateHtml(String pageId, String template) {
        // 获取页面信息
        CmsPage cmsPage = this.get(pageId);
        // 获得页面数据
        Map model = this.getModelByPageId(pageId);
        String html = this.staticize(template, model);
        //保存html至文件服务器
        ObjectId htmlFileId = gridFsTemplate.store(new ByteArrayInputStream(html.getBytes()), cmsPage.getPageName());
        //修改页面信息
        cmsPage.setHtmlFileId(htmlFileId.toString());
        return html;
    }

    @Override
    public String getTemplateFile(String id) {
        // 获取页面信息
        CmsPage cmsPage = this.get(id);
        // 获取模板信息
        CmsTemplate cmsTemplate = cmsTemplateService.get(cmsPage.getTemplateId());
        return cmsTemplateService.getFile(cmsTemplate.getTemplateFileId());
    }

    /**
     * 根据pageId获取页面的配置信息
     * @param pageId
     * @return
     */
    private Map getModelByPageId(String pageId){
        CmsPage cmsPage = this.get(pageId);
        String dataUrl = cmsPage.getDataUrl();
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        return forEntity.getBody();
    }

    /**
     * 执行静态化
     * @param template
     * @param model
     * @return
     */
    private String staticize(String template, Map model){
        try {
            //创建配置类
            Configuration configuration = new Configuration(Configuration.getVersion());
            //模板加载器
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("html", template);
            //配置模板加载器
            configuration.setTemplateLoader(stringTemplateLoader);
            //获取模板
            Template template1 = configuration.getTemplate("html");
            //执行静态化
            return FreeMarkerTemplateUtils.processTemplateIntoString(template1, model);
        }catch (Exception e){
            log.error("静态化失败!", e);
            throw new SysException(ExceptionEnum.FAILED);
        }
    }

    @Override
    public void postPage(String pageId) {
        this.generateHtml(pageId);
        Map<String, String> map = new HashMap<>();
        map.put("pageId", pageId);
        rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE, "cms",map);
    }

    @Override
    public void save(CmsPage cmsPage) {
        Optional<CmsPage> optional = cmsPageRepository.findByPageNameAndPagePhysicalPathAndPageWebPath(cmsPage.getPageName(), cmsPage.getPagePhysicalPath(), cmsPage.getPageWebPath());
        if (!optional.isPresent()) {
            //不存在
            cmsPageRepository.save(cmsPage);
        }else {
            cmsPage.setPageId(optional.get().getPageId());
            cmsPageRepository.save(cmsPage);
        }
    }
}
