package com.xc.cms.web;

import com.xc.common.model.entity.CmsPage;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.model.vo.QueryResult;
import com.xc.cms.service.CmsPageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(page.getTotalElements());
        queryResult.setList(page.getContent());
        return ResponseEntity.ok(queryResult);
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

    @GetMapping("/postPage/{pageId}")
    public ResponseEntity postPage(@PathVariable("pageId") String pageId) {
        cmsPageService.postPage(pageId);
        return ResponseEntity.ok().build();
    }
}
