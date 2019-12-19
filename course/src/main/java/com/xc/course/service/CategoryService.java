package com.xc.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xc.model.course.Category;
import com.xc.model.course.ext.CategoryNode;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 23:12
 * @description :
 */
public interface CategoryService extends IService<Category> {
    /**
     * 查询分类信息列表
     * @return CategoryNode
     */
    CategoryNode listCategoryNode();
}
