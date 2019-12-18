package com.xc.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.model.course.Category;
import com.xc.model.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 02:32
 * @description :
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    CategoryNode findCategoryList();
}
