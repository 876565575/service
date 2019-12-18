package com.xc.model.course.ext;

import com.xc.model.course.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 *
 * @author Wu
 * @date 2018/2/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class CategoryParameter extends Category {

    /**
     * 二级分类ids
     */
    List<String> bIds;

    /**
     * 三级分类ids
     */
    List<String> cIds;

}
