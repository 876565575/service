package com.xc.cms.dao;

import com.xc.model.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/22 15:15 <br>
 * @see com.xc.cms.dao <br>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void test() {
        List<CmsPage> cmsPages = cmsPageRepository.findAll();
        cmsPages.forEach(System.out::println);
    }

    @Test
    public void page() {
        Pageable Pageable = PageRequest.of(1, 10);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(Pageable);
        cmsPages.forEach(System.out::println);
    }

    @Test
    public void modify() {
        Optional<CmsPage> optional = cmsPageRepository.findById("5a9620b9b00ffc5a9cdebaed");
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            cmsPage.setPageType("250");
            cmsPageRepository.save(cmsPage);
            System.out.println("ok");
        }
    }

    @Test
    public void query() {
        List<CmsPage> cmsPages = cmsPageRepository.findByPageAliaseLike("首页");
        cmsPages.forEach(System.out::println);
    }

    @Test
    public void findAll(){
        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //设置条件值
        CmsPage cmsPage = new CmsPage();

        //定义条件对象
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);



        Pageable pageable = PageRequest.of(0, 10);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example, pageable);
        cmsPages.forEach(System.out::println);
    }
}
