package com.xc.cmsclient.mq;

import cn.hutool.json.JSONUtil;
import com.xc.cmsclient.service.PageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : 吴后荣
 * @date : 2019/12/4 23:46
 * @description :
 */
@Component
public class ConsumerPostPage {

    @Autowired
    PageService pageService;

    @RabbitListener(queues = {"${xc.mq.queue}"})
    public void postPage(Map<String, String> map) {
        String pageId = map.get("pageId");
        pageService.publishPage(pageId);
    }
}
