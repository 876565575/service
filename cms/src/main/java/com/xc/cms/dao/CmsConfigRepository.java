package com.xc.cms.dao;

import com.xc.model.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * @author : 吴后荣
 * @date : 2019/10/17 14:29
 * @description :
 */
@Repository
public interface CmsConfigRepository extends MongoRepository<CmsConfig, String> {
}
