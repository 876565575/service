package com.xc.cms.web;

import com.xc.cms.service.CmsConfigService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author : 吴后荣
 * @date : 2019/10/17 14:25
 * @description :
 */
@RestController
@RequestMapping("/cms/config")

public class CmsConfigController {

    private final CmsConfigService cmsConfigService;

    public CmsConfigController(CmsConfigService cmsConfigService) {
        this.cmsConfigService = cmsConfigService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id){
        return ResponseEntity.ok(cmsConfigService.getConfigById(id));
    }

    @GetMapping("/test")
    public ResponseEntity test(){
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("name", "小明");
        return ResponseEntity.ok(map);
    }
}
