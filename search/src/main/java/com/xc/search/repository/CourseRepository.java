package com.xc.search.repository;

import com.xc.model.course.CoursePub;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author : 吴后荣
 * @date : 2020/1/10 01:24
 * @description :
 */
@Mapper
public interface CourseRepository extends ElasticsearchRepository<CoursePub, String> {
}
