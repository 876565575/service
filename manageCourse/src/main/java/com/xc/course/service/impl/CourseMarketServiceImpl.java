package com.xc.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xc.course.mapper.CourseMarketMapper;
import com.xc.course.service.CourseMarketService;
import com.xc.model.course.CourseMarket;
import org.springframework.stereotype.Service;

/**
 * @author : 吴后荣
 * @date : 2019/12/18 16:48
 * @description :
 */
@Service
public class CourseMarketServiceImpl extends ServiceImpl<CourseMarketMapper, CourseMarket> implements CourseMarketService {
}
