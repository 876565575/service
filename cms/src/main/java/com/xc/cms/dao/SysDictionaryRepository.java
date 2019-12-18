package com.xc.cms.dao;

import com.xc.model.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/12/18 00:38
 * @description :
 */
@Repository
public interface SysDictionaryRepository extends MongoRepository<SysDictionary, String> {

    SysDictionary findByDTypeEquals(String dType);
}
