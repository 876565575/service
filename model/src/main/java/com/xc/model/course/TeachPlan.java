package com.xc.model.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;


/**
 * @author Wu
 */
@Data
@ToString
@TableName("teachplan")
public class TeachPlan implements Serializable {

    @TableId(type =IdType.UUID)
    private String id;
    private String pname;
    @TableField("parentid")
    private String parentId;
    private String grade;
    private String ptype;
    private String description;
    @TableField("courseid")
    private String courseId;
    private String status;
    private Integer orderby;
    private Double timelength;
    private String trylearn;

}
