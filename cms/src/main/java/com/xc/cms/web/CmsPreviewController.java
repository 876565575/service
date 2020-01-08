package com.xc.cms.web;

import com.xc.cms.service.CmsPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : 吴后荣
 * @date : 2019/11/23 17:47
 * @description :
 */
@Api("页面预览")
@Controller
@RequestMapping("/cms/preview")
public class CmsPreviewController {

    @Autowired
    CmsPageService cmsPageService;

    @GetMapping("/{id}")
    public void preview(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        String html = cmsPageService.generateHtml(id);
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(html.getBytes("UTF-8"));
    }
}
