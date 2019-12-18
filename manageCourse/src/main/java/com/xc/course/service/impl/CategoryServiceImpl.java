package com.xc.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xc.course.mapper.CategoryMapper;
import com.xc.course.service.CategoryService;
import com.xc.model.course.Category;
import com.xc.model.course.ext.CategoryNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 23:14
 * @description :
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryNode listCategoryNode() {
        return categoryMapper.findCategoryList();
    }
}
