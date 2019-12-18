package com.xc.model.cms;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 */
@NoArgsConstructor
@Data
@Document("cms_site")
public class CmsSite {

    @Id
    private String id;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 站点域名
     */
    private String siteDomain;

    /**
     * 站点端口
     */
    private String sitePort;

    /**
     * 网站路径
     */
    private String siteWebPath;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String siteCreateTime;
}
