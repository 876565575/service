package com.xc.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.exception.ExceptionEnum;
import com.xc.common.exception.SysException;
import com.xc.course.mapper.CourseBaseMapper;
import com.xc.course.mapper.TeachPlanMapper;
import com.xc.course.service.CourseBaseService;
import com.xc.model.course.CourseBase;
import com.xc.model.course.TeachPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    TeachPlanMapper teachPlanMapper;

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


    @Override
    @Transactional(rollbackFor = SysException.class)
    public Boolean add(CourseBase courseBase) {
        //添加课程基本信息
        courseBaseMapper.insert(courseBase);
        //新增此课程的课程计划根节点
        TeachPlan teachPlan = new TeachPlan();
        teachPlan.setGrade("1");
        teachPlan.setParentId("0");
        teachPlan.setCourseId(courseBase.getId());
        teachPlan.setOrderby(1);
        teachPlan.setStatus("0");
        teachPlan.setPname(courseBase.getName());
        teachPlanMapper.insert(teachPlan);
        return true;
    }
}
