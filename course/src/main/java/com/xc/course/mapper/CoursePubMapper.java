package com.xc.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.course.feign.CmsPageClient;
import com.xc.model.course.CoursePub;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : 吴后荣
 * @date : 2020/1/11 11:16
 * @description :
 */
@Mapper
public interface CoursePubMapper extends BaseMapper<CoursePub> {

}
