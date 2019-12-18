package com.xc.model.cms.response;

import com.xc.common.model.response.ResponseResult;
import com.xc.common.model.response.ResultCode;
import com.xc.model.cms.CmsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class CmsPageResult extends ResponseResult {
    CmsPage cmsPage;
    public CmsPageResult(ResultCode resultCode, CmsPage cmsPage) {
        super(resultCode);
        this.cmsPage = cmsPage;
    }
}
