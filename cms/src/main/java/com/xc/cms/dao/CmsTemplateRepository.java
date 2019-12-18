package com.xc.cms.dao;

import com.xc.model.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : 吴后荣
 * @date : 2019/10/22 00:19
 * @description :
 */
@Repository
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String> {
}
