package com.xc.model.course.ext;

import com.xc.model.course.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * @author Wu
 * @date 2018/2/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryNode extends Category {

    List<CategoryNode> children;
}
