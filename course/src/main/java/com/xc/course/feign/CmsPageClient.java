package com.xc.course.feign;

import com.xc.model.cms.CmsPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : 吴后荣
 * @date : 2019/12/19 19:59
 * @description :
 */
@FeignClient(value = "xc.cms")
public interface CmsPageClient {

    @GetMapping("/cms/page/get/{id}")
    CmsPage findById(@PathVariable("id") String id);

    @PostMapping("/cms/page/save")
    CmsPage save(@RequestBody CmsPage cmsPage);

    @PostMapping("/cms/page/publish")
    String publishPage (@RequestBody CmsPage cmsPage);
}
