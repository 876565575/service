package com.xc.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.xc.common.model.response.CommonCode;
import com.xc.common.model.response.QueryResponseResult;
import com.xc.common.model.response.QueryResult;
import com.xc.common.model.response.ResponseResult;
import com.xc.course.feign.CmsPageClient;
import com.xc.course.mapper.CourseBaseMapper;
import com.xc.course.service.CourseBaseService;
import com.xc.course.service.CourseMarketService;
import com.xc.course.service.CoursePicService;
import com.xc.course.service.TeachPlanService;
import com.xc.model.cms.CmsPage;
import com.xc.model.course.CourseBase;
import com.xc.model.course.CourseMarket;
import com.xc.model.course.CoursePic;
import com.xc.model.course.TeachPlan;
import com.xc.model.course.ext.CourseInfo;
import com.xc.model.course.ext.TeachPlanNode;
import com.xc.model.course.response.CourseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 吴后荣
 * @date : 2019/12/13 22:06
 * @description :
 */

@Api(tags = "课程基本信息API")
@RestController
@RequestMapping("/course")
public class CourseController {


    @Value("${course-publish.templateId}")
    private String templateId;

    @Value("${course-publish.siteId}")
    private String siteId;

    @Value("${course-publish.previewUrl}")
    private String previewUrl;

    @Value("${course-publish.pageWebPath}")
    private String pageWebPath;

    @Value("${course-publish.pagePhysicalPath}")
    private String pagePhysicalPath;

    @Value("${course-publish.dataUrl}")
    private String dataUrl;

    @Autowired
    CourseBaseService courseBaseService;

    @Autowired
    TeachPlanService teachPlanService;

    @Autowired
    CourseMarketService courseMarketService;

    @Autowired
    CoursePicService coursePicService;

    @Autowired
    CmsPageClient cmsPageClient;

    @ApiOperation("分页查询课程基本信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码", paramType = "path", required = true, dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "size", value = "每页大小", paramType = "path", required = true, dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "courseBase", value = "课程基本信息实体类", paramType = "query", required = false, dataTypeClass = CourseBase.class)})
    @GetMapping("/coursebase/list/{page}/{size}")
    public ResponseEntity<QueryResponseResult> listCourseBase(@PathVariable("page") Integer page, @PathVariable("size") Integer size, CourseBase courseBase) {
        PageInfo<CourseInfo> pageInfo = courseBaseService.findCourseBaseList(page, size, courseBase);
        QueryResult<CourseInfo> queryResult = new QueryResult<>();
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
        courseBase.setStatus("202001");
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
    public ResponseEntity updateCourseMarket(@PathVariable("courseId") String courseId, @RequestBody CourseMarket courseMarket) {
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

    @ApiOperation("获取课程图片")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataTypeClass = String.class)
    @GetMapping("/coursepic/get/{courseId}")
    public ResponseEntity getCoursePic(@PathVariable("courseId") String courseId) {
        CoursePic coursePic = coursePicService.getById(courseId);
        return ResponseEntity.ok(coursePic);
    }

    @ApiOperation("添加课程图片")
    @ApiImplicitParam(name = "coursePic", value = "课程图片信息", paramType = "query", required = true, dataTypeClass = CoursePic.class)
    @PostMapping("/coursepic/add")
    public ResponseEntity addCoursePic(CoursePic coursePic) {
        coursePicService.save(coursePic);
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

    @ApiOperation("删除课程图片")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "query", required = true, dataTypeClass = String.class)
    @DeleteMapping("/coursepic/delete")
    public ResponseEntity deleteCoursePic(String courseId) {
        coursePicService.removeById(courseId);
        return ResponseEntity.ok(new ResponseResult(CommonCode.SUCCESS));
    }

    @ApiOperation("获取课程的基本信息、图片、课程计划、营销信息")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataTypeClass = String.class)
    @GetMapping("/allInfo/{courseId}")
    public ResponseEntity getAllInfo(@PathVariable("courseId") String courseId) {
        CourseMarket courseMarket = courseMarketService.getById(courseId);
        CoursePic coursePic = coursePicService.getById(courseId);
        CourseBase courseBase = courseBaseService.selectById(courseId);
        TeachPlanNode teachplanNode = teachPlanService.findTeachPlanList(courseId);
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("courseMarket", courseMarket);
        map.put("coursePic", coursePic);
        map.put("courseBase", courseBase);
        map.put("teachplanNode", teachplanNode);
        return ResponseEntity.ok(map);
    }

    @ApiOperation("页面预览，返回预览url")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataTypeClass = String.class)
    @PostMapping("/preview/{courseId}")
    public ResponseEntity preview(@PathVariable("courseId") String courseId) {
        CourseBase courseBase = courseBaseService.getById(courseId);

        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId(siteId);
        cmsPage.setTemplateId(templateId);
        cmsPage.setPageName(courseId + ".html");
        cmsPage.setPageWebPath(pageWebPath);
        cmsPage.setPagePhysicalPath(pagePhysicalPath);
        cmsPage.setDataUrl(dataUrl + courseId);
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPageType("2");
        cmsPage.setPageCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

        String pageId = cmsPageClient.save(cmsPage).getPageId();
        return ResponseEntity.ok(previewUrl + pageId);
    }

    @ApiOperation("页面发布，返回访问地址")
    @ApiImplicitParam(name = "courseId", value = "课程id", paramType = "path", required = true, dataTypeClass = String.class)
    @PostMapping("/publish/{courseId}")
    public ResponseEntity publish(@PathVariable("courseId") String courseId) {

        CourseBase courseBase = courseBaseService.getById(courseId);
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId(siteId);
        cmsPage.setTemplateId(templateId);
        cmsPage.setPageName(courseId + ".html");
        cmsPage.setPageWebPath(pageWebPath);
        cmsPage.setPagePhysicalPath(pagePhysicalPath);
        cmsPage.setDataUrl(dataUrl + courseId);
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPageType("2");
        cmsPage.setPageCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        String url = cmsPageClient.publishPage(cmsPage);

        //修改课程状态为已发布
        courseBase.setStatus("202002");
        courseBaseService.updateById(courseBase);

        return ResponseEntity.ok(url);
    }
}
