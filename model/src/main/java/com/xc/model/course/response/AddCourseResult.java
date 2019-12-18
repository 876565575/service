package com.xc.model.course.response;

import com.xc.common.model.response.ResponseResult;
import com.xc.common.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/20.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class AddCourseResult extends ResponseResult {
    public AddCourseResult(ResultCode resultCode, String courseid) {
        super(resultCode);
        this.courseid = courseid;
    }
    private String courseid;

}
