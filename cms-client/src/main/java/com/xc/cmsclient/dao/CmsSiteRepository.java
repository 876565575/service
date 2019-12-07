package com.xc.cmsclient.dao;

import com.xc.common.model.entity.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/24 0:34 <br>
 * @see com.xc.cms.dao <br>
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {
}
