package com.xc.cms.web;

import cn.hutool.core.io.FileUtil;
import com.xc.cms.model.entity.CmsPage;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.model.vo.PageQueryResult;
import com.xc.cms.service.CmsPageService;
import com.xc.cms.service.CmsTemplateService;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/22 15:12 <br>
 * @see com.xc.cms.web <br>
 */

@RestController
@RequestMapping("/cms/page")
public class CmsPageController {

    private final CmsPageService cmsPageService;

    public CmsPageController(CmsPageService cmsPageService) {
        this.cmsPageService = cmsPageService;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity query(@PathVariable("id") String id){
        return ResponseEntity.ok(cmsPageService.get(id));
    }

    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResponseEntity findList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, PageQueryRequest pageQueryRequest){
        Page<CmsPage> page = cmsPageService.findList(pageNum, pageSize, pageQueryRequest);
        PageQueryResult pageQueryResult = new PageQueryResult();
        pageQueryResult.setTotal(page.getTotalElements());
        pageQueryResult.setList(page.getContent());
        return ResponseEntity.ok(pageQueryResult);
    }

    @GetMapping("/getHtml/{id}")
    public ResponseEntity getHtml(@PathVariable("id") String id){
        String html = cmsPageService.getTemplateFile(id);
        return ResponseEntity.ok(html);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity remove(@PathVariable("id") String id){
        cmsPageService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody CmsPage cmsPage){
        cmsPageService.edit(cmsPage);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CmsPage cmsPage){
        return ResponseEntity.ok(cmsPageService.add(cmsPage));
    }

    @PostMapping("/generateHtml/{pageId}")
    public ResponseEntity generateHtml(@PathVariable("pageId") String pageId, String template){
        String html = cmsPageService.generateHtml(pageId, template);
        return ResponseEntity.ok(html);
    }
}
