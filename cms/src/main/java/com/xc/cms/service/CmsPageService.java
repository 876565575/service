package com.xc.cms.service;

import com.xc.cms.model.entity.CmsPage;
import com.xc.cms.model.vo.PageQueryRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/22 23:48 <br>
 * @see com.xc.cms.service <br>
 */


public interface CmsPageService {

    /**
     * 自定义条件分页查询
     * @param pageNum 页码
     * @param pageSize 单页展示数量
     * @param pageQueryRequest 查询条件
     * @return
     */
    Page<CmsPage> findList(Integer pageNum, Integer pageSize, PageQueryRequest pageQueryRequest);

    /**
     * 根据id查找页面信息
     * @param pageId
     * @return
     */
    CmsPage get(String pageId);


    /**
     * 根据id删除页面信息
     * @param pageId
     */
    void remove(String pageId);

    /**
     * 修改页面信息
     * @param cmsPage
     */
    void edit(CmsPage cmsPage);

    /**
     * 新增页面信息
     * @param cmsPage
     * @return
     */
    CmsPage add(CmsPage cmsPage);

    /**
     * 生成静态化页面
     * @param pageId
     * @param template
     * @return
     */
    String generateHtml(String pageId, String template);

    /**
     * 生成静态化页面
     * @param pageId
     * @return
     */
    String generateHtml(String pageId);

    /**
     * 获取页面的模板文件
     * @param id
     * @return
     */
    String getTemplateFile(String id);
}
