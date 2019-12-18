package com.xc.cms.service;


import com.xc.model.cms.CmsConfig;

/**
 * @author : 吴后荣
 * @date : 2019/10/17 14:27
 * @description :
 */
public interface CmsConfigService {
    /**
     * 根据id查询配置信息
     * @param id
     * @return
     */
    CmsConfig getConfigById(String id);
}
