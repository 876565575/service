package com.xc.model.course.ext;

import com.xc.model.course.TeachPlan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Wu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class TeachPlanNode extends TeachPlan {

    List<TeachPlanNode> children;

}
