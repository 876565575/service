package com.xc.course.service.impl;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xc.course.feign.CmsPageClient;
import com.xc.course.mapper.*;
import com.xc.course.repository.CourseRepository;
import com.xc.course.service.CoursePubService;
import com.xc.model.cms.CmsPage;
import com.xc.model.course.*;
import com.xc.model.course.ext.TeachPlanNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : 吴后荣
 * @date : 2020/1/11 11:14
 * @description :
 */
@Service
public class CoursePubServiceImpl extends ServiceImpl<CoursePubMapper, CoursePub> implements CoursePubService {

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
    CmsPageClient cmsPageClient;

    @Autowired
    CoursePubMapper coursePubMapper;

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CoursePicMapper coursePicMapper;

    @Autowired
    TeachPlanMapper teachPlanMapper;

    @Autowired
    CourseRepository courseRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String publish(String courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
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
        courseBaseMapper.updateById(courseBase);

        //添加课程发布信息
        CoursePub newCoursePub = null;
        CoursePub coursePub = coursePubMapper.selectById(courseId);
        if (coursePub != null) {
            newCoursePub = coursePub;
        }else {
            newCoursePub = new CoursePub();
        }
        //课程基础信息
        BeanUtils.copyProperties(courseBase, newCoursePub);
        //课程营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        BeanUtils.copyProperties(courseMarket, newCoursePub);
        //课程图片
        CoursePic coursePic = coursePicMapper.selectById(courseId);
        BeanUtils.copyProperties(coursePic, newCoursePub);
        //课程计划
        TeachPlanNode teachPlanList = teachPlanMapper.findTeachPlanList(courseId);
        newCoursePub.setTeachplan(JSON.toJSONString(teachPlanList));

        newCoursePub.setId(courseId);
        newCoursePub.setPubTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        newCoursePub.setTimestamp(new Date());

        //保存至mysql
        coursePubMapper.insert(coursePub);

        //保存至elasticsearch
        courseRepository.save(newCoursePub);

        return url;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String courseId) {
        //删除静态化页面

        //删除coursePub
        coursePubMapper.deleteById(courseId);
        courseRepository.deleteById(courseId);
        //修改课程状态
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        courseBase.setStatus("202001");
        courseBaseMapper.updateById(courseBase);
    }
}
