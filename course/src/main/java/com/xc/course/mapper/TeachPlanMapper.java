package com.xc.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.model.course.TeachPlan;
import com.xc.model.course.ext.TeachPlanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 14:06
 * @description :
 */
@Mapper
public interface TeachPlanMapper extends BaseMapper<TeachPlan> {

    TeachPlanNode findTeachPlanList(@Param("courseId") String courseId);
}
