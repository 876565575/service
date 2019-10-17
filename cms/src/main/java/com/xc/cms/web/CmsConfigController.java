package com.xc.cms.web;

import com.xc.cms.model.entity.CmsConfig;
import com.xc.cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 吴后荣
 * @date : 2019/10/17 14:25
 * @description :
 */
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController {

    @Autowired
    CmsConfigService cmsConfigService;

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id){
        return ResponseEntity.ok(cmsConfigService.getConfigById(id));
    }
}
