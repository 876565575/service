package com.xc.cms.freemaker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.HashMap;

/**
 * @author : 吴后荣
 * @date : 2019/10/28 22:58
 * @description :
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerTest {


    @Test
    public void testGenerateHtml() throws Exception {
        //创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板路径
        String path = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File(path + "/templates/"));;
        //设置字符集
        configuration.setDefaultEncoding("utf-8");
        //加载模板
        Template template = configuration.getTemplate("scratch.ftl");
        //加载模型
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "小明");
        //静态化
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //静态化内容
        System.out.println(html);
    }

    @Test
    public void testStringGenerateHtml() throws Exception {
        //创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板路径
        String templateStr =
                "<html lang=\"cn\">\n" +
                "    <head>\n" +
                "        <title></title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        ${name}\n" +
                "        ${name}\n" +
                "    </body>\n" +
                "</html>";
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate("template", templateStr);
        //设置模板加载器
        configuration.setTemplateLoader(templateLoader);
        //设置字符集
        configuration.setDefaultEncoding("utf-8");
        //加载模板
        Template template = configuration.getTemplate("template");
        //加载模型
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "小明");
        //静态化
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //静态化内容
        System.out.println(html);
    }
}
