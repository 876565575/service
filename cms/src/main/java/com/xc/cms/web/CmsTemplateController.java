package com.xc.cms.web;

import com.xc.cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity getAll(){
        return ResponseEntity.ok(cmsTemplateService.findAll());
    }

}
