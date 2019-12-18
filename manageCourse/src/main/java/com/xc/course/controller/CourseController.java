package com.xc.course.controller;

import com.github.pagehelper.PageInfo;
import com.xc.common.model.response.CommonCode;
import com.xc.common.model.response.QueryResponseResult;
import com.xc.common.model.response.QueryResult;
import com.xc.course.mapper.CourseBaseMapper;
import com.xc.course.service.CourseBaseService;
import com.xc.model.course.CourseBase;
import com.xc.model.course.response.CourseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 22:06
 * @description :
 */

@Api(tags = "课程基本信息API")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseBaseService courseBaseService;

    @ApiOperation("分页查询课程基本信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码", paramType = "path", required = true, dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "size", value = "每页大小", paramType = "path", required = true, dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "courseBase", value = "课程基本信息实体类", paramType = "query", required = false, dataTypeClass = CourseBase.class)})
    @GetMapping("/coursebase/list/{page}/{size}")
    public ResponseEntity<QueryResponseResult> listCourseBase(@PathVariable("page") Integer page, @PathVariable("size") Integer size, CourseBase courseBase) {
        PageInfo<CourseBase> pageInfo = courseBaseService.findCourseBaseList(page, size, courseBase);
        QueryResult<CourseBase> queryResult = new QueryResult<>();
        queryResult.setList(pageInfo.getList());
        queryResult.setTotal(pageInfo.getTotal());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return ResponseEntity.ok(queryResponseResult);
    }

    @ApiOperation("根据课程id查询课程基本信息")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataTypeClass = String.class)
    @GetMapping("/coursebase/get/{courseId}")
    public ResponseEntity<CourseBase> queryCourseBase(@PathVariable("courseId") String courseId) {
        return ResponseEntity.ok(courseBaseService.selectById(courseId));
    }
}
