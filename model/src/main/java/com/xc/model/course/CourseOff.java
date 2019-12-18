package com.xc.model.course;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 03:04
 * @description :
 */

@NoArgsConstructor
@Data
@TableName("course_off")
public class CourseOff {

    @TableId
    private String id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 适用人群
     */
    private String users;

    /**
     * 大分类
     */
    private String mt;

    /**
     * 小分类
     */
    private String st;

    /**
     * 课程等级
     */
    private String grade;

    /**
     * 学习模式
     */
    @TableField("studymodel")
    private String studyModel;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 收费规则
     */
    private String charge;

    /**
     * 有效性
     */
    private String valid;

    /**
     * 咨询QQ
     */
    private String qq;

    /**
     * 价格
     */
    private String price;

    /**
     * 原价
     */
    @TableField("price_old")
    private String priceOld;

    /**
     * 过期时间
     */
    private String expires;

    /**
     * 图片
     */
    private String pic;

    /**
     * 教学计划
     */
    @TableField("teachplan")
    private String teachPlan;
}
