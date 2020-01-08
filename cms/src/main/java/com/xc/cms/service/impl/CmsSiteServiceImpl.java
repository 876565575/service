package com.xc.cms.service.impl;

import com.xc.cms.dao.CmsSiteRepository;
import com.xc.model.cms.CmsSite;
import com.xc.model.cms.response.QueryResult;
import com.xc.cms.service.CmsSiteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public QueryResult list(Integer pageNum, Integer pageSize) {
        Page<CmsSite> page = cmsSiteRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(page.getTotalElements());
        queryResult.setList(page.getContent());
        return queryResult;
    }

    @Override
    public CmsSite get(String siteId) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        return optional.orElse(null);
    }
}
