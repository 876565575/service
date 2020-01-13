package com.xc.search.controller;

import cn.hutool.core.util.StrUtil;
import com.xc.model.course.CoursePub;
import com.xc.model.course.request.CourseSearchRequest;
import com.xc.model.course.response.CourseSearchResponse;
import com.xc.search.config.HighLightResultMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 吴后荣
 * @date : 2020/1/13 10:10
 * @description :
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    HighLightResultMapper highLightResultMapper;

    @PostMapping("/course")
    public ResponseEntity courseSearch(@RequestBody CourseSearchRequest searchRequest){
        if (searchRequest.getPageNum() < 1 || searchRequest.getPageNum() == null) {
            searchRequest.setPageNum(1);
        }
        if (searchRequest.getPageSize() == null) {
            searchRequest.setPageSize(20);
        }
        PageRequest pageRequest = PageRequest.of(searchRequest.getPageNum() - 1, searchRequest.getPageSize());
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(searchRequest.getKeyword()).field("name").field("description").field("teachplan"))
                .withPageable(pageRequest);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StrUtil.isEmpty(searchRequest.getMt())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("mt", searchRequest.getMt()));
        }
        if (!StrUtil.isEmpty(searchRequest.getSt())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("st", searchRequest.getSt()));
        }
        if (!StrUtil.isEmpty(searchRequest.getGrade())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("grade", searchRequest.getGrade()));
        }
        NativeSearchQuery searchQuery = queryBuilder.withFilter(boolQueryBuilder).build();
        AggregatedPage<CoursePub> coursePubs = elasticsearchTemplate.queryForPage(searchQuery, CoursePub.class, highLightResultMapper);

        CourseSearchResponse courseSearchResponse = new CourseSearchResponse(coursePubs.getSize(), coursePubs.getContent());

        return ResponseEntity.ok(courseSearchResponse);
    }

}
