package com.xc.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.xc.model.course.CourseBase;
import com.xc.model.course.ext.CourseInfo;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 22:09
 * @description :
 */
public interface CourseBaseService extends IService<CourseBase> {

    /**
     * 根据条件分页查询课程基本信息
     * @param page 页码
     * @param size 每页大小
     * @param courseBase 条件
     * @return PageInfo<CourseInfo>
     */
    PageInfo<CourseInfo> findCourseBaseList(Integer page, Integer size, CourseBase courseBase);

    /**
     * 根据课程id查询课程基本信息
     * @param courseId 课程id
     * @return CourseBase
     */
    CourseBase selectById(String courseId);

    /**
     * 新增课程基本信息，并添加课程计划根节点
     * @param courseBase 课程基本信息
     * @return 成功返回true， 失败抛出异常
     */
    Boolean add(CourseBase courseBase);
}
