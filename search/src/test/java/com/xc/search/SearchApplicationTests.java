package com.xc.search;

import cn.hutool.json.JSONUtil;
import com.xc.model.course.CoursePub;
import com.xc.search.config.HighLightResultMapper;
import com.xc.search.repository.CourseRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchApplicationTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    HighLightResultMapper highLightResultMapper;

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
        coursePub.setId("5206700b9d4d44f78bac99b0db32ebe5");
        coursePub.setName("vue.js");
        coursePub.setDescription("前端框架，vue.js，学习难度低，易上手，社区强大");
        coursePub.setPrice(80f);
        courseRepository.save(coursePub);
    }

    @Test
    public void search() {
        //分词后 and 查询
        //List<CoursePub> list = courseRepository.findByDescription("vue真的很好用");
        //System.out.println("list = " + list);

        //分词后 or 查询
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("vue真的很好用").field("description").field("name"))
//                .withQuery(QueryBuilders.rangeQuery("price").from(1).to(200))
                .withHighlightFields(
                        new HighlightBuilder.Field("name").preTags("<span style='color:red'>").postTags("</span>"),
                        new HighlightBuilder.Field("description").preTags("<span style='color:red'>").postTags("</span>")
                )
                .build();
        Page<CoursePub> coursePubs = elasticsearchTemplate.queryForPage(nativeSearchQuery, CoursePub.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<CoursePub> list = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    CoursePub coursePub = JSONUtil.toBean(JSONUtil.parseFromMap(searchHit.getSourceAsMap()), CoursePub.class);
                    //name or memoe
                    HighlightField description = searchHit.getHighlightFields().get("description");
                    if (description != null) {
                        coursePub.setDescription(description.fragments()[0].toString());
                    }
                    HighlightField name = searchHit.getHighlightFields().get("name");
                    if (name != null) {
                        coursePub.setName(name.fragments()[0].toString());
                    }
                    list.add(coursePub);
                }
                if (list.size() > 0) {
                    return new AggregatedPageImpl(list);
                }
                return null;
            }
        });
        coursePubs.forEach(System.out::println);
    }

    @Test
    public void searchHighLight() {
        //分词后 or 查询
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("vue真的很好用").field("description").field("name"))
                .withFilter(QueryBuilders.rangeQuery("price").from(1).to(200))
                .withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0, 10))
                .withHighlightFields(
                        new HighlightBuilder.Field("name").preTags("<span style='color:red'>").postTags("</span>"),
                        new HighlightBuilder.Field("description").preTags("<span style='color:red'>").postTags("</span>")
                )
                .build();
        Page<CoursePub> coursePubs = elasticsearchTemplate.queryForPage(nativeSearchQuery, CoursePub.class, highLightResultMapper);
        coursePubs.forEach(System.out::println);
    }
}
