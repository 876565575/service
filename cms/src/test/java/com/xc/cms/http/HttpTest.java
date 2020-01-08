package com.xc.cms.http;

import com.xc.model.cms.CmsConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author : 吴后荣
 * @date : 2019/10/29 21:33
 * @description :
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpTest {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void test(){
        ResponseEntity<CmsConfig> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/get/5a791725dd573c3574ee333f", CmsConfig.class);
        System.out.println("forEntity = " + forEntity.getBody());
    }

}
