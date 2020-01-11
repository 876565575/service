package com.xc.model.course;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author admin
 * @date 2018/2/10
 */
@Data
@ToString
@Document(indexName = "xc_course", type = "xc_course", shards = 1, replicas = 0)
public class CoursePub implements Serializable {

    @Id
    private String id;

    /**
     * 课程名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String name;

    /**
     * 适用人群
     */
    @Field(type = FieldType.Text, index = false)
    private String users;

    /**
     * 大分类
     */
    @Field(type = FieldType.Keyword)
    private String mt;

    /**
     * 小分类
     */
    @Field(type = FieldType.Keyword)
    private String st;

    /**
     * 课程等级
     */
    @Field(type = FieldType.Keyword)
    private String grade;

    /**
     * 学习模式
     */
    @Field(type = FieldType.Keyword)
    private String studymodel;

    /**
     * 教学模式
     */
    @Field(type = FieldType.Keyword)
    private String teachmode;

    /**
     * 课程介绍
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String description;

    /**
     * 图片
     */
    @Field(type = FieldType.Keyword, index = false)
    private String pic;

    /**
     * 时间戳
     */

    private Date timestamp;

    /**
     * 收费规则
     */
    @Field(type = FieldType.Keyword)
    private String charge;

    /**
     * 有效性
     */
    @Field(type = FieldType.Keyword)
    private String valid;

    /**
     * 联系QQ
     */
    @Field(type = FieldType.Keyword, index = false)
    private String qq;

    /**
     * 价格
     */
    private Float price;

    /**
     * 原价格
     */
    @TableField(value = "price_old")
    private Float priceOld;

    /**
     * 过期时间
     */
    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private Date expires;

    /**
     * 课程计划
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_max_word")
    private String teachplan;

    /**
     * 课程发布时间
     */
    @Field(type =  FieldType.Date)
    private String pubTime;

    /**
     * 课程有效期-开始时间
     */
    @Field(type = FieldType.Date)
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 课程有效期-结束时间
     */
    @Field(type = FieldType.Date)
    @TableField(value = "end_time")
    private LocalDateTime endTime;
}
