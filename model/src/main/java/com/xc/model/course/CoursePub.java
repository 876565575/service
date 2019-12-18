package com.xc.model.course;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
public class CoursePub implements Serializable {
    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    private String pic;//图片
    private Date timestamp;//时间戳
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    private String expires;
    private String teachplan;//课程计划
    private String pubTime;//课程发布时间
}
