package com.xc.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.xc.common.model.response.CommonCode;
import com.xc.common.model.response.QueryResponseResult;
import com.xc.common.model.response.QueryResult;
import com.xc.common.model.response.ResponseResult;
import com.xc.course.mapper.CourseBaseMapper;
import com.xc.course.service.CourseBaseService;
import com.xc.course.service.CourseMarketService;
import com.xc.course.service.TeachPlanService;
import com.xc.model.course.CourseBase;
import com.xc.model.course.CourseMarket;
import com.xc.model.course.TeachPlan;
import com.xc.model.course.response.CourseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    TeachPlanService teachPlanService;

    @Autowired
    CourseMarketService courseMarketService;

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

    @ApiOperation("添加课程基本信息")
    @ApiImplicitParam(name = "courseBase", value = "课程基本信息", paramType = "body", required = true, dataTypeClass = CourseBase.class)
    @PostMapping("/coursebase/add")
    public ResponseEntity addCourseBase(@RequestBody CourseBase courseBase){
        courseBaseService.add(courseBase);
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

    @ApiOperation("修改课程基本信息")
    @ApiImplicitParam(name = "courseBase", value = "课程基本信息", paramType = "body", required = true, dataTypeClass = CourseBase.class)
    @PutMapping("/coursebase/update")
    public ResponseEntity updateCourseBase(@RequestBody CourseBase courseBase){
        UpdateWrapper<CourseBase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", courseBase.getId());
        courseBaseService.update(courseBase, updateWrapper);
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

    @ApiOperation("获取课程营销信息")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataTypeClass = String.class)
    @GetMapping("/coursemarket/get/{courseId}")
    public ResponseEntity getCourseMarket(@PathVariable("courseId") String courseId){
        CourseMarket courseMarket = courseMarketService.getById(courseId);
        return ResponseEntity.ok(courseMarket);
    }


    @ApiOperation("修改课程营销信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataTypeClass = String.class),
        @ApiImplicitParam(name = "courseMarket", value = "课程营销信息", paramType = "body", required = true, dataTypeClass = CourseMarket.class)})
    @PutMapping("/coursemarket/update/{courseId}")
    public ResponseEntity updateCourseMarket(@PathVariable("courseId") String courseId, @RequestBody CourseMarket courseMarket){
        CourseMarket oldCourseMarket = courseMarketService.getById(courseId);
        if (oldCourseMarket == null){
            //新增
            courseMarketService.save(courseMarket);
        }else {
            //修改
            courseMarketService.updateById(courseMarket);
        }
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

}
