package com.xc.model.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 02:09
 * @description : 课程分类
 */
@Data
public class Category {

    @TableId
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 标签
     */
    private String label;

    /**
     *父节点id
     */
    @TableField(value = "parentid")
    private String parentId;

    /**
     * 是否展示
     */
    @TableField(value = "isshow")
    private String isShow;

    /**
     * 排序
     */
    private int orderby;

    @TableField(value = "isleaf")
    private String isLeaf;

}
