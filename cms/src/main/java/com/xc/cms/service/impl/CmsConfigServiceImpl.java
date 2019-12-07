package com.xc.cms.service.impl;

import com.xc.cms.dao.CmsConfigRepository;
import com.xc.common.model.entity.CmsConfig;
import com.xc.cms.service.CmsConfigService;
import com.xc.common.exception.ExceptionEnum;
import com.xc.common.exception.SysException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : 吴后荣
 * @date : 2019/10/17 14:28
 * @description :
 */
@Service
public class CmsConfigServiceImpl implements CmsConfigService {

    private final CmsConfigRepository cmsConfigRepository;

    public CmsConfigServiceImpl(CmsConfigRepository cmsConfigRepository) {
        this.cmsConfigRepository = cmsConfigRepository;
    }

    @Override
    public CmsConfig getConfigById(String id) {
        Optional<CmsConfig> optional = cmsConfigRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }else {
            throw new SysException(ExceptionEnum.NOT_FIND);
        }

    }
}
