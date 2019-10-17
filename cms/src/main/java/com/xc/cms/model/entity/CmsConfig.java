package com.xc.cms.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/10/17 14:15
 * @description : 配置
 */
@Document("cms_config")
@Data
public class CmsConfig {

    /**
     * _id : 5a791725dd573c3574ee333f
     * _class : com.xuecheng.framework.domain.cms.CmsConfig
     * name : 轮播图
     * model : [{"key":"banner1","name":"轮播图1地址","value":"http://192.168.101.64/group1/M00/00/01/wKhlQFp5wnCAG-kAAATMXxpSaMg864.png"},{"key":"banner2","name":"轮播图2地址","value":"http://192.168.101.64/group1/M00/00/01/wKhlQVp5wqyALcrGAAGUeHA3nvU867.jpg"},{"key":"banner3","name":"轮播图3地址","value":"http://192.168.101.64/group1/M00/00/01/wKhlQFp5wtWAWNY2AAIkOHlpWcs395.jpg"}]
     */

    @Id
    private String id;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置
     */
    private List<CmsConfigModel> model;

}
