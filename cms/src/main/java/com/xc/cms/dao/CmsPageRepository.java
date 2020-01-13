package com.xc.cms.dao;

import com.xc.model.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/22 15:13 <br>
 * @see com.xc.cms.dao <br>
 */

@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

    List<CmsPage> findByPageNameContains(String pageName);

    List<CmsPage> findByPageAliaseContains(String pageAliase);

    List<CmsPage> findByPageAliaseLike(String pageAliase);

    Optional<CmsPage> findByPageNameAndPagePhysicalPathAndPageWebPath(String pageName, String pagePhysicalPath, String pageWebPath);

    Optional<CmsPage> findByPageName(String pageName);
}

