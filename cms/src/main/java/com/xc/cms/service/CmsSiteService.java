package com.xc.cms.service;

import com.xc.model.cms.CmsPage;
import com.xc.model.cms.CmsSite;
import com.xc.model.cms.response.QueryResult;

import java.util.List;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/24 0:25 <br>
 * @see com.xc.cms.service <br>
 */
public interface CmsSiteService {
    /**
     * 获取所有的站点信息
     * @return
     */
    List<CmsSite> findAll();

    /**
     * 分页查询站点信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    QueryResult list(Integer pageNum, Integer pageSize);

    /**
     *
     * @param siteId
     * @return
     */
    CmsSite get(String siteId);
}
