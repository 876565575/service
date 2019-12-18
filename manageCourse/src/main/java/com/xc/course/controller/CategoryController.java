package com.xc.course.controller;

import com.xc.course.service.CategoryService;
import com.xc.model.course.ext.CategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 23:06
 * @description :
 */
@Api(tags = "分类信息API")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation("分类信息列表")
    @GetMapping("/list")
    public ResponseEntity<CategoryNode> list() {
        return ResponseEntity.ok(categoryService.listCategoryNode());
    }
}
