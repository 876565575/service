package com.xc.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xc.model.course.TeachPlan;
import com.xc.model.course.ext.TeachPlanNode;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 15:53
 * @description :
 */

public interface TeachPlanService extends IService<TeachPlan> {

    /**
     * 根据课程id查询课程计划
     * @param courseId 课程id
     * @return TeachPlanNode
     */
    TeachPlanNode findTeachPlanList(String courseId);

    /**
     * 添加课程计划，失败抛出异常
     * @param teachPlan 课程计划
     */
    void add(TeachPlan teachPlan);

    /**
     * 修改课程计划
     * @param teachPlan 课程计划
     */
    void update(TeachPlan teachPlan);
}
