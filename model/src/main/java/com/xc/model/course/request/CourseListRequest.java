package com.xc.model.course.request;

import com.xc.common.model.request.RequestData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/4/13.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class CourseListRequest extends RequestData {
    /**
     * 公司Id
     */
    private String companyId;
}
