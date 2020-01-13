package com.xc.course;

import com.xc.course.feign.CmsPageClient;
import com.xc.course.mapper.CourseBaseMapper;
import com.xc.course.repository.CourseRepository;
import com.xc.course.service.CoursePubService;
import com.xc.model.cms.CmsPage;
import com.xc.model.course.CourseBase;
import com.xc.model.course.CoursePub;
import com.xc.model.course.ext.CourseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author : 吴后荣
 * @date : 2019/12/19 19:46
 * @description :
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseApplicationTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CmsPageClient cmsPageClient;

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CoursePubService coursePubService;

    @Autowired
    CourseRepository courseRepository;


    @Test
    public void test() {
        String serviceId = "XC.CMS";
        for (int i = 0; i < 10; i++) {
            ResponseEntity<CmsPage> forEntity = restTemplate.getForEntity("http://" + serviceId + "/cms/page/get/5a754adf6abb500ad05688d9", CmsPage.class);
            CmsPage body = forEntity.getBody();
            System.out.println(body);
        }
    }

    @Test
    public void feignClientTest() {
        CmsPage cmsPage = cmsPageClient.findById("5a754adf6abb500ad05688d9");
        System.out.println(cmsPage);
    }

    @Test
    public void courseBaseMapperTest() {
        CourseBase courseBase = new CourseBase();
        courseBase.setUserId("1");
        List<CourseInfo> list = courseBaseMapper.findCourseInfoList(courseBase);
        System.out.println("list = " + list);
    }

    @Test
    public void copyDateFromMysqlToEs() {
        List<CoursePub> list = coursePubService.list();
        courseRepository.saveAll(list);
    }


}
