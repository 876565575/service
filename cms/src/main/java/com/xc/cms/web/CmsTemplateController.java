package com.xc.cms.web;

import com.xc.cms.model.entity.CmsTemplate;
import com.xc.cms.model.vo.PageQueryRequest;
import com.xc.cms.model.vo.PageQueryResult;
import com.xc.cms.model.vo.TemplateQueryRequest;
import com.xc.cms.service.CmsTemplateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CmsTemplateService cmsTemplateService;

    @GetMapping("/list")
    public ResponseEntity all(){
        return ResponseEntity.ok(cmsTemplateService.findAll());
    }

    @GetMapping("/list/{page}/{size}")
    public ResponseEntity list(@PathVariable("page") int page, @PathVariable("size") int size, TemplateQueryRequest templateQueryRequest) {
        Page<CmsTemplate> pages =  cmsTemplateService.list(page, size, templateQueryRequest);
        PageQueryResult pageQueryResult = new PageQueryResult();
        pageQueryResult.setTotal(pages.getTotalElements());
        pageQueryResult.setList(pages.getContent());
        return ResponseEntity.ok(pageQueryResult);
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

@Data
class CmsTemplateRequest{
    private CmsTemplate cmsTemplate;
    private String newTemplateFileId;
}