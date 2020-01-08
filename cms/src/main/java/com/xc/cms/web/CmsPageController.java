package com.xc.cms.web;

import com.xc.cms.service.CmsSiteService;
import com.xc.model.cms.CmsPage;
import com.xc.model.cms.CmsSite;
import com.xc.model.cms.request.PageQueryRequest;
import com.xc.model.cms.response.QueryResult;
import com.xc.cms.service.CmsPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "页面信息接口")
@RestController
@RequestMapping("/cms/page")
public class CmsPageController {

    private final CmsSiteService cmsSiteService;

    private final CmsPageService cmsPageService;

    public CmsPageController(CmsPageService cmsPageService, CmsSiteService cmsSiteService) {
        this.cmsPageService = cmsPageService;
        this.cmsSiteService = cmsSiteService;
    }

    @ApiOperation("根据id获取页面信息")
    @GetMapping("/get/{id}")
    public ResponseEntity query(@PathVariable("id") String id){
        return ResponseEntity.ok(cmsPageService.get(id));
    }

    @ApiOperation("分页查询页面信息")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResponseEntity findList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, PageQueryRequest pageQueryRequest){
        Page<CmsPage> page = cmsPageService.findList(pageNum, pageSize, pageQueryRequest);
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(page.getTotalElements());
        queryResult.setList(page.getContent());
        return ResponseEntity.ok(queryResult);
    }

    @ApiOperation("获取页面模板文件")
    @GetMapping("/getHtml/{id}")
    public ResponseEntity getHtml(@PathVariable("id") String id){
        String html = cmsPageService.getTemplateFile(id);
        return ResponseEntity.ok(html);
    }

    @ApiOperation("删除页面信息")
    @DeleteMapping("/del/{id}")
    public ResponseEntity remove(@PathVariable("id") String id){
        cmsPageService.remove(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("修改页面信息")
    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody CmsPage cmsPage){
        cmsPageService.edit(cmsPage);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CmsPage cmsPage){
        return ResponseEntity.ok(cmsPageService.add(cmsPage));
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CmsPage cmsPage) {
        cmsPageService.save(cmsPage);
        return ResponseEntity.ok(cmsPage);
    }

    @ApiOperation("生成静态页面")
    @PostMapping("/generateHtml/{pageId}")
    public ResponseEntity generateHtml(@PathVariable("pageId") String pageId, String template){
        String html = cmsPageService.generateHtml(pageId, template);
        return ResponseEntity.ok(html);
    }

    @ApiOperation("生成静态页面")
    @GetMapping("/postPage/{pageId}")
    public ResponseEntity postPage(@PathVariable("pageId") String pageId) {
        cmsPageService.postPage(pageId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("生成并发布静态页面")
    @PostMapping("/publish")
    public ResponseEntity publishPage(@RequestBody CmsPage cmsPage) {
        cmsPageService.save(cmsPage);
        cmsPageService.generateHtml(cmsPage.getPageId());
        cmsPageService.postPage(cmsPage.getPageId());

        CmsSite cmsSite = cmsSiteService.get(cmsPage.getSiteId());

        String url = cmsSite.getSiteDomain() + ":" + cmsSite.getSitePort() + cmsPage.getPageWebPath() + cmsPage.getPageName();

        return ResponseEntity.ok(url);
    }
}
