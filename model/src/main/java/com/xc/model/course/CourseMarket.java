package com.xc.model.course;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 02:55
 * @description :
 */
@NoArgsConstructor
@Data
@TableName("course_market")
public class CourseMarket {

    /**
     * 课程id
     */
    @TableId
    private String id;

    /**
     * 收费规则，对应数据字典
     */
    private String charge;

    /**
     * 有效性，对应数据字典
     */
    private String valid;

    /**
     * 过期时间
     */
    private Timestamp expires;

    /**
     * 咨询qq
     */
    private String qq;

    /**
     * 价格
     */
    private double price;

    /**
     * 原价
     */
    @TableField(value = "price_old")
    private float priceOld;

    /**
     * 课程有效期-开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 课程有效期-结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;
}
