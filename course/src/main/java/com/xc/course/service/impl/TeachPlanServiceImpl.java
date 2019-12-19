package com.xc.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xc.course.mapper.TeachPlanMapper;
import com.xc.model.course.TeachPlan;
import com.xc.model.course.ext.TeachPlanNode;
import com.xc.course.service.TeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 15:55
 * @description :
 */
@Service
public class TeachPlanServiceImpl extends ServiceImpl<TeachPlanMapper, TeachPlan> implements TeachPlanService {

    @Autowired
    TeachPlanMapper teachPlanMapper;

    @Override
    public TeachPlanNode findTeachPlanList(String courseId) {
        return teachPlanMapper.findTeachPlanList(courseId);
    }

    @Override
    public void add(TeachPlan teachPlan) {
        teachPlanMapper.insert(teachPlan);
    }

    @Override
    public void update(TeachPlan teachPlan) {
        teachPlanMapper.updateById(teachPlan);
    }
}
