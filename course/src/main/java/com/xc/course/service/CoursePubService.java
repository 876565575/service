package com.xc.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xc.model.course.CourseBase;
import com.xc.model.course.CoursePub;

/**
 * @author : 吴后荣
 * @date : 2020/1/11 11:14
 * @description :
 */
public interface CoursePubService extends IService<CoursePub> {
    String publish(String courseId);

    void delete(String courseId);
}
