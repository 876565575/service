package com.xc.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.course.mapper.CourseBaseMapper;
import com.xc.course.service.CourseBaseService;
import com.xc.model.course.CourseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 22:16
 * @description :
 */

@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Override
    public PageInfo<CourseBase> findCourseBaseList(Integer page, Integer size, CourseBase courseBase) {
        PageHelper.startPage(page, size);
        QueryWrapper<CourseBase> queryWrapper = new QueryWrapper<>(courseBase);
        List<CourseBase> courseBases = courseBaseMapper.selectList(queryWrapper);
        return new PageInfo<>(courseBases);
    }

    @Override
    public CourseBase selectById(String courseId) {
        return courseBaseMapper.selectById(courseId);
    }
}
