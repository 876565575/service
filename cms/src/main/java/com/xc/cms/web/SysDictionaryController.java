package com.xc.cms.web;

import com.xc.cms.dao.SysDictionaryRepository;
import com.xc.model.system.SysDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/12/18 00:36
 * @description :
 */

@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController {

    @Autowired
    SysDictionaryRepository sysDictionaryRepository;

    @GetMapping("/get/{dType}")
    public ResponseEntity getByDType(@PathVariable("dType") String dType) {
        SysDictionary sysDictionary = sysDictionaryRepository.findByDTypeEquals(dType);
        return ResponseEntity.ok(sysDictionary);
    }
}
