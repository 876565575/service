package com.xc.model.cms.response;

import com.xc.common.model.response.ResponseResult;
import com.xc.common.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
public class CoursePreviewResult extends ResponseResult {
    public CoursePreviewResult(ResultCode resultCode, String url) {
        super(resultCode);
        this.url = url;
    }

    String url;
}
