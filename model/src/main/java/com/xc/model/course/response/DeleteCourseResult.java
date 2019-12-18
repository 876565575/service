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
@EqualsAndHashCode(callSuper = false)
@ToString
public class DeleteCourseResult extends ResponseResult {
    public DeleteCourseResult(ResultCode resultCode, String courseId) {
        super(resultCode);
        this.courseid = courseid;
    }
    private String courseid;

}
