package com.xc.model.course.ext;

import com.xc.model.course.TeachPlan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/2/7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class TeachPlanParameter extends TeachPlan {

    /**
     * 二级分类ids
     */
    List<String> bIds;

    /**
     * 三级分类ids
     */
    List<String> cIds;

}
