package com.xc.cms.web;

import com.xc.common.model.entity.CmsTemplate;
import com.xc.cms.model.vo.CmsTemplateRequest;
import com.xc.cms.model.vo.QueryResult;
import com.xc.cms.model.vo.TemplateQueryRequest;
import com.xc.cms.service.CmsTemplateService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author : 吴后荣
 * @date : 2019/10/22 00:21
 * @description :
 */

@RestController
@RequestMapping("/cms/temp")
public class CmsTemplateController {

    private final CmsTemplateService cmsTemplateService;

    public CmsTemplateController(CmsTemplateService cmsTemplateService) {
        this.cmsTemplateService = cmsTemplateService;
    }

    @GetMapping("/get/tempId")
    public ResponseEntity get(@PathVariable("tempId") String tmepId){
        return ResponseEntity.ok(cmsTemplateService.get(tmepId));
    }

    @GetMapping("/list")
    public ResponseEntity all(){
        return ResponseEntity.ok(cmsTemplateService.findAll());
    }

    @GetMapping("/list/{page}/{size}")
    public ResponseEntity list(@PathVariable("page") int page, @PathVariable("size") int size, TemplateQueryRequest templateQueryRequest) {
        Page<CmsTemplate> pages =  cmsTemplateService.list(page, size, templateQueryRequest);
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(pages.getTotalElements());
        queryResult.setList(pages.getContent());
        return ResponseEntity.ok(queryResult);
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestBody MultipartFile file, String fileName) throws Exception{
        InputStream inputStream = file.getInputStream();
        String fileId = cmsTemplateService.saveFile(inputStream, fileName);
        return ResponseEntity.ok(fileId);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CmsTemplate cmsTemplate){
        return ResponseEntity.ok(cmsTemplateService.save(cmsTemplate));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CmsTemplateRequest cmsTemplateRequest){
        if (StringUtils.isEmpty(cmsTemplateRequest.getCmsTemplate().getId()) || StringUtils.isEmpty(cmsTemplateRequest.getNewTemplateFileId())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cmsTemplateService.update(cmsTemplateRequest.getCmsTemplate(), cmsTemplateRequest.getNewTemplateFileId()));
    }

    @DeleteMapping("/del/{cmsTemplateId}")
    public ResponseEntity delete(@PathVariable("cmsTemplateId") String cmsTemplateId){
        cmsTemplateService.delete(cmsTemplateId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delFile/{fileId}")
    public ResponseEntity deleteFile(@PathVariable("fileId") String fileId){
        cmsTemplateService.deleteFile(fileId);
        return ResponseEntity.ok().build();
    }

}
