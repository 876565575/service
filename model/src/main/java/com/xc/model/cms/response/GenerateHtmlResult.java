package com.xc.model.cms.response;

import com.xc.common.model.response.ResponseResult;
import com.xc.common.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by mrt on 2018/3/31.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GenerateHtmlResult extends ResponseResult {
    String html;
    public GenerateHtmlResult(ResultCode resultCode, String html) {
        super(resultCode);
        this.html = html;
    }
}
