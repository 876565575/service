package com.xc.model.course.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : 吴后荣
 * @date : 2020/1/13 10:13
 * @description :
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchRequest {

    private String keyword;

    private String mt;

    private String st;

    private String grade;

    private Integer pageNum;

    private Integer pageSize;
}
