package com.xc.cms.service.impl;

import com.xc.cms.dao.CmsSiteRepository;
import com.xc.cms.model.entity.CmsSite;
import com.xc.cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/24 0:26 <br>
 * @see com.xc.cms.service.impl <br>
 */
@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    private final CmsSiteRepository cmsSiteRepository;

    public CmsSiteServiceImpl(CmsSiteRepository cmsSiteRepository) {
        this.cmsSiteRepository = cmsSiteRepository;
    }

    @Override
    public List<CmsSite> findAll() {
        return cmsSiteRepository.findAll();
    }
}
