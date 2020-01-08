package com.xc.cms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author : 吴后荣
 * @date : 2019/10/31 01:30
 * @description :
 */
@Controller
@RequestMapping("/cms/freemarker")
public class FreemarkerController {

    private final RestTemplate restTemplate;

    public FreemarkerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/banner")
    public String banner(Map<String, Object> map){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/get/5a791725dd573c3574ee333f", Map.class);
        map.putAll(forEntity.getBody());
        return "index_banner";
    }

    @GetMapping("/course")
    public String course(Map<String, Object> map){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31003/course/allInfo/297e7c7c62b888f00162b8a7dec20000", Map.class);
        map.putAll(forEntity.getBody());
        return "course";
    }
}
