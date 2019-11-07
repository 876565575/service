package com.xc.cms.service;

import com.xc.cms.model.entity.CmsTemplate;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.model.vo.TemplateQueryRequest;
import org.springframework.data.domain.Page;

import java.io.InputStream;
import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/10/22 00:20
 * @description :
 */
public interface CmsTemplateService {
    /**
     * 获得所有的模板
     * @return
     */
    List<CmsTemplate> findAll();

    /**
     * 分页查询模板信息
     * @param page
     * @param size
     * @param templateQueryRequest
     * @return
     */
    Page<CmsTemplate> list(int page, int size, TemplateQueryRequest templateQueryRequest);

    /**
     * 保存模板文件
     * @param template
     * @return
     */
    String saveFile(InputStream template, String fileName);

    /**
     * 保存模板文件信息
     * @param cmsTemplate
     * @return
     */
    CmsTemplate save(CmsTemplate cmsTemplate);

    /**
     * 修改，删除旧文件
     * @param cmsTemplate
     * @param newTemplateFileId 新的模板文件id
     * @return
     */
    CmsTemplate update(CmsTemplate cmsTemplate, String newTemplateFileId);

    /**
     * 删除并删除文件
     * @param cmsTemplateId
     * @return
     */
    void delete(String cmsTemplateId);

    /**
     * 根据文件id删除文件
     * @param fileId
     */
    void deleteFile(String fileId);
}
