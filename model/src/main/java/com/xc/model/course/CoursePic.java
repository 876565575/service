package com.xc.model.course;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 03:07
 * @description :
 */
@NoArgsConstructor
@Data
@TableName("course_pic")
public class CoursePic {


    @TableId
    private String courseId;

    /**
     * 图片路径
     */
    private String pic;
}
