package com.xc.cms.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/24 0:23 <br>
 * @see com.xc.cms.model.entity <br>
 */
@NoArgsConstructor
@Data
@Document("cms_site")
public class CmsSite {

    @Id
    private String id;
    private String siteName;
    private String siteDomain;
    private String sitePort;
    private String siteWebPath;
    private String siteCreateTime;
}
