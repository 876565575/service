package com.xc.search;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.xc.model.course.CoursePub;
import com.xc.search.repository.CourseRepository;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchApplicationTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void contextLoads() {
        //elasticsearchTemplate.createIndex("");
//        elasticsearchTemplate.deleteIndex("");
        List<AliasMetaData> list = elasticsearchTemplate.queryForAlias("xc_course");
        System.out.println("list = " + list);
    }

    @Test
    public void deleteIndex() {
        boolean flag = elasticsearchTemplate.deleteIndex("xc_course");
        System.out.println("flag = " + flag);
    }

    @Test
    public void creatIndex() {
//        Settings settings = Settings.builder().put("number_of_shards", "1").put("number_of_replicas", "0").build();
//        boolean flag = elasticsearchTemplate.createIndex("xc_course", settings.toString());

        boolean flag = elasticsearchTemplate.createIndex(CoursePub.class);
        System.out.println("flag = " + flag);
    }

    @Test
    public void creatMapping() {
        boolean flag = elasticsearchTemplate.putMapping(CoursePub.class);
        System.out.println("flag = " + flag);
    }

    @Test
    public void insert() {
        CoursePub coursePub = new CoursePub();
        coursePub.setId(IdUtil.simpleUUID());
        coursePub.setName("vue.js");
        coursePub.setDescription("前端框架，vue.js，学习难度低，易上手，社区强大");
        courseRepository.save(coursePub);
    }

    @Test
    public void search() {

    }

}
