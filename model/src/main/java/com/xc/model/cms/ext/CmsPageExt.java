package com.xc.model.cms.ext;

import com.xc.model.cms.CmsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class CmsPageExt extends CmsPage {
    private String htmlValue;

}
