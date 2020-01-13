package com.xc.model.course.response;

import com.xc.model.course.CoursePub;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2020/1/13 11:48
 * @description :
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseSearchResponse {

    private long total;

    private List<CoursePub> list;

}
