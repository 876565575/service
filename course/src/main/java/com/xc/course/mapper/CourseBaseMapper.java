package com.xc.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.model.course.CourseBase;
import com.xc.model.course.ext.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 22:10
 * @description :
 */
@Mapper
public interface CourseBaseMapper extends BaseMapper<CourseBase> {

    List<CourseInfo> findCourseInfoList(CourseBase courseBase);
}
