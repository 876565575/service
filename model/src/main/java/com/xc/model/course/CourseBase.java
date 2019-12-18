package com.xc.model.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 02:51
 * @description : 课程基本信息
 */
@NoArgsConstructor
@Data
@TableName("course_base")
public class CourseBase {

    /**
     * 课程id
     */
    @TableId(type = IdType.UUID)
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
     * 课程大分类
     */
    private String mt;

    /**
     * 课程等级
     */
    private String grade;

    /**
     * 学习模式
     */
    @TableField(value = "studymodel")
    private String studyModel;

    /**
     * 授课模式
     */
    @TableField(value = "teachmode")
    private String teachMode;

    /**
     *课程介绍
     */
    private String description;

    /**
     * 课程小分类
     */
    private String st;

    /**
     * 课程状态
     */
    private String status;

    /**
     * 教育机构
     */
    @TableField(value = "company_id")
    private String companyId;

    /**
     * 创建用户
     */
    @TableField(value = "user_id")
    private String userId;
}
