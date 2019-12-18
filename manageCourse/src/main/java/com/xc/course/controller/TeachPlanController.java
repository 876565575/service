package com.xc.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.common.exception.SysException;
import com.xc.common.model.response.CommonCode;
import com.xc.common.model.response.ResponseResult;
import com.xc.course.service.TeachPlanService;
import com.xc.model.course.TeachPlan;
import com.xc.model.course.ext.TeachPlanNode;
import com.xc.model.course.response.CourseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : 吴后荣
 * @date : 2019/12/8 15:51
 * @description :
 */
@Api(tags = "课程计划API接口")
@RestController
@RequestMapping("/course/teachplan")
public class TeachPlanController {

    @Autowired
    TeachPlanService teachPlanService;




    @ApiOperation("根据id查询课程计划")
    @ApiImplicitParam(name = "id", value = "课程计划id", paramType = "path", required = true, dataType = "String")
    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id){
        TeachPlan teachPlan = teachPlanService.getById(id);
        return ResponseEntity.ok(teachPlan);
    }

    @ApiOperation("课程计划列表查询")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataType = "String")
    @GetMapping("/list/{courseId}")
    public ResponseEntity<TeachPlanNode> list(@PathVariable("courseId") String courseId) {
        return ResponseEntity.ok(teachPlanService.findTeachPlanList(courseId));
    }

    @ApiOperation("修改课程计划")
    @ApiImplicitParam(name = "teachPlan", value = "课程计划实体类", paramType = "body", required = true, dataTypeClass = TeachPlan.class)
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody TeachPlan teachPlan) {
        teachPlanService.update(teachPlan);
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

    @ApiOperation("添加课程计划")
    @ApiImplicitParam(name = "teachPlan", value = "课程计划实体类", paramType = "body", required = true, dataTypeClass = TeachPlan.class)
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody TeachPlan teachPlan) {
        if (teachPlan.getParentId() == null) {
            QueryWrapper<TeachPlan> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("courseid", teachPlan.getCourseId()).eq("grade", 1);
            TeachPlan parentTeachPlan = teachPlanService.getOne(queryWrapper);
            teachPlan.setParentId(parentTeachPlan.getId());
            teachPlan.setGrade("2");
        }else {
            teachPlan.setGrade("3");
        }
        teachPlanService.add(teachPlan);
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

    @ApiOperation("删除课程计划")
    @ApiImplicitParam(name = "id", value = "课程计划id", paramType = "body", required = true, dataTypeClass = String.class)
    @DeleteMapping("/del/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        //同时删除子计划下的子计划
        QueryWrapper<TeachPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).or().eq("parentid", id);
        teachPlanService.remove(queryWrapper);
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

}
